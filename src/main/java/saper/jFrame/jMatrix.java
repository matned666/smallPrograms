package saper.jFrame;

import saper.gameLogic.GameState;
import saper.gameLogic.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class jMatrix {

    private String time;
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
    private JLabel timer;

    jMatrix(int col, int row, int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
        this.col = col;
        this.row = row;
        frameXSize = col * 20 + 35;
        frameYSize = row * 20 + 60;
        jFrame();
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public String getTime() { return time; }

    private void jFrame() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(frameXSize, frameYSize + 70);
        createFrame();
        messageTxt = new JLabel();
        messageTxt.setBounds(20, frameYSize - 50, frameXSize - 60, 40);
        messageTxt.setFont(new Font("Arial", Font.BOLD, 16));
        messageTxt.setText("WELCOME");
        messageImage = new JButton(new ImageIcon("src\\main\\resources\\saper\\emptyField.png"));
        messageImage.setBounds(frameXSize - 70, frameYSize - 40, 40, 40);
        messageImage.addActionListener(actionEvent -> frame.dispose());
        timer();
        timerLabel();
        frame.add(timer);
        frame.add(messageTxt);
        frame.add(messageImage);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void timer() {
        int delay = 1000;
        AtomicInteger seconds = new AtomicInteger();
        AtomicInteger minutes = new AtomicInteger();
        AtomicInteger hours = new AtomicInteger();
        AtomicReference<String> timerMessage = new AtomicReference<>("");
        seconds.set(0);
        minutes.set(0);
        hours.set(0);
        ActionListener taskPerformer = evt -> {
            time = timerMessage.get();
            if(matrix.getGameState()==GameState.PENDING ) {
                seconds.getAndIncrement();
                if (seconds.get() >= 60) {
                    seconds.set(0);
                    minutes.getAndIncrement();
                    if (minutes.get() >= 60) {
                        seconds.set(0);
                        minutes.set(0);
                        hours.getAndIncrement();
                    }
                }
                timerMessage.updateAndGet(v -> v + hours + ":");
                if (minutes.get() < 10) {
                    timerMessage.updateAndGet(v -> v + "0" + minutes + ":");
                } else {
                    timerMessage.updateAndGet(v -> v + minutes + ":");
                }
                if (seconds.get() < 10) {
                    timerMessage.updateAndGet(v -> v + "0" + seconds);
                } else {
                    timerMessage.updateAndGet(v -> v + seconds);
                }
                timer.setText("Your time (h:mm:ss) :" + timerMessage.get());
                timerMessage.set("");
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    private void timerLabel(){
        timer = new JLabel();
        timer.setBounds(frameXSize-210, frameYSize + 10,200,20);
        timer.setText("Your time (h:mm:ss) :0:00:00");
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

        jFieldAction.getButton().addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
             if(!matrix.getMatrix()[row][col].isChecked()) {
                 matrix.getMatrix()[row][col].setChecked(true);
                 jFields[row][col].getButton().setIcon(new ImageIcon("src\\main\\resources\\saper\\saperButtonFieldChecked.png"));
             }else{
                 matrix.getMatrix()[row][col].setChecked(false);
                 jFields[row][col].getButton().setIcon(new ImageIcon("src\\main\\resources\\saper\\saperButtonField.png"));
             }
            }
        });
    }
}
