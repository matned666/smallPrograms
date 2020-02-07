package treasureFinder.gameMechanics;

public class Field {

    private boolean isOpen;
    private boolean topNeighbour;
    private boolean bottomNeighbour;
    private boolean rightNeighbour;
    private boolean leftNeighbour;
    private boolean isHidden;

    public Field(boolean isOpen, boolean topNeighbour, boolean bottomNeighbour, boolean rightNeighbour, boolean leftNeighbour) {
        this.isOpen = isOpen;
        this.topNeighbour = topNeighbour;
        this.bottomNeighbour = bottomNeighbour;
        this.rightNeighbour = rightNeighbour;
        this.leftNeighbour = leftNeighbour;
        this.isHidden = true;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isTopNeighbour() {
        return topNeighbour;
    }

    public void setTopNeighbour(boolean topNeighbour) {
        this.topNeighbour = topNeighbour;
    }

    public boolean isBottomNeighbour() {
        return bottomNeighbour;
    }

    public void setBottomNeighbour(boolean bottomNeighbour) {
        this.bottomNeighbour = bottomNeighbour;
    }

    public boolean isRightNeighbour() {
        return rightNeighbour;
    }

    public void setRightNeighbour(boolean rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    public boolean isLeftNeighbour() {
        return leftNeighbour;
    }

    public void setLeftNeighbour(boolean leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    @Override
    public String toString() {
        if(topNeighbour && bottomNeighbour && rightNeighbour && leftNeighbour) return "\u254B";
        else if(topNeighbour && bottomNeighbour && rightNeighbour ) return "\u2523";
        else if(topNeighbour && bottomNeighbour && leftNeighbour) return "\u252B";
        else if(topNeighbour && rightNeighbour && leftNeighbour) return "\u253B";
        else if(bottomNeighbour && rightNeighbour && leftNeighbour) return "\u2533";
        else if(topNeighbour && bottomNeighbour ) return "\u2503";
        else if(topNeighbour && leftNeighbour) return "\u251B";
        else if(topNeighbour && rightNeighbour) return "\u2517";
        else if(bottomNeighbour && rightNeighbour) return "\u250F";
        else if(bottomNeighbour && leftNeighbour) return "\u2513";
        else if(rightNeighbour && leftNeighbour) return "\u2501";
        else if(topNeighbour ) return "\u2579";
        else if(bottomNeighbour) return "\u257B";
        else if(rightNeighbour) return "\u257A";
        else if(leftNeighbour) return "\u2578";
        else return ".";
    }
}
