package treasureFinder.jFrame;

import treasureFinder.gameMechanics.Field;

import javax.swing.*;

public class JField {

    private JLabel label;
//    private boolean isTreasureHere;
//    private boolean isHeroHere;
    private Field fieldData;

    public JField(Field fieldData) {
        this.fieldData = fieldData;
        getImageLabel();
    }

    public JLabel getLabel() {
        return label;
    }

    public void getImageLabel() {
        label = new JLabel();
        if(fieldData.isFirst())  label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\firstField.jpg"));
        else {
        
            if (fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TRLB.jpg"));
            else if (fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TLR.jpg"));
            else if (fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TLB.jpg"));
            else if (fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TRB.jpg"));
            else if (!fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RLB.jpg"));
            else if (fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TL.jpg"));
            else if (fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TR.jpg"));
            else if (!fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\LB.jpg"));
            else if (!fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RL.jpg"));
            else if (!fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RB.jpg"));
            else if (fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TB.jpg"));
            else if (fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\T.jpg"));
            else if (!fieldData.isTopNeighbour() && fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\R.jpg"));
            else if (!fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && fieldData.isBottomNeighbour() && !fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\B.jpg"));
            else if (!fieldData.isTopNeighbour() && !fieldData.isRightNeighbour() && !fieldData.isBottomNeighbour() && fieldData.isLeftNeighbour())
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\L.jpg"));
            else label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\EMPTY.jpg"));
        }
    }


}
