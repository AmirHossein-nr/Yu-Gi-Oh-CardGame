package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class GamePlay extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Yu-Gi-OH!");
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/GamePlayGraphic.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/GamePlay.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
