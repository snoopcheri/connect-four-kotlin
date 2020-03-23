package name.sargon.connect4

enum class Side(val value: String) {

    EQUES("x"),
    KNOTT("o");

    fun opponent(): Side {
        return when (this) {
            EQUES -> KNOTT
            KNOTT -> EQUES
        }
    }

    override fun toString(): String {
        return value
    }

}
