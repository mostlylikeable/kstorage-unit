package mostlylikeable.kotlin.storage

import kotlin.test.Test
import kotlin.test.assertEquals
import mostlylikeable.kotlin.storage.DecimalUnit.*

class DecimalUnitTest {

    @Test
    fun `test converting zero`() {
        val test = DecimalConvertTest()
        DecimalUnit.values().forEach { source ->
            DecimalUnit.values().forEach { target ->
                test converting 0.0 from source to target shouldBe 0.0
            }
        }
    }

    @Test
    fun `test converting BYTE up and down`() {
        val test = DecimalConvertTest()
        // FIXME: commented out cases suffer from precision loss

        test converting 1.0 from BYTE to KILOBYTE  shouldBe 0.00_1
        test converting 1.0 from BYTE to MEGABYTE  shouldBe 0.00_000_1
        test converting 1.0 from BYTE to GIGABYTE  shouldBe 0.00_000_000_1
        test converting 1.0 from BYTE to TERABYTE  shouldBe 0.00_000_000_000_1
        test converting 1.0 from BYTE to PETABYTE  shouldBe 0.00_000_000_000_000_1
        test converting 1.0 from BYTE to EXABYTE   shouldBe 0.00_000_000_000_000_000_1
        test converting 1.0 from BYTE to ZETTABYTE shouldBe 0.00_000_000_000_000_000_000_1
//        test converting 1.0 from BYTE to YOTTABYTE shouldBe 0.00_000_000_000_000_000_000_000_1

        test converting 1.0 from YOTTABYTE to BYTE shouldBe 1_000_000_000_000_000_000_000_000.0
        test converting 1.0 from ZETTABYTE to BYTE shouldBe     1_000_000_000_000_000_000_000.0
        test converting 1.0 from EXABYTE   to BYTE shouldBe         1_000_000_000_000_000_000.0
        test converting 1.0 from PETABYTE  to BYTE shouldBe             1_000_000_000_000_000.0
        test converting 1.0 from TERABYTE  to BYTE shouldBe                 1_000_000_000_000.0
        test converting 1.0 from GIGABYTE  to BYTE shouldBe                     1_000_000_000.0
        test converting 1.0 from MEGABYTE  to BYTE shouldBe                         1_000_000.0
        test converting 1.0 from KILOBYTE  to BYTE shouldBe                             1_000.0

//        test converting 0.00_000_000_000_000_000_000_000_1 from YOTTABYTE to BYTE shouldBe 1.0
//        test converting     0.00_000_000_000_000_000_000_1 from ZETTABYTE to BYTE shouldBe 1.0
        test converting         0.00_000_000_000_000_000_1 from EXABYTE   to BYTE shouldBe 1.0
        test converting             0.00_000_000_000_000_1 from PETABYTE  to BYTE shouldBe 1.0
        test converting                 0.00_000_000_000_1 from TERABYTE  to BYTE shouldBe 1.0
        test converting                     0.00_000_000_1 from GIGABYTE  to BYTE shouldBe 1.0
        test converting                         0.00_000_1 from MEGABYTE  to BYTE shouldBe 1.0
        test converting                             0.00_1 from KILOBYTE  to BYTE shouldBe 1.0

        test converting                             1_000.0 from BYTE to KILOBYTE  shouldBe 1.0
        test converting                         1_000_000.0 from BYTE to MEGABYTE  shouldBe 1.0
        test converting                     1_000_000_000.0 from BYTE to GIGABYTE  shouldBe 1.0
        test converting                 1_000_000_000_000.0 from BYTE to TERABYTE  shouldBe 1.0
        test converting             1_000_000_000_000_000.0 from BYTE to PETABYTE  shouldBe 1.0
        test converting         1_000_000_000_000_000_000.0 from BYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000_000_000_000_000_000.0 from BYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000_000_000_000_000_000.0 from BYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting KILOBYTE up and down`() {
        val test = DecimalConvertTest()
        // FIXME: commented out cases suffer from precision loss

        test converting 1.0 from KILOBYTE to MEGABYTE  shouldBe 0.00_1
        test converting 1.0 from KILOBYTE to GIGABYTE  shouldBe 0.00_000_1
        test converting 1.0 from KILOBYTE to TERABYTE  shouldBe 0.00_000_000_1
        test converting 1.0 from KILOBYTE to PETABYTE  shouldBe 0.00_000_000_000_1
        test converting 1.0 from KILOBYTE to EXABYTE   shouldBe 0.00_000_000_000_000_1
        test converting 1.0 from KILOBYTE to ZETTABYTE shouldBe 0.00_000_000_000_000_000_1
        test converting 1.0 from KILOBYTE to YOTTABYTE shouldBe 0.00_000_000_000_000_000_000_1

        test converting 1.0 from YOTTABYTE to KILOBYTE shouldBe 1_000_000_000_000_000_000_000.0
        test converting 1.0 from ZETTABYTE to KILOBYTE shouldBe     1_000_000_000_000_000_000.0
        test converting 1.0 from EXABYTE   to KILOBYTE shouldBe         1_000_000_000_000_000.0
        test converting 1.0 from PETABYTE  to KILOBYTE shouldBe             1_000_000_000_000.0
        test converting 1.0 from TERABYTE  to KILOBYTE shouldBe                 1_000_000_000.0
        test converting 1.0 from GIGABYTE  to KILOBYTE shouldBe                     1_000_000.0
        test converting 1.0 from MEGABYTE  to KILOBYTE shouldBe                         1_000.0

//        test converting 0.00_000_000_000_000_000_000_1 from YOTTABYTE to KILOBYTE shouldBe 1.0
        test converting     0.00_000_000_000_000_000_1 from ZETTABYTE to KILOBYTE shouldBe 1.0
        test converting         0.00_000_000_000_000_1 from EXABYTE   to KILOBYTE shouldBe 1.0
        test converting             0.00_000_000_000_1 from PETABYTE  to KILOBYTE shouldBe 1.0
        test converting                 0.00_000_000_1 from TERABYTE  to KILOBYTE shouldBe 1.0
        test converting                     0.00_000_1 from GIGABYTE  to KILOBYTE shouldBe 1.0
        test converting                         0.00_1 from MEGABYTE  to KILOBYTE shouldBe 1.0

        test converting                         1_000.0 from KILOBYTE to MEGABYTE  shouldBe 1.0
        test converting                     1_000_000.0 from KILOBYTE to GIGABYTE  shouldBe 1.0
        test converting                 1_000_000_000.0 from KILOBYTE to TERABYTE  shouldBe 1.0
        test converting             1_000_000_000_000.0 from KILOBYTE to PETABYTE  shouldBe 1.0
        test converting         1_000_000_000_000_000.0 from KILOBYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000_000_000_000_000.0 from KILOBYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000_000_000_000_000.0 from KILOBYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting MEGABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from MEGABYTE to GIGABYTE  shouldBe 0.00_1
        test converting 1.0 from MEGABYTE to TERABYTE  shouldBe 0.00_000_1
        test converting 1.0 from MEGABYTE to PETABYTE  shouldBe 0.00_000_000_1
        test converting 1.0 from MEGABYTE to EXABYTE   shouldBe 0.00_000_000_000_1
        test converting 1.0 from MEGABYTE to ZETTABYTE shouldBe 0.00_000_000_000_000_1
        test converting 1.0 from MEGABYTE to YOTTABYTE shouldBe 0.00_000_000_000_000_000_1

        test converting 1.0 from YOTTABYTE to MEGABYTE shouldBe 1_000_000_000_000_000_000.0
        test converting 1.0 from ZETTABYTE to MEGABYTE shouldBe     1_000_000_000_000_000.0
        test converting 1.0 from EXABYTE   to MEGABYTE shouldBe         1_000_000_000_000.0
        test converting 1.0 from PETABYTE  to MEGABYTE shouldBe             1_000_000_000.0
        test converting 1.0 from TERABYTE  to MEGABYTE shouldBe                 1_000_000.0
        test converting 1.0 from GIGABYTE  to MEGABYTE shouldBe                     1_000.0

        test converting 0.00_000_000_000_000_000_1 from YOTTABYTE to MEGABYTE shouldBe 1.0
        test converting     0.00_000_000_000_000_1 from ZETTABYTE to MEGABYTE shouldBe 1.0
        test converting         0.00_000_000_000_1 from EXABYTE   to MEGABYTE shouldBe 1.0
        test converting             0.00_000_000_1 from PETABYTE  to MEGABYTE shouldBe 1.0
        test converting                 0.00_000_1 from TERABYTE  to MEGABYTE shouldBe 1.0
        test converting                     0.00_1 from GIGABYTE  to MEGABYTE shouldBe 1.0

        test converting                     1_000.0 from MEGABYTE to GIGABYTE  shouldBe 1.0
        test converting                 1_000_000.0 from MEGABYTE to TERABYTE  shouldBe 1.0
        test converting             1_000_000_000.0 from MEGABYTE to PETABYTE  shouldBe 1.0
        test converting         1_000_000_000_000.0 from MEGABYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000_000_000_000.0 from MEGABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000_000_000_000.0 from MEGABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting GIGABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from GIGABYTE to TERABYTE  shouldBe 0.00_1
        test converting 1.0 from GIGABYTE to PETABYTE  shouldBe 0.00_000_1
        test converting 1.0 from GIGABYTE to EXABYTE   shouldBe 0.00_000_000_1
        test converting 1.0 from GIGABYTE to ZETTABYTE shouldBe 0.00_000_000_000_1
        test converting 1.0 from GIGABYTE to YOTTABYTE shouldBe 0.00_000_000_000_000_1

        test converting 1.0 from YOTTABYTE to GIGABYTE shouldBe 1_000_000_000_000_000.0
        test converting 1.0 from ZETTABYTE to GIGABYTE shouldBe     1_000_000_000_000.0
        test converting 1.0 from EXABYTE   to GIGABYTE shouldBe         1_000_000_000.0
        test converting 1.0 from PETABYTE  to GIGABYTE shouldBe             1_000_000.0
        test converting 1.0 from TERABYTE  to GIGABYTE shouldBe                 1_000.0

        test converting 0.00_000_000_000_000_1 from YOTTABYTE to GIGABYTE shouldBe 1.0
        test converting     0.00_000_000_000_1 from ZETTABYTE to GIGABYTE shouldBe 1.0
        test converting         0.00_000_000_1 from EXABYTE   to GIGABYTE shouldBe 1.0
        test converting             0.00_000_1 from PETABYTE  to GIGABYTE shouldBe 1.0
        test converting                 0.00_1 from TERABYTE  to GIGABYTE shouldBe 1.0

        test converting                 1_000.0 from GIGABYTE to TERABYTE  shouldBe 1.0
        test converting             1_000_000.0 from GIGABYTE to PETABYTE  shouldBe 1.0
        test converting         1_000_000_000.0 from GIGABYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000_000_000.0 from GIGABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000_000_000.0 from GIGABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting TERABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from TERABYTE to PETABYTE  shouldBe 0.00_1
        test converting 1.0 from TERABYTE to EXABYTE   shouldBe 0.00_000_1
        test converting 1.0 from TERABYTE to ZETTABYTE shouldBe 0.00_000_000_1
        test converting 1.0 from TERABYTE to YOTTABYTE shouldBe 0.00_000_000_000_1

        test converting 1.0 from YOTTABYTE to TERABYTE shouldBe 1_000_000_000_000.0
        test converting 1.0 from ZETTABYTE to TERABYTE shouldBe     1_000_000_000.0
        test converting 1.0 from EXABYTE   to TERABYTE shouldBe         1_000_000.0
        test converting 1.0 from PETABYTE  to TERABYTE shouldBe             1_000.0

        test converting 0.00_000_000_000_1 from YOTTABYTE to TERABYTE shouldBe 1.0
        test converting     0.00_000_000_1 from ZETTABYTE to TERABYTE shouldBe 1.0
        test converting         0.00_000_1 from EXABYTE   to TERABYTE shouldBe 1.0
        test converting             0.00_1 from PETABYTE  to TERABYTE shouldBe 1.0

        test converting             1_000.0 from TERABYTE to PETABYTE  shouldBe 1.0
        test converting         1_000_000.0 from TERABYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000_000.0 from TERABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000_000.0 from TERABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting PETABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from PETABYTE to EXABYTE   shouldBe 0.00_1
        test converting 1.0 from PETABYTE to ZETTABYTE shouldBe 0.00_000_1
        test converting 1.0 from PETABYTE to YOTTABYTE shouldBe 0.00_000_000_1

        test converting 1.0 from YOTTABYTE to PETABYTE shouldBe 1_000_000_000.0
        test converting 1.0 from ZETTABYTE to PETABYTE shouldBe     1_000_000.0
        test converting 1.0 from EXABYTE   to PETABYTE shouldBe         1_000.0

        test converting 0.00_000_000_1 from YOTTABYTE to PETABYTE shouldBe 1.0
        test converting     0.00_000_1 from ZETTABYTE to PETABYTE shouldBe 1.0
        test converting         0.00_1 from EXABYTE   to PETABYTE shouldBe 1.0

        test converting         1_000.0 from PETABYTE to EXABYTE   shouldBe 1.0
        test converting     1_000_000.0 from PETABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000_000.0 from PETABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting EXABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from EXABYTE to ZETTABYTE shouldBe 0.00_1
        test converting 1.0 from EXABYTE to YOTTABYTE shouldBe 0.00_000_1

        test converting 1.0 from YOTTABYTE to EXABYTE shouldBe 1_000_000.0
        test converting 1.0 from ZETTABYTE to EXABYTE shouldBe     1_000.0

        test converting 0.00_000_1 from YOTTABYTE to EXABYTE shouldBe 1.0
        test converting     0.00_1 from ZETTABYTE to EXABYTE shouldBe 1.0

        test converting     1_000.0 from EXABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000_000.0 from EXABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting ZETTABYTE up and down`() {
        val test = DecimalConvertTest()

        test converting 1.0 from ZETTABYTE to YOTTABYTE shouldBe 0.00_1
        test converting 1.0 from YOTTABYTE to ZETTABYTE shouldBe 1_000.0

        test converting 0.00_1  from YOTTABYTE to ZETTABYTE shouldBe 1.0
        test converting 1_000.0 from ZETTABYTE to YOTTABYTE shouldBe 1.0
    }

    @Test
    fun `test converting to itself`() {
        val test = DecimalConvertTest()

        test converting 1.0 from BYTE      to BYTE      shouldBe 1.0
        test converting 1.0 from KILOBYTE  to KILOBYTE  shouldBe 1.0
        test converting 1.0 from MEGABYTE  to MEGABYTE  shouldBe 1.0
        test converting 1.0 from GIGABYTE  to GIGABYTE  shouldBe 1.0
        test converting 1.0 from TERABYTE  to TERABYTE  shouldBe 1.0
        test converting 1.0 from PETABYTE  to PETABYTE  shouldBe 1.0
        test converting 1.0 from EXABYTE   to EXABYTE   shouldBe 1.0
        test converting 1.0 from ZETTABYTE to ZETTABYTE shouldBe 1.0
        test converting 1.0 from YOTTABYTE to YOTTABYTE shouldBe 1.0

        test converting -1.0 from BYTE      to BYTE      shouldBe -1.0
        test converting -1.0 from KILOBYTE  to KILOBYTE  shouldBe -1.0
        test converting -1.0 from MEGABYTE  to MEGABYTE  shouldBe -1.0
        test converting -1.0 from GIGABYTE  to GIGABYTE  shouldBe -1.0
        test converting -1.0 from TERABYTE  to TERABYTE  shouldBe -1.0
        test converting -1.0 from PETABYTE  to PETABYTE  shouldBe -1.0
        test converting -1.0 from EXABYTE   to EXABYTE   shouldBe -1.0
        test converting -1.0 from ZETTABYTE to ZETTABYTE shouldBe -1.0
        test converting -1.0 from YOTTABYTE to YOTTABYTE shouldBe -1.0

        test converting 10.0 from BYTE      to BYTE      shouldBe 10.0
        test converting 10.0 from KILOBYTE  to KILOBYTE  shouldBe 10.0
        test converting 10.0 from MEGABYTE  to MEGABYTE  shouldBe 10.0
        test converting 10.0 from GIGABYTE  to GIGABYTE  shouldBe 10.0
        test converting 10.0 from TERABYTE  to TERABYTE  shouldBe 10.0
        test converting 10.0 from PETABYTE  to PETABYTE  shouldBe 10.0
        test converting 10.0 from EXABYTE   to EXABYTE   shouldBe 10.0
        test converting 10.0 from ZETTABYTE to ZETTABYTE shouldBe 10.0
        test converting 10.0 from YOTTABYTE to YOTTABYTE shouldBe 10.0
    }

    @Test
    fun `test converting unevenly down`() {
        val test = DecimalConvertTest()

        test converting 0.1 from KILOBYTE  to BYTE      shouldBe 100.0
        test converting 0.1 from MEGABYTE  to KILOBYTE  shouldBe 100.0
        test converting 0.1 from GIGABYTE  to MEGABYTE  shouldBe 100.0
        test converting 0.1 from TERABYTE  to GIGABYTE  shouldBe 100.0
        test converting 0.1 from PETABYTE  to TERABYTE  shouldBe 100.0
        test converting 0.1 from EXABYTE   to PETABYTE  shouldBe 100.0
        test converting 0.1 from ZETTABYTE to EXABYTE   shouldBe 100.0
        test converting 0.1 from YOTTABYTE to ZETTABYTE shouldBe 100.0

        test converting 1.1 from KILOBYTE  to BYTE      shouldBe 1100.0
        test converting 1.1 from MEGABYTE  to KILOBYTE  shouldBe 1100.0
        test converting 1.1 from GIGABYTE  to MEGABYTE  shouldBe 1100.0
        test converting 1.1 from TERABYTE  to GIGABYTE  shouldBe 1100.0
        test converting 1.1 from PETABYTE  to TERABYTE  shouldBe 1100.0
        test converting 1.1 from EXABYTE   to PETABYTE  shouldBe 1100.0
        test converting 1.1 from ZETTABYTE to EXABYTE   shouldBe 1100.0
        test converting 1.1 from YOTTABYTE to ZETTABYTE shouldBe 1100.0

        test converting -1.1 from KILOBYTE  to BYTE      shouldBe -1100.0
        test converting -1.1 from MEGABYTE  to KILOBYTE  shouldBe -1100.0
        test converting -1.1 from GIGABYTE  to MEGABYTE  shouldBe -1100.0
        test converting -1.1 from TERABYTE  to GIGABYTE  shouldBe -1100.0
        test converting -1.1 from PETABYTE  to TERABYTE  shouldBe -1100.0
        test converting -1.1 from EXABYTE   to PETABYTE  shouldBe -1100.0
        test converting -1.1 from ZETTABYTE to EXABYTE   shouldBe -1100.0
        test converting -1.1 from YOTTABYTE to ZETTABYTE shouldBe -1100.0

        test converting 10.1 from KILOBYTE  to BYTE      shouldBe 10_100.0
        test converting 10.1 from MEGABYTE  to KILOBYTE  shouldBe 10_100.0
        test converting 10.1 from GIGABYTE  to MEGABYTE  shouldBe 10_100.0
        test converting 10.1 from TERABYTE  to GIGABYTE  shouldBe 10_100.0
        test converting 10.1 from PETABYTE  to TERABYTE  shouldBe 10_100.0
        test converting 10.1 from EXABYTE   to PETABYTE  shouldBe 10_100.0
        test converting 10.1 from ZETTABYTE to EXABYTE   shouldBe 10_100.0
        test converting 10.1 from YOTTABYTE to ZETTABYTE shouldBe 10_100.0
    }

    @Test
    fun `test converting unevenly up`() {
        val test = DecimalConvertTest()

        test converting 100.0 from BYTE      to KILOBYTE  shouldBe 0.1
        test converting 100.0 from KILOBYTE  to MEGABYTE  shouldBe 0.1
        test converting 100.0 from MEGABYTE  to GIGABYTE  shouldBe 0.1
        test converting 100.0 from GIGABYTE  to TERABYTE  shouldBe 0.1
        test converting 100.0 from TERABYTE  to PETABYTE  shouldBe 0.1
        test converting 100.0 from PETABYTE  to EXABYTE   shouldBe 0.1
        test converting 100.0 from EXABYTE   to ZETTABYTE shouldBe 0.1
        test converting 100.0 from ZETTABYTE to YOTTABYTE shouldBe 0.1

        test converting 1100.0 from BYTE      to KILOBYTE  shouldBe 1.1
        test converting 1100.0 from KILOBYTE  to MEGABYTE  shouldBe 1.1
        test converting 1100.0 from MEGABYTE  to GIGABYTE  shouldBe 1.1
        test converting 1100.0 from GIGABYTE  to TERABYTE  shouldBe 1.1
        test converting 1100.0 from TERABYTE  to PETABYTE  shouldBe 1.1
        test converting 1100.0 from PETABYTE  to EXABYTE   shouldBe 1.1
        test converting 1100.0 from EXABYTE   to ZETTABYTE shouldBe 1.1
        test converting 1100.0 from ZETTABYTE to YOTTABYTE shouldBe 1.1

        test converting -1100.0 from BYTE      to KILOBYTE  shouldBe -1.1
        test converting -1100.0 from KILOBYTE  to MEGABYTE  shouldBe -1.1
        test converting -1100.0 from MEGABYTE  to GIGABYTE  shouldBe -1.1
        test converting -1100.0 from GIGABYTE  to TERABYTE  shouldBe -1.1
        test converting -1100.0 from TERABYTE  to PETABYTE  shouldBe -1.1
        test converting -1100.0 from PETABYTE  to EXABYTE   shouldBe -1.1
        test converting -1100.0 from EXABYTE   to ZETTABYTE shouldBe -1.1
        test converting -1100.0 from ZETTABYTE to YOTTABYTE shouldBe -1.1

        test converting 10_100.0 from BYTE      to KILOBYTE  shouldBe 10.1
        test converting 10_100.0 from KILOBYTE  to MEGABYTE  shouldBe 10.1
        test converting 10_100.0 from MEGABYTE  to GIGABYTE  shouldBe 10.1
        test converting 10_100.0 from GIGABYTE  to TERABYTE  shouldBe 10.1
        test converting 10_100.0 from TERABYTE  to PETABYTE  shouldBe 10.1
        test converting 10_100.0 from PETABYTE  to EXABYTE   shouldBe 10.1
        test converting 10_100.0 from EXABYTE   to ZETTABYTE shouldBe 10.1
        test converting 10_100.0 from ZETTABYTE to YOTTABYTE shouldBe 10.1
    }

    @Test
    fun `test short names`() {
        val test = AssertEquals()
        test that BYTE.shortName()      isEqualTo "B"
        test that KILOBYTE.shortName()  isEqualTo "kB"
        test that MEGABYTE.shortName()  isEqualTo "MB"
        test that GIGABYTE.shortName()  isEqualTo "GB"
        test that TERABYTE.shortName()  isEqualTo "TB"
        test that PETABYTE.shortName()  isEqualTo "PB"
        test that EXABYTE.shortName()   isEqualTo "EB"
        test that ZETTABYTE.shortName() isEqualTo "ZB"
        test that YOTTABYTE.shortName() isEqualTo "YB"
    }
}

class DecimalConvertTest {
    private var value: Double? = null
    private var source: DecimalUnit? = null
    private var target: DecimalUnit? = null

    private val failMessage: String get() = "converting $value $source to $target -> "

    infix fun converting(value: Double): DecimalConvertTest = this.apply { this.value = value }
    infix fun from(source: DecimalUnit): DecimalConvertTest = this.apply { this.source = source }
    infix fun to(target: DecimalUnit): DecimalConvertTest = this.apply { this.target = target }
    infix fun shouldBe(expected: Double): DecimalConvertTest = this.apply {
        assertEquals(expected, convertDecimalUnit(value!!, source!!, target!!), failMessage)
    }
}

