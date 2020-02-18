package personelInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import personelInfo.mechanics.Person;

public class PersonButtonFX {

    private Button button;
    private int ID;
    private int age;
    private String name;
    private String surName;
    private String position;
    private WorkersType workersType;
    private Person actualPerson;
    private EventHandler<ActionEvent> event;

    public Button getButton() {
        return button;
    }

    public Person getActualPerson() {
        return actualPerson;
    }

    public PersonButtonFX(Person person) {
        actualPerson = person;
        ID = person.getID();
        name = person.getNAME();
        surName = person.getSURNAME();
        age = person.getAGE();
        position = person.getPosition();
        workersType = person.getWorkerType();
        buttonCreation();

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setWorkersType(WorkersType workersType) {
        this.workersType = workersType;
    }

    private void buttonCreation() {
        button = new Button();
        button.setPrefSize(430,30);
        button.setText(toString());
        button.setWrapText(true);
    }

    @Override
    public String toString() {
        return "ID:"+ID+", name:"+name+", surname:"+surName+", age:"+age+", position: "+position+", status:"+workersType.toString();
    }
}
