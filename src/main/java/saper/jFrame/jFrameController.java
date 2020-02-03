package saper.jFrame;

import javax.swing.*;

public class jFrameController {

    private JLabel firstWindowMessage = new JLabel();

    public jFrameController() {
      initialize();
    }

    private void initialize() {
        firstWindowMessage.setBounds(10,5,400,30);
        firstWindowMessage.setText("Input parameters of your game:");
        firstWindow();
    }

    private void firstWindow(){
        JFrame firstWindow = new JFrame();
        firstWindow.setTitle("Super Retro Saper");
        firstWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        firstWindow.setSize(540,200);
        firstWindow.setResizable(true);

        JButton acceptButton = new JButton(new ImageIcon("src\\main\\resources\\saper\\buttonAccept.png"));
        acceptButton.setBounds(10,100,400,40);
        acceptButton.setText("ACCEPT GAME");
        acceptButton.setHorizontalTextPosition(JLabel.CENTER);
        acceptButton.setVerticalTextPosition(JLabel.CENTER);


        JTextField chooseXSizeField = new JTextField();
        JTextField chooseYSizeField = new JTextField();
        JTextField chooseNumberOfBombs = new JTextField();
        chooseXSizeField.setBounds(10,50,120,40);
        chooseYSizeField.setBounds(150,50,120,40);
        chooseNumberOfBombs.setBounds(290,50,120,40);

        JLabel chooseXSizeFieldJLabel = new JLabel();
        JLabel chooseYSizeFieldJLabel= new JLabel();
        JLabel chooseNumberOfBombsJLabel = new JLabel();
        chooseXSizeFieldJLabel.setBounds(10,25,120,30);
        chooseYSizeFieldJLabel.setBounds(150,25,120,30);
        chooseNumberOfBombsJLabel.setBounds(290,25,120,30);
        chooseXSizeFieldJLabel.setText("X size:");
        chooseYSizeFieldJLabel.setText("Y size:");
        chooseNumberOfBombsJLabel.setText("Number of bombs:");

        firstWindow.add(acceptButton);
        firstWindow.add(chooseXSizeField);
        firstWindow.add(chooseYSizeField);
        firstWindow.add(chooseNumberOfBombs);
        firstWindow.add(chooseXSizeFieldJLabel);
        firstWindow.add(chooseYSizeFieldJLabel);
        firstWindow.add(chooseNumberOfBombsJLabel);
        firstWindow.add(firstWindowMessage);
        firstWindow.add(firstWindowImageLabel());

        acceptButton.addActionListener(actionEvent -> {
            try {
                jMatrix matrix = new jMatrix(Integer.parseInt(chooseXSizeField.getText()), Integer.parseInt(chooseYSizeField.getText()), Integer.parseInt(chooseNumberOfBombs.getText()));

            }catch(Exception ex){
                firstWindowMessage.setText("Input CORRECT parameters of your game:");
            }
        });

        firstWindow.setLayout(null);
        firstWindow.setVisible(true);
    }

    private JLabel firstWindowImageLabel() {
        JLabel imageLabel = new JLabel(new ImageIcon("src\\main\\resources\\saper\\mine_field_first.png"));
        imageLabel.setBounds(420, 10, 100, 150);
        return imageLabel;
    }

}
