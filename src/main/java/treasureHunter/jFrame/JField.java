package treasureHunter.jFrame;

import treasureHunter.gameMechanics.Field;

import javax.swing.*;

class JField {

    private JLabel label;
    private Field fieldData;

    JField(Field fieldData) {
        this.fieldData = fieldData;
        label = new JLabel();
        getImageLabel();
    }

    JLabel getLabel() {
        return label;
    }

    void getImageLabel() {
        switch (fieldData.toFieldTypeByEntrance()) {
            case PLAYER:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\Player.jpg"));
                break;
            case ROOM:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\Room.jpg"));
                break;
            case ENTRANCE:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\Entrance.jpg"));
                break;
            case TREASURE:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\Treasure.jpg"));
                break;
            case TOP_BOTTOM_RIGHT_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TRLB.jpg"));
                break;
            case TOP_BOTTOM_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TLB.jpg"));
                break;
            case TOP_BOTTOM_RIGHT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TRB.jpg"));
                break;
            case TOP_RIGHT_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TLR.jpg"));
                break;
            case BOTTOM_RIGHT_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RLB.jpg"));
                break;
            case TOP_BOTTOM:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TB.jpg"));
                break;
            case TOP_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TL.jpg"));
                break;
            case TOP_RIGHT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\TR.jpg"));
                break;
            case BOTTOM_LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\LB.jpg"));
                break;
            case BOTTOM_RIGHT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RB.jpg"));
                break;
            case LEFT_RIGHT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\RL.jpg"));
                break;
            case TOP:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\T.jpg"));
                break;
            case LEFT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\L.jpg"));
                break;
            case RIGHT:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\R.jpg"));
                break;
            case BOTTOM:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\B.jpg"));
                break;


            default:
                label.setIcon(new ImageIcon("src\\main\\resources\\treasureHunter\\img\\EMPTY.jpg"));
                break;
        }
    }

}
