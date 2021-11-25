package mostlylikeable.kotlin.storage

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.jvm.JvmInline

private inline val storedAsUnit get() = BinaryUnit.BYTE
private inline val unitScale get() = 1024

@JvmInline
value class BinarySize internal constructor(internal val value: Double) : Comparable<BinarySize> {

    init {
        require(this.value.isNaN().not())
        require(this.value.isInfinite().not())
    }

    companion object {
        /** A size equal to exactly 0 bytes */
        val ZERO: BinarySize = BinarySize(0.0)
    }

    override fun compareTo(other: BinarySize): Int = this.value.compareTo(other.value)

    // operators
    // todo: incr and decr?
    operator fun unaryMinus(): BinarySize = BinarySize(-this.value)
    operator fun inc(): BinarySize = BinarySize(this.value + 1)
    operator fun dec(): BinarySize = BinarySize(this.value - 1)

    operator fun plus(that: BinarySize): BinarySize = BinarySize(this.value + that.value)
    operator fun minus(other: BinarySize): BinarySize = BinarySize(this.value - other.value)

    operator fun times(multiplier: Int): BinarySize = BinarySize(this.value * multiplier)
    operator fun times(multiplier: Double): BinarySize = BinarySize(this.value * multiplier)
    operator fun times(other: BinarySize): BinarySize = BinarySize(this.value * other.value)

    operator fun div(divisor: Int): BinarySize = BinarySize(this.value / divisor)
    operator fun div(divisor: Double): BinarySize = BinarySize(this.value / divisor)
    operator fun div(other: BinarySize): BinarySize = BinarySize(this.value / other.value)

    fun isNegative(): Boolean = value < 0
    fun isPositive(): Boolean = value > 0

    val absoluteValue: BinarySize get() = if (isNegative()) -this else this
    val inBytes: Double get() = toDouble(BinaryUnit.BYTE)
    val inKibibytes: Double get() = toDouble(BinaryUnit.KIBIBYTE)
    val inMebibytes: Double get() = toDouble(BinaryUnit.MEBIBYTE)
    val inGibibytes: Double get() = toDouble(BinaryUnit.GIBIBYTE)
    val inTebibytes: Double get() = toDouble(BinaryUnit.TEBIBYTE)
    val inPebibytes: Double get() = toDouble(BinaryUnit.PEBIBYTE)
    val inExbibytes: Double get() = toDouble(BinaryUnit.EXBIBYTE)
    val inZebibytes: Double get() = toDouble(BinaryUnit.ZEBIBYTE)
    val inYobibytes: Double get() = toDouble(BinaryUnit.YOBIBYTE)

    fun toDouble(unit: BinaryUnit): Double = convertBinaryUnit(value, storedAsUnit, unit)
    fun toLong(unit: BinaryUnit): Long = toDouble(unit).toLong()
    fun toInt(unit: BinaryUnit): Int = toDouble(unit).toInt()

    @PublishedApi
    internal val zebibytesComponent: Long get() = (inZebibytes % unitScale).toLong()
    @PublishedApi
    internal val exbibytesComponent: Long get() = (inExbibytes % unitScale).toLong()
    @PublishedApi
    internal val pebibytesComponent: Long get() = (inPebibytes % unitScale).toLong()
    @PublishedApi
    internal val tebibytesComponent: Long get() = (inTebibytes % unitScale).toLong()
    @PublishedApi
    internal val gibibytesComponent: Long get() = (inGibibytes % unitScale).toLong()
    @PublishedApi
    internal val mebibytesComponent: Long get() = (inMebibytes % unitScale).toLong()
    @PublishedApi
    internal val kibibytesComponent: Long get() = (inKibibytes % unitScale).toLong()
    @PublishedApi
    internal val bytesComponent:     Long get() = (inBytes % unitScale).toLong()

    @OptIn(ExperimentalContracts::class)
    inline fun <T> toComponents(
        action: (
            yobibytes: Long,
            zebibytes: Long,
            exbibytes: Long,
            pebibytes: Long,
            tebibytes: Long,
            gibibytes: Long,
            mebibytes: Long,
            kibibytes: Long,
            bytes: Long
        ) -> T
    ): T {
        contract { callsInPlace(action, InvocationKind.EXACTLY_ONCE) }
        return action(
            toLong(BinaryUnit.YOBIBYTE),
            zebibytesComponent,
            exbibytesComponent,
            pebibytesComponent,
            tebibytesComponent,
            gibibytesComponent,
            mebibytesComponent,
            kibibytesComponent,
            bytesComponent
        )
    }

    override fun toString(): String = when {
        value == 0.0 -> "0B"
        else -> {
            val isNegative = isNegative()
            buildString {
                absoluteValue.apply {
                    toComponents { yobi, zebi, exbi, pebi, tebi, gibi, mebi, kibi, b ->
                        val nameToCount = mapOf(
                            BinaryUnit.YOBIBYTE.shortName() to yobi,
                            BinaryUnit.ZEBIBYTE.shortName() to zebi,
                            BinaryUnit.EXBIBYTE.shortName() to exbi,
                            BinaryUnit.PEBIBYTE.shortName() to pebi,
                            BinaryUnit.TEBIBYTE.shortName() to tebi,
                            BinaryUnit.GIBIBYTE.shortName() to gibi,
                            BinaryUnit.MEBIBYTE.shortName() to mebi,
                            BinaryUnit.KIBIBYTE.shortName() to kibi,
                            BinaryUnit.BYTE.shortName() to b,
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

fun Int.toBinarySize(unit: BinaryUnit): BinarySize = toDouble().toBinarySize(unit)
val Int.byteBinary get() = toBinarySize(BinaryUnit.BYTE)
val Int.bytesBinary get() = toBinarySize(BinaryUnit.BYTE)
val Int.kibibyte get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Int.kibibytes get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Int.mebibyte get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Int.mebibytes get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Int.gibibyte get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Int.gibibytes get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Int.tebibyte get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Int.tebibytes get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Int.pebibyte get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Int.pebibytes get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Int.exbibyte get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Int.exbibytes get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Int.zebibyte get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Int.zebibytes get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Int.yobibyte get() = toBinarySize(BinaryUnit.YOBIBYTE)
val Int.yobibytes get() = toBinarySize(BinaryUnit.YOBIBYTE)

fun Long.toBinarySize(unit: BinaryUnit): BinarySize = toDouble().toBinarySize(unit)
val Long.byteBinary get() = toBinarySize(BinaryUnit.BYTE)
val Long.bytesBinary get() = toBinarySize(BinaryUnit.BYTE)
val Long.kibibyte get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Long.kibibytes get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Long.mebibyte get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Long.mebibytes get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Long.gibibyte get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Long.gibibytes get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Long.tebibyte get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Long.tebibytes get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Long.pebibyte get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Long.pebibytes get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Long.exbibyte get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Long.exbibytes get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Long.zebibyte get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Long.zebibytes get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Long.yobibyte get() = toBinarySize(BinaryUnit.YOBIBYTE)
val Long.yobibytes get() = toBinarySize(BinaryUnit.YOBIBYTE)

fun Double.toBinarySize(unit: BinaryUnit): BinarySize = BinarySize(convertBinaryUnit(this, unit, storedAsUnit))
val Double.byteBinary get() = toBinarySize(BinaryUnit.BYTE)
val Double.bytesBinary get() = toBinarySize(BinaryUnit.BYTE)
val Double.kibibyte get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Double.kibibytes get() = toBinarySize(BinaryUnit.KIBIBYTE)
val Double.mebibyte get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Double.mebibytes get() = toBinarySize(BinaryUnit.MEBIBYTE)
val Double.gibibyte get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Double.gibibytes get() = toBinarySize(BinaryUnit.GIBIBYTE)
val Double.tebibyte get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Double.tebibytes get() = toBinarySize(BinaryUnit.TEBIBYTE)
val Double.pebibyte get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Double.pebibytes get() = toBinarySize(BinaryUnit.PEBIBYTE)
val Double.exbibyte get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Double.exbibytes get() = toBinarySize(BinaryUnit.EXBIBYTE)
val Double.zebibyte get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Double.zebibytes get() = toBinarySize(BinaryUnit.ZEBIBYTE)
val Double.yobibyte get() = toBinarySize(BinaryUnit.YOBIBYTE)
val Double.yobibytes get() = toBinarySize(BinaryUnit.YOBIBYTE)
