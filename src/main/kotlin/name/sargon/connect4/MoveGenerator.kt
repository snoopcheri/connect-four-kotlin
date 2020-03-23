package name.sargon.connect4

interface MoveGenerator {

    fun moves(board: Board): Bitboard

}
