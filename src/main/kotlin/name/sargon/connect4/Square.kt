package name.sargon.connect4

typealias Square = Int

@Suppress("unused")
enum class NamedSquare(val value: Int) {

    A1(0 + 8 * 0),
    A2(1 + 8 * 0),
    A3(2 + 8 * 0),
    A4(3 + 8 * 0),
    A5(4 + 8 * 0),
    A6(5 + 8 * 0),

    B1(0 + 8 * 1),
    B2(1 + 8 * 1),
    B3(2 + 8 * 1),
    B4(3 + 8 * 1),
    B5(4 + 8 * 1),
    B6(5 + 8 * 1),

    C1(0 + 8 * 2),
    C2(1 + 8 * 2),
    C3(2 + 8 * 2),
    C4(3 + 8 * 2),
    C5(4 + 8 * 2),
    C6(5 + 8 * 2),

    D1(0 + 8 * 3),
    D2(1 + 8 * 3),
    D3(2 + 8 * 3),
    D4(3 + 8 * 3),
    D5(4 + 8 * 3),
    D6(5 + 8 * 3),

    E1(0 + 8 * 4),
    E2(1 + 8 * 4),
    E3(2 + 8 * 4),
    E4(3 + 8 * 4),
    E5(4 + 8 * 4),
    E6(5 + 8 * 4),

    F1(0 + 8 * 5),
    F2(1 + 8 * 5),
    F3(2 + 8 * 5),
    F4(3 + 8 * 5),
    F5(4 + 8 * 5),
    F6(5 + 8 * 5),

    G1(0 + 8 * 6),
    G2(1 + 8 * 6),
    G3(2 + 8 * 6),
    G4(3 + 8 * 6),
    G5(4 + 8 * 6),
    G6(5 + 8 * 6);

    companion object {
        private val values = values()
        fun getByValue(value: Int) = values.firstOrNull { it.value == value }
    }

}
