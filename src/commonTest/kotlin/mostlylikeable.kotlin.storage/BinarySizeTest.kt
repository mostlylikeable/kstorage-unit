package mostlylikeable.kotlin.storage

import kotlin.test.Test

import kotlin.Double.Companion.NaN
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.Double.Companion.POSITIVE_INFINITY

class BinarySizeTest {

    @Test
    fun `test NaN is not allowed`() {
        AssertThrows().also { test ->
            test that { BinarySize(NaN) } threw IllegalArgumentException::class withMessage "Failed requirement."
        }
    }

    @Test
    fun `test infinity is not allowed`() {
        AssertThrows().also { test ->
            test that { BinarySize(NEGATIVE_INFINITY) } threw IllegalArgumentException::class withMessage "Failed requirement."
            test that { BinarySize(POSITIVE_INFINITY) } threw IllegalArgumentException::class withMessage "Failed requirement."
        }
    }

    @Test
    fun `test zero is zero`() {
        AssertEquals().also { test ->
            test that BinarySize(0.0) isEqualTo BinarySize.ZERO
            test that 0.kibibytes isEqualTo BinarySize.ZERO
            test that 0.0.kibibytes isEqualTo BinarySize.ZERO
        }
    }

    @Test
    fun `test sorting`() {
        val sizes = listOf(
            1.kibibyte,
            BinarySize(-1.0),
            BinarySize.ZERO,
            2.mebibytes
        )

        AssertEquals().also { test ->
            test that sizes.sorted() isEqualTo listOf(
                BinarySize(-1.0),
                BinarySize(0.0),
                BinarySize(1024.0),
                BinarySize(2_097_152.0)
            )
        }
    }

    @Test
    fun `test unary operators`() {
        AssertEquals().also { test ->
            test that -(1.kibibyte).value isEqualTo -1024.0
            test that -(1.byteBinary).value isEqualTo -1.0
            test that -BinarySize.ZERO.value isEqualTo -0.0
        }
    }

    @Test
    fun `test addition`() {
        AssertEquals().also { test ->
            test that 1.kibibyte + 1.kibibyte isEqualTo 2.kibibytes
            test that 1.byteBinary + 1.kibibyte isEqualTo 1025.bytesBinary
            test that 2.gibibytes - 1024.mebibytes isEqualTo 1.gibibyte
            test that 10.bytesBinary - (-5).bytesBinary isEqualTo 15.bytesBinary
            test that 0.yobibytes + 10.bytesBinary isEqualTo 10.bytesBinary
            test that 1.kibibyte + (-10).bytesBinary isEqualTo 1014.bytesBinary
            test that BinarySize(1.0) + BinarySize(1023.0) isEqualTo 1.kibibyte
        }
    }

    @Test
    fun `test multiplication`() {
        AssertEquals().also { test ->
            test that 1.kibibyte * 2 isEqualTo 2.kibibytes
            test that 1.byteBinary * 2048 isEqualTo 2.kibibytes
            test that 2.kibibytes * 5.0 isEqualTo 10.0.kibibytes
            test that 1.exbibyte * 1024 isEqualTo 1.zebibyte
            test that BinarySize(1024.0) * 20 isEqualTo 20.kibibytes
            test that BinarySize(1024.0) * BinarySize(1024.0) isEqualTo 1.mebibyte
            test that 2.mebibytes * 524_288.0.kibibytes isEqualTo 1.pebibyte
        }
    }

    @Test
    fun `test division`() {
        AssertEquals().also { test ->
            test that 2048.byteBinary / 2 isEqualTo 1.kibibyte
            test that 1024.pebibytes / 8.0 isEqualTo 128.pebibytes
            test that 1.gibibyte / 1.kibibytes isEqualTo 1.mebibyte
            test that BinarySize(10240.0) / BinarySize(10.0) isEqualTo 1.kibibyte
            test that (-10).kibibytes * 10.kibibytes isEqualTo (-100).mebibyte
        }
    }

    @Test
    fun `test negative vs positive`() {
        AssertBool().also { test ->
            test that 1.mebibyte.isPositive() shouldBe true
            test that 1.0.kibibyte.isPositive() shouldBe true
            test that (-1).mebibyte.isPositive() shouldBe false
            test that (-1.0).kibibyte.isPositive() shouldBe false

            test that 1.mebibyte.isNegative() shouldBe false
            test that 1.0.kibibyte.isNegative() shouldBe false
            test that (-1).mebibyte.isNegative() shouldBe true
            test that (-1.0).kibibyte.isNegative() shouldBe true
        }
    }

    @Test
    fun `test absolute value`() {
        AssertEquals().also { test ->
            test that 1.mebibyte.absoluteValue isEqualTo (-1).mebibyte.absoluteValue
        }
    }

    @Test
    fun `test primitive extensions`() {
        AssertEquals().also { test ->
            test that 1.yobibyte.inYobibytes isEqualTo 1.0
            test that 1.zebibyte.inZebibytes isEqualTo 1.0
            test that 1.exbibyte.inExbibytes isEqualTo 1.0
            test that 1.pebibyte.inPebibytes isEqualTo 1.0
            test that 1.tebibyte.inTebibytes isEqualTo 1.0
            test that 1.gibibyte.inGibibytes isEqualTo 1.0
            test that 1.mebibyte.inMebibytes isEqualTo 1.0
            test that 1.kibibyte.inKibibytes isEqualTo 1.0
            test that 1.byteBinary.inBytes         isEqualTo 1.0

            test that 2.yobibytes.inYobibytes isEqualTo 2.0
            test that 2.zebibytes.inZebibytes isEqualTo 2.0
            test that 2.exbibytes.inExbibytes isEqualTo 2.0
            test that 2.pebibytes.inPebibytes isEqualTo 2.0
            test that 2.tebibytes.inTebibytes isEqualTo 2.0
            test that 2.gibibytes.inGibibytes isEqualTo 2.0
            test that 2.mebibytes.inMebibytes isEqualTo 2.0
            test that 2.kibibytes.inKibibytes isEqualTo 2.0
            test that 2.bytesBinary.inBytes         isEqualTo 2.0

            test that 1L.yobibyte.inYobibytes isEqualTo 1.0
            test that 1L.zebibyte.inZebibytes isEqualTo 1.0
            test that 1L.exbibyte.inExbibytes isEqualTo 1.0
            test that 1L.pebibyte.inPebibytes isEqualTo 1.0
            test that 1L.tebibyte.inTebibytes isEqualTo 1.0
            test that 1L.gibibyte.inGibibytes isEqualTo 1.0
            test that 1L.mebibyte.inMebibytes isEqualTo 1.0
            test that 1L.kibibyte.inKibibytes isEqualTo 1.0
            test that 1L.byteBinary.inBytes         isEqualTo 1.0

            test that 2L.yobibytes.inYobibytes isEqualTo 2.0
            test that 2L.zebibytes.inZebibytes isEqualTo 2.0
            test that 2L.exbibytes.inExbibytes isEqualTo 2.0
            test that 2L.pebibytes.inPebibytes isEqualTo 2.0
            test that 2L.tebibytes.inTebibytes isEqualTo 2.0
            test that 2L.gibibytes.inGibibytes isEqualTo 2.0
            test that 2L.mebibytes.inMebibytes isEqualTo 2.0
            test that 2L.kibibytes.inKibibytes isEqualTo 2.0
            test that 2L.bytesBinary.inBytes         isEqualTo 2.0

            test that 1.0.yobibyte.inYobibytes isEqualTo 1.0
            test that 1.0.zebibyte.inZebibytes isEqualTo 1.0
            test that 1.0.exbibyte.inExbibytes isEqualTo 1.0
            test that 1.0.pebibyte.inPebibytes isEqualTo 1.0
            test that 1.0.tebibyte.inTebibytes isEqualTo 1.0
            test that 1.0.gibibyte.inGibibytes isEqualTo 1.0
            test that 1.0.mebibyte.inMebibytes isEqualTo 1.0
            test that 1.0.kibibyte.inKibibytes isEqualTo 1.0
            test that 1.0.byteBinary.inBytes         isEqualTo 1.0

            test that 2.0.yobibytes.inYobibytes isEqualTo 2.0
            test that 2.0.zebibytes.inZebibytes isEqualTo 2.0
            test that 2.0.exbibytes.inExbibytes isEqualTo 2.0
            test that 2.0.pebibytes.inPebibytes isEqualTo 2.0
            test that 2.0.tebibytes.inTebibytes isEqualTo 2.0
            test that 2.0.gibibytes.inGibibytes isEqualTo 2.0
            test that 2.0.mebibytes.inMebibytes isEqualTo 2.0
            test that 2.0.kibibytes.inKibibytes isEqualTo 2.0
            test that 2.0.bytesBinary.inBytes         isEqualTo 2.0
        }
    }

    @Test
    fun `test size in other units`() {
        AssertEquals().also { test ->
            test that 1.yobibyte.inYobibytes isEqualTo 1.0
            test that 1.yobibyte.inZebibytes isEqualTo 1024.0
            test that 1.yobibyte.inExbibytes isEqualTo 1_048_576.0
            test that 1.yobibyte.inPebibytes isEqualTo 1_073_741_824.0
            test that 1.yobibyte.inTebibytes isEqualTo 1_099_511_627_776.0
            test that 1.yobibyte.inGibibytes isEqualTo 1_125_899_906_842_624.0
            test that 1.yobibyte.inMebibytes isEqualTo 1_152_921_504_606_846_976.0
            test that 1.yobibyte.inKibibytes isEqualTo 1_180_591_620_717_411_303_424.0
            test that 1.yobibyte.inBytes     isEqualTo 1_208_925_819_614_629_174_706_176.0

            test that 1.zebibyte.inYobibytes isEqualTo 9.765625E-4
            test that 1.zebibyte.inZebibytes isEqualTo 1.0
            test that 1.zebibyte.inExbibytes isEqualTo 1024.0
            test that 1.zebibyte.inPebibytes isEqualTo 1_048_576.0
            test that 1.zebibyte.inTebibytes isEqualTo 1_073_741_824.0
            test that 1.zebibyte.inGibibytes isEqualTo 1_099_511_627_776.0
            test that 1.zebibyte.inMebibytes isEqualTo 1_125_899_906_842_624.0
            test that 1.zebibyte.inKibibytes isEqualTo 1_152_921_504_606_846_976.0
            test that 1.zebibyte.inBytes     isEqualTo 1_180_591_620_717_411_303_424.0

            test that 1.exbibyte.inYobibytes isEqualTo 9.5367431640625E-7
            test that 1.exbibyte.inZebibytes isEqualTo 9.765625E-4
            test that 1.exbibyte.inExbibytes isEqualTo 1.0
            test that 1.exbibyte.inPebibytes isEqualTo 1024.0
            test that 1.exbibyte.inTebibytes isEqualTo 1_048_576.0
            test that 1.exbibyte.inGibibytes isEqualTo 1_073_741_824.0
            test that 1.exbibyte.inMebibytes isEqualTo 1_099_511_627_776.0
            test that 1.exbibyte.inKibibytes isEqualTo 1_125_899_906_842_624.0
            test that 1.exbibyte.inBytes     isEqualTo 1_152_921_504_606_846_976.0

            test that 1.pebibyte.inYobibytes isEqualTo 9.313225746154785E-10
            test that 1.pebibyte.inZebibytes isEqualTo 9.5367431640625E-7
            test that 1.pebibyte.inExbibytes isEqualTo 9.765625E-4
            test that 1.pebibyte.inPebibytes isEqualTo 1.0
            test that 1.pebibyte.inTebibytes isEqualTo 1024.0
            test that 1.pebibyte.inGibibytes isEqualTo 1_048_576.0
            test that 1.pebibyte.inMebibytes isEqualTo 1_073_741_824.0
            test that 1.pebibyte.inKibibytes isEqualTo 1_099_511_627_776.0
            test that 1.pebibyte.inBytes     isEqualTo 1_125_899_906_842_624.0

            test that 1.tebibyte.inYobibytes isEqualTo 9.094947017729282E-13
            test that 1.tebibyte.inZebibytes isEqualTo 9.313225746154785E-10
            test that 1.tebibyte.inExbibytes isEqualTo 9.5367431640625E-7
            test that 1.tebibyte.inPebibytes isEqualTo 9.765625E-4
            test that 1.tebibyte.inTebibytes isEqualTo 1.0
            test that 1.tebibyte.inGibibytes isEqualTo 1024.0
            test that 1.tebibyte.inMebibytes isEqualTo 1_048_576.0
            test that 1.tebibyte.inKibibytes isEqualTo 1_073_741_824.0
            test that 1.tebibyte.inBytes     isEqualTo 1_099_511_627_776.0

            test that 1.gibibytes.inYobibytes isEqualTo 8.881784197001252E-16
            test that 1.gibibytes.inZebibytes isEqualTo 9.094947017729282E-13
            test that 1.gibibytes.inExbibytes isEqualTo 9.313225746154785E-10
            test that 1.gibibytes.inPebibytes isEqualTo 9.5367431640625E-7
            test that 1.gibibytes.inTebibytes isEqualTo 9.765625E-4
            test that 1.gibibytes.inGibibytes isEqualTo 1.0
            test that 1.gibibytes.inMebibytes isEqualTo 1024.0
            test that 1.gibibytes.inKibibytes isEqualTo 1_048_576.0
            test that 1.gibibytes.inBytes     isEqualTo 1_073_741_824.0

            test that 1.mebibytes.inYobibytes isEqualTo 8.673617379884035E-19
            test that 1.mebibytes.inZebibytes isEqualTo 8.881784197001252E-16
            test that 1.mebibytes.inExbibytes isEqualTo 9.094947017729282E-13
            test that 1.mebibytes.inPebibytes isEqualTo 9.313225746154785E-10
            test that 1.mebibytes.inTebibytes isEqualTo 9.5367431640625E-7
            test that 1.mebibytes.inGibibytes isEqualTo 9.765625E-4
            test that 1.mebibytes.inMebibytes isEqualTo 1.0
            test that 1.mebibytes.inKibibytes isEqualTo 1024.0
            test that 1.mebibytes.inBytes     isEqualTo 1_048_576.0

            test that 1.kibibyte.inYobibytes isEqualTo 8.470329472543003E-22
            test that 1.kibibyte.inZebibytes isEqualTo 8.673617379884035E-19
            test that 1.kibibyte.inExbibytes isEqualTo 8.881784197001252E-16
            test that 1.kibibyte.inPebibytes isEqualTo 9.094947017729282E-13
            test that 1.kibibyte.inTebibytes isEqualTo 9.313225746154785E-10
            test that 1.kibibyte.inGibibytes isEqualTo 9.5367431640625E-7
            test that 1.kibibyte.inMebibytes isEqualTo 9.765625E-4
            test that 1.kibibyte.inKibibytes isEqualTo 1.0
            test that 1.kibibyte.inBytes     isEqualTo 1024.0

            test that 1.byteBinary.inYobibytes isEqualTo 8.271806125530277E-25
            test that 1.byteBinary.inZebibytes isEqualTo 8.470329472543003E-22
            test that 1.byteBinary.inExbibytes isEqualTo 8.673617379884035E-19
            test that 1.byteBinary.inPebibytes isEqualTo 8.881784197001252E-16
            test that 1.byteBinary.inTebibytes isEqualTo 9.094947017729282E-13
            test that 1.byteBinary.inGibibytes isEqualTo 9.313225746154785E-10
            test that 1.byteBinary.inMebibytes isEqualTo 9.5367431640625E-7
            test that 1.byteBinary.inKibibytes isEqualTo 9.765625E-4
            test that 1.byteBinary.inBytes     isEqualTo 1.0
        }
    }

    @Test
    fun `test to number`() {
        AssertEquals().also { test ->
            test that 1.kibibyte.toDouble(BinaryUnit.BYTE) isEqualTo 1024.0
            test that 1.kibibyte.toDouble(BinaryUnit.KIBIBYTE) isEqualTo 1.0
            test that 1.kibibyte.toLong(BinaryUnit.BYTE) isEqualTo 1024L
            test that 1.kibibyte.toLong(BinaryUnit.KIBIBYTE) isEqualTo 1L
            test that 1.kibibyte.toInt(BinaryUnit.BYTE) isEqualTo 1024
            test that 1.kibibyte.toInt(BinaryUnit.KIBIBYTE) isEqualTo 1
        }
    }

    @Test
    fun `test toString`() {
        AssertEquals().also { test ->
            println((1.yobibyte.inBytes.bytesBinary + 1_000_000.bytesBinary).value)

            test that 1.bytesBinary.toString()                               isEqualTo "1B"
            test that 1_000.bytesBinary.toString()                           isEqualTo "1000B"
            test that 1_024.bytesBinary.toString()                           isEqualTo "1KiB"
            test that 1_000_000.bytesBinary.toString()                       isEqualTo "976KiB 576B"
            test that 1_048_576.bytesBinary.toString()                       isEqualTo "1MiB"
            test that 1_000_000_000.bytesBinary.toString()                   isEqualTo "953MiB 690KiB 512B"
            test that 1_073_741_824.bytesBinary.toString()                   isEqualTo "1GiB"
            test that 1_000_000_000_000.bytesBinary.toString()               isEqualTo "931GiB 330MiB 324KiB"
            test that 1_099_511_627_776.bytesBinary.toString()               isEqualTo "1TiB"
            test that 1_000_000_000_000_000.bytesBinary.toString()           isEqualTo "909TiB 506GiB 588MiB 416KiB"
            test that 1_125_899_906_842_624.bytesBinary.toString()           isEqualTo "1PiB"
            test that 1_000_000_000_000_000_000.bytesBinary.toString()       isEqualTo "888PiB 182TiB 718GiB 630MiB 256KiB"
            test that 1_152_921_504_606_846_976.bytesBinary.toString()       isEqualTo "1EiB"
//            test that 1_000_000_000_000_000_000_000.0.bytes.toString() isEqualTo "888PiB 182TiB 718GiB 630MiB 256KiB"

//            // if remaining is larger than max Long, we just ignore it
//            test that (1.yobibyte.inBytes.bytes + 1_000_000.bytes).toString() isEqualTo "1YiB"
////            test that (1.yobibyte.inBytes.bytes + 1_000_000_000.bytes).toString() isEqualTo "1YiB"
//            test that (1.yobibyte.inBytes.bytes + 1_000_000_000_000.bytes).toString() isEqualTo "1YiB"
        }
    }
}
