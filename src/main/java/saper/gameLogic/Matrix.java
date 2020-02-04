package saper.gameLogic;

public class Matrix {

    private FieldObj[][] matrix;
    private int numberOfBombs;
    private int numberOfFields;
    private boolean[] bombFields;
    private String gameMessage;
    private GameState gameState;

    public Matrix(int col, int row, int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
        this.numberOfFields = (col) * (row);
        this.matrix = new FieldObj[row][col];
        gameMessage = "WELCOME";
        gameState = GameState.PENDING;
        generateMatrix();
    }

    public int getNumberOfClosedFields() {
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!matrix[i][j].isOpen()) temp++;
            }
        }
        return temp;
    }


    public FieldObj[][] getMatrix() {
        return matrix;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setNeighbors() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int numberOfBombsInSurround = 0;
                try {
                    if (matrix[i - 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i - 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i - 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    if (matrix[i + 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                matrix[i][j].setNumberOfBombsInSurround(numberOfBombsInSurround);
            }
        }
    }

    public void openField(int x, int y) {
        if (matrix[x][y].isChecked()) {
            matrix[x][y].setChecked(false);
        }
        if (matrix[x][y].isBomb()) {
            openAll();
            matrix[x][y].setBlownBomb(true);
            gameMessage = "GAME OVER ";
            System.out.println("GAME OVER !!!");
            gameState = GameState.GAME_OVER;
        }
        openFieldInner(x, y);
        if (getNumberOfClosedFields() == numberOfBombs) {
            openAll();
            gameMessage = "YOU WIN";
            System.out.println("CONGRATULATIONS !!!");
            gameState = GameState.WIN;
        }
    }

    private void openFieldInner(int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[x].length && !matrix[x][y].isBomb() && !matrix[x][y].isOpen()) {
            matrix[x][y].setOpen(true);
            if (x > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x - 1, y);
            if (x > 0 && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x - 1, y - 1);
            if (x > 0 && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x - 1, y + 1);
            if (y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x, y - 1);
            if (y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x, y + 1);
            if (x < matrix.length && y > 0 && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x + 1, y - 1);
            if (x < matrix.length && matrix[x][y].getNumberOfBombsInSurround() == 0) openFieldInner(x + 1, y);
            if (x < matrix.length && y < matrix[x].length && matrix[x][y].getNumberOfBombsInSurround() == 0)
                openFieldInner(x + 1, y + 1);
        }
    }

    private void openAll() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j].setOpen(true);
            }
        }
    }

    private void generateRandomBombFields() {
        this.bombFields = new boolean[numberOfFields];
        for (boolean el : this.bombFields) el = false;
        int temp;
        for (int i = 0; i < numberOfBombs; i++) {
            temp = (int) (Math.random() * numberOfFields - 1);
            if (!this.bombFields[temp]) {
                this.bombFields[temp] = true;
            } else if (this.bombFields[temp]) {
                i -= 1;
            }
        }
    }

    private void generateMatrix() {
        generateRandomBombFields();
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new FieldObj(this.bombFields[counter], false);
                counter++;
            }
        }
        setNeighbors();
    }

}
