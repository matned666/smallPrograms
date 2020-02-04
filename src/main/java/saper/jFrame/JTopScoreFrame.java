package saper.jFrame;

import saper.fileOperations.ScoreTop100List;

import javax.swing.*;

class JTopScoreFrame {

    private ScoreTop100List list;
    private JLabel resultLabel;

    JTopScoreFrame() {
        list = new ScoreTop100List();
        resultLabel = new JLabel();
        init();
    }

    private void init() {
        JFrame firstWindow = new JFrame();
        firstWindow.setTitle("Saper TOP SCORE");
        firstWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        firstWindow.setSize(535,740);
        firstWindow.setResizable(false);
        firstWindow.add(resultLabel());
        addText();
        firstWindow.setLayout(null);
        firstWindow.setVisible(true);
    }

    private JScrollPane resultLabel() {
        resultLabel.setBounds(20, 20, 480, 660);
        resultLabel.setVerticalAlignment(1);
        resultLabel.setText("");
        JScrollPane scroller = new JScrollPane(resultLabel);
        scroller.setBounds(20, 20, 480, 660);
        return scroller;
    }

    private void addText(){
        StringBuilder tempStr = new StringBuilder();
        list.read();
        tempStr.append("<html>");
        for(int i = 0; i < list.get().size(); i++){
            tempStr.append("<p>").append((list.get().get(i)).toString()).append("</p>");
        }
        tempStr.append("</html>");
        resultLabel.setText(tempStr.toString());

    }


}

class Test {

    public static void main(String[] args) {
        JTopScoreFrame a = new JTopScoreFrame();
    }

}