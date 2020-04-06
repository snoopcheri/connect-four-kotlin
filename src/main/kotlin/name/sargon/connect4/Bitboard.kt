package name.sargon.connect4

@Suppress("unused", "MemberVisibilityCanBePrivate")
object Bitboards {
    val FILE_A = Bitboard(0x0000000000003F)
    val FILE_B = Bitboard(0x00000000003F00)
    val FILE_C = Bitboard(0x000000003F0000)
    val FILE_D = Bitboard(0x0000003F000000)
    val FILE_E = Bitboard(0x00003F00000000)
    val FILE_F = Bitboard(0x003F0000000000)
    val FILE_G = Bitboard(0x3F000000000000)

    val RANK_1 = Bitboard(0x01010101010101)
    val RANK_2 = Bitboard(0x02020202020202)
    val RANK_3 = Bitboard(0x04040404040404)
    val RANK_4 = Bitboard(0x08080808080808)
    val RANK_5 = Bitboard(0x10101010101010)
    val RANK_6 = Bitboard(0x20202020202020)

    val FULL_BOARD = Bitboard(
        0L or
                RANK_1.value or
                RANK_2.value or
                RANK_3.value or
                RANK_4.value or
                RANK_5.value or
                RANK_6.value
    )

}

inline class Bitboard(val value: Long = 0): Iterable<Int> {

    fun countOnes(): Int = value.countOneBits()
    fun get(bit: Int): Boolean = (value and 1L.shl(bit)) != 0L

    fun set(bit: Int): Bitboard {
        return Bitboard(value or 1L.shl(bit))
    }

    fun cleared(bit: Int): Bitboard {
        return Bitboard(value and 1L.shl(bit).inv())
    }

    fun and(other: Bitboard): Bitboard {
        return Bitboard(value and other.value)
    }

    fun or(other: Bitboard): Bitboard {
        return Bitboard(value or other.value)
    }

    fun nextOne(): Int {
        val next = value.countTrailingZeroBits()
        assert(next in 0..63)

        return next
    }

    fun isEmpty(): Boolean {
        return value == 0L
    }

    override fun iterator(): Iterator<Int> {
        return BitIterator(this)
    }

    fun weightedIterator(): Iterator<Int> {
        return WeightedBitIterator(this)
    }
    /**
     * @see <a href="https://github.com/qu1j0t3/fhourstones/blob/master/Connect4.java#L96-L110">Algorithm</a>
     */
    fun isWon(): Boolean {
        // Check for horizontal win
        var t: Long = value and (value ushr 8)
        if (t and (t.ushr(2 * 8)) != 0L) {
            return true
        }

        // Check for vertical win
        t = value and (value ushr 1)
        if (t and (t.ushr(2 * 1)) != 0L) {
            return true
        }

        // Check for / diagonal win
        t = value and (value ushr 9)
        if (t and (t.ushr(2 * 9)) != 0L) {
            return true
        }

        // Check for \ diagonal win
        t = value and (value ushr 7)
        if (t and (t.ushr(2 * 7)) != 0L) {
            return true
        }

        return false
    }

    override fun toString(): String {
        val str = StringBuilder()

        for (y in 7.downTo(0)) {
            str.append(y + 1)

            for (x in 0..7) {
                val square = (x * 8) + y
                str.append(" ")

                if (isInRange(x, y)) {
                    str.append(if (this.get(square)) "*" else ".")
                } else {
                    str.append(if (this.get(square)) "#" else "-")
                }
            }

            str.append(" " + (y + 1) + "\n")
        }

        str.append("  a b c d e f g x\n")

        return str.toString()
    }

    private fun isInRange(x: Int, y: Int): Boolean {
        return x < 7 && y < 6
    }
}


class BitIterator(private var bitboard: Bitboard): Iterator<Int> {
    override fun hasNext(): Boolean {
        return !bitboard.isEmpty()
    }

    override fun next(): Int {
        val next = bitboard.nextOne()
        bitboard = bitboard.cleared(next)

        return next
    }

}


class WeightedBitIterator(bitboard: Bitboard): Iterator<Int> {
    private val weightedBits = IntArray(bitboard.countOnes())
    private var idx = 0

    init {
        val bits = BitIterator(bitboard)
        var i = 0
        while (bits.hasNext()) {
            weightedBits[i++] = bits.next()
        }

        sort(weightedBits)
    }

    override fun hasNext(): Boolean {
        return idx < weightedBits.size
    }

    override fun next(): Int {
        return weightedBits[idx++]
    }

}
