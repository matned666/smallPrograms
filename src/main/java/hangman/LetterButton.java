package hangman;

import javax.swing.*;
import java.awt.*;

public class LetterButton {
    private String letter;
    private JButton button;

    public LetterButton(String letter, int x, int y) {
        this.letter = letter;
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBounds(x, y, 40, 40);
        button.setText(letter);
    }

    public JButton getButton() {
        return button;
    }
}
