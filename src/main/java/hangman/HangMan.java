package hangman;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class HangMan {

    private String puzzle;
    private String inputText = "";
    private Set<Character> guessedLetters;
    private String output;
    private boolean isGuessedByOneShot;
    private int hp;


    public HangMan() {

    }

    public void game() {
        Scanner reader = new Scanner(System.in);
        setPuzzle(Resources.getRandomPuzzle());

        while (!inputText.equalsIgnoreCase("quit")) {
            while (!isOver() && !inputText.equalsIgnoreCase("quit")) {
                isGuessedByOneShot = false;
                inputText = "";
                System.out.println(String.format("HP: %d/%d", getHp(), 7));
                System.out.println(output);
                System.out.print("Podaj literę: ");
                inputText = reader.nextLine();
                if (inputText.equalsIgnoreCase("quit")) {
                    System.out.println("Dzięki za grę.");
                } else {
                    char c = inputText.charAt(0);
                    guessLetters(c);
                }
            }
            if (!inputText.equalsIgnoreCase("quit")) System.out.println("\n\nNOWA GRA:\n");
            setPuzzle(Resources.getRandomPuzzle());
        }
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getOutput() {
        StringBuilder output = new StringBuilder();
        if (inputText.equalsIgnoreCase(puzzle)) return puzzle;
        else {
            for (int i = 0; i < puzzle.length(); i++) {
                char c = puzzle.charAt(i);
                if (Character.isWhitespace(c)) {
                    output.append(" ");
                } else {
                    if (guessedLetters.contains(Character.toLowerCase(c))) {
                        output.append(c);
                    } else output.append("*");
                }
            }
            this.output = output.toString();
            return output.toString();
        }
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String text) {
        this.puzzle = text;
        hp = 7;
        guessedLetters = new LinkedHashSet<>();

    }

    public void guessLetters(char n) {
        if (hp != 0) {
            char letter = Character.toLowerCase(n);
            guessedLetters.add(letter);
            boolean isCorrect = puzzle.toLowerCase()
                    .contains(Character.toString(letter));
            if (isCorrect) {
                if (!isOver() && !isGuessedByOneShot) System.out.println("DOBRY STRZAŁ\n");
                else if (isOver() && !isGuessedByOneShot) {
                    System.out.println("BRAWO, ZGADŁEŚ !!!!!!");
                    System.out.println("Hasło: '" + puzzle + "'");

                }
            }
            if (!isCorrect && !getOutput().equals(puzzle)) {
                hp--;
                if (!isOver()) System.out.println("ŹLE - ZAWIŚNIESZ !!!\n");
                else if (isOver() && hp <= 0) {
                    System.out.println("ZAWISŁEŚ - WISIELCU !!!!!!!!!!");
                    System.out.println("Hasło było: '" + puzzle + "'");
                }
            }
        }
    }


    public int getHp() {
        return hp;
    }

    public boolean isOver() {
        return hp <= 0 || getOutput().equals(puzzle);
    }
}


