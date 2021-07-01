package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Objects;

public class DuelMenuGraphic extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("src/main/resources/FXMLs/DuelMenuGraphic.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //scene.getStylesheets().add();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
