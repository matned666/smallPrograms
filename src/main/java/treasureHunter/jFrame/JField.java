package treasureHunter.jFrame;

import treasureHunter.gameMechanics.Field;

import javax.swing.*;

class JField {

    private JLabel label;
    private Field fieldData;
    private final String RESOURCES_PATH = "src\\main\\resources\\treasureHunter\\img\\";

    JField(Field fieldData) {
        this.fieldData = fieldData;
        label = new JLabel();
        getImageLabel();
    }

    JLabel getLabel() {
        return label;
    }

    void getImageLabel() {
        label.setIcon(new ImageIcon(RESOURCES_PATH+getImage()+".jpg"));
    }

    private String getImage() {
        switch (fieldData.toFieldTypeByEntrance()) {
            case PLAYER:
                return "Player";
            case ROOM:
                return "Room";
            case ENTRANCE:
                return "Entrance";
            case TREASURE:
                return "Treasure";
            case TOP_BOTTOM_RIGHT_LEFT:
                return "TRLB";
            case TOP_BOTTOM_LEFT:
                return "TLB";
            case TOP_BOTTOM_RIGHT:
                return "TRB";
            case TOP_RIGHT_LEFT:
                return "TLR";
            case BOTTOM_RIGHT_LEFT:
                return "RLB";
            case TOP_BOTTOM:
                return "TB";
            case TOP_LEFT:
                return "TL";
            case TOP_RIGHT:
                return "TR";
            case BOTTOM_LEFT:
                return "LB";
            case BOTTOM_RIGHT:
                return "RB";
            case LEFT_RIGHT:
                return "RL";
            case TOP:
                return "T";
            case LEFT:
                return "L";
            case RIGHT:
                return "R";
            case BOTTOM:
                return "B";
            default:
                return "EMPTY";
        }
    }

}
