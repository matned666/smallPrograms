package rockPaperScissors;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Player player1 = new StonedPlayer("Computer");
        Player player2 = new StonedPlayer("Ziutek");

        GameAction action2 = GameAction.STONE;

        Scanner read = new Scanner(System.in);

        int humanPoints = 0;
        int compPoints = 0;
        int drawPts = 0;

        boolean isQuit = false;
        while (!isQuit) {
            try {

                System.out.println("\n1:Stone, 2:Paper, 3:Scissors, else: quit game");
                int a = read.nextInt();
                GameAction action1 = player1.getAction(randomAction());
                if (a == 1) action2 = player2.getAction(GameAction.STONE);
                else if (a == 2) action2 = player2.getAction(GameAction.PAPER);
                else if (a == 3) action2 = player2.getAction(GameAction.SCISSORS);
                else isQuit = true;

                if (getResult(action1, action2) == GameResult.COMPUTER_HAS_WON) compPoints++;
                if (getResult(action1, action2) == GameResult.HUMAN_PLAYER_HAS_WON) humanPoints++;
                if (getResult(action1, action2) == GameResult.DRAW) drawPts++;

                System.out.println(player1.getNick() + " put:" + action1 + ", " + player2.getNick() + " put:" + action2 + ", game result:" + getResult(action1, action2));
                System.out.println("Twoje punkty:" + humanPoints + ", punkty komputera: " + compPoints + ", remisy:" + drawPts);

            } catch (InputMismatchException ex) {
                isQuit = true;
            }
        }

        if (humanPoints > compPoints) System.out.println("WYGRAŁEŚ ŚWIAT");
        else if (humanPoints == compPoints) System.out.println("REMIS");
        else System.out.println("PRZEGRYW");


    }

    private static boolean isPlayer1Won(GameAction action1, GameAction action2) {
        return (action1 == GameAction.STONE && action2 == GameAction.SCISSORS)
                || (action1 == GameAction.PAPER && action2 == GameAction.STONE
                || (action1 == GameAction.SCISSORS && action2 == GameAction.PAPER));
    }

    private static GameResult getResult(GameAction action1, GameAction action2) {
        if (!isPlayer1Won(action1, action2) && action1 == action2) return GameResult.DRAW;
        else if (isPlayer1Won(action1, action2) && action1 != action2) return GameResult.COMPUTER_HAS_WON;
        else return GameResult.HUMAN_PLAYER_HAS_WON;
    }

    public static GameAction randomAction() {
        int temp = (int) (Math.random() * 99);
        if (temp < 33) return GameAction.STONE;
        else if (temp > 66) return GameAction.PAPER;
        else return GameAction.SCISSORS;
    }
}
