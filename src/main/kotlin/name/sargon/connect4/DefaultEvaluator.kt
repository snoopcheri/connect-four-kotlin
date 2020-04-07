package name.sargon.connect4

import name.sargon.connect4.Side.EQUES
import name.sargon.connect4.Side.KNOTT

class DefaultEvaluator : Evaluator {

    override fun evaluationOf(board: Board): Int {
        if (board.isWonFor(board.sideToMove)) {
            return 100
        }

        if (board.isWonFor(board.sideToMove.opponent())) {
            return -100
        }

        return when (board.sideToMove) {
            EQUES -> board.equesEval - board.knottEval
            KNOTT -> board.knottEval - board.equesEval
        }
    }

}
