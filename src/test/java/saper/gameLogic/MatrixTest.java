package saper.gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void isCreatingFieldWithProperNumberOfRows() {
        Matrix matrix = new Matrix(5, 3, 0);

        assertEquals(matrix.getMatrix().length, 3);
    }

    @Test
    public void isCreatingFieldWithProperNumberOfColumns() {
        Matrix matrix = new Matrix(5, 3, 0);

        assertEquals(matrix.getMatrix()[0].length, 5);
    }

    @Test
    public void isProperNumberBomBsOnMatrix() {
        Matrix matrix = new Matrix(5, 3, 10);

        int bombCounter = 0;
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix()[i].length; j++) {
                if (matrix.getMatrix()[i][j].isBomb()) bombCounter++;
            }
        }

        assertEquals(bombCounter, 10);
    }

    @Test
    public void isOpeningProperly() {
        Matrix matrix = new Matrix(5, 5, 0);
        matrix.getMatrix()[0][1].setBomb(true);
        matrix.getMatrix()[0][2].setBomb(true);
        matrix.getMatrix()[2][2].setBomb(true);
        matrix.getMatrix()[2][0].setBomb(true);
        matrix.getMatrix()[4][0].setBomb(true);
        matrix.setNeighbors();

        matrix.openField(4, 4);

        int openFieldsCounter = 0;
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix()[i].length; j++) {
                if (matrix.getMatrix()[i][j].isOpen()) openFieldsCounter++;
            }
        }

        System.out.println(matrix.getMatrix()[0][0].isOpen());
        assertEquals(openFieldsCounter, 14);
    }

    @Test
    public void isBombBlownWhenStepOnIt() {
        Matrix matrix = new Matrix(5, 5, 0);
        matrix.getMatrix()[0][1].setBomb(true);
        matrix.getMatrix()[0][2].setBomb(true);
        matrix.getMatrix()[2][2].setBomb(true);
        matrix.getMatrix()[2][0].setBomb(true);
        matrix.getMatrix()[4][0].setBomb(true);
        matrix.setNeighbors();

        matrix.openField(2, 2);

        assertTrue(matrix.getMatrix()[2][2].isBlownBomb());
    }

    @Test
    public void isPending() {
        Matrix matrix = new Matrix(5, 5, 0);
        matrix.getMatrix()[0][1].setBomb(true);
        matrix.getMatrix()[0][2].setBomb(true);
        matrix.getMatrix()[2][2].setBomb(true);
        matrix.getMatrix()[2][0].setBomb(true);
        matrix.getMatrix()[4][0].setBomb(true);
        matrix.setNeighbors();

        matrix.openField(2, 1);

        assertEquals(matrix.getGameState(), GameState.PENDING);
    }

    @Test
    public void isWinAllOpen() {
        Matrix matrix = new Matrix(5, 5, 0);
        matrix.getMatrix()[0][1].setBomb(true);
        matrix.getMatrix()[0][2].setBomb(true);
        matrix.getMatrix()[2][2].setBomb(true);
        matrix.getMatrix()[2][0].setBomb(true);
        matrix.getMatrix()[4][0].setBomb(true);
        matrix.setNeighbors();

        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix()[i].length; j++) {
                if (!matrix.getMatrix()[i][j].isBomb()) matrix.openField(i, j);
            }
        }

        assertEquals(matrix.getNumberOfClosedFields(), 5);
    }


}