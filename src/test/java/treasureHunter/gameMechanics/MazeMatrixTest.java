package treasureHunter.gameMechanics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeMatrixTest {

    private MazeMatrix matrix;
    private int col = 10;
    private int row = 10;

    @BeforeEach
    void setUp() {

    }

    @Test
    void isStartPositionGeneratedInBounds10000triesA(){
        boolean isFitInBounds = true;
        int highestNumber = 1;
        int lowestNumber = col*row;
        for (int i = 0; i < 10000; i++) {
            matrix = new MazeMatrix(col,row,2,2);
            if (matrix.getStartPosition() <= 0 || matrix.getStartPosition() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPosition()) highestNumber = matrix.getStartPosition();
            if (lowestNumber > matrix.getStartPosition()) lowestNumber = matrix.getStartPosition();
        }
        assertTrue(isFitInBounds);
    }
    //passed

    @Test
    void isStartPositionGeneratedInBounds10000triesB(){
        boolean isFitInBounds = true;
        int highestNumber = 1;
        int lowestNumber = col*row;
        for (int i = 0; i < 10000; i++) {
            matrix = new MazeMatrix(col,row,2,2);
            if (matrix.getStartPosition() <= 0 || matrix.getStartPosition() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPosition()) highestNumber = matrix.getStartPosition();
            if (lowestNumber > matrix.getStartPosition()) lowestNumber = matrix.getStartPosition();
        }
        assertEquals(highestNumber,col*row);
    }
    //passed

    @Test
    void isStartPositionGeneratedInBounds10000triesC(){
        boolean isFitInBounds = true;
        int highestNumber = 1;
        int lowestNumber = col*row;
        for (int i = 0; i < 10000; i++) {
            matrix = new MazeMatrix(col,row,2,2);
            if (matrix.getStartPosition() <= 0 || matrix.getStartPosition() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPosition()) highestNumber = matrix.getStartPosition();
            if (lowestNumber > matrix.getStartPosition()) lowestNumber = matrix.getStartPosition();
        }
        assertEquals(lowestNumber,1);
    }
    //passed

    @Test
    void isStartPositionPlacedWellOnMatrix(){
        matrix = new MazeMatrix(6,5,2,2);
        matrix.setStartPosition(30);
        System.out.println("matrix.length:"+matrix.getMatrix().length);
        System.out.println("matrix[0].length:"+matrix.getMatrix()[0].length);
        System.out.println("x:"+matrix.getStartPositionColumn());
        System.out.println("y:"+matrix.getStartPositionRow());
        System.out.println("StartPos:"+matrix.getStartPosition());
        assertEquals(matrix.getStartPositionColumn(), 5);
        assertEquals(matrix.getStartPositionRow(), 4);
    }

    @Test
    void checkIfThereIsAccessToAllTreasures(){
        int assertsCounter = 0;
        for (int k = 0; k < 1000; k++) {

            matrix = new MazeMatrix(20, 20, 12, 10);
            int counter = 0;
            for (int i = 0; i < matrix.getMatrix().length; i++) {
                for (int j = 0; j < matrix.getMatrix().length; j++) {
                    Field thisPlace = matrix.getMatrix()[i][j];
                    if (thisPlace.isTreasure() && thisPlace.isOpened())
                        counter++;
                }
            }
            if (counter == matrix.getNUMBER_OF_TREASURES()) assertsCounter++;
        }

        assertEquals(assertsCounter,1000);

    }

    @Test
    void checkHowManyFieldsAreDone(){
        final int NUMB_OF_CHECKS = 1000;
        final int ROWS = 30;
        final int COLUMNS = 20;
        final int NUMBER_OF_MONSTERS = 10;
        final int NUMBER_OF_TREASURES = 5;
        final int NUMB_OF_FIELDS = ROWS * COLUMNS;
        int assertsCounter = 0;
        int stackCounter = 0;
        int lowest = NUMB_OF_FIELDS;
        for (int k = 0; k < NUMB_OF_CHECKS; k++) {
            matrix = new MazeMatrix(ROWS, COLUMNS, NUMBER_OF_MONSTERS, NUMBER_OF_TREASURES);
            if(matrix.isStackOverFlowError()) stackCounter++;

            int counter = 0;
            for (int i = 0; i < matrix.getMatrix().length; i++) {
                for (int j = 0; j < matrix.getMatrix().length; j++) {
                    Field thisPlace = matrix.getMatrix()[i][j];
                    if (thisPlace.isOpened())
                        counter++;
                }
            }
            assertsCounter += counter;
        }

        System.out.println("avarage:"+(assertsCounter/NUMB_OF_CHECKS)+" fields open/"+NUMB_OF_FIELDS+" fields, StackOverFlowError count:"+stackCounter+"/"+NUMB_OF_CHECKS+" tries.");

    }


}