package treasureFinder.gameMechanics;

import treasureFinder.jFrame.FieldTypeByEntrance;

public class Field {

    private boolean isOpen;
    private boolean topNeighbour;
    private boolean bottomNeighbour;
    private boolean rightNeighbour;
    private boolean leftNeighbour;
    private boolean isHidden;
    private boolean isFirst = false;
    private boolean isRoom;
    private boolean isTreasure;
    private boolean isPlayer;

    Field(boolean isOpen, boolean topNeighbour, boolean bottomNeighbour, boolean rightNeighbour, boolean leftNeighbour) {
        this.isOpen = isOpen;
        this.topNeighbour = topNeighbour;
        this.bottomNeighbour = bottomNeighbour;
        this.rightNeighbour = rightNeighbour;
        this.leftNeighbour = leftNeighbour;
        this.isHidden = true;
        this.isRoom = false;
        this.isTreasure = false;
        this.isPlayer = false;
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

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    boolean isOpened() {
        return !isOpen;
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

    public boolean isRoom() {
        return isRoom;
    }

    public void setRoom(boolean room) {
        isRoom = room;
    }

    public boolean isTreasure() {
        return isTreasure;
    }

    public void setTreasure(boolean treasure) {
        isTreasure = treasure;
    }

    @Override
    public String toString() {
        if(isRoom) return "r";
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

    public FieldTypeByEntrance toFieldTypeByEntrance() {
        if(isPlayer) return  FieldTypeByEntrance.PLAYER;
        if (isTreasure) return FieldTypeByEntrance.TREASURE;
        if (isRoom) return FieldTypeByEntrance.ROOM;
        if (isFirst) return FieldTypeByEntrance.ENTRANCE;
        else {
            if (topNeighbour && bottomNeighbour && rightNeighbour && leftNeighbour)
                return FieldTypeByEntrance.TOP_BOTTOM_RIGHT_LEFT;
            else if (topNeighbour && bottomNeighbour && rightNeighbour) return FieldTypeByEntrance.TOP_BOTTOM_RIGHT;
            else if (topNeighbour && bottomNeighbour && leftNeighbour) return FieldTypeByEntrance.TOP_BOTTOM_LEFT;
            else if (topNeighbour && rightNeighbour && leftNeighbour) return FieldTypeByEntrance.TOP_RIGHT_LEFT;
            else if (bottomNeighbour && rightNeighbour && leftNeighbour) return FieldTypeByEntrance.BOTTOM_RIGHT_LEFT;
            else if (topNeighbour && bottomNeighbour) return FieldTypeByEntrance.TOP_BOTTOM;
            else if (topNeighbour && leftNeighbour) return FieldTypeByEntrance.TOP_LEFT;
            else if (topNeighbour && rightNeighbour) return FieldTypeByEntrance.TOP_RIGHT;
            else if (bottomNeighbour && rightNeighbour) return FieldTypeByEntrance.BOTTOM_RIGHT;
            else if (bottomNeighbour && leftNeighbour) return FieldTypeByEntrance.BOTTOM_LEFT;
            else if (rightNeighbour && leftNeighbour) return FieldTypeByEntrance.LEFT_RIGHT;
            else if (topNeighbour) return FieldTypeByEntrance.TOP;
            else if (bottomNeighbour) return FieldTypeByEntrance.BOTTOM;
            else if (rightNeighbour) return FieldTypeByEntrance.RIGHT;
            else if (leftNeighbour) return FieldTypeByEntrance.LEFT;
            else return FieldTypeByEntrance.NO_ENTRIES;
        }
    }
}
