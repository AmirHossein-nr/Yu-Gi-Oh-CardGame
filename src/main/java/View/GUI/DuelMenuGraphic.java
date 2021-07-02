package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class DuelMenuGraphic extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlURL = getClass().getResource("/fxml/DuelMenuGraphic.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
