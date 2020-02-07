package treasureFinder.gameMechanics;

public class LabirynthGenerator {

    private int row;
    private int col;
    private Field[][] matrix;
    private int startPos;
    private int startPosX;
    private int startPosY;
    private int numberOfFields;
    private int openFields = 1;


    public LabirynthGenerator(int col, int row) {
        this.row = row;
        this.col = col;
        matrix = new Field[row][col];
        numberOfFields = row*col;
        initialize();
    }

    private void initialize(){
        generateStartPosition();
        getStartPositionIndex();
        generateMaze();
        printMaze();

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

    private void generateMaze(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Field(false,false,false,false,false);
            }
        }
        matrix[startPosY][startPosX] = new Field(true,false,false,false,false);
        generateInner(startPosY, startPosX);
    }

    private void generateInner(int y, int x) {
        try {
            int randomDirection = (int) (Math.random() * (100)) + 1;
            if (openFields < (numberOfFields-10)) {
                if (randomDirection <= 25 && (y - 1) >= 0) {
                    if (!matrix[y - 1][x].isOpen()) {

                        matrix[y][x].setTopNeighbour(true);
                        matrix[y - 1][x].setBottomNeighbour(true);
                        matrix[y - 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y - 1, x);
                } else if (randomDirection > 25 && randomDirection <= 50 && (x + 1) < matrix[y].length) {
                    if (!matrix[y][x + 1].isOpen()) {
                        matrix[y][x].setRightNeighbour(true);
                        matrix[y][x + 1].setLeftNeighbour(true);
                        matrix[y][x + 1].setOpen(true);
                        openFields++;
                    }
                    generateInner(y, x + 1);
                } else if (randomDirection > 50 && randomDirection <= 75 && (y + 1) < matrix.length) {
                    if (!matrix[y + 1][x].isOpen()) {
                        matrix[y][x].setBottomNeighbour(true);
                        matrix[y + 1][x].setTopNeighbour(true);
                        matrix[y + 1][x].setOpen(true);
                        openFields++;
                    }
                    generateInner(y + 1, x);
                } else if (randomDirection > 75 && randomDirection <= 100 && (x - 1) >= 0) {
                    if (!matrix[y][x - 1].isOpen()) {
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
        }catch(StackOverflowError ex){
            System.out.println("StackOverflow");
        }
    }


    private void generateStartPosition(){
        startPos = (int) (Math.random() * (col * row)) + 1;
    }

    public int getStartPos(){
        return startPos;
    }

    private void getStartPositionIndex(){
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(counter == startPos) {
                    startPosX = j;
                    startPosY = i;
                }
                counter ++;
            }
        }

    }

    public void printMaze(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i == startPosY && j == startPosX) System.out.print("@");
                else System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }


}
