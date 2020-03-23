package name.sargon.connect4

interface Evaluator {

    fun evaluationOf(board: Board): Int

}
