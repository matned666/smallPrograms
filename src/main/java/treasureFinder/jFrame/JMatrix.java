package treasureFinder.jFrame;


import treasureFinder.gameMechanics.Field;
import treasureFinder.gameMechanics.LabirynthGenerator;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class JMatrix {

    private LabirynthGenerator mazeGen;
    private JField[][] jMatrix;
    private JFrame frame;
    private final int FIELD_SIZE = 20;

    private Field playerField;

    private int col;
    private int row;

    JMatrix(int col, int row) {
        this.col = col;
        this.row = row;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Maze hunter");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE * col + 35, FIELD_SIZE * row + 60);
        initializeMatrix();
        playerField = mazeGen.getMatrix()[mazeGen.getPlayer().getCol()][mazeGen.getPlayer().getRow()];

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'w' && playerField.isTopNeighbour()) {
                    move(1, 0);
                } else if (keyEvent.getKeyChar() == 's' && playerField.isBottomNeighbour()) {
                    move(-1, 0);
                } else if (keyEvent.getKeyChar() == 'd' && playerField.isRightNeighbour()) {
                    move(0, -1);
                } else if (keyEvent.getKeyChar() == 'a' && playerField.isLeftNeighbour()) {
                    move(0, 1);
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void move(int col, int row) {
        int colOfPlayer = mazeGen.getPlayer().getCol();
        int rowOfPlayer = mazeGen.getPlayer().getRow();
        mazeGen.getMatrix()[colOfPlayer][rowOfPlayer].setPlayer(false);
        mazeGen.getMatrix()[colOfPlayer - col][rowOfPlayer - row].setPlayer(true);
        mazeGen.getPlayer().setCol(colOfPlayer - col);
        mazeGen.getPlayer().setRow(rowOfPlayer - row);
        colOfPlayer = mazeGen.getPlayer().getCol();
        rowOfPlayer = mazeGen.getPlayer().getRow();
        jMatrix[colOfPlayer][rowOfPlayer].getImageLabel();
        jMatrix[colOfPlayer + col][rowOfPlayer + row].getImageLabel();
        mazeGen.getPlayer().setMovementPossibilities(mazeGen.getMatrix()[colOfPlayer][rowOfPlayer].toFieldTypeByEntrance());
        playerField = mazeGen.getMatrix()[colOfPlayer][rowOfPlayer];
        isRoomFound(colOfPlayer, rowOfPlayer);
        isTreasureFound(colOfPlayer, rowOfPlayer);
    }

    private void isRoomFound(int col, int row) {
        if (mazeGen.getMatrix()[col][row].isRoom()) {
            mazeGen.getPlayer().setHP(mazeGen.getPlayer().getHP() - 1);
            System.out.println("Player HP:" + mazeGen.getPlayer().getHP() + " points:" + mazeGen.getPlayer().getPoints());
            mazeGen.getMatrix()[col][row].setRoom(false);
        }
    }

    private void isTreasureFound(int col, int row) {
        if (mazeGen.getMatrix()[col][row].isTreasure()) {
            mazeGen.getPlayer().setPoints(mazeGen.getPlayer().getPoints() + 1);
            System.out.println("Player HP:" + mazeGen.getPlayer().getHP() + " points:" + mazeGen.getPlayer().getPoints());
            mazeGen.getMatrix()[col][row].setTreasure(false);

        }
    }

    private void initializeMatrix() {
        mazeGen = new LabirynthGenerator(col, row);
        jMatrix = new JField[row][col];

        for (int i = 0; i < jMatrix.length; i++) {
            for (int j = 0; j < jMatrix[i].length; j++) {
                jMatrix[i][j] = new JField(mazeGen.getMatrix()[i][j]);
                jMatrix[i][j].getLabel().setBounds(j * FIELD_SIZE + 10, i * FIELD_SIZE + 10, FIELD_SIZE, FIELD_SIZE);
                frame.add(jMatrix[i][j].getLabel());
            }
        }

    }

}
