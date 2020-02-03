package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class jFrame {

    private JButton[] buttons;
    private JLabel textField;
    private TicTacToe ticTacToe;
    private boolean isX;

    jFrame() {
        initialize();
    }
    
    private void initialize(){
        isX= true;
        ticTacToe = new TicTacToe();
        JFrame frame = new JFrame();
        frame.setSize(600,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        initializeButtons();
        textField();
        frame.add(textField);
        for(JButton el: buttons) frame.add(el);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private JLabel textField(){
        textField = new JLabel();
        textField.setBounds(50,610,500,40);
        textField.setText("LET US START");
        textField.setFont(new Font("Arial", Font.PLAIN, 40));
        return textField;
    }

    private JButton[] buttons(){
        buttons = new JButton[9];
        for (int i = 0; i< 9 ; i++) buttons[i] =  new JButton();
        return buttons;
    }

    private void initializeButtons(){
        buttons();
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton el = buttons[3*i+j];
                int x = j;
                int y = i;
                el.setText("");
                el.setFont(new Font("Arial", Font.BOLD, 100));
                el.setVerticalTextPosition(SwingConstants.CENTER);
                el.setHorizontalTextPosition(SwingConstants.CENTER);
                el.setBounds(j*200,i*200,200,200);
                el.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        action(el,x,y);
                    }
                });
            }
        }
    }

    private boolean isFinished(){
        return (ticTacToe.checkResult().equals(GameResult.PLAYER_O_WIN)
                || ticTacToe.checkResult().equals(GameResult.PLAYER_X_WIN)
                || ticTacToe.checkResult().equals(GameResult.DRAW));
    }

    private void action(JButton id , int col,int row){
        if(ticTacToe.getFieldStatus(col,row).equals(FieldStatus.EMPTY) && !isFinished()) {
            if (isX) {
                id.setText("X");
                isX = false;
                ticTacToe.add(col, row, FieldStatus.X);
                textField.setText(ticTacToe.checkResult().toString());
            } else {
                id.setText("O");
                isX = true;
                ticTacToe.add(col, row, FieldStatus.O);
                textField.setText(ticTacToe.checkResult().toString());
            }
        } else if(isFinished()) {
                ticTacToe.setAllEmpty();
            for(JButton el: buttons) el.setText("");
            textField.setText("LET US START AGAIN");
        }
    }
}