package ticTacToe;

public enum FieldStatus {

    EMPTY,
    O,
    X;


    public String getText() {
        switch (this) {
            case EMPTY:
                return " ";
            case O:
                return "O";
            case X:
                return "X";
            default:
                throw new IllegalStateException("Nope");
        }
    }

}
