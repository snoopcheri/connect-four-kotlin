package name.sargon.connect4

class WeightedMoveGenerator: MoveGenerator {

    override fun moves(board: Board): Iterator<Int> {
        return WeightedBitIterator(board.moves())
    }

}
