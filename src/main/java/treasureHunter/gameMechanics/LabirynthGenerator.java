package treasureHunter.gameMechanics;

import treasureHunter.gameMechanics.player.Player;
import treasureHunter.jFrame.FieldTypeByEntrance;

public class LabirynthGenerator {

    private int row;
    private int col;
    private Field[][] matrix;
    private int startPos;
    private int startPosX;
    private int startPosY;
    private int numberOfFields;
    private int openFields = 1;
    private boolean[] roomFields;
    private boolean[] treasureFields;
    private int NUMBER_OF_TREASURES = 5;
    private int NUMBER_OF_ROOMS = 10;
    private final int PLAYER_BASE_HP = 5;
    private Player player;


    public LabirynthGenerator(int col, int row, int rooms, int treasures ) {
        this.row = row;
        this.col = col;
        NUMBER_OF_TREASURES = treasures;
        NUMBER_OF_ROOMS = rooms;
        matrix = new Field[row][col];
        numberOfFields = row * col;
        initialize();
        player = new Player(PLAYER_BASE_HP);
        player.setCol(startPosY);
        player.setRow(startPosX);
        matrix[startPosY][startPosX].setPlayer(true);


    }

    private void initialize() {
        generateStartPosition();
        generateMaze();
        generateRoomLocations();
        generateTreasureLocations();
        printMaze();

    }

    public int getNUMBER_OF_TREASURES() {
        return NUMBER_OF_TREASURES;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Field[][] getMatrix() {
        return matrix;
    }

    int getStartPosX() {
        return startPosX;
    }

    int getStartPosY() {
        return startPosY;
    }

    void setStartPos(int startPos) {
        this.startPos = startPos;
        getStartPositionIndex();
    }

    private void generateMaze() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Field(false, false, false, false, false);
            }
        }
        matrix[startPosY][startPosX] = new Field(true, false, false, false, false);
        matrix[startPosY][startPosX].setFirst(true);
        generateInner(startPosY, startPosX);
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

    private void generateRoomLocations() {
        roomFields = new boolean[numberOfFields];
        generateLocationsInner(NUMBER_OF_ROOMS, roomFields);
        int temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (roomFields[temp] && matrix[i][j].toFieldTypeByEntrance() != FieldTypeByEntrance.NO_ENTRIES)
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
                if (treasureFields[temp] && matrix[i][j].toFieldTypeByEntrance() != FieldTypeByEntrance.NO_ENTRIES)
                    matrix[i][j].setTreasure(true);
                temp++;
            }
        }
    }


    private void generateTreasures() {
        this.treasureFields = new boolean[numberOfFields];
        for (boolean el : this.treasureFields) el = false;
        int temp;
        for (int i = 0; i < NUMBER_OF_TREASURES; i++) {
            temp = (int) (Math.random() * numberOfFields - 1);
            if (!this.treasureFields[temp]) {
                this.treasureFields[temp] = true;
            } else if (this.treasureFields[temp]) {
                i -= 1;
            }
        }
        temp = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (this.treasureFields[temp] && matrix[i][j].toFieldTypeByEntrance() != FieldTypeByEntrance.NO_ENTRIES)
                    matrix[i][j].setRoom(true);
                temp++;
            }
        }
    }

    private void generateInner(int y, int x) {
        try {
            int randomDirection = (int) (Math.random() * (100)) + 1;
            if (openFields < (numberOfFields - 10)) {
                if (randomDirection <= 25 && (y - 1) >= 0) {
                    if (matrix[y - 1][x].isOpened()) {

                        matrix[y][x].setTopNeighbour(true);
                        matrix[y - 1][x].setBottomNeighbour(true);
                        matrix[y - 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y - 1, x);

                } else if (randomDirection > 25 && randomDirection <= 50 && (x + 1) < matrix[y].length) {
                    if (matrix[y][x + 1].isOpened()) {
                        matrix[y][x].setRightNeighbour(true);
                        matrix[y][x + 1].setLeftNeighbour(true);
                        matrix[y][x + 1].setOpen(true);
                        openFields++;
                    }
                    generateInner(y, x + 1);
                } else if (randomDirection > 50 && randomDirection <= 75 && (y + 1) < matrix.length) {
                    if (matrix[y + 1][x].isOpened()) {
                        matrix[y][x].setBottomNeighbour(true);
                        matrix[y + 1][x].setTopNeighbour(true);
                        matrix[y + 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y + 1, x);
                } else if (randomDirection > 75 && randomDirection <= 100 && (x - 1) >= 0) {
                    if (matrix[y][x - 1].isOpened()) {
                        matrix[y][x].setLeftNeighbour(true);
                        matrix[y][x - 1].setRightNeighbour(true);
                        matrix[y][x - 1].setOpen(true);
                        openFields++;
                    }
                    generateInner(y, x - 1);
                } else {
                    generateInner(y, x);
                }

            }
        } catch (StackOverflowError ex) {
            System.out.println(" * StackOverflow * ");
        }
    }


    private void generateStartPosition() {
        startPos = (int) (Math.random() * (col * row)) + 1;
        getStartPositionIndex();
    }

    int getStartPos() {
        return startPos;
    }

    private void getStartPositionIndex() {
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (counter == startPos) {
                    startPosX = j;
                    startPosY = i;
                }
                counter++;
            }
        }

    }

    private void printMaze() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == startPosY && j == startPosX) System.out.print("@");
                else System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }


}
