package name.sargon.connect4

import kotlin.random.Random

class RandomComputerPlayer: Player {

    override fun nextMove(board: Board): Square {
        val moves = board.moves()
        val nMoves = moves.countOnes()
        val chosenMoveNr = Random.nextInt(0, nMoves)

        var randomMove = moves

        repeat(chosenMoveNr - 1) {
            val nextOne = randomMove.nextOne()
            randomMove = randomMove.cleared(nextOne)
        }

        return randomMove.nextOne()
    }

}
