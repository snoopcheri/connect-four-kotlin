package name.sargon.connect4


fun main() {
    val eques = HumanPlayer()
    val knott = ComputerPlayer()
    val game = Game(eques, knott)

    game.play()
}
