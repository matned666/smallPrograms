package treasureFinder.jFrame;


import treasureFinder.gameMechanics.Field;
import treasureFinder.gameMechanics.LabirynthGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Game {

    private LabirynthGenerator mazeGen;
    private JField[][] jMatrix;
    private JFrame frame;
    private final int FIELD_SIZE = 20;
    private GameState gameState;
    private JLabel gameInfo;
    private int levelNumber;
    private int rooms;
    private int treasures;

    private final int NUMBER_OF_TREASURES = 5;
    private final int NUMBER_OF_ROOMS = 10;


    private Field playerField;

    private int col;
    private int row;

    public Game(int col, int row) {
        this.col = col;
        this.row = row;
        levelNumber = 1;
        rooms = NUMBER_OF_ROOMS;
        treasures = NUMBER_OF_TREASURES;
        initialize(rooms, treasures);
    }

    private void initialize( int rooms, int treasures ) {

        gameState = GameState.PLAYING;
        frame = new JFrame();
        frame.setTitle("Maze hunter");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE * col + 35, FIELD_SIZE * row + 160);
        initializeMatrix(rooms, treasures);
        playerField = mazeGen.getMatrix()[mazeGen.getPlayer().getCol()][mazeGen.getPlayer().getRow()];
        gameInfo = new JLabel();
        gameInfo.setBounds(10,FIELD_SIZE * row + 30, FIELD_SIZE * col + 25 , 50);
        gameInfo.setFont(new Font("Arial", Font.BOLD,20));
        gameInfo.setText("Let's start");
        frame.add(gameInfo);


        frame.addKeyListener(new KeyListener() {


            @Override
            public void keyTyped(KeyEvent keyEvent) {
         if(gameState != GameState.PLAYING) gameOver();

                if (keyEvent.getKeyChar() == '8' && playerField.isTopNeighbour() && gameState == GameState.PLAYING) {
                    move(1, 0);
                } else if (keyEvent.getKeyChar() == '2' && playerField.isBottomNeighbour() && gameState == GameState.PLAYING) {
                    move(-1, 0);
                } else if (keyEvent.getKeyChar() == '6' && playerField.isRightNeighbour() && gameState == GameState.PLAYING) {
                    move(0, -1);
                } else if (keyEvent.getKeyChar() == '4' && playerField.isLeftNeighbour() && gameState == GameState.PLAYING) {
                    move(0, 1);
                }else if (keyEvent.getKeyChar() == '5' && gameState == GameState.PLAYING) {
                    move(0, 0);
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

    private void gameOver() {
        if(gameState == GameState.GAME_OVER) {
            newGame(NUMBER_OF_ROOMS, NUMBER_OF_TREASURES);
        }
        if(gameState == GameState.WIN) {
            rooms+=5;
            treasures+=2;
            newGame(rooms, treasures);
            levelNumber++;
        }
    }

    private void setGameInfo(){
        gameInfo.setText("HP:"+mazeGen.getPlayer().getHP()+" Points:"+mazeGen.getPlayer().getPoints()+"/"+treasures+" Game:"+gameState+"  level:"+levelNumber);
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
            moveAllRooms();
            if (mazeGen.getPlayer().getHP() <= 0) {
                gameState = GameState.GAME_OVER;
                levelNumber = 1;
            }
            if (mazeGen.getPlayer().getPoints() >= mazeGen.getNUMBER_OF_TREASURES()) {
                gameState = GameState.WIN;
            }
        System.out.println("treasures:"+mazeGen.getNUMBER_OF_TREASURES());
            setGameInfo();

    }

    private void newGame(int rooms, int treasures){
        frame.setVisible(false);
        initialize(rooms, treasures);
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

    private void moveRoomInner(int col, int row, int colMove, int rowMove) {
        mazeGen.getMatrix()[col][row].setRoom(false);
        mazeGen.getMatrix()[col - colMove][row - rowMove].setRoom(true);
        jMatrix[col][row].getImageLabel();
        jMatrix[col - colMove][row - rowMove].getImageLabel();
        if(mazeGen.getMatrix()[col - colMove][row - rowMove].isPlayer()) mazeGen.getPlayer().setHP(mazeGen.getPlayer().getHP()-1);
    }

    private void moveRoom(int col, int row){
        int randomDirection = (int) (Math.random() * (100)) + 1;
        Field thisRoomField = mazeGen.getMatrix()[col][row];
        if(randomDirection<25 && thisRoomField.isTopNeighbour()) moveRoomInner(col, row, 1,0);
        else if(randomDirection>= 25 && randomDirection<50 && thisRoomField.isBottomNeighbour()) moveRoomInner(col, row, -1,0);
        else if(randomDirection>= 50 && randomDirection<75 && thisRoomField.isLeftNeighbour()) moveRoomInner(col, row, 0,1);
        else if(randomDirection>= 75 && randomDirection<100 && thisRoomField.isRightNeighbour()) moveRoomInner(col, row, 0,-1);
    }

    private void moveAllRooms(){
        for(int i = 0; i < mazeGen.getMatrix().length;i++) {
            for (int j = 0; j < mazeGen.getMatrix()[i].length; j++) {
                if(mazeGen.getMatrix()[i][j].isRoom()) moveRoom(i,j);
            }
        }
    }


    private void initializeMatrix(int rooms, int treasures) {
        mazeGen = new LabirynthGenerator(col, row, rooms, treasures);
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
