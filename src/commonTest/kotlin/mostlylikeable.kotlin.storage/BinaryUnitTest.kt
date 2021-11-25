package mostlylikeable.kotlin.storage

import kotlin.test.Test
import kotlin.test.assertEquals
import mostlylikeable.kotlin.storage.BinaryUnit.*

class BinaryUnitTest {

    @Test
    fun `test converting zero`() {
        val test = BinaryConvertTest()

        BinaryUnit.values().forEach { source ->
            BinaryUnit.values().forEach { target ->
                test converting 0.0 from source to target shouldBe 0.0
            }
        }
    }

    @Test
    fun `test converting all the way up`() {
        val test = BinaryConvertTest()

        test converting                              1024.0 from BYTE to KIBIBYTE shouldBe 1.0
        test converting                         1_048_576.0 from BYTE to MEBIBYTE shouldBe 1.0
        test converting                     1_073_741_824.0 from BYTE to GIBIBYTE shouldBe 1.0
        test converting                 1_099_511_627_776.0 from BYTE to TEBIBYTE shouldBe 1.0
        test converting             1_125_899_906_842_624.0 from BYTE to PEBIBYTE shouldBe 1.0
        test converting         1_152_921_504_606_846_976.0 from BYTE to EXBIBYTE shouldBe 1.0
        test converting     1_180_591_620_717_411_303_424.0 from BYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_208_925_819_614_629_174_706_176.0 from BYTE to YOBIBYTE shouldBe 1.0

        test converting                          1024.0 from KIBIBYTE to MEBIBYTE shouldBe 1.0
        test converting                     1_048_576.0 from KIBIBYTE to GIBIBYTE shouldBe 1.0
        test converting                 1_073_741_824.0 from KIBIBYTE to TEBIBYTE shouldBe 1.0
        test converting             1_099_511_627_776.0 from KIBIBYTE to PEBIBYTE shouldBe 1.0
        test converting         1_125_899_906_842_624.0 from KIBIBYTE to EXBIBYTE shouldBe 1.0
        test converting     1_152_921_504_606_846_976.0 from KIBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_180_591_620_717_411_303_424.0 from KIBIBYTE to YOBIBYTE shouldBe 1.0

        test converting                      1024.0 from MEBIBYTE to GIBIBYTE shouldBe 1.0
        test converting                 1_048_576.0 from MEBIBYTE to TEBIBYTE shouldBe 1.0
        test converting             1_073_741_824.0 from MEBIBYTE to PEBIBYTE shouldBe 1.0
        test converting         1_099_511_627_776.0 from MEBIBYTE to EXBIBYTE shouldBe 1.0
        test converting     1_125_899_906_842_624.0 from MEBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_152_921_504_606_846_976.0 from MEBIBYTE to YOBIBYTE shouldBe 1.0

        test converting                  1024.0 from GIBIBYTE to TEBIBYTE shouldBe 1.0
        test converting             1_048_576.0 from GIBIBYTE to PEBIBYTE shouldBe 1.0
        test converting         1_073_741_824.0 from GIBIBYTE to EXBIBYTE shouldBe 1.0
        test converting     1_099_511_627_776.0 from GIBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_125_899_906_842_624.0 from GIBIBYTE to YOBIBYTE shouldBe 1.0

        test converting              1024.0 from TEBIBYTE to PEBIBYTE shouldBe 1.0
        test converting         1_048_576.0 from TEBIBYTE to EXBIBYTE shouldBe 1.0
        test converting     1_073_741_824.0 from TEBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_099_511_627_776.0 from TEBIBYTE to YOBIBYTE shouldBe 1.0

        test converting          1024.0 from PEBIBYTE to EXBIBYTE shouldBe 1.0
        test converting     1_048_576.0 from PEBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_073_741_824.0 from PEBIBYTE to YOBIBYTE shouldBe 1.0

        test converting      1024.0 from EXBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1_048_576.0 from EXBIBYTE to YOBIBYTE shouldBe 1.0

        test converting 1024.0 from ZEBIBYTE to YOBIBYTE shouldBe 1.0
    }

    @Test
    fun `test converting all the way down`() {
        val test = BinaryConvertTest()

        test converting 1.0 from YOBIBYTE to BYTE     shouldBe 1_208_925_819_614_629_174_706_176.0
        test converting 1.0 from YOBIBYTE to KIBIBYTE shouldBe 1_180_591_620_717_411_303_424.0
        test converting 1.0 from YOBIBYTE to MEBIBYTE shouldBe 1_152_921_504_606_846_976.0
        test converting 1.0 from YOBIBYTE to GIBIBYTE shouldBe 1_125_899_906_842_624.0
        test converting 1.0 from YOBIBYTE to TEBIBYTE shouldBe 1_099_511_627_776.0
        test converting 1.0 from YOBIBYTE to PEBIBYTE shouldBe 1_073_741_824.0
        test converting 1.0 from YOBIBYTE to EXBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from YOBIBYTE to ZEBIBYTE shouldBe 1024.0

        test converting 1.0 from ZEBIBYTE to BYTE     shouldBe 1_180_591_620_717_411_303_424.0
        test converting 1.0 from ZEBIBYTE to KIBIBYTE shouldBe 1_152_921_504_606_846_976.0
        test converting 1.0 from ZEBIBYTE to MEBIBYTE shouldBe 1_125_899_906_842_624.0
        test converting 1.0 from ZEBIBYTE to GIBIBYTE shouldBe 1_099_511_627_776.0
        test converting 1.0 from ZEBIBYTE to TEBIBYTE shouldBe 1_073_741_824.0
        test converting 1.0 from ZEBIBYTE to PEBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from ZEBIBYTE to EXBIBYTE shouldBe 1024.0

        test converting 1.0 from EXBIBYTE to BYTE     shouldBe 1_152_921_504_606_846_976.0
        test converting 1.0 from EXBIBYTE to KIBIBYTE shouldBe 1_125_899_906_842_624.0
        test converting 1.0 from EXBIBYTE to MEBIBYTE shouldBe 1_099_511_627_776.0
        test converting 1.0 from EXBIBYTE to GIBIBYTE shouldBe 1_073_741_824.0
        test converting 1.0 from EXBIBYTE to TEBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from EXBIBYTE to PEBIBYTE shouldBe 1024.0

        test converting 1.0 from PEBIBYTE to BYTE     shouldBe 1_125_899_906_842_624.0
        test converting 1.0 from PEBIBYTE to KIBIBYTE shouldBe 1_099_511_627_776.0
        test converting 1.0 from PEBIBYTE to MEBIBYTE shouldBe 1_073_741_824.0
        test converting 1.0 from PEBIBYTE to GIBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from PEBIBYTE to TEBIBYTE shouldBe 1024.0

        test converting 1.0 from TEBIBYTE to BYTE     shouldBe 1_099_511_627_776.0
        test converting 1.0 from TEBIBYTE to KIBIBYTE shouldBe 1_073_741_824.0
        test converting 1.0 from TEBIBYTE to MEBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from TEBIBYTE to GIBIBYTE shouldBe 1024.0

        test converting 1.0 from GIBIBYTE to BYTE     shouldBe 1_073_741_824.0
        test converting 1.0 from GIBIBYTE to KIBIBYTE shouldBe 1_048_576.0
        test converting 1.0 from GIBIBYTE to MEBIBYTE shouldBe 1024.0

        test converting 1.0 from MEBIBYTE to BYTE     shouldBe 1_048_576.0
        test converting 1.0 from MEBIBYTE to KIBIBYTE shouldBe 1024.0

        test converting 1.0 from KIBIBYTE to BYTE shouldBe 1024.0
    }

    @Test
    fun `test converting to itself`() {
        val test = BinaryConvertTest()

        test converting 1.0 from BYTE     to BYTE     shouldBe 1.0
        test converting 1.0 from KIBIBYTE to KIBIBYTE shouldBe 1.0
        test converting 1.0 from MEBIBYTE to MEBIBYTE shouldBe 1.0
        test converting 1.0 from GIBIBYTE to GIBIBYTE shouldBe 1.0
        test converting 1.0 from TEBIBYTE to TEBIBYTE shouldBe 1.0
        test converting 1.0 from PEBIBYTE to PEBIBYTE shouldBe 1.0
        test converting 1.0 from EXBIBYTE to EXBIBYTE shouldBe 1.0
        test converting 1.0 from ZEBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1.0 from YOBIBYTE to YOBIBYTE shouldBe 1.0

        test converting -1.0 from BYTE     to BYTE     shouldBe -1.0
        test converting -1.0 from KIBIBYTE to KIBIBYTE shouldBe -1.0
        test converting -1.0 from MEBIBYTE to MEBIBYTE shouldBe -1.0
        test converting -1.0 from GIBIBYTE to GIBIBYTE shouldBe -1.0
        test converting -1.0 from TEBIBYTE to TEBIBYTE shouldBe -1.0
        test converting -1.0 from PEBIBYTE to PEBIBYTE shouldBe -1.0
        test converting -1.0 from EXBIBYTE to EXBIBYTE shouldBe -1.0
        test converting -1.0 from ZEBIBYTE to ZEBIBYTE shouldBe -1.0
        test converting -1.0 from YOBIBYTE to YOBIBYTE shouldBe -1.0

        test converting 10.0 from BYTE     to BYTE     shouldBe 10.0
        test converting 10.0 from KIBIBYTE to KIBIBYTE shouldBe 10.0
        test converting 10.0 from MEBIBYTE to MEBIBYTE shouldBe 10.0
        test converting 10.0 from GIBIBYTE to GIBIBYTE shouldBe 10.0
        test converting 10.0 from TEBIBYTE to TEBIBYTE shouldBe 10.0
        test converting 10.0 from PEBIBYTE to PEBIBYTE shouldBe 10.0
        test converting 10.0 from EXBIBYTE to EXBIBYTE shouldBe 10.0
        test converting 10.0 from ZEBIBYTE to ZEBIBYTE shouldBe 10.0
        test converting 10.0 from YOBIBYTE to YOBIBYTE shouldBe 10.0
    }

    @Test
    fun `test converting evenly down`() {
        val test = BinaryConvertTest()

        test converting 1.0 from KIBIBYTE to BYTE     shouldBe 1024.0
        test converting 1.0 from MEBIBYTE to KIBIBYTE shouldBe 1024.0
        test converting 1.0 from GIBIBYTE to MEBIBYTE shouldBe 1024.0
        test converting 1.0 from TEBIBYTE to GIBIBYTE shouldBe 1024.0
        test converting 1.0 from PEBIBYTE to TEBIBYTE shouldBe 1024.0
        test converting 1.0 from EXBIBYTE to PEBIBYTE shouldBe 1024.0
        test converting 1.0 from ZEBIBYTE to EXBIBYTE shouldBe 1024.0
        test converting 1.0 from YOBIBYTE to ZEBIBYTE shouldBe 1024.0

        test converting -1.0 from KIBIBYTE to BYTE     shouldBe -1024.0
        test converting -1.0 from MEBIBYTE to KIBIBYTE shouldBe -1024.0
        test converting -1.0 from GIBIBYTE to MEBIBYTE shouldBe -1024.0
        test converting -1.0 from TEBIBYTE to GIBIBYTE shouldBe -1024.0
        test converting -1.0 from PEBIBYTE to TEBIBYTE shouldBe -1024.0
        test converting -1.0 from EXBIBYTE to PEBIBYTE shouldBe -1024.0
        test converting -1.0 from ZEBIBYTE to EXBIBYTE shouldBe -1024.0
        test converting -1.0 from YOBIBYTE to ZEBIBYTE shouldBe -1024.0

        test converting 10.0 from KIBIBYTE to BYTE     shouldBe 10_240.0
        test converting 10.0 from MEBIBYTE to KIBIBYTE shouldBe 10_240.0
        test converting 10.0 from GIBIBYTE to MEBIBYTE shouldBe 10_240.0
        test converting 10.0 from TEBIBYTE to GIBIBYTE shouldBe 10_240.0
        test converting 10.0 from PEBIBYTE to TEBIBYTE shouldBe 10_240.0
        test converting 10.0 from EXBIBYTE to PEBIBYTE shouldBe 10_240.0
        test converting 10.0 from ZEBIBYTE to EXBIBYTE shouldBe 10_240.0
        test converting 10.0 from YOBIBYTE to ZEBIBYTE shouldBe 10_240.0
    }

    @Test
    fun `test converting evenly up`() {
        val test = BinaryConvertTest()

        test converting 1024.0 from BYTE     to KIBIBYTE shouldBe 1.0
        test converting 1024.0 from KIBIBYTE to MEBIBYTE shouldBe 1.0
        test converting 1024.0 from MEBIBYTE to GIBIBYTE shouldBe 1.0
        test converting 1024.0 from GIBIBYTE to TEBIBYTE shouldBe 1.0
        test converting 1024.0 from TEBIBYTE to PEBIBYTE shouldBe 1.0
        test converting 1024.0 from PEBIBYTE to EXBIBYTE shouldBe 1.0
        test converting 1024.0 from EXBIBYTE to ZEBIBYTE shouldBe 1.0
        test converting 1024.0 from ZEBIBYTE to YOBIBYTE shouldBe 1.0

        test converting -1024.0 from BYTE     to KIBIBYTE shouldBe -1.0
        test converting -1024.0 from KIBIBYTE to MEBIBYTE shouldBe -1.0
        test converting -1024.0 from MEBIBYTE to GIBIBYTE shouldBe -1.0
        test converting -1024.0 from GIBIBYTE to TEBIBYTE shouldBe -1.0
        test converting -1024.0 from TEBIBYTE to PEBIBYTE shouldBe -1.0
        test converting -1024.0 from PEBIBYTE to EXBIBYTE shouldBe -1.0
        test converting -1024.0 from EXBIBYTE to ZEBIBYTE shouldBe -1.0
        test converting -1024.0 from ZEBIBYTE to YOBIBYTE shouldBe -1.0

        test converting 10240.0 from BYTE     to KIBIBYTE shouldBe 10.0
        test converting 10240.0 from KIBIBYTE to MEBIBYTE shouldBe 10.0
        test converting 10240.0 from MEBIBYTE to GIBIBYTE shouldBe 10.0
        test converting 10240.0 from GIBIBYTE to TEBIBYTE shouldBe 10.0
        test converting 10240.0 from TEBIBYTE to PEBIBYTE shouldBe 10.0
        test converting 10240.0 from PEBIBYTE to EXBIBYTE shouldBe 10.0
        test converting 10240.0 from EXBIBYTE to ZEBIBYTE shouldBe 10.0
        test converting 10240.0 from ZEBIBYTE to YOBIBYTE shouldBe 10.0
    }

    @Test
    fun `test converting unevenly down`() {
        val test = BinaryConvertTest()

        test converting 0.1 from KIBIBYTE to BYTE     shouldBe 102.4
        test converting 0.1 from MEBIBYTE to KIBIBYTE shouldBe 102.4
        test converting 0.1 from GIBIBYTE to MEBIBYTE shouldBe 102.4
        test converting 0.1 from TEBIBYTE to GIBIBYTE shouldBe 102.4
        test converting 0.1 from PEBIBYTE to TEBIBYTE shouldBe 102.4
        test converting 0.1 from EXBIBYTE to PEBIBYTE shouldBe 102.4
        test converting 0.1 from ZEBIBYTE to EXBIBYTE shouldBe 102.4
        test converting 0.1 from YOBIBYTE to ZEBIBYTE shouldBe 102.4

        test converting 1.1 from KIBIBYTE to BYTE     shouldBe 1126.4
        test converting 1.1 from MEBIBYTE to KIBIBYTE shouldBe 1126.4
        test converting 1.1 from GIBIBYTE to MEBIBYTE shouldBe 1126.4
        test converting 1.1 from TEBIBYTE to GIBIBYTE shouldBe 1126.4
        test converting 1.1 from PEBIBYTE to TEBIBYTE shouldBe 1126.4
        test converting 1.1 from EXBIBYTE to PEBIBYTE shouldBe 1126.4
        test converting 1.1 from ZEBIBYTE to EXBIBYTE shouldBe 1126.4
        test converting 1.1 from YOBIBYTE to ZEBIBYTE shouldBe 1126.4

        test converting -1.1 from KIBIBYTE to BYTE     shouldBe -1126.4
        test converting -1.1 from MEBIBYTE to KIBIBYTE shouldBe -1126.4
        test converting -1.1 from GIBIBYTE to MEBIBYTE shouldBe -1126.4
        test converting -1.1 from TEBIBYTE to GIBIBYTE shouldBe -1126.4
        test converting -1.1 from PEBIBYTE to TEBIBYTE shouldBe -1126.4
        test converting -1.1 from EXBIBYTE to PEBIBYTE shouldBe -1126.4
        test converting -1.1 from ZEBIBYTE to EXBIBYTE shouldBe -1126.4
        test converting -1.1 from YOBIBYTE to ZEBIBYTE shouldBe -1126.4

        test converting 10.1 from KIBIBYTE to BYTE     shouldBe 10_342.4
        test converting 10.1 from MEBIBYTE to KIBIBYTE shouldBe 10_342.4
        test converting 10.1 from GIBIBYTE to MEBIBYTE shouldBe 10_342.4
        test converting 10.1 from TEBIBYTE to GIBIBYTE shouldBe 10_342.4
        test converting 10.1 from PEBIBYTE to TEBIBYTE shouldBe 10_342.4
        test converting 10.1 from EXBIBYTE to PEBIBYTE shouldBe 10_342.4
        test converting 10.1 from ZEBIBYTE to EXBIBYTE shouldBe 10_342.4
        test converting 10.1 from YOBIBYTE to ZEBIBYTE shouldBe 10_342.4
    }

    @Test
    fun `test converting unevenly up`() {
        val test = BinaryConvertTest()

        test converting 102.4 from BYTE     to KIBIBYTE shouldBe 0.1
        test converting 102.4 from KIBIBYTE to MEBIBYTE shouldBe 0.1
        test converting 102.4 from MEBIBYTE to GIBIBYTE shouldBe 0.1
        test converting 102.4 from GIBIBYTE to TEBIBYTE shouldBe 0.1
        test converting 102.4 from TEBIBYTE to PEBIBYTE shouldBe 0.1
        test converting 102.4 from PEBIBYTE to EXBIBYTE shouldBe 0.1
        test converting 102.4 from EXBIBYTE to ZEBIBYTE shouldBe 0.1
        test converting 102.4 from ZEBIBYTE to YOBIBYTE shouldBe 0.1

        test converting 1126.4 from BYTE     to KIBIBYTE shouldBe 1.1
        test converting 1126.4 from KIBIBYTE to MEBIBYTE shouldBe 1.1
        test converting 1126.4 from MEBIBYTE to GIBIBYTE shouldBe 1.1
        test converting 1126.4 from GIBIBYTE to TEBIBYTE shouldBe 1.1
        test converting 1126.4 from TEBIBYTE to PEBIBYTE shouldBe 1.1
        test converting 1126.4 from PEBIBYTE to EXBIBYTE shouldBe 1.1
        test converting 1126.4 from EXBIBYTE to ZEBIBYTE shouldBe 1.1
        test converting 1126.4 from ZEBIBYTE to YOBIBYTE shouldBe 1.1

        test converting -1126.4 from BYTE     to KIBIBYTE shouldBe -1.1
        test converting -1126.4 from KIBIBYTE to MEBIBYTE shouldBe -1.1
        test converting -1126.4 from MEBIBYTE to GIBIBYTE shouldBe -1.1
        test converting -1126.4 from GIBIBYTE to TEBIBYTE shouldBe -1.1
        test converting -1126.4 from TEBIBYTE to PEBIBYTE shouldBe -1.1
        test converting -1126.4 from PEBIBYTE to EXBIBYTE shouldBe -1.1
        test converting -1126.4 from EXBIBYTE to ZEBIBYTE shouldBe -1.1
        test converting -1126.4 from ZEBIBYTE to YOBIBYTE shouldBe -1.1

        test converting 10_342.4 from BYTE     to KIBIBYTE shouldBe 10.1
        test converting 10_342.4 from KIBIBYTE to MEBIBYTE shouldBe 10.1
        test converting 10_342.4 from MEBIBYTE to GIBIBYTE shouldBe 10.1
        test converting 10_342.4 from GIBIBYTE to TEBIBYTE shouldBe 10.1
        test converting 10_342.4 from TEBIBYTE to PEBIBYTE shouldBe 10.1
        test converting 10_342.4 from PEBIBYTE to EXBIBYTE shouldBe 10.1
        test converting 10_342.4 from EXBIBYTE to ZEBIBYTE shouldBe 10.1
        test converting 10_342.4 from ZEBIBYTE to YOBIBYTE shouldBe 10.1
    }

    @Test
    fun `test short names`() {
        val test = AssertEquals()
        test that BYTE.shortName()     isEqualTo "B"
        test that KIBIBYTE.shortName() isEqualTo "KiB"
        test that MEBIBYTE.shortName() isEqualTo "MiB"
        test that GIBIBYTE.shortName() isEqualTo "GiB"
        test that TEBIBYTE.shortName() isEqualTo "TiB"
        test that PEBIBYTE.shortName() isEqualTo "PiB"
        test that EXBIBYTE.shortName() isEqualTo "EiB"
        test that ZEBIBYTE.shortName() isEqualTo "ZiB"
        test that YOBIBYTE.shortName() isEqualTo "YiB"
    }
}

class BinaryConvertTest {
    private var value: Double? = null
    private var source: BinaryUnit? = null
    private var target: BinaryUnit? = null

    private val failMessage: String get() = "converting $value $source to $target -> "

    infix fun converting(value: Double): BinaryConvertTest = this.apply { this.value = value }
    infix fun from(source: BinaryUnit): BinaryConvertTest = this.apply { this.source = source }
    infix fun to(target: BinaryUnit): BinaryConvertTest = this.apply { this.target = target }
    infix fun shouldBe(expected: Double): BinaryConvertTest = this.apply {
        assertEquals(expected, convertBinaryUnit(value!!, source!!, target!!), failMessage)
    }
}
