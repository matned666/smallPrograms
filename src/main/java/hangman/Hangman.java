package hangman;

import java.util.LinkedHashSet;
import java.util.Set;

public class Hangman {
    private String puzzle;
    private Set<Character> guessedLetters = new LinkedHashSet<>();
    private int hp;
    private String message;

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
        hp = 7;
        guessedLetters.clear();
    }

    public String getOutput() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < puzzle.length(); i++) {
            char c = puzzle.charAt(i);
            if (Character.isWhitespace(c)) {
                output.append(" ");
            } else {
                if (guessedLetters.contains(Character.toLowerCase(c))) {
                    output.append(c);
                } else {
                    output.append(".");
                }
            }
        }
        return output.toString();
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void guessLetter(char c) {
        if (hp != 0) {
            char smallChar = Character.toLowerCase(c);
            guessedLetters.add(smallChar);
            boolean isCorrect = puzzle.toLowerCase()
                    .contains(Character.toString(smallChar));
            message = "Congratulations";
            if (!isCorrect) {
                message = "Bad luck";
                hp--;
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public int getHp() {
        return hp;
    }

    public boolean isPuzzleSolved() {
        return getOutput().equals(puzzle);
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    public boolean isGameOver() {
        return isPuzzleSolved() || !isAlive();
    }

    public void guessPuzzle(String guess) {
        String normalizedGuess = guess.trim().toLowerCase();
        String normalizedPuzzle = puzzle.trim().toLowerCase();
        if (normalizedGuess.equals(normalizedPuzzle)) {
            for (int i = 0; i < normalizedGuess.length(); i++) {
                guessedLetters.add(normalizedGuess.charAt(i));
            }
        }
    }


}
