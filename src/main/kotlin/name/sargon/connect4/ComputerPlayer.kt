package name.sargon.connect4

var nodes = 0L

fun countNode() {
    nodes++
}

class ComputerPlayer : Player {

    private val moveGenerator = WeightedMoveGenerator()
    private val negamax = Negamax(DefaultEvaluator(), moveGenerator, ::countNode)

    override fun nextMove(board: Board): Square {
        var best = -1000
        var bestMove: Square = -1
        nodes = 0L

        val moves = moveGenerator.moves(board)
        for (move in moves) {
            board.doMove(move)
            val score = -negamax.search(board, -1000, +1000, 12)
            board.undoMove(move)

            val namedMove = NamedSquare.getByValue(move)
            println("-> move $namedMove has score $score")

            if (score > best) {
                best = score
                bestMove = move
            }
        }

        val bestNamedMove = NamedSquare.getByValue(bestMove)
        println("Best move $bestNamedMove with score $best and $nodes nodes")

        return bestMove
    }

}
