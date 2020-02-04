package saper.gameLogic;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import saper.fileOperations.Points;
import saper.fileOperations.ScoreObject;
import saper.fileOperations.ScoreTop100List;

public class Matrix {

    private FieldObj[][] matrix;
    private int numberOfBombs;
    private int numberOfFields;
    private boolean[] bombFields;
    private GameState gameState;
    private long startTime;
    private int gameTime;
    private String playerRandomName;

    private int timeFormatReducer ;
    private String gameOverMessage;
    private String gameWinMessage ;
    private String gameMessage ;
    private int points;
    private  int col;
    private  int row;
    private ScoreTop100List top100;

    public Matrix(int col, int row, int numberOfBombs) {
        this.row = row;
        this.col = col;
        startTime = System.nanoTime();
        this.numberOfBombs = numberOfBombs;
        this.numberOfFields = (col) * (row);
        this.matrix = new FieldObj[row][col];
        gameState = GameState.PENDING;

        int playerNameLenght = (int) (Math.random() * 10) +3;
        playerRandomName = RandomStringUtils.random(1, true, false).toUpperCase()+RandomStringUtils.random(playerNameLenght, true, false).toLowerCase()+RandomStringUtils.random(2, false, true);

        top100 = new ScoreTop100List();
        top100.read();

        timeFormatReducer = 10000000;
        gameOverMessage = "GAME OVER";
        gameWinMessage = "YOU WIN";
        gameMessage = "WELCOME";

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

    public String getPlayerRandomName() {
        return playerRandomName;
    }

    public long getGameTime() {
        return gameTime;
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

    public void setPlayerRandomName(String playerRandomName) {
        if(!playerRandomName.equals(""))
        this.playerRandomName = playerRandomName;
    }

    public void setNeighbors() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int numberOfBombsInSurround = 0;
                try {
                    if (matrix[i - 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i - 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i - 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i + 1][j - 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i + 1][j].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
                try {
                    if (matrix[i + 1][j + 1].isBomb()) numberOfBombsInSurround++;
                } catch (ArrayIndexOutOfBoundsException ignored) { }
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
            this.gameTime = (int) ((System.nanoTime() - startTime)/timeFormatReducer);

            matrix[x][y].setBlownBomb(true);
            this.points = 0;
            gameMessage = gameOverMessage +" "+  this.points +"pts.";
            gameState = GameState.GAME_OVER;
        }
        openFieldInner(x, y);
        if (getNumberOfClosedFields() == numberOfBombs) {
            gameState = GameState.WIN;
            this.gameTime = (int) ((System.nanoTime() - startTime)/timeFormatReducer);
            this.points = Points.get(gameTime,col,row,numberOfBombs);
            openAll();
            gameMessage = gameWinMessage+" "+  this.points +"pts.";
            top100.read();
            top100.add(new ScoreObject(playerRandomName,new DateTime(),points,String.valueOf(gameTime)));
            top100.save();
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


