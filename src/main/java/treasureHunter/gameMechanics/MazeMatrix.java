package treasureHunter.gameMechanics;

import treasureHunter.gameMechanics.player.Player;
import treasureHunter.jFrame.FieldTypeByEntrance;

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

    // constructor and initializer
    public MazeMatrix(int column, int row, int rooms, int treasures ) {
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

    private void generateMonsterLocations() {
        monsterFields = new boolean[numberOfFields];
        generateLocationsInner(NUMBER_OF_MONSTERS, monsterFields);
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (monsterFields[temp] && matrix[i][j].toFieldTypeByEntrance() != FieldTypeByEntrance.NO_ENTRIES)
                    matrix[i][j].setRoom(true);
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
                        matrix[i][j].setTreasure(true);
                    if (!matrix[i][j].isOpened()) {
                        generateInner(i,j,false);
                        matrix[i][j].setOpen(true);
                    }
                }
                temp++;
            }
        }
    }

    private void generateInner(int y, int x, boolean isCorrection) {
        try {
            int randomDirection = (int) (Math.random() * (100)) + 1;
            if (openFields < (numberOfFields - 10)) {
                if (randomDirection <= 25 && (y - 1) >= 0) {
                    if (!matrix[y - 1][x].isOpened()) {
                        matrix[y][x].setTopNeighbour(true);
                        matrix[y - 1][x].setBottomNeighbour(true);
                        matrix[y - 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y - 1, x, isCorrection);

                } else if (randomDirection > 25 && randomDirection <= 50 && (x + 1) < matrix[y].length) {
                    if (!matrix[y][x + 1].isOpened()) {
                        matrix[y][x].setRightNeighbour(true);
                        matrix[y][x + 1].setLeftNeighbour(true);
                        matrix[y][x + 1].setOpen(true);
                        openFields++;
                    }
                    generateInner(y, x + 1, isCorrection);
                } else if (randomDirection > 50 && randomDirection <= 75 && (y + 1) < matrix.length) {
                    if (!matrix[y + 1][x].isOpened()) {
                        matrix[y][x].setBottomNeighbour(true);
                        matrix[y + 1][x].setTopNeighbour(true);
                        matrix[y + 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y + 1, x, isCorrection);
                } else if (randomDirection > 75 && randomDirection <= 100 && (x - 1) >= 0) {
                    if (!matrix[y][x - 1].isOpened()) {
                        matrix[y][x].setLeftNeighbour(true);
                        matrix[y][x - 1].setRightNeighbour(true);
                        matrix[y][x - 1].setOpen(true);
                        openFields++;
                    }
                     generateInner(y, x - 1, isCorrection);
                } else {
                    generateInner(y, x, isCorrection);
                }

            }
        } catch (StackOverflowError ex) {
            System.out.println(" * StackOverflow * ");
        }
    }

    private void generateStartPosition() {
        startPosition = (int) (Math.random() * (column * row)) + 1;
        getStartPositionIndex();
    }

    private void generateMaze() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Field(false, false, false, false, false);
            }
        }
        matrix[startPositionRow][startPositionColumn] = new Field(true, false, false, false, false);
        matrix[startPositionRow][startPositionColumn].setFirst(true);
        generateInner(startPositionRow, startPositionColumn, false);
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
