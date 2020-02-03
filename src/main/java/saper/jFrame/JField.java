package saper.jFrame;

import saper.gameLogic.FieldObj;

import javax.swing.*;

class JField {

    private int col;
    private int row;
    private JButton button;
    private JLabel label;
    private FieldObj fieldData;



    JField(FieldObj fieldData, int col, int row) {
        this.col = col;
        this.row = row;
        this.fieldData = fieldData;
        draw();
        label.setBounds(col, row, 20, 20);
        button = new JButton(new ImageIcon("src\\main\\resources\\saper\\saperButtonField.png"));
        button.setBounds(col, row, 20, 20);
    }

    JButton getButton() {
        return button;
    }

    int getCol() {
        return col;
    }

    int getRow() {
        return row;
    }

    JLabel getLabel() {
        return label;
    }

    private void draw() {
        if (fieldData.isBomb() && !fieldData.isChecked() && !fieldData.isBlownBomb())
            label = new JLabel(new ImageIcon("src\\main\\resources\\saper\\saperBombField.png"));
        else if (fieldData.isBomb() && fieldData.isChecked() && !fieldData.isBlownBomb())
            label = new JLabel(new ImageIcon("src\\main\\resources\\saper\\saperCheckedBombField.png"));
        else if (fieldData.isBomb() && fieldData.isBlownBomb())
            label = new JLabel(new ImageIcon("src\\main\\resources\\saper\\saperBlownBombField.png"));
        else {
            if (fieldData.getNumberOfBombsInSurround() == 0)
                label = new JLabel(new ImageIcon("src\\main\\resources\\saper\\saperEmptyField.png"));
            else {
                label = new JLabel(new ImageIcon("src\\main\\resources\\saper\\saperEmptyField.png"));
                label.setText(String.valueOf(fieldData.getNumberOfBombsInSurround()));
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.CENTER);
            }
        }
    }

}
