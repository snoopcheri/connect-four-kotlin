package name.sargon.connect4

class SimpleMoveGenerator : MoveGenerator {

    override fun moves(board: Board): Iterator<Int> {
        return BitIterator(board.moves())
    }

}
