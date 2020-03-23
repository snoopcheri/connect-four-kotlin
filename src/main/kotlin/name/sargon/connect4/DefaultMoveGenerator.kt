package name.sargon.connect4

class DefaultMoveGenerator: MoveGenerator {

    override fun moves(board: Board): Bitboard {
        return board.moves()
    }

}
