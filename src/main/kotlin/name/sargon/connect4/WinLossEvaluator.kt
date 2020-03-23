package name.sargon.connect4

class WinLossEvaluator: Evaluator {

    override fun evaluationOf(board: Board): Int {
        if (board.isWonFor(board.sideToMove)) {
            return 100
        }

        if (board.isWonFor(board.sideToMove.opponent())) {
            return -100
        }

        return 0
    }

}
