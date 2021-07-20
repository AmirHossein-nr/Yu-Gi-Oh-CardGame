package Client.View.GUI;

import Model.User;
import Client.Controller.Menu.ScoreBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class ScoreBoardGraphic extends Application {

    public static User loggedUser = ScoreBoard.getLoggedUser();
    private ScoreBoard controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scoreBoard.fxml"));
        AnchorPane anchorPane = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(anchorPane, 800, 750);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Css/back.css"))
                .toExternalForm());
        primaryStage.setScene(scene);
        ScoreBoard.mainStage = primaryStage;
        Button refresh = new Button("Refresh");
        refresh.setLayoutX(400.0);
        refresh.setLayoutY(672.0);
        refresh.setOnMouseClicked(event -> {
            try {
                controller.reMake();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        anchorPane.getChildren().add(refresh);
        controller.refresh(anchorPane);

        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        primaryStage.show();
        ScoreBoard.mainStage = primaryStage;
    }
}
