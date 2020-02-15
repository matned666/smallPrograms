package treasureHunter.gameMechanics.player;

import treasureHunter.jFrame.FieldTypeByEntrance;

public class Player {
    private FieldTypeByEntrance movementPossibilities;
    private int HP;
    private int col;
    private int row;
    private int points;

    public Player(int HP) {
        this.HP = HP;
        this.points = 0;
    }

    public void setMovementPossibilities(FieldTypeByEntrance movementPossibilities) {
        this.movementPossibilities = movementPossibilities;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
