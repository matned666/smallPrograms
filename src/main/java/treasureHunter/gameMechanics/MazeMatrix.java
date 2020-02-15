package treasureHunter.gameMechanics;

import treasureHunter.gameMechanics.player.Player;
import treasureHunter.jFrame.FieldType;

public class MazeMatrix {



    // main variables
    private int NUMBER_OF_TREASURES;
    private int NUMBER_OF_MONSTERS;
    private final int PLAYER_BASE_HP = 5;

    // other variables
    private Player player;
    private int row;
    private int column;
    private Field[][] matrix;
    private int startPosition;
    private int startPositionColumn;
    private int startPositionRow;
    private int numberOfFields;
    private int openFields = 1;
    private boolean[] monsterFields;
    private boolean[] treasureFields;
    private boolean isStackOverFlowError;

    // constructor and initializer
    public MazeMatrix(int column, int row, int rooms, int treasures ) {
        isStackOverFlowError = false;
        this.row = row;
        this.column = column;
        NUMBER_OF_TREASURES = treasures;
        NUMBER_OF_MONSTERS = rooms;
        matrix = new Field[row][column];
        numberOfFields = row * column;
        initialize();
        player = new Player(PLAYER_BASE_HP);
        player.setCol(startPositionRow);
        player.setRow(startPositionColumn);
        matrix[startPositionRow][startPositionColumn].setPlayer(true);
    }

    private void initialize() {
        generateStartPosition();
        generateMaze();
        generateMonsterLocations();
        generateTreasureLocations();
//        printMaze();

    }

    // getters and setters

    boolean isStackOverFlowError() {
        return isStackOverFlowError;
    }

    public int getNUMBER_OF_TREASURES() {
        return NUMBER_OF_TREASURES;
    }

    public Player getPlayer() {
        return player;
    }

    public Field[][] getMatrix() {
        return matrix;
    }

    int getStartPositionColumn() {
        return startPositionColumn;
    }

    int getStartPositionRow() {
        return startPositionRow;
    }

    void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
        getStartPositionIndex();
    }

    int getStartPosition() {
        return startPosition;
    }

    private void getStartPositionIndex() {
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (counter == startPosition) {
                    startPositionColumn = j;
                    startPositionRow = i;
                }
                counter++;
            }
        }

    }

    // generators  - maze and fields
    private void generateStartPosition() {
        startPosition = (int) (Math.random() * (column * row)) + 1;
        getStartPositionIndex();
    }

    private void generateMonsterLocations() {
        monsterFields = new boolean[numberOfFields];
        generateLocationsInner(NUMBER_OF_MONSTERS, monsterFields);
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (monsterFields[temp] && matrix[i][j].toFieldTypeByEntrance() != FieldType.NO_ENTRIES)
                    matrix[i][j].setMonster(true);
                temp++;
            }
        }
    }

    private void generateTreasureLocations() {
        treasureFields = new boolean[numberOfFields];
        generateLocationsInner(NUMBER_OF_TREASURES, treasureFields);
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (treasureFields[temp]) {
                    if (matrix[i][j].isOpened()) matrix[i][j].setTreasure(true);
                        else NUMBER_OF_TREASURES --;
                }
                temp++;
            }
        }
    }

    private void generateLocationsInner(int numberOfElements, boolean[] elementFields) {
        for (boolean el : elementFields) el = false;
        int temp;
        for (int i = 0; i < numberOfElements; i++) {
            temp = (int) (Math.random() * numberOfFields - 1);
            if (!elementFields[temp]) {
                elementFields[temp] = true;
            } else if (elementFields[temp]) {
                i -= 1;
            }
        }
    }

    private void generateMaze() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Field();
            }
        }
        matrix[startPositionRow][startPositionColumn] = new Field();
        matrix[startPositionRow][startPositionColumn].setOpen(true);
        matrix[startPositionRow][startPositionColumn].setFirst(true);
        generateMazeInner(startPositionRow, startPositionColumn);
    }

    private void generateMazeInner(int y, int x) {
        try {
            int randomDirection = (int) (Math.random() * (100)) + 1;
            if (openFields < (numberOfFields - 10)) {
                if (randomDirection <= 25 && (y - 1) >= 0) {
                    generateMazeInnerStatements(y,x,-1,0);
                } else if (randomDirection > 25 && randomDirection <= 50 && (x + 1) < matrix[y].length) {
                    generateMazeInnerStatements(y,x,0,1);
                } else if (randomDirection > 50 && randomDirection <= 75 && (y + 1) < matrix.length) {
                    generateMazeInnerStatements(y,x,1,0);
                } else if (randomDirection > 75 && randomDirection <= 100 && (x - 1) >= 0) {
                    generateMazeInnerStatements(y,x,0,-1);
                } else {
                    generateMazeInner(y, x);
                }
            }
        } catch (StackOverflowError ex) {
            isStackOverFlowError = true;
            System.out.println(" StackOverflow ");
        }
    }

    private void generateMazeInnerStatements(int y, int x, int yC, int xC){
        if (!matrix[y + yC][x + xC].isOpened()) {
            if(yC == -1 && xC == 0) {
                matrix[y][x].setTopNeighbour(true);
                matrix[y + yC][x + xC].setBottomNeighbour(true);
            }
            if(yC == 1 && xC == 0) {
                matrix[y][x].setBottomNeighbour(true);
                matrix[y + yC][x + xC].setTopNeighbour(true);
            }
            if(yC == 0 && xC == 1) {
                matrix[y][x].setRightNeighbour(true);
                matrix[y + yC][x + xC].setLeftNeighbour(true);
            }
            if(yC == 0 && xC == -1) {
                matrix[y][x].setLeftNeighbour(true);
                matrix[y + yC][x + xC].setRightNeighbour(true);
            }
            matrix[y + yC][x + xC].setOpen(true);
            openFields++;
        }

            generateMazeInner(y + yC, x + xC);

    }

    // maze console printer
    private void printMaze() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == startPositionRow && j == startPositionColumn) System.out.print("@");
                else System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }


}
