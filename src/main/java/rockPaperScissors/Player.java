package rockPaperScissors;

public abstract class Player {

    private String nick;

    public Player(String nick) {
        this.nick = nick;
    }

    protected abstract GameAction getAction(GameAction action);

    public String getNick() {
        return nick;
    }

}
