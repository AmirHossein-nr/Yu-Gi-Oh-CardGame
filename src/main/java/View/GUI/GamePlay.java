package View.GUI;

import Controller.Game;
import Model.*;
import View.Main;
import Controller.Menu.Shop;
import animatefx.animation.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public static AnchorPane root;
    public static Game game;

    public static User loggedUser;
    public static User rivalUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResource
                ("/images/Icons/_images_item_bg00.png")).toExternalForm()));
        game = new Game(loggedUser, rivalUser, 3);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Yu-Gi-OH!");
        Game.mainStage = mainStage;
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/gamePlay.fxml")));
        loader.setController(game);
        root = loader.load();
        Scene scene = new Scene(root);
        Button button = new Button("_c");
        root.getChildren().add(button);
        cheatSceneExecution(button);
        button.setMaxWidth(0.2);
        button.setMaxHeight(0.2);
        scene.getStylesheets().add("/Css/GamePlay.css");
        button.setStyle("-fx-text-fill: Black; -fx-opacity: 0; -fx-border-color: Black;");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));

        primaryStage.setOnShown(event -> {
            Task task = new Task() {
                @Override
                protected Object call() throws Exception {
                    game.startADuel();
                    return null;
                }
            };
            task.run();
        });
        primaryStage.show();
        game.initialiseLabelNames();
    }


    private void cheatSceneExecution(Button button) {
        button.setOnAction(event -> {
            VBox cheatRoot = new VBox(20);
            root.setEffect(new GaussianBlur());
            Label label1 = new Label("Cheat !");
            label1.setStyle("-fx-text-fill: White;-fx-font-family: 'Harlow Solid Italic'; -fx-font-size: 20px");
            cheatRoot.getChildren().add(label1);
            cheatRoot.setStyle("-fx-background-color: rgba(0,0,0,0.8);");
            cheatRoot.setAlignment(Pos.CENTER);
            cheatRoot.setPadding(new Insets(26));

            TextField textField = new TextField("Enter Your Cheat Code !");
            Button close = new Button("Close");
            Label label = new Label();
            Button button1 = new Button("OK");
            cheatRoot.getChildren().add(textField);
            Stage popUp = new Stage(StageStyle.TRANSPARENT);
            cheatRoot.getChildren().add(label);
            cheatRoot.getChildren().add(button1);
            cheatRoot.getChildren().add(close);
            button1.setOnMouseClicked(event2 -> {
                if (game.processText(textField.getText())) {
                    label.setText("Cheat Successful !");
                    label.setStyle("-fx-text-fill: Green;-fx-font-family: 'Harlow Solid Italic'; -fx-font-size: 22px");
                } else {
                    label.setText("Wrong Cheat !");
                    label.setStyle("-fx-text-fill: Red;-fx-font-family: 'Harlow Solid Italic'; -fx-font-size: 22px");
                }
            });
            popUp.initOwner(mainStage);
            popUp.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(cheatRoot, Color.TRANSPARENT);

            popUp.setScene(scene);
            popUp.show();

            new ZoomInUp(cheatRoot).play();

            close.setOnAction(event1 -> {
                ZoomOutDown right = new ZoomOutDown(cheatRoot);
                right.setOnFinished(eventt -> {
                    popUp.hide();
                });
                right.play();
                root.setEffect(null);
                mainStage.show();
            });
        });
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
        surrender.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are Giving Up !!");
            alert.setContentText("Are You Sure ?");
            alert.getButtonTypes().set(0, ButtonType.YES);
            alert.getButtonTypes().set(1, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                try {
                    game.surrender();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.close();
            }
        });
        Button exit = new Button("Exit");
        pauseRoot.getChildren().add(exit);
        exit.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Exiting ...");
            alert.setContentText("Are You Sure ?");
            alert.getButtonTypes().set(0, ButtonType.YES);
            alert.getButtonTypes().set(1, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                try {
                    root.setEffect(null);
                    mainStage.close();
                    new DuelMenuGraphic().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.close();
            }
        });
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

    public static void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
