package ticTacToe;

public class TicTacToe {

    public static final int BOARD_SIZE = 3;

    private FieldStatus[][] board = new FieldStatus[BOARD_SIZE][BOARD_SIZE];
    private boolean isOTurn = false;

    public TicTacToe() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = FieldStatus.EMPTY;
            }
        }
    }

    public FieldStatus[][] getBoard() {
        return board;
    }

    public void setBoard(FieldStatus[][] board) {
        this.board = board;
    }

    public void setOTurn(boolean OTurn) {
        isOTurn = OTurn;
    }

    public FieldStatus getFieldStatus(int col, int row) {
        return board[row][col];
    }

    public void action(int col, int row) {

        GameResult result = checkResult();

        FieldStatus status = getFieldStatus(col, row);
        if (status != FieldStatus.EMPTY) {
            throw new IllegalStateException();
        }
        if (isOTurn) {
            board[row][col] = FieldStatus.O;
            isOTurn = false;
        } else {
            board[row][col] = FieldStatus.X;
            isOTurn = true;
        }
//        isOTurn = !isOTurn; // inna opcja
    }

    public void add(int col,int row,FieldStatus fieldStatus){
        board[row][col] = fieldStatus;
    }

    public void setAllEmpty(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = FieldStatus.EMPTY;
            }
        }
    }

    public GameResult checkResult() {

        for (int i = 0; i < BOARD_SIZE; i++) {
            FieldStatus col1 = getFieldStatus(0, i);
            FieldStatus col2 = getFieldStatus(1, i);
            FieldStatus col3 = getFieldStatus(2, i);
            if (col1 == FieldStatus.X && col2 == FieldStatus.X && col3 == FieldStatus.X) {
                return GameResult.PLAYER_X_WIN;
            }
            else if (col1 == FieldStatus.O && col2 == FieldStatus.O && col3 == FieldStatus.O) {
                return GameResult.PLAYER_O_WIN;
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            FieldStatus col1 = getFieldStatus(i, 0);
            FieldStatus col2 = getFieldStatus(i, 1);
            FieldStatus col3 = getFieldStatus(i, 2);
            if (col1 == FieldStatus.X && col2 == FieldStatus.X && col3 == FieldStatus.X) {
                return GameResult.PLAYER_X_WIN;
            }
            else if (col1 == FieldStatus.O && col2 == FieldStatus.O && col3 == FieldStatus.O) {
                return GameResult.PLAYER_O_WIN;
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            FieldStatus col1 = getFieldStatus(0, 0);
            FieldStatus col2 = getFieldStatus(1, 1);
            FieldStatus col3 = getFieldStatus(2, 2);
            if (col1 == FieldStatus.X && col2 == FieldStatus.X && col3 == FieldStatus.X) {
                return GameResult.PLAYER_X_WIN;
            }
            else if (col1 == FieldStatus.O && col2 == FieldStatus.O && col3 == FieldStatus.O) {
                return GameResult.PLAYER_O_WIN;
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            FieldStatus col1 = getFieldStatus(0, 2);
            FieldStatus col2 = getFieldStatus(1, 1);
            FieldStatus col3 = getFieldStatus(2, 0);
            if (col1 == FieldStatus.X && col2 == FieldStatus.X && col3 == FieldStatus.X) {
                return GameResult.PLAYER_X_WIN;
            }
            else if (col1 == FieldStatus.O && col2 == FieldStatus.O && col3 == FieldStatus.O) {
                return GameResult.PLAYER_O_WIN;
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(getFieldStatus(i,j).equals(FieldStatus.EMPTY)) {
                    return GameResult.PENDING;
                }
            }
        }

        return GameResult.DRAW;
    }
}
