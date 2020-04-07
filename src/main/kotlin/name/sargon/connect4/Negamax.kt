package name.sargon.connect4

import java.lang.Integer.max

class Negamax(
    private val evaluator: Evaluator,
    private val moveGenerator: MoveGenerator,
    private val updateNodeStatistics: () -> Unit
) : Search {

    override fun search(board: Board, alpha: Int, beta: Int, depth: Int): Int {
        updateNodeStatistics()

        var best = alpha

        if (depth == 0) {
            return evaluator.evaluationOf(board)
        }

        if (opponentHasWon(board)) {
            return -100
        }

        val moves = moveGenerator.moves(board)

        for (move in moves) {
            board.place(move)
            val score = -search(board, -beta, -best, depth - 1)
            board.unplace(move)

            if (score >= beta) {
                return beta
            }

            best = max(score, best)
        }

        return best
    }

    private fun opponentHasWon(board: Board): Boolean {
        return board.isWonFor(board.sideToMove.opponent())
    }

}
