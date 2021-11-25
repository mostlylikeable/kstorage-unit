package mostlylikeable.kotlin.storage

import kotlin.test.Test

class DecimalSizeTest {

//    infix fun test(block: AssertThrows.() -> Unit) {
//        AssertThrows().apply(block)
////        block()
//    }

    @Test
    fun `test NaN is not allowed`() {
        AssertThrows().also { test ->
            test that { DecimalSize(Double.NaN) } threw IllegalArgumentException::class withMessage "Failed requirement."
        }
    }

    @Test
    fun `test zero is zero`() {
        AssertEquals().also { test ->
            test that DecimalSize(0.0) isEqualTo DecimalSize.ZERO
            test that 0.kilobytes isEqualTo DecimalSize.ZERO
            test that 0.0.kilobytes isEqualTo DecimalSize.ZERO
        }
    }

    @Test
    fun `test sorting`() {
        val sizes = listOf(
            1.kilobyte,
            DecimalSize(-1.0),
            DecimalSize.ZERO,
            2.megabytes
        )

        AssertEquals().also { test ->
            test that sizes.sorted() isEqualTo listOf(
                DecimalSize(-1.0),
                DecimalSize(0.0),
                DecimalSize(1_000.0),
                DecimalSize(2_000_000.0)
            )
        }
    }

    @Test
    fun `test unary operators`() {
        AssertEquals().also { test ->
            test that -(1.kilobyte).value isEqualTo -1000.0
            test that -(1.byteDecimal).value isEqualTo -1.0
            test that -DecimalSize.ZERO.value isEqualTo -0.0
        }
    }

    @Test
    fun `test addition`() {
        AssertEquals().also { test ->
            test that 1.kilobyte + 1.kilobyte isEqualTo 2.kilobytes
            test that 1.byteDecimal + 1.kilobyte isEqualTo 1001.bytesDecimal
            test that 2.gigabytes - 1000.megabytes isEqualTo 1.gigabyte
            test that 10.bytesDecimal - (-5).bytesDecimal isEqualTo 15.bytesDecimal
            test that 0.yottabytes + 10.bytesDecimal isEqualTo 10.bytesDecimal
            test that 1.kilobyte + (-10).bytesDecimal isEqualTo 990.bytesDecimal
            test that DecimalSize(1.0) + DecimalSize(999.0) isEqualTo 1.kilobyte
        }
    }

    @Test
    fun `test multiplication`() {
        AssertEquals().also { test ->
            test that 1.kilobyte * 2 isEqualTo 2.kilobytes
            test that 1.byteDecimal * 2000 isEqualTo 2.kilobytes
            test that 2.kilobytes * 5.0 isEqualTo 10.0.kilobytes
            test that 1.exabyte * 1000 isEqualTo 1.zettabyte
            test that DecimalSize(1000.0) * 20 isEqualTo 20.kilobytes
            test that DecimalSize(1000.0) * DecimalSize(1000.0) isEqualTo 1.megabyte
            test that 2.megabytes * 500_000.0.kilobytes isEqualTo 1.petabyte
        }
    }

    @Test
    fun `test division`() {
        AssertEquals().also { test ->
            test that 2000.byteDecimal / 2 isEqualTo 1.kilobyte
            test that 1000.petabytes / 10.0 isEqualTo 100.petabytes
            test that 1.gigabyte / 1.kilobytes isEqualTo 1.megabyte
            test that DecimalSize(10000.0) / DecimalSize(10.0) isEqualTo 1.kilobyte
            test that (-10).kilobytes * 10.kilobytes isEqualTo (-100).megabyte
        }
    }

    @Test
    fun `test negative vs positive`() {
        AssertBool().also { test ->
            test that 1.megabyte.isPositive() shouldBe true
            test that 1.0.kilobyte.isPositive() shouldBe true
            test that (-1).megabyte.isPositive() shouldBe false
            test that (-1.0).kilobyte.isPositive() shouldBe false

            test that 1.megabyte.isNegative() shouldBe false
            test that 1.0.kilobyte.isNegative() shouldBe false
            test that (-1).megabyte.isNegative() shouldBe true
            test that (-1.0).kilobyte.isNegative() shouldBe true
        }
    }

    @Test
    fun `test absolute value`() {
        AssertEquals().also { test ->
            test that 1.megabyte.absoluteValue isEqualTo (-1).megabyte.absoluteValue
        }
    }

    @Test
    fun `test primitive extensions`() {
        AssertEquals().also { test ->
            test that 1.yottabyte.inYottabytes isEqualTo 1.0
            test that 1.zettabyte.inZettabytes isEqualTo 1.0
            test that 1.exabyte.inExabytes     isEqualTo 1.0
            test that 1.petabyte.inPetabytes   isEqualTo 1.0
            test that 1.terabyte.inTerabytes   isEqualTo 1.0
            test that 1.gigabyte.inGigabytes   isEqualTo 1.0
            test that 1.megabyte.inMegabytes   isEqualTo 1.0
            test that 1.kilobyte.inKilobytes   isEqualTo 1.0
            test that 1.byteDecimal.inBytes    isEqualTo 1.0

            test that 2.yottabytes.inYottabytes isEqualTo 2.0
            test that 2.zettabytes.inZettabytes isEqualTo 2.0
            test that 2.exabytes.inExabytes     isEqualTo 2.0
            test that 2.petabytes.inPetabytes   isEqualTo 2.0
            test that 2.terabytes.inTerabytes   isEqualTo 2.0
            test that 2.gigabytes.inGigabytes   isEqualTo 2.0
            test that 2.megabytes.inMegabytes   isEqualTo 2.0
            test that 2.kilobytes.inKilobytes   isEqualTo 2.0
            test that 2.bytesDecimal.inBytes    isEqualTo 2.0

            test that 1L.yottabyte.inYottabytes isEqualTo 1.0
            test that 1L.zettabyte.inZettabytes isEqualTo 1.0
            test that 1L.exabyte.inExabytes     isEqualTo 1.0
            test that 1L.petabyte.inPetabytes   isEqualTo 1.0
            test that 1L.terabyte.inTerabytes   isEqualTo 1.0
            test that 1L.gigabyte.inGigabytes   isEqualTo 1.0
            test that 1L.megabyte.inMegabytes   isEqualTo 1.0
            test that 1L.kilobyte.inKilobytes   isEqualTo 1.0
            test that 1L.byteDecimal.inBytes    isEqualTo 1.0

            test that 2L.yottabytes.inYottabytes isEqualTo 2.0
            test that 2L.zettabytes.inZettabytes isEqualTo 2.0
            test that 2L.exabytes.inExabytes     isEqualTo 2.0
            test that 2L.petabytes.inPetabytes   isEqualTo 2.0
            test that 2L.terabytes.inTerabytes   isEqualTo 2.0
            test that 2L.gigabytes.inGigabytes   isEqualTo 2.0
            test that 2L.megabytes.inMegabytes   isEqualTo 2.0
            test that 2L.kilobytes.inKilobytes   isEqualTo 2.0
            test that 2L.bytesDecimal.inBytes    isEqualTo 2.0

            test that 1.0.yottabyte.inYottabytes isEqualTo 1.0
            test that 1.0.zettabyte.inZettabytes isEqualTo 1.0
            test that 1.0.exabyte.inExabytes     isEqualTo 1.0
            test that 1.0.petabyte.inPetabytes   isEqualTo 1.0
            test that 1.0.terabyte.inTerabytes   isEqualTo 1.0
            test that 1.0.gigabyte.inGigabytes   isEqualTo 1.0
            test that 1.0.megabyte.inMegabytes   isEqualTo 1.0
            test that 1.0.kilobyte.inKilobytes   isEqualTo 1.0
            test that 1.0.byteDecimal.inBytes    isEqualTo 1.0

            test that 2.0.yottabytes.inYottabytes isEqualTo 2.0
            test that 2.0.zettabytes.inZettabytes isEqualTo 2.0
            test that 2.0.exabytes.inExabytes     isEqualTo 2.0
            test that 2.0.petabytes.inPetabytes   isEqualTo 2.0
            test that 2.0.terabytes.inTerabytes   isEqualTo 2.0
            test that 2.0.gigabytes.inGigabytes   isEqualTo 2.0
            test that 2.0.megabytes.inMegabytes   isEqualTo 2.0
            test that 2.0.kilobytes.inKilobytes   isEqualTo 2.0
            test that 2.0.bytesDecimal.inBytes    isEqualTo 2.0
        }
    }

    @Test
    fun `test size in other units`() {
        // FIXME: commented out cases suffer from precision loss

        AssertEquals().also { test ->
            test that 1.yottabyte.inYottabytes isEqualTo 1.0
            test that 1.yottabyte.inZettabytes isEqualTo 1_000.0
            test that 1.yottabyte.inExabytes   isEqualTo 1_000_000.0
            test that 1.yottabyte.inPetabytes  isEqualTo 1_000_000_000.0
            test that 1.yottabyte.inTerabytes  isEqualTo 1_000_000_000_000.0
            test that 1.yottabyte.inGigabytes  isEqualTo 1_000_000_000_000_000.0
            test that 1.yottabyte.inMegabytes  isEqualTo 1_000_000_000_000_000_000.0
            test that 1.yottabyte.inKilobytes  isEqualTo 1_000_000_000_000_000_000_000.0
            test that 1.yottabyte.inBytes      isEqualTo 1_000_000_000_000_000_000_000_000.0

            test that 1.zettabyte.inYottabytes isEqualTo 0.001
            test that 1.zettabyte.inZettabytes isEqualTo 1.0
            test that 1.zettabyte.inExabytes   isEqualTo 1_000.0
            test that 1.zettabyte.inPetabytes  isEqualTo 1_000_000.0
            test that 1.zettabyte.inTerabytes  isEqualTo 1_000_000_000.0
            test that 1.zettabyte.inGigabytes  isEqualTo 1_000_000_000_000.0
            test that 1.zettabyte.inMegabytes  isEqualTo 1_000_000_000_000_000.0
            test that 1.zettabyte.inKilobytes  isEqualTo 1_000_000_000_000_000_000.0
            test that 1.zettabyte.inBytes      isEqualTo 1_000_000_000_000_000_000_000.0

            test that 1.exabyte.inYottabytes isEqualTo 0.000001
            test that 1.exabyte.inZettabytes isEqualTo 0.001
            test that 1.exabyte.inExabytes   isEqualTo 1.0
            test that 1.exabyte.inPetabytes  isEqualTo 1_000.0
            test that 1.exabyte.inTerabytes  isEqualTo 1_000_000.0
            test that 1.exabyte.inGigabytes  isEqualTo 1_000_000_000.0
            test that 1.exabyte.inMegabytes  isEqualTo 1_000_000_000_000.0
            test that 1.exabyte.inKilobytes  isEqualTo 1_000_000_000_000_000.0
            test that 1.exabyte.inBytes      isEqualTo 1_000_000_000_000_000_000.0

            test that 1.petabyte.inYottabytes isEqualTo 0.000000001
            test that 1.petabyte.inZettabytes isEqualTo 0.000001
            test that 1.petabyte.inExabytes   isEqualTo 0.001
            test that 1.petabyte.inPetabytes  isEqualTo 1.0
            test that 1.petabyte.inTerabytes  isEqualTo 1_000.0
            test that 1.petabyte.inGigabytes  isEqualTo 1_000_000.0
            test that 1.petabyte.inMegabytes  isEqualTo 1_000_000_000.0
            test that 1.petabyte.inKilobytes  isEqualTo 1_000_000_000_000.0
            test that 1.petabyte.inBytes      isEqualTo 1_000_000_000_000_000.0

            test that 1.terabyte.inYottabytes isEqualTo 0.000000000001
            test that 1.terabyte.inZettabytes isEqualTo 0.000000001
            test that 1.terabyte.inExabytes   isEqualTo 0.000001
            test that 1.terabyte.inPetabytes  isEqualTo 0.001
            test that 1.terabyte.inTerabytes  isEqualTo 1.0
            test that 1.terabyte.inGigabytes  isEqualTo 1_000.0
            test that 1.terabyte.inMegabytes  isEqualTo 1_000_000.0
            test that 1.terabyte.inKilobytes  isEqualTo 1_000_000_000.0
            test that 1.terabyte.inBytes      isEqualTo 1_000_000_000_000.0

            test that 1.gigabytes.inYottabytes isEqualTo 0.000000000000001
            test that 1.gigabytes.inZettabytes isEqualTo 0.000000000001
            test that 1.gigabytes.inExabytes   isEqualTo 0.000000001
            test that 1.gigabytes.inPetabytes  isEqualTo 0.000001
            test that 1.gigabytes.inTerabytes  isEqualTo 0.001
            test that 1.gigabytes.inGigabytes  isEqualTo 1.0
            test that 1.gigabytes.inMegabytes  isEqualTo 1_000.0
            test that 1.gigabytes.inKilobytes  isEqualTo 1_000_000.0
            test that 1.gigabytes.inBytes      isEqualTo 1_000_000_000.0

            test that 1.megabytes.inYottabytes isEqualTo 0.000000000000000001
            test that 1.megabytes.inZettabytes isEqualTo 0.000000000000001
            test that 1.megabytes.inExabytes   isEqualTo 0.000000000001
            test that 1.megabytes.inPetabytes  isEqualTo 0.000000001
            test that 1.megabytes.inTerabytes  isEqualTo 0.000001
            test that 1.megabytes.inGigabytes  isEqualTo 0.001
            test that 1.megabytes.inMegabytes  isEqualTo 1.0
            test that 1.megabytes.inKilobytes  isEqualTo 1_000.0
            test that 1.megabytes.inBytes      isEqualTo 1_000_000.0

//            test that 1.kilobyte.inYottabytes isEqualTo 0.000000000000000000001
            test that 1.kilobyte.inZettabytes isEqualTo 0.000000000000000001
            test that 1.kilobyte.inExabytes   isEqualTo 0.000000000000001
            test that 1.kilobyte.inPetabytes  isEqualTo 0.000000000001
            test that 1.kilobyte.inTerabytes  isEqualTo 0.000000001
            test that 1.kilobyte.inGigabytes  isEqualTo 0.000001
            test that 1.kilobyte.inMegabytes  isEqualTo 0.001
            test that 1.kilobyte.inKilobytes  isEqualTo 1.0
            test that 1.kilobyte.inBytes      isEqualTo 1_000.0

//            test that 1.byteDecimal.inYottabytes isEqualTo 0.000000000000000000000001
            test that 1.byteDecimal.inZettabytes isEqualTo 0.000000000000000000001
            test that 1.byteDecimal.inExabytes   isEqualTo 0.000000000000000001
            test that 1.byteDecimal.inPetabytes  isEqualTo 0.000000000000001
            test that 1.byteDecimal.inTerabytes  isEqualTo 0.000000000001
            test that 1.byteDecimal.inGigabytes  isEqualTo 0.000000001
            test that 1.byteDecimal.inMegabytes  isEqualTo 0.000001
            test that 1.byteDecimal.inKilobytes  isEqualTo 0.001
            test that 1.byteDecimal.inBytes      isEqualTo 1.0
        }
    }

    @Test
    fun `test to number`() {
        AssertEquals().also { test ->
            test that 1.kilobyte.toDouble(DecimalUnit.BYTE) isEqualTo 1_000.0
            test that 1.kilobyte.toDouble(DecimalUnit.KILOBYTE) isEqualTo 1.0
            test that 1.kilobyte.toLong(DecimalUnit.BYTE) isEqualTo 1_000L
            test that 1.kilobyte.toLong(DecimalUnit.KILOBYTE) isEqualTo 1L
            test that 1.kilobyte.toInt(DecimalUnit.BYTE) isEqualTo 1_000
            test that 1.kilobyte.toInt(DecimalUnit.KILOBYTE) isEqualTo 1
        }
    }

    @Test
    fun `test toString`() {
        AssertEquals().also { test ->
            test that                             1.bytesDecimal.toString()   isEqualTo "1B"
            test that                         1_000.bytesDecimal.toString()   isEqualTo "1kB"
            test that                         1_024.bytesDecimal.toString()   isEqualTo "1kB 24B"
            test that                       976_576.bytesDecimal.toString()   isEqualTo "976kB 576B"
            test that                     1_000_000.bytesDecimal.toString()   isEqualTo "1MB"
            test that                   953_690_512.bytesDecimal.toString()   isEqualTo "953MB 690kB 512B"
            test that                 1_000_000_000.bytesDecimal.toString()   isEqualTo "1GB"
            test that               931_330_000_324.bytesDecimal.toString()   isEqualTo "931GB 330MB 324B"
            test that             1_000_000_000_000.bytesDecimal.toString()   isEqualTo "1TB"
            test that           909_506_588_123_416.bytesDecimal.toString()   isEqualTo "909TB 506GB 588MB 123kB 416B"
            test that         1_000_000_000_000_000.bytesDecimal.toString()   isEqualTo "1PB"
//            test that       888_182_718_630_256_333.bytesDecimal.toString()   isEqualTo "888PB 182TB 718GB 630MB 256kB 333B"
            test that     1_000_000_000_000_000_000.bytesDecimal.toString()   isEqualTo "1EB"
//            test that 1_000_000_000_000_000_000_000.0.bytesDecimal.toString() isEqualTo "888PiB 182TiB 718GiB 630MiB 256KiB"
        }
    }
}
