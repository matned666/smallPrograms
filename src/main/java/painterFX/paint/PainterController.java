package painterFX.paint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import painterFX.paint.shapes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PainterController {


    @FXML
    public Button starTool;
    @FXML
    public Button multiplyTool1;
    @FXML
    public Button pointTool;
    @FXML
    private Button lineTool;
    @FXML
    private Button rectangleTool;
    @FXML
    private Button triangleTool;
    @FXML
    private Button circleTool;
    @FXML
    private Button ellipseTool;
    @FXML
    private ColorPicker fillColorTool;
    @FXML
    private ColorPicker strokeColorTool;
    @FXML
    private Canvas canvas;

    private boolean multiply;
    private LinkedList<Shape> shapeList;
    private Shape currentShape;
    private GraphicsContext context;
    private Tool currentTool;
    private  double startX;
    private  double startY;
    private  double endX;
    private  double endY;
    private boolean isBrushToolOn;

    public PainterController() {
    }

    public void initialize(){
        currentTool = Tool.LINE;
        multiply = false;
        isBrushToolOn = false;
        shapeList = new LinkedList<>();
        fillColorTool.setValue(Color.RED);
        strokeColorTool.setValue(Color.BLACK);


        refreshCanvas();
        canvas.setOnMousePressed(event -> {
            startX = event.getX();
            startY = event.getY();
//            System.out.printf("MPressed: start: x=%f, y=%f \n",startX,startY);
        });

        canvas.setOnMouseReleased(event -> {


            endX = event.getX();
            endY = event.getY();
//            System.out.printf("MReleased: end: x=%f, y=%f \n",endX,endY);
            prepareShape();
            applyShape();
            refreshCanvas();

        });

        canvas.setOnMouseDragged(event -> {

            endX = event.getX();
            endY = event.getY();

            if(isBrushToolOn){
                startX = event.getX();
                startY = event.getY();
            }
//            System.out.printf("MDragged: actual: x=%f, y=%f \n",endX,endY);
            prepareShape();
            if (multiply || isBrushToolOn) applyShape();
            refreshCanvas();
        });
    }

    private void applyShape() {
        shapeList.add(currentShape);
    }

    private void prepareShape() {
        currentShape = shape();
        currentShape.setStrokeColor(strokeColorTool.getValue());
        currentShape.setFillColor(fillColorTool.getValue());
    }

    private Shape shape(){
            switch(currentTool) {
                default:
                case LINE:
                    isBrushToolOn = false;
                    return new Line(startX, startY, endX, endY);
                case RECTANGLE:
                    isBrushToolOn = false;
                    return new Rectangle(startX, startY, endX, endY);
                case TRIANGLE:
                    isBrushToolOn = false;
                    return new Triangle(startX, startY, endX, endY);
                case CIRCLE:
                    isBrushToolOn = false;
                    return new Circle(startX, startY, endX, endY);
                case ELLIPSE:
                    isBrushToolOn = false;
                    return new Ellipse(startX, startY, endX, endY);
                case STAR:
                    isBrushToolOn = false;
                    return new Star(startX, startY, endX, endY);
                case POINT:
                    isBrushToolOn = true;
                    return new Point(startX, startY);
            }
        }

    private void refreshCanvas() {
        context = canvas.getGraphicsContext2D();
        context.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        context.setStroke(Color.BLACK);
        context.strokeRect(0,0,canvas.getWidth(), canvas.getHeight());
        for (Shape shape: shapeList){
            shape.drawShape(context);
        }
        if(currentShape!= null) {
            currentShape.drawShape(context);
        }

    }

    public  void changeTool(ActionEvent actionEvent){
        Object source = actionEvent.getSource();
        if(source == lineTool){
            currentTool = Tool.LINE;
        }else if(source == rectangleTool){
            currentTool = Tool.RECTANGLE;
        }else if(source == circleTool){
            currentTool = Tool.CIRCLE;
        }else if(source == ellipseTool){
            currentTool = Tool.ELLIPSE;
        }else if(source ==triangleTool){
            currentTool = Tool.TRIANGLE;
        }else if(source ==starTool){
            currentTool = Tool.STAR;
        }else if(source ==pointTool){
            currentTool = Tool.POINT;
        }else{
            currentTool = Tool.NO_TOOL;
        }
    }

    public void newButtonAction() {
        context.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        shapeList.clear();
    }

    public void multiplyAll() {
        multiply = !multiply;
    }

    public void saveAction() {
        Optional<String> reduce = shapeList.stream()
                .map(Shape::getData)
                .reduce((acc, text) -> acc + "\n" + text);
        if (reduce.isPresent()) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YOLO files (*.yolo)", "*.yolo");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                saveTextToFile(reduce.get(), file);
            }
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadAction() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YOLO files (*.yolo)", "*.yolo");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(new Stage());
        List<String[]> temp = new LinkedList<>();
        if (file != null) {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            if(sc.hasNextLine())temp.add(sc.nextLine().split(";"));
            else temp.add(sc.next().split(";"));
          }
        }
        readFile(temp);
    }

    private void readFile(List<String[]> list) {
        for (String[] savedElement : list) {
            StringBuilder fillTemp = new StringBuilder();
            StringBuilder strokeTemp = new StringBuilder();
            for(int i = 2; i < 8; i++) {
                fillTemp.append(savedElement[5].charAt(i));
                strokeTemp.append(savedElement[6].charAt(i));
            }
            savedElement[5] = fillTemp.toString();
            savedElement[6] = strokeTemp.toString();
            chooseType(savedElement[0]);
            startX = Double.parseDouble(savedElement[1]);
            startY = Double.parseDouble(savedElement[2]);
            endX = Double.parseDouble(savedElement[3]);
            endY = Double.parseDouble(savedElement[4]);
            currentShape = shape();
            currentShape.setStrokeColor(Color.valueOf(savedElement[6]));
            currentShape.setFillColor(Color.valueOf(savedElement[5]));
            applyShape();
            refreshCanvas();
        }
    }

    private void chooseType(String toolType){
        switch (toolType) {
            case "Line":
                currentTool = Tool.LINE;
                break;
            case "Rectangle":
                currentTool = Tool.RECTANGLE;
                break;
            case "Triangle":
                currentTool = Tool.TRIANGLE;
                break;
            case "Circle":
                currentTool = Tool.CIRCLE;
                break;
            case "Ellipse":
                currentTool = Tool.ELLIPSE;
                break;
            case "Star":
                currentTool = Tool.STAR;
                break;
            case "Point":
                currentTool = Tool.POINT;
                break;
            default:
                break;
        }
    }
}
