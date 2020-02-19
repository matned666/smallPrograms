package personelInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonnelMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass()
                .getResource("/personnelInfo.fxml"));
        AnchorPane anchorPane = loader.load();
        PersonnelController controller = loader.getController();
        primaryStage.setTitle("Personnel Info");
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}