package personelInfo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import personelInfo.mechanics.Company;
public class PersonnelController {


    @FXML
    public TextField additionalSearchTextField;
    @FXML
    public VBox vBoxWithWorkers;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField workersIdTextField;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private TextField numberOfWorkersTextField;
    @FXML
    private ChoiceBox<String> sortByChoiceBox;
    @FXML
    private ChoiceBox<String> workersTypeShowChoiceBox;
    @FXML
    public ChoiceBox<String> workerStatusChoiceBox;



    private Company company;
    private CompanyFX companyFX;
    private PersonButtonFX actualPerson;

    public PersonnelController() {
    }

    @FXML
    private void initialize(){
        initializeChoiceBoxes();

    }

    private void initializeChoiceBoxes(){
        ObservableList<String> sortByChoiceBoxList = FXCollections
                .observableArrayList(
                        SortType.SORT_BY_ID.toString(),SortType.SORT_BY_ID_REV.toString(),
                        SortType.SORT_BY_NAME.toString(),SortType.SORT_BY_NAME_REV.toString(),
                        SortType.SORT_BY_SURNAME.toString(),SortType.SORT_BY_SURNAME_REV.toString(),
                        SortType.SORT_BY_AGE.toString(),SortType.SORT_BY_AGE_REV.toString(),
                        SortType.SORT_BY_POSITION.toString(),SortType.SORT_BY_POSITION_REV.toString());

        ObservableList<String> workersTypeShowChoiceBoxList = FXCollections
                .observableArrayList(
                        WorkersType.ACTUAL_WORKER.toString(),
                        WorkersType.ACTUAL_AND_REMOVED.toString(),
                        WorkersType.REMOVED_WORKER.toString());

        ObservableList<String> workersTypeList = FXCollections
                .observableArrayList(
                        WorkersType.ACTUAL_WORKER.toString(),
                        WorkersType.REMOVED_WORKER.toString());

        sortByChoiceBox.setValue(SortType.SORT_BY_ID.toString());
        sortByChoiceBox.setItems(sortByChoiceBoxList);
        workersTypeShowChoiceBox.setValue(WorkersType.ACTUAL_WORKER.toString());
        workersTypeShowChoiceBox.setItems(workersTypeShowChoiceBoxList);
        workerStatusChoiceBox.setValue(WorkersType.ACTUAL_WORKER.toString());
        workerStatusChoiceBox.setItems(workersTypeList);
    }


    @FXML
    private void confirmAndNext(ActionEvent actionEvent) {
        actualPerson.setAge(Integer.parseInt(ageTextField.getText()));
        actualPerson.setName(nameTextField.getText());
        actualPerson.setSurName(surnameTextField.getText());
        actualPerson.setPosition(positionTextField.getText());
        actualPerson.setWorkersType(workerType(workerStatusChoiceBox.getValue().trim()));
        actualPerson.getButton().setText(actualPerson.toString());
        actualPerson.getActualPerson().setAGE(Integer.parseInt(ageTextField.getText()));
        actualPerson.getActualPerson().setNAME(nameTextField.getText());
        actualPerson.getActualPerson().setSURNAME(surnameTextField.getText());
        actualPerson.getActualPerson().setPosition(positionTextField.getText());
        actualPerson.getActualPerson().setWorkerType(workerType(workerStatusChoiceBox.getValue().trim()));
        actualPerson.getButton().setText(actualPerson.toString());
    }

    private WorkersType workerType(String value){
        if ("REMOVED_WORKER".equals(value)) {
            return WorkersType.REMOVED_WORKER;
        }
        return WorkersType.ACTUAL_WORKER;
    }

    @FXML
    private void acceptCompany(ActionEvent actionEvent) {
        vBoxWithWorkers.getChildren().clear();
        company = new Company(companyNameTextField.getText(),Integer.parseInt(numberOfWorkersTextField.getText()));
        companyFX = new CompanyFX(company);
        for(PersonButtonFX el: companyFX.getList()){
            el.getButton().setOnAction(eventHandler->{
                actualPerson = el;
                setTextFields();
            });
            vBoxWithWorkers.getChildren().add(el.getButton());
        }
    }

    private void setTextFields() {
       nameTextField.setText(actualPerson.getActualPerson().getNAME());
       surnameTextField.setText(actualPerson.getActualPerson().getSURNAME());
       ageTextField.setText(String.valueOf(actualPerson.getActualPerson().getAGE()));
        positionTextField.setText(actualPerson.getActualPerson().getPosition());
        workersIdTextField.setText(String.valueOf(actualPerson.getActualPerson().getID()));
        workerStatusChoiceBox.setValue(actualPerson.getActualPerson().getWorkerType().toString());
    }

    @FXML
    private void sortBy(ActionEvent actionEvent) {
//        vBoxWithWorkers.getChildren().sort(companyFX.getCompany().sort(sortByChoiceBox.getValue()));
    }

    private WorkersType sortType(String value){
        if ("REMOVED_WORKER".equals(value)) {
            return WorkersType.REMOVED_WORKER;
        }
        return WorkersType.ACTUAL_WORKER;
    }

    @FXML
    private void addWorker(ActionEvent actionEvent) {

    }

    @FXML
    private void removeWorker(ActionEvent actionEvent) {

    }

    @FXML
    private void searchWorkers(ActionEvent actionEvent) {

    }




    @FXML
    private void menuItemNewCompany(ActionEvent actionEvent) {

    }
    @FXML
    private void menuItemClose(ActionEvent actionEvent) {

    }
    @FXML
    private void menuItemLoad(ActionEvent actionEvent) {

    }
    @FXML
    private void menuItemSave(ActionEvent actionEvent) {

    }
    @FXML
    private void menuItemInformation(ActionEvent actionEvent) {

    }
}
