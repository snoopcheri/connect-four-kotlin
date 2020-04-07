package name.sargon.connect4

import name.sargon.connect4.Side.EQUES
import name.sargon.connect4.Side.KNOTT
import kotlin.system.measureTimeMillis
import kotlin.time.milliseconds

class Game(private val eques: Player, private val knott: Player) {

    private val board = Board()

    fun play() {
        while (!gameOver()) {
            println(board)

            var move: Square = 0

            val duration = (measureTimeMillis {
                move = when (board.sideToMove) {
                    EQUES -> eques.nextMove(board)
                    KNOTT -> knott.nextMove(board)
                }
            }).milliseconds

            println("Duration: $duration")

            board.doMove(move)
        }

        println(board)
    }

    private fun gameOver(): Boolean {
        return board.equesPieces.isWon() or board.knottPieces.isWon() or board.moves().isEmpty()
    }

}
