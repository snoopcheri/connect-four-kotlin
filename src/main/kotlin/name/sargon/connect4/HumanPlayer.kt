package name.sargon.connect4

class HumanPlayer: Player {

    override fun nextMove(board: Board): Square {
        while(true) {
            print("Your move: ")
            val userInput = readLine()

            val choice: NamedSquare
            try {
                choice = NamedSquare.valueOf(userInput ?: "")
            } catch (ignored: IllegalArgumentException) {
                println("Error: $userInput is not a valid square!")
                continue
            }

            if (!board.moves().get(choice.value)) {
                println("Error: $choice is not a valid move!")
                continue
            }

            return choice.value
        }
    }

}
