package mostlylikeable.kotlin.storage

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.jvm.JvmInline

private inline val storedAsUnit get() = DecimalUnit.BYTE
private inline val unitScale get() = 1000

@JvmInline
value class DecimalSize internal constructor(internal val value: Double): Comparable<DecimalSize> {

    init {
        require(this.value.isNaN().not())
        require(this.value.isInfinite().not())
    }

    companion object {
        /** A size equal to exactly 0 bytes */
        val ZERO: DecimalSize = DecimalSize(0.0)

        fun convert(value: Double, sourceUnit: DecimalUnit, targetUnit: DecimalUnit): Double =
            convertDecimalUnit(value, sourceUnit, targetUnit)

        fun bytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun bytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun bytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)

        fun kilobytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.KILOBYTE)
        fun kilobytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.KILOBYTE)
        fun kilobytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.KILOBYTE)

        fun megabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.MEGABYTE)
        fun megabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.MEGABYTE)
        fun megabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.MEGABYTE)

        fun gigabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.GIGABYTE)
        fun gigabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.GIGABYTE)
        fun gigabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.GIGABYTE)

        fun terabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun terabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun terabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)

        fun petabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun petabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun petabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)

        fun exabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun exabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun exabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)

        fun zettabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun zettabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun zettabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)

        fun yottabytes(value: Int): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun yottabytes(value: Long): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
        fun yottabytes(value: Double): DecimalSize = value.toDecimalSize(DecimalUnit.BYTE)
    }

    override fun compareTo(other: DecimalSize): Int = this.value.compareTo(other.value)

    // operators
    operator fun unaryMinus(): DecimalSize = DecimalSize(-this.value)
    operator fun inc(): DecimalSize = DecimalSize(this.value + 1)
    operator fun dec(): DecimalSize = DecimalSize(this.value - 1)

    operator fun plus(that: DecimalSize): DecimalSize = DecimalSize(this.value + that.value)
    operator fun minus(other: DecimalSize): DecimalSize = DecimalSize(this.value - other.value)

    operator fun times(multiplier: Int): DecimalSize = DecimalSize(this.value * multiplier)
    operator fun times(multiplier: Double): DecimalSize = DecimalSize(this.value * multiplier)
    operator fun times(other: DecimalSize): DecimalSize = DecimalSize(this.value * other.value)

    operator fun div(divisor: Int): DecimalSize = DecimalSize(this.value / divisor)
    operator fun div(divisor: Double): DecimalSize = DecimalSize(this.value / divisor)
    operator fun div(other: DecimalSize): DecimalSize = DecimalSize(this.value / other.value)

    fun isNegative(): Boolean = value < 0
    fun isPositive(): Boolean = value > 0

    val absoluteValue: DecimalSize get() = if (isNegative()) -this else this
    val inBytes: Double get() = toDouble(DecimalUnit.BYTE)
    val inKilobytes: Double get() = toDouble(DecimalUnit.KILOBYTE)
    val inMegabytes: Double get() = toDouble(DecimalUnit.MEGABYTE)
    val inGigabytes: Double get() = toDouble(DecimalUnit.GIGABYTE)
    val inTerabytes: Double get() = toDouble(DecimalUnit.TERABYTE)
    val inPetabytes: Double get() = toDouble(DecimalUnit.PETABYTE)
    val inExabytes: Double get() = toDouble(DecimalUnit.EXABYTE)
    val inZettabytes: Double get() = toDouble(DecimalUnit.ZETTABYTE)
    val inYottabytes: Double get() = toDouble(DecimalUnit.YOTTABYTE)

    fun toDouble(unit: DecimalUnit): Double = convertDecimalUnit(value, storedAsUnit, unit)
    fun toLong(unit: DecimalUnit): Long = toDouble(unit).toLong()
    fun toInt(unit: DecimalUnit): Int = toDouble(unit).toInt()

    @PublishedApi
    internal val zettabytesComponent: Long get() = (inZettabytes % unitScale).toLong()
    @PublishedApi
    internal val exabytesComponent: Long get() = (inExabytes % unitScale).toLong()
    @PublishedApi
    internal val petabytesComponent: Long get() = (inPetabytes % unitScale).toLong()
    @PublishedApi
    internal val terabytesComponent: Long get() = (inTerabytes % unitScale).toLong()
    @PublishedApi
    internal val gigabytesComponent: Long get() = (inGigabytes % unitScale).toLong()
    @PublishedApi
    internal val megabytesComponent: Long get() = (inMegabytes % unitScale).toLong()
    @PublishedApi
    internal val kilobytesComponent: Long get() = (inKilobytes % unitScale).toLong()
    @PublishedApi
    internal val bytesComponent:     Long get() = (inBytes % unitScale).toLong()

    @OptIn(ExperimentalContracts::class)
    inline fun <T> toComponents(
        action: (
            yottabytes: Long,
            zettabytes: Long,
            exabytes: Long,
            petabytes: Long,
            terabytes: Long,
            gigabytes: Long,
            megabytes: Long,
            kilobytes: Long,
            bytes: Long
        ) -> T
    ): T {
        contract { callsInPlace(action, InvocationKind.EXACTLY_ONCE) }
        return action(
            toLong(DecimalUnit.YOTTABYTE),
            zettabytesComponent,
            exabytesComponent,
            petabytesComponent,
            terabytesComponent,
            gigabytesComponent,
            megabytesComponent,
            kilobytesComponent,
            bytesComponent
        )
    }

    override fun toString(): String = when {
        value == 0.0 -> "0B"
        else -> {
            val isNegative = isNegative()
            buildString {
                absoluteValue.apply {
                    toComponents { yotta, zetta, exa, peta, tera, giga, mega, kilo, b ->
                        val nameToCount = mapOf(
                            DecimalUnit.YOTTABYTE.shortName() to yotta,
                            DecimalUnit.ZETTABYTE.shortName() to zetta,
                            DecimalUnit.EXABYTE.shortName() to exa,
                            DecimalUnit.PETABYTE.shortName() to peta,
                            DecimalUnit.TERABYTE.shortName() to tera,
                            DecimalUnit.GIGABYTE.shortName() to giga,
                            DecimalUnit.MEGABYTE.shortName() to mega,
                            DecimalUnit.KILOBYTE.shortName() to kilo,
                            DecimalUnit.BYTE.shortName() to b,
                        )

                        if (isNegative) append('-')
                        var componentCount = 0
                        nameToCount.forEach { (shortName, count) ->
                            if (count > 0) {
                                if (componentCount++ > 0) append(' ')
                                append(count).append(shortName)
                            }
                        }
                        if (isNegative && componentCount > 1) insert(1, '(').append(')')
                    }
                }
            }
        }
    }
}

fun Int.toDecimalSize(unit: DecimalUnit): DecimalSize = toDouble().toDecimalSize(unit)
val Int.byteDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Int.bytesDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Int.kilobyte get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Int.kilobytes get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Int.megabyte get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Int.megabytes get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Int.gigabyte get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Int.gigabytes get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Int.terabyte get() = toDecimalSize(DecimalUnit.TERABYTE)
val Int.terabytes get() = toDecimalSize(DecimalUnit.TERABYTE)
val Int.petabyte get() = toDecimalSize(DecimalUnit.PETABYTE)
val Int.petabytes get() = toDecimalSize(DecimalUnit.PETABYTE)
val Int.exabyte get() = toDecimalSize(DecimalUnit.EXABYTE)
val Int.exabytes get() = toDecimalSize(DecimalUnit.EXABYTE)
val Int.zettabyte get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Int.zettabytes get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Int.yottabyte get() = toDecimalSize(DecimalUnit.YOTTABYTE)
val Int.yottabytes get() = toDecimalSize(DecimalUnit.YOTTABYTE)

fun Long.toDecimalSize(unit: DecimalUnit): DecimalSize = toDouble().toDecimalSize(unit)
val Long.byteDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Long.bytesDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Long.kilobyte get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Long.kilobytes get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Long.megabyte get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Long.megabytes get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Long.gigabyte get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Long.gigabytes get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Long.terabyte get() = toDecimalSize(DecimalUnit.TERABYTE)
val Long.terabytes get() = toDecimalSize(DecimalUnit.TERABYTE)
val Long.petabyte get() = toDecimalSize(DecimalUnit.PETABYTE)
val Long.petabytes get() = toDecimalSize(DecimalUnit.PETABYTE)
val Long.exabyte get() = toDecimalSize(DecimalUnit.EXABYTE)
val Long.exabytes get() = toDecimalSize(DecimalUnit.EXABYTE)
val Long.zettabyte get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Long.zettabytes get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Long.yottabyte get() = toDecimalSize(DecimalUnit.YOTTABYTE)
val Long.yottabytes get() = toDecimalSize(DecimalUnit.YOTTABYTE)

fun Double.toDecimalSize(unit: DecimalUnit): DecimalSize = DecimalSize(convertDecimalUnit(this, unit, storedAsUnit))
val Double.byteDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Double.bytesDecimal get() = toDecimalSize(DecimalUnit.BYTE)
val Double.kilobyte get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Double.kilobytes get() = toDecimalSize(DecimalUnit.KILOBYTE)
val Double.megabyte get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Double.megabytes get() = toDecimalSize(DecimalUnit.MEGABYTE)
val Double.gigabyte get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Double.gigabytes get() = toDecimalSize(DecimalUnit.GIGABYTE)
val Double.terabyte get() = toDecimalSize(DecimalUnit.TERABYTE)
val Double.terabytes get() = toDecimalSize(DecimalUnit.TERABYTE)
val Double.petabyte get() = toDecimalSize(DecimalUnit.PETABYTE)
val Double.petabytes get() = toDecimalSize(DecimalUnit.PETABYTE)
val Double.exabyte get() = toDecimalSize(DecimalUnit.EXABYTE)
val Double.exabytes get() = toDecimalSize(DecimalUnit.EXABYTE)
val Double.zettabyte get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Double.zettabytes get() = toDecimalSize(DecimalUnit.ZETTABYTE)
val Double.yottabyte get() = toDecimalSize(DecimalUnit.YOTTABYTE)
val Double.yottabytes get() = toDecimalSize(DecimalUnit.YOTTABYTE)
