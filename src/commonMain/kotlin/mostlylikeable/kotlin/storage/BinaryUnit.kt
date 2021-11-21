package mostlylikeable.kotlin.storage

enum class BinaryUnit(internal val bytesPer: Double) {
    /**
     * Storage unit representing 1 Byte, which is 8 (2 ^ 3) bits.
     */
    BYTE(1.0),

    /**
     * Storage unit representing 1 KiB, which is 1024 bytes.
     */
    KIBIBYTE(1024.0),

    /**
     * Storage unit representing 1 MiB, which is 1024 ^ 2 bytes (1,048,576 bytes) .
     */
    MEBIBYTE(1_048_576.0),

    /**
     * Storage unit representing 1 GiB, which is 1024 ^ 3 bytes (1,073,741,824 bytes).
     */
    GIBIBYTE(1_073_741_824.0),

    /**
     * Storage unit representing 1 TiB, which is 1024 ^ 4 bytes (1,099,511,627,776 bytes).
     */
    TEBIBYTE(1_099_511_627_776.0),

    /**
     * Storage unit representing 1 PiB, which is 1024 ^ 5 bytes (1,125,899,906,842,624 bytes).
     */
    PEBIBYTE(1_125_899_906_842_624.0),

    /**
     * Storage unit representing 1 EiB, which is 1024 ^ 6 bytes (1,152,921,504,606,846,976 bytes).
     */
    EXBIBYTE(1_152_921_504_606_846_976.0),

    /**
     * Storage unit representing 1 ZiB, which is 1024 ^ 7 bytes (1,180,591,620,717,411,303,424 bytes).
     */
    ZEBIBYTE(1_180_591_620_717_411_303_424.0),

    /**
     * Storage unit representing 1 YiB, which is 1024 ^ 8 bytes (1,208,925,819,614,629,174,706,176 bytes).
     */
    YOBIBYTE(1_208_925_819_614_629_174_706_176.0)
}

internal fun convertBinaryUnit(value: Double, sourceUnit: BinaryUnit, targetUnit: BinaryUnit): Double {
    return when {
        targetUnit.bytesPer < sourceUnit.bytesPer -> value * (sourceUnit.bytesPer / targetUnit.bytesPer)
        targetUnit.bytesPer > sourceUnit.bytesPer -> value / (targetUnit.bytesPer / sourceUnit.bytesPer)
        else -> value
    }
}

fun BinaryUnit.shortName(): String = when (this) {
    BinaryUnit.BYTE -> "B"
    BinaryUnit.KIBIBYTE -> "KiB"
    BinaryUnit.MEBIBYTE -> "MiB"
    BinaryUnit.GIBIBYTE -> "GiB"
    BinaryUnit.TEBIBYTE -> "TiB"
    BinaryUnit.PEBIBYTE -> "PiB"
    BinaryUnit.EXBIBYTE -> "EiB"
    BinaryUnit.ZEBIBYTE -> "ZiB"
    BinaryUnit.YOBIBYTE -> "YiB"
}

