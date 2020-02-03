package saper.gameLogic;

public class FieldObj {

    private boolean isBomb;
    private boolean isChecked;
    private boolean isOpen;
    private int numberOfBombsInSurround;
    private boolean isBlownBomb = false;

    FieldObj(boolean isBomb, boolean isOpen) {
        this.isBomb = isBomb;
        this.isChecked = false;
        this.isOpen = isOpen;
    }


    public void setBomb(boolean bomb) { isBomb = bomb; }
    public void setBlownBomb(boolean blownBomb) { isBlownBomb = blownBomb; }
    void setChecked(boolean checked) { isChecked = checked; }
    void setOpen(boolean open) { isOpen = open; }
    void setNumberOfBombsInSurround(int numberOfBombsInSurround) { this.numberOfBombsInSurround = numberOfBombsInSurround; }

    public boolean isBomb() { return isBomb; }
    public boolean isBlownBomb() { return isBlownBomb; }
    public boolean isChecked() { return isChecked; }
    public boolean isOpen() { return isOpen; }
    public int getNumberOfBombsInSurround() { return numberOfBombsInSurround; }

    public String toString(){
        if(this.isBomb && !this.isChecked && !this.isBlownBomb) return "\u26AB";
        else if(this.isBomb && this.isBlownBomb) return "\u2605";
        else{
            if(numberOfBombsInSurround==0) return ".";
            else
                return String.valueOf(getNumberOfBombsInSurround());
        }
    }
}
