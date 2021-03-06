package name.sargon.connect4

import name.sargon.connect4.Bitboards.FULL_BOARD
import name.sargon.connect4.Side.EQUES
import name.sargon.connect4.Side.KNOTT
import java.security.SecureRandom

class Board {

    val evalValues: IntArray = intArrayOf(
        3, 4, 5, 5, 4, 3, 0, 0,
        4, 6, 8, 8, 6, 4, 0, 0,
        5, 8, 11, 11, 8, 5, 0, 0,
        7, 10, 13, 13, 10, 7, 0, 0,
        5, 8, 11, 11, 8, 5, 0, 0,
        4, 6, 8, 8, 6, 4, 0, 0,
        3, 4, 5, 5, 4, 3, 0, 0
    )

    val equesZobristKeys: LongArray
    val knottZobristKeys: LongArray

    init {
        equesZobristKeys = randomLongs().toLongArray()
        knottZobristKeys = randomLongs().toLongArray()
    }

    private fun randomLongs(): List<Long> {
        val random = SecureRandom()
        return (1..64).map { random.nextLong() }
    }

    var equesPieces: Bitboard = Bitboard()
    var knottPieces: Bitboard = Bitboard()
    var sideToMove: Side = EQUES
    var equesEval = 0
    var knottEval = 0
    var hash: Long = 0

    fun doMove(square: Square) {
        assert(!equesPieces.get(square))
        assert(!knottPieces.get(square))

        when (sideToMove) {
            EQUES -> doMoveForEques(square)
            KNOTT -> doMoveForKnott(square)
        }

        sideToMove = sideToMove.opponent()

        assert(equesPieces == equesPieces.and(FULL_BOARD))
        assert(knottPieces == knottPieces.and(FULL_BOARD))
    }

    private fun doMoveForEques(square: Square) {
        equesPieces = equesPieces.set(square)
        equesEval += evalValues[square]
        hash = hash xor equesZobristKeys[square]
    }

    private fun doMoveForKnott(square: Square) {
        knottPieces = knottPieces.set(square)
        knottEval += evalValues[square]
        hash = hash xor knottZobristKeys[square]
    }

    fun undoMove(square: Square) {
        assert(sideToMove == EQUES || equesPieces.get(square))
        assert(sideToMove == KNOTT || knottPieces.get(square))

        when (sideToMove) {
            EQUES -> undoMoveForKnott(square)
            KNOTT -> undoMoveForEques(square)
        }

        sideToMove = sideToMove.opponent()
    }

    private fun undoMoveForEques(square: Square) {
        equesPieces = equesPieces.cleared(square)
        equesEval -= evalValues[square]
        hash = hash xor equesZobristKeys[square]
    }

    private fun undoMoveForKnott(square: Square) {
        knottPieces = knottPieces.cleared(square)
        knottEval -= evalValues[square]
        hash = hash xor knottZobristKeys[square]
    }

    private fun sideAt(square: Square): Side? {
        if (equesPieces.get(square)) {
            return EQUES
        } else if (knottPieces.get(square)) {
            return KNOTT
        } else {
            return null
        }
    }

    fun willWinForSideAt(side: Side, square: Square): Boolean {
        assert(sideAt(square) == null)

        return when (side) {
            EQUES -> equesPieces.set(square).isWon()
            KNOTT -> knottPieces.set(square).isWon()
        }
    }

    fun isWonAt(square: Square): Boolean {
        return when (sideAt(square)) {
            EQUES -> equesPieces.isWon()
            KNOTT -> knottPieces.isWon()
            null -> false
        }
    }

    fun isWonFor(side: Side): Boolean {
        return when (side) {
            EQUES -> equesPieces.isWon()
            KNOTT -> knottPieces.isWon()
        }
    }

    fun moves(): Bitboard {
        val occupied = equesPieces.or(knottPieces)
        val oneUp = occupied.value.shl(1) or Bitboards.RANK_1.value
        val potentialMoves = occupied.value xor oneUp
        val moves = potentialMoves and FULL_BOARD.value

        return Bitboard(moves)
    }

    override fun toString(): String {
        val str = StringBuilder()

        for (y in 5.downTo(0)) {
            str.append(y + 1)

            for (x in 0..6) {
                val square = (x * 8) + y
                str.append(" ")
                str.append(sideAt(square) ?: ".")
            }

            str.append(" " + (y + 1) + "\n")
        }

        str.append("  a b c d e f g\n")

        if (equesPieces.isWon() || knottPieces.isWon()) {
            val opponent = sideToMove.opponent()
            str.append("\n $opponent has won!\n")
        } else if (moves().isEmpty()) {
            str.append("\n Game is drawn!\n")
        } else {
            str.append("\n $sideToMove to move\n")
        }

        return str.toString()
    }

}
