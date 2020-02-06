package treasureFinder.gameMechanics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabirynthGeneratorTest {

    private LabirynthGenerator matrix;
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
            matrix = new LabirynthGenerator(col,row);
            if (matrix.getStartPos() <= 0 || matrix.getStartPos() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPos()) highestNumber = matrix.getStartPos();
            if (lowestNumber > matrix.getStartPos()) lowestNumber = matrix.getStartPos();
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
            matrix = new LabirynthGenerator(col,row);
            if (matrix.getStartPos() <= 0 || matrix.getStartPos() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPos()) highestNumber = matrix.getStartPos();
            if (lowestNumber > matrix.getStartPos()) lowestNumber = matrix.getStartPos();
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
            matrix = new LabirynthGenerator(col,row);
            if (matrix.getStartPos() <= 0 || matrix.getStartPos() > col*row) isFitInBounds = false;
            if (highestNumber < matrix.getStartPos()) highestNumber = matrix.getStartPos();
            if (lowestNumber > matrix.getStartPos()) lowestNumber = matrix.getStartPos();
        }
        assertEquals(lowestNumber,1);
    }
    //passed

    @Test
    void isStartPositionPlacedWellOnMatrix(){
        matrix = new LabirynthGenerator(6,5);
        matrix.setStartPos(30);
        System.out.println("matrix.length:"+matrix.getMatrix().length);
        System.out.println("matrix[0].length:"+matrix.getMatrix()[0].length);
        System.out.println("x:"+matrix.getStartPosX());
        System.out.println("y:"+matrix.getStartPosY());
        System.out.println("StartPos:"+matrix.getStartPos());
        System.out.println("counter:"+matrix.counter);
        assertEquals(matrix.getStartPosX(), 5);
        assertEquals(matrix.getStartPosY(), 4);
    }


}