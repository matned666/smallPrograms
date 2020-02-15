package treasureHunter.jFrame;


import treasureHunter.gameMechanics.Field;
import treasureHunter.gameMechanics.MazeMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {

    private MazeMatrix myMaze;
    private JField[][] jMatrix;
    private JFrame frame;
    private GameState gameState;
    private JLabel gameInfo;
    private int levelNumber;
    private int monsters;
    private int treasures;

    private final int FIELD_SIZE = 20;
    private final int NUMBER_OF_TREASURES = 5;
    private final int NUMBER_OF_MONSTERS = 10;


    private Field playerField;

    private int column;
    private int row;

    public Game(int column, int row) {
        this.column = column;
        this.row = row;
        levelNumber = 1;
        monsters = NUMBER_OF_MONSTERS;
        treasures = NUMBER_OF_TREASURES;
        initialize(monsters, treasures);
    }

    private void initialize( int monsters, int treasures ) {
        gameState = GameState.PLAYING;
        frame = new JFrame();
        frame.setTitle("Maze hunter");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE * column + 35, FIELD_SIZE * row + 160);
        initializeMatrix(monsters, treasures);
        playerField = myMaze.getMatrix()[myMaze.getPlayer().getCol()][myMaze.getPlayer().getRow()];
        gameInfo = new JLabel();
        gameInfo.setBounds(10,FIELD_SIZE * row + 30, FIELD_SIZE * column + 25 , 50);
        gameInfo.setFont(new Font("Arial", Font.BOLD,20));
        gameInfo.setText("Lets go");
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
            newGame(NUMBER_OF_MONSTERS, NUMBER_OF_TREASURES);
            levelNumber = 1;
        }
        else if(gameState == GameState.WIN) {
            monsters +=5;
            treasures+=2;
            newGame(monsters, treasures);
            levelNumber++;
        }
    }



    private void setGameInfo(){
        gameInfo.setText("HP:"+ myMaze.getPlayer().getHP()+" Treasure:"+ myMaze.getPlayer().getPoints()+"/"+treasures+" Game:"+gameState+"  level:"+levelNumber);
    }

    private void move(int column, int row) {
            int colOfPlayer = myMaze.getPlayer().getCol();
            int rowOfPlayer = myMaze.getPlayer().getRow();
            myMaze.getMatrix()[colOfPlayer][rowOfPlayer].setPlayer(false);
            myMaze.getMatrix()[colOfPlayer - column][rowOfPlayer - row].setPlayer(true);
            myMaze.getPlayer().setCol(colOfPlayer - column);
            myMaze.getPlayer().setRow(rowOfPlayer - row);
            colOfPlayer = myMaze.getPlayer().getCol();
            rowOfPlayer = myMaze.getPlayer().getRow();
            jMatrix[colOfPlayer][rowOfPlayer].getImageLabel();
            jMatrix[colOfPlayer + column][rowOfPlayer + row].getImageLabel();
            myMaze.getPlayer().setMovementPossibilities(myMaze.getMatrix()[colOfPlayer][rowOfPlayer].toFieldTypeByEntrance());
            playerField = myMaze.getMatrix()[colOfPlayer][rowOfPlayer];
            isRoomFound(colOfPlayer, rowOfPlayer);
            isTreasureFound(colOfPlayer, rowOfPlayer);
            moveAllRooms();
            if (myMaze.getPlayer().getHP() <= 0) {
                gameState = GameState.GAME_OVER;
                dialogWithWinOrLooseMessage();
            }
            if (myMaze.getPlayer().getPoints() >= myMaze.getNUMBER_OF_TREASURES()) {
                gameState = GameState.WIN;
                dialogWithWinOrLooseMessage();
            }
            setGameInfo();

    }

    private void newGame(int monsters, int treasures){
        frame.setVisible(false);
        initialize(monsters, treasures);
    }

    private void isRoomFound(int column, int row) {
        if (myMaze.getMatrix()[column][row].isRoom()) {
            myMaze.getPlayer().setHP(myMaze.getPlayer().getHP() - 1);
            System.out.println("Player HP:" + myMaze.getPlayer().getHP() + " points:" + myMaze.getPlayer().getPoints());
            myMaze.getMatrix()[column][row].setRoom(false);
        }
    }

    private void isTreasureFound(int column, int row) {
        if (myMaze.getMatrix()[column][row].isTreasure()) {
            myMaze.getPlayer().setPoints(myMaze.getPlayer().getPoints() + 1);
            System.out.println("Player HP:" + myMaze.getPlayer().getHP() + " points:" + myMaze.getPlayer().getPoints());
            myMaze.getMatrix()[column][row].setTreasure(false);

        }
    }

    private void moveRoomInner(int column, int row, int columnMove, int rowMove) {
        myMaze.getMatrix()[column][row].setRoom(false);
        myMaze.getMatrix()[column - columnMove][row - rowMove].setRoom(true);
        jMatrix[column][row].getImageLabel();
        jMatrix[column - columnMove][row - rowMove].getImageLabel();
        if(myMaze.getMatrix()[column - columnMove][row - rowMove].isPlayer()) myMaze.getPlayer().setHP(myMaze.getPlayer().getHP()-1);
    }

    private void moveRoom(int column, int row){
        int randomDirection = (int) (Math.random() * (100)) + 1;
        Field thisRoomField = myMaze.getMatrix()[column][row];
        if(randomDirection<25 && thisRoomField.isTopNeighbour()) moveRoomInner(column, row, 1,0);
        else if(randomDirection>= 25 && randomDirection<50 && thisRoomField.isBottomNeighbour()) moveRoomInner(column, row, -1,0);
        else if(randomDirection>= 50 && randomDirection<75 && thisRoomField.isLeftNeighbour()) moveRoomInner(column, row, 0,1);
        else if(randomDirection>= 75 && randomDirection<100 && thisRoomField.isRightNeighbour()) moveRoomInner(column, row, 0,-1);
    }

    private void moveAllRooms(){
        for(int i = 0; i < myMaze.getMatrix().length; i++) {
            for (int j = 0; j < myMaze.getMatrix()[i].length; j++) {
                if(myMaze.getMatrix()[i][j].isRoom()) moveRoom(i,j);
            }
        }
    }

    private void dialogWithWinOrLooseMessage (){
        JDialog dialogWithWinOrLoseMessage = new JDialog(frame);
        dialogWithWinOrLoseMessage.setSize(400,200);
        JLabel labelWithMessage = new JLabel();
        labelWithMessage.setBounds(10,10,380,50);
        if(gameState == GameState.GAME_OVER) labelWithMessage.setText("GAME OVER");
        else if(gameState == GameState.WIN) labelWithMessage.setText("LEVEL:"+levelNumber+" ID DONE");
        labelWithMessage.setVerticalAlignment(SwingConstants.CENTER);
        labelWithMessage.setHorizontalAlignment(SwingConstants.CENTER);
        dialogWithWinOrLoseMessage.add(labelWithMessage);
        labelWithMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        JButton confirmationButton = new JButton();
        confirmationButton.setBounds(150,70,100,50);
        confirmationButton.setText("CONTINUE");
        confirmationButton.addActionListener(actionEvent -> {
            dialogWithWinOrLoseMessage.dispose();
            gameOver();
        });
        confirmationButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 10) {
                    dialogWithWinOrLoseMessage.dispose();
                    gameOver();
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        JLabel labelWithImage = new JLabel();
        labelWithImage.setBounds(270,70,50,50);
        if(gameState==GameState.GAME_OVER) labelWithImage.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\gameOver.jpg"));
        if(gameState==GameState.WIN) labelWithImage.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\winLevel.jpg"));


        dialogWithWinOrLoseMessage.add(labelWithImage);
        dialogWithWinOrLoseMessage.add(confirmationButton);
        dialogWithWinOrLoseMessage.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dialogWithWinOrLoseMessage.dispose();
                gameOver();
            }
        });
        dialogWithWinOrLoseMessage.setLayout(null);
        dialogWithWinOrLoseMessage.setVisible(true);
    }


    private void initializeMatrix(int rooms, int treasures) {
        myMaze = new MazeMatrix(column, row, rooms, treasures);
        jMatrix = new JField[row][column];

        for (int i = 0; i < jMatrix.length; i++) {
            for (int j = 0; j < jMatrix[i].length; j++) {
                jMatrix[i][j] = new JField(myMaze.getMatrix()[i][j]);
                jMatrix[i][j].getLabel().setBounds(j * FIELD_SIZE + 10, i * FIELD_SIZE + 10, FIELD_SIZE, FIELD_SIZE);
                frame.add(jMatrix[i][j].getLabel());
            }
        }
    }

}
