package mostlylikeable.kotlin.storage

import kotlin.math.pow

private inline val base get() = 10.0

/**
 * The list of possible decimal storage units.
 */
enum class DecimalUnit(internal val power: Short) {
    /**
     * Storage unit representing 1 Byte.
     */
    BYTE(0),

    /**
     * Storage unit representing 1 KB, which is 1000 bytes.
     */
    KILOBYTE(3),

    /**
     * Storage unit representing 1 MB, which is 1000 ^ 3 bytes (1,000,000 bytes) .
     */
    MEGABYTE(6),

    /**
     * Storage unit representing 1 GB, which is 1000 ^ 6 bytes (1,000,000,000 bytes) .
     */
    GIGABYTE(9),

    /**
     * Storage unit representing 1 TB, which is 1000 ^ 9 bytes (1,000,000,000,000 bytes) .
     */
    TERABYTE(12),

    /**
     * Storage unit representing 1 PB, which is 1000 ^ 12 bytes (1,000,000,000,000,000 bytes) .
     */
    PETABYTE(15),

    /**
     * Storage unit representing 1 EB, which is 1000 ^ 15 bytes (1,000,000,000,000,000,000 bytes) .
     */
    EXABYTE(18),

    /**
     * Storage unit representing 1 ZB, which is 1000 ^ 18 bytes (1,000,000,000,000,000,000,000 bytes) .
     */
    ZETTABYTE(21),

    /**
     * Storage unit representing 1 YB, which is 1000 ^ 21 bytes (1,000,000,000,000,000,000,000,000 bytes) .
     */
    YOTTABYTE(24);
}

@Suppress("REDUNDANT_ELSE_IN_WHEN")
internal fun DecimalUnit.shortName(): String = when (this) {
    DecimalUnit.BYTE -> "B"
    DecimalUnit.KILOBYTE -> "kB"
    DecimalUnit.MEGABYTE -> "MB"
    DecimalUnit.GIGABYTE -> "GB"
    DecimalUnit.TERABYTE -> "TB"
    DecimalUnit.PETABYTE -> "PB"
    DecimalUnit.EXABYTE -> "EB"
    DecimalUnit.ZETTABYTE -> "ZB"
    DecimalUnit.YOTTABYTE -> "YB"
    else -> error("Unknown unit: $this")
}

internal fun decimalUnitFromShortName(shortName: String): DecimalUnit = when (shortName) {
    "B" -> DecimalUnit.BYTE
    "kB" -> DecimalUnit.KILOBYTE
    "MB" -> DecimalUnit.MEGABYTE
    "GB" -> DecimalUnit.GIGABYTE
    "TB" -> DecimalUnit.TERABYTE
    "PB" -> DecimalUnit.PETABYTE
    "EB" -> DecimalUnit.EXABYTE
    "ZB" -> DecimalUnit.ZETTABYTE
    "YB" -> DecimalUnit.YOTTABYTE
    else -> throw IllegalArgumentException("Unknown decimal unit short name: $shortName")
}

internal fun convertDecimalUnit(value: Double, sourceUnit: DecimalUnit, targetUnit: DecimalUnit): Double {
    val powerDiff = sourceUnit.power - targetUnit.power
    return when {
        powerDiff == 0 -> value
        powerDiff < 0 -> value / base.pow(-powerDiff)
        else -> value * base.pow(powerDiff)
    }
}

internal fun convertDecimalUnitToLongOverflow(value: Long, sourceUnit: DecimalUnit, targetUnit: DecimalUnit): Long {
    return convertDecimalUnit(value.toDouble(), sourceUnit, targetUnit).toLong()
}

internal fun convertDecimalUnitToLongCoerce(value: Long, sourceUnit: DecimalUnit, targetUnit: DecimalUnit): Long {
    if (sourceUnit == targetUnit) {
        return value
    }
    val powerDiff = targetUnit.power - sourceUnit.power
    val convertFactor = base.pow(powerDiff)
    val max = Long.MAX_VALUE / convertFactor
    return when {
        value > max -> Long.MAX_VALUE
        value < -max -> Long.MIN_VALUE
        else -> (value * convertFactor).toLong()
    }
}
