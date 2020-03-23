package name.sargon.connect4

interface Search {

    fun search(board: Board, alpha: Int, beta: Int, depth: Int): Int

}
