package name.sargon.connect4

class RandomComputerPlayer : Player {

    private val moveGenerator = SimpleMoveGenerator()

    override fun nextMove(board: Board): Square {
        return moveGenerator.moves(board)
            .asSequence()
            .toList()
            .shuffled()
            .first()
    }

}
