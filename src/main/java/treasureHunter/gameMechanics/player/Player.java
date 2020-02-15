package treasureHunter.gameMechanics.player;

import treasureHunter.jFrame.FieldType;

public class Player {
    private FieldType movementPossibilities;
    private int HP;
    private int col;
    private int row;
    private int collectedTreasuresInLevel;

    public Player(int HP) {
        this.HP = HP;
        this.collectedTreasuresInLevel = 0;
    }

    public void setMovementPossibilities(FieldType movementPossibilities) {
        this.movementPossibilities = movementPossibilities;
    }

    public void setCollectedTreasuresInLevel(int collectedTreasuresInLevel) {
        this.collectedTreasuresInLevel = collectedTreasuresInLevel;
    }

    public int getCollectedTreasuresInLevel() {
        return collectedTreasuresInLevel;
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
