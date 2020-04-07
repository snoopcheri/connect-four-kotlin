package name.sargon.connect4


fun main() {
    val eques = HumanPlayer()
    val knott = RandomComputerPlayer()
    val game = Game(eques, knott)

    game.play()
}
