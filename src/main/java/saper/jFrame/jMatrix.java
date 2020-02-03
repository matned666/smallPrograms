package saper.jFrame;

import saper.gameLogic.GameState;
import saper.gameLogic.Matrix;

import javax.swing.*;
import java.awt.*;


class jMatrix {

    private int col;
    private int row;
    private JField[][] jFields;
    private int frameXSize;
    private int frameYSize;
    private JFrame frame;
    private Matrix matrix;
    private int numberOfBombs;
    private JLabel messageTxt;
    private JButton messageImage;

    jMatrix(int col, int row, int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
        this.col = col;
        this.row = row;
        frameXSize = col * 20 + 35;
        frameYSize = row * 20 + 60;
        jFrame();
    }

    private void jFrame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(frameXSize, frameYSize + 50);
        frame.setMaximizedBounds(new Rectangle(50, 50, 400, 400));
        createFrame();
        messageTxt = new JLabel();
        messageTxt.setBounds(20, frameYSize - 50, frameXSize - 60, 40);
        messageTxt.setFont(new Font("Arial", Font.BOLD, 20));
        messageTxt.setText("WELCOME");
        messageImage = new JButton(new ImageIcon("src\\main\\resources\\saper\\emptyField.png"));
        messageImage.setBounds(frameXSize - 70, frameYSize - 40, 40, 40);
        messageImage.addActionListener(actionEvent -> frame.dispose());

        frame.add(messageTxt);
        frame.add(messageImage);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void jFields() {
        jFields = new JField[row][col];
        matrix = new Matrix(col, row, numberOfBombs);
        for (int y = 0; y < jFields.length; y++) {
            for (int x = 0; x < jFields[y].length; x++) {
                jFields[y][x] = new JField(matrix.getMatrix()[y][x], x * 20 + 10, y * 20 + 10);
            }
        }
    }

    private void createFrame() {
        jFields();
        for (int y = 0; y < jFields.length; y++) {
            for (int x = 0; x < jFields[y].length; x++) {
                frame.add(jFields[y][x].getButton());
                frame.add(jFields[y][x].getLabel());
                addAction(jFields[y][x], y, x);
            }
        }
    }

    private void addAction(JField jFieldAction, int row, int col) {
        jFieldAction.getButton().addActionListener(actionEvent -> {
            matrix.openField(row, col);
            for (int y = 0; y < jFields.length; y++) {
                for (int x = 0; x < jFields[y].length; x++) {
                    if (matrix.getMatrix()[y][x].isOpen()) jFields[y][x].getButton().setSize(0, 0);
                    messageTxt.setText(matrix.getGameMessage());
                    if (matrix.getGameState() == GameState.GAME_OVER) {
                        messageImage.setIcon(new ImageIcon("src\\main\\resources\\saper\\death.png"));
                        matrix.getMatrix()[row][col].setBlownBomb(true);
                        jFields[row][col].getLabel().setIcon(new ImageIcon("src\\main\\resources\\saper\\saperBlownBombField.png"));
                    }
                    if (matrix.getGameState() == GameState.WIN)
                        messageImage.setIcon(new ImageIcon("src\\main\\resources\\saper\\win.png"));
                }
            }
        });
    }
}
