package rockPaperScissors;

public class StonedPlayer extends Player {

    public StonedPlayer(String nick) {
        super(nick);
    }

    @Override
    public GameAction getAction(GameAction action) {
        return action;
    }
}
