package View.GUI;

import animatefx.animation.BounceInLeft;
import animatefx.animation.BounceOutRight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class GamePlay extends Application {

    protected static Stage mainStage;
    private static AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.getIcons().add(new Image(getClass().getResource
                ("/images/Icons/_images_item_bg00.png").toExternalForm()));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Yu-Gi-OH!");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/GamePlayGraphic.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/GamePlay.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void pauseButtonExecution() {
        VBox pauseRoot = new VBox(20);
        root.setEffect(new GaussianBlur());

        pauseRoot.getChildren().add(new Label("Paused !"));
        pauseRoot.setStyle("-fx-background-color: rgba(255,0,0,0.8);");
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setPadding(new Insets(25));

        Button resume = new Button("Resume");
        pauseRoot.getChildren().add(resume);

        Button surrender = new Button("Surrender !");
        pauseRoot.getChildren().add(surrender);

        Button save = new Button("Save");
        pauseRoot.getChildren().add(save);

        Button exit = new Button("Exit");
        pauseRoot.getChildren().add(exit);

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene pause = new Scene(pauseRoot, Color.TRANSPARENT);
        pause.getStylesheets().add("/Css/Gameplay.css");

        popupStage.setScene(pause);
        popupStage.show();
        new BounceInLeft(pauseRoot).play();

        resume.setOnAction(event -> {

            BounceOutRight right = new BounceOutRight(pauseRoot);
            right.setOnFinished(eventt -> {
                popupStage.hide();
            });
            right.play();
            root.setEffect(null);
            mainStage.show();
        });
    }

}
