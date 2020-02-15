package treasureHunter.gameMechanics;

import treasureHunter.jFrame.FieldType;

public class Field {

    private boolean isOpen;
    private boolean topNeighbour;
    private boolean bottomNeighbour;
    private boolean rightNeighbour;
    private boolean leftNeighbour;
    private boolean isFirst = false;
    private boolean isMonster;
    private boolean isTreasure;
    private boolean isPlayer;

    Field() {
        this.isOpen = false;
        this.topNeighbour = false;
        this.bottomNeighbour = false;
        this.rightNeighbour = false;
        this.leftNeighbour = false;
        this.isMonster = false;
        this.isTreasure = false;
        this.isPlayer = false;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public boolean isFirst() {
        return isFirst;
    }

    void setFirst(boolean first) {
        isFirst = first;
    }

    boolean isOpened() {
        return isOpen;
    }

    void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isTopNeighbour() {
        return topNeighbour;
    }

    void setTopNeighbour(boolean topNeighbour) {
        this.topNeighbour = topNeighbour;
    }

    public boolean isBottomNeighbour() {
        return bottomNeighbour;
    }

    void setBottomNeighbour(boolean bottomNeighbour) {
        this.bottomNeighbour = bottomNeighbour;
    }

    public boolean isRightNeighbour() {
        return rightNeighbour;
    }

    void setRightNeighbour(boolean rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    public boolean isLeftNeighbour() {
        return leftNeighbour;
    }

    void setLeftNeighbour(boolean leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    public boolean isMonster() {
        return isMonster;
    }

    public void setMonster(boolean monster) {
        isMonster = monster;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void setTreasure(boolean treasure) {
        isTreasure = treasure;
    }

    @Override
    public String toString() {
        if(isMonster) return "r";
        if(isTreasure) return "g";
        else {
            if (topNeighbour && bottomNeighbour && rightNeighbour && leftNeighbour) return "\u254B";
            else if (topNeighbour && bottomNeighbour && rightNeighbour) return "\u2523";
            else if (topNeighbour && bottomNeighbour && leftNeighbour) return "\u252B";
            else if (topNeighbour && rightNeighbour && leftNeighbour) return "\u253B";
            else if (bottomNeighbour && rightNeighbour && leftNeighbour) return "\u2533";
            else if (topNeighbour && bottomNeighbour) return "\u2503";
            else if (topNeighbour && leftNeighbour) return "\u251B";
            else if (topNeighbour && rightNeighbour) return "\u2517";
            else if (bottomNeighbour && rightNeighbour) return "\u250F";
            else if (bottomNeighbour && leftNeighbour) return "\u2513";
            else if (rightNeighbour && leftNeighbour) return "\u2501";
            else if (topNeighbour) return "\u2579";
            else if (bottomNeighbour) return "\u257B";
            else if (rightNeighbour) return "\u257A";
            else if (leftNeighbour) return "\u2578";
            else return ".";
        }
    }

    public FieldType toFieldTypeByEntrance() {
        if(isPlayer) return  FieldType.PLAYER;
        if (isMonster) return FieldType.ROOM;
        if (isTreasure) return FieldType.TREASURE;
        if (isFirst) return FieldType.ENTRANCE;
        else {
            if (topNeighbour && bottomNeighbour && rightNeighbour && leftNeighbour)
                return FieldType.TOP_BOTTOM_RIGHT_LEFT;
            else if (topNeighbour && bottomNeighbour && rightNeighbour) return FieldType.TOP_BOTTOM_RIGHT;
            else if (topNeighbour && bottomNeighbour && leftNeighbour) return FieldType.TOP_BOTTOM_LEFT;
            else if (topNeighbour && rightNeighbour && leftNeighbour) return FieldType.TOP_RIGHT_LEFT;
            else if (bottomNeighbour && rightNeighbour && leftNeighbour) return FieldType.BOTTOM_RIGHT_LEFT;
            else if (topNeighbour && bottomNeighbour) return FieldType.TOP_BOTTOM;
            else if (topNeighbour && leftNeighbour) return FieldType.TOP_LEFT;
            else if (topNeighbour && rightNeighbour) return FieldType.TOP_RIGHT;
            else if (bottomNeighbour && rightNeighbour) return FieldType.BOTTOM_RIGHT;
            else if (bottomNeighbour && leftNeighbour) return FieldType.BOTTOM_LEFT;
            else if (rightNeighbour && leftNeighbour) return FieldType.LEFT_RIGHT;
            else if (topNeighbour) return FieldType.TOP;
            else if (bottomNeighbour) return FieldType.BOTTOM;
            else if (rightNeighbour) return FieldType.RIGHT;
            else if (leftNeighbour) return FieldType.LEFT;
            else return FieldType.NO_ENTRIES;
        }
    }
}
