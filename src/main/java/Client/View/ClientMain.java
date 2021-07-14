package Client.View;

import Client.Controller.Menu.Menu;
import Client.View.GUI.SignUpAndLoginGraphic;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Menu.initializeNetwork();
        new SignUpAndLoginGraphic().start(primaryStage);
    }
}
