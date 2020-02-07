package treasureFinder.jFrame;


import treasureFinder.gameMechanics.LabirynthGenerator;

import javax.swing.*;

public class JMatrix {

    private LabirynthGenerator mazeGen;
    private JField[][] jMatrix;
    private JFrame frame;
    private final int FIELD_SIZE = 20;

    private int col;
    private int row;

    public JMatrix(int col, int row) {
        this.col = col;
        this.row = row;
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setTitle("Maze hunter");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1800,930);
        initializeMatrix();
        frame.setLayout(null);
        frame.setVisible(true);
    }



    private void initializeMatrix(){
        mazeGen = new LabirynthGenerator(col,row);
        jMatrix = new JField[row][col];

        for(int i = 0;i< jMatrix.length;i++){
            for(int j = 0;j<jMatrix[i].length;j++){
                jMatrix[i][j] = new JField(mazeGen.getMatrix()[i][j]);
                jMatrix[i][j].getLabel().setBounds(j*FIELD_SIZE+10,i*FIELD_SIZE+10,FIELD_SIZE,FIELD_SIZE);
                frame.add(jMatrix[i][j].getLabel());
            }
        }

    }


//
//    private JScrollPane scrollPane(){
//        JScrollPane scrollPane = new JScrollPane();
//
//        return null;
//    }


}
