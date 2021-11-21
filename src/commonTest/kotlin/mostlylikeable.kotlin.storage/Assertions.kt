package mostlylikeable.kotlin.storage

import kotlin.reflect.KClass
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AssertEquals {
    var value: Any? = null

    infix fun that(value: Any?): AssertEquals = this.apply { this.value = value }
    infix fun isEqualTo(expected: Any?): AssertEquals = this.apply {
        assertEquals(expected, value)
    }
}

class AssertBool {
    var value: Boolean? = null

    infix fun that(value: Boolean): AssertBool = this.apply { this.value = value }
    infix fun shouldBe(expected: Boolean): AssertBool = this.apply {
        if (expected) {
            assertTrue(value!!)
        } else {
            assertFalse(value!!)
        }
    }
}

class AssertThrows {
    var block: (AssertThrows.() -> Unit)? = null
    var actualMessage: String? = null

    infix fun that(block: AssertThrows.() -> Unit): AssertThrows = this.apply { this.block = block }

    infix fun <T: Throwable> threw(expected: KClass<T>): AssertThrows = this.apply {
        var thrown: Throwable? = null
        actualMessage = null
        try {
            block!!()
        } catch (e: Throwable) {
            thrown = e
            actualMessage = e.message
        }
        assertNotNull(thrown)
        assertEquals(expected.qualifiedName, thrown::class.qualifiedName)
    }

    infix fun withMessage(expected: String): AssertThrows = this.apply {
        assertEquals(expected, actualMessage)
    }
}
