package personnelInfo;


import javafx.scene.control.Button;
import personnelInfo.mechanics.Person;

public class WorkerField {

    private Button button;
    private Person person;

    public WorkerField(Person person) {
        button = new Button();
        this.person = person;
    }

    public WorkerField() {

    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
