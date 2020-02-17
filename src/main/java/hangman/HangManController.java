package hangman;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HangManController {


    public Line hp3;
    public Line hp1;
    public Line hp2;
    public Line hp4;
    public Line hp5;
    public Line hp6;
    public Group hp7;

    public TextField textField;

    public Text textToGuess;
    public Text messageLabel;

    Hangman game;

    public HangManController() {
    }
    
    public void initialize(){
        reset();
    }

    public void reset(){
        game = new Hangman();
        String randomPuzzle = Resources.getRandomPuzzle();
        game.setPuzzle(randomPuzzle);
        textToGuess.setText(game.getOutput());
        refreshUI();
    }

    public void acceptButtonAction() {

        if(game.isGameOver()){
            reset();
        }else{
            submitGuess();
        }
        refreshUI();
    }




    private void submitGuess() {
        String text = textField.getText();
        if(text.length() > 1 && text.trim().equalsIgnoreCase(game.getPuzzle()) ) game.guessPuzzle(text);
        else {
            if(text.length() > 0)game.guessLetter(text.charAt(0));

        }

        messageLabel.setText(game.getMessage());
        textToGuess.setText(game.getOutput());
    }


    public void resetButtonAction() {
        reset();
    }

    public void refreshUI(){
        refreshPassword();
        textField.setText("");
        refreshHP();
    }

    private void refreshHP() {

        hp1.setVisible(false);
        hp2.setVisible(false);
        hp3.setVisible(false);
        hp4.setVisible(false);
        hp5.setVisible(false);
        hp6.setVisible(false);
        hp7.setVisible(false);

        switch (game.getHp()){
            case 0: hp7.setVisible(true);
            case 1: hp6.setVisible(true);
            case 2: hp5.setVisible(true);
            case 3: hp4.setVisible(true);
            case 4: hp3.setVisible(true);
            case 5: hp2.setVisible(true);
            case 6: hp1.setVisible(true);

        }
    }

    private void refreshPassword() {
        textToGuess.setText(game.getOutput());
    }
}

