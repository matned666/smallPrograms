package calculatorFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalcController {

    public Text operationTextField;

    @FXML
    private TextField calculatorScreen;

    private Double firstNumber;
    private Double secondNumber;
    private Operation action;
    private boolean isError;

    public CalcController() {
    }

    public void initialize(){
    }

    @FXML
    public void buttonNumberAction(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        if(obj instanceof Button) {
            Button button = (Button) obj;
            String text = button.getText();
            calculatorScreen.appendText(text);
        }
    }



    @FXML
    public void buttonACAction() {
        isError = false;
        calculatorScreen.clear();
        operationTextField.setText("");
    }

    @FXML
    public void buttonCAction() {
        isError = false;
        calculatorScreen.clear();
        operationTextField.setText("");
    }

    @FXML
    public void buttonPlusAction() {
        action = Operation.PLUS;
        operation("+");
    }

    @FXML
    public void buttonMinusAction() {
        action = Operation.MINUS;
        operation("-");
    }

    @FXML
    public void buttonTimesAction() {
        action = Operation.MULTIPLY;
        operation("x");
    }

    @FXML
    public void buttonDivideAction() {
        action = Operation.DIVIDE;
        operation("/");
    }

    public void buttonModuloAction() {
        action = Operation.MODULO;
        operation("%");
    }

    @FXML
    public void buttonEqualsAction() {
        secondNumber = getNumberFromScreen();
        operationTextField.setText(operationTextField.getText()+secondNumber+"=");
        calculatorScreen.setText(String.valueOf(equalsNumber()));
        firstNumber = Double.parseDouble(calculatorScreen.getText());
        if(isError) calculatorScreen.setText("ERR");
    }

    private Double getNumberFromScreen(){
        return Double.parseDouble(calculatorScreen.getText());
    }

    private void operation(String op){
        firstNumber = Double.parseDouble(calculatorScreen.getText());
        operationTextField.setText(firstNumber+op);
        calculatorScreen.clear();
    }

    private Double equalsNumber(){
        try {
            switch (action) {
                case PLUS: return firstNumber+secondNumber;
                case MINUS: return firstNumber-secondNumber;
                case DIVIDE: return firstNumber/secondNumber;
                case MULTIPLY: return firstNumber*secondNumber;
                case MODULO: return firstNumber%secondNumber;
                default:
                    isError = true;
                    return null;
            }
        }catch(Exception ex){
            isError = true;
            return null;
        }
    }

    @FXML
    public void buttonDotAction() {
        calculatorScreen.appendText(".");

    }

    public void menuFileCloseAction() {

    }

    public void menuEditHistoryAction() {
    }

    public void buttonPlusMinusAction() {
        double temp = getNumberFromScreen();
        temp *= -1;
        calculatorScreen.setText(String.valueOf(temp));
    }


}
