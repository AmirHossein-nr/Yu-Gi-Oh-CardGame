package View.GUI;

import Model.AI;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class DuelMenuGraphic extends Application {

    public static User loggedUser;
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/duelMenu.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        createOtherPlayers(root);
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        scene.getStylesheets().add("/Css/duelMenu.css");
        primaryStage.show();
        mainStage = primaryStage;
    }

    public void createOtherPlayers (AnchorPane anchorPane) {
        int count = 1;
        for (User user : User.getAllUsers()) {
            if (user != loggedUser) {
                Label label = new Label(user.getNickName());
                label.setLayoutX(10);
                label.setLayoutY(count * 45 + 5);
                label.setTextAlignment(TextAlignment.CENTER);
                label.setAlignment(Pos.CENTER);
                Button button = new Button("start duel");
                button.setCursor(Cursor.HAND);
                button.setLayoutX(300);
                button.setLayoutY(count * 45 + 5);
                button.setOnMouseClicked(event -> {
                    GamePlay gamePlay = new GamePlay();
                    try {
                        gamePlay.start(mainStage);
                        GamePlay.game.rivalUser = User.getUserByNickname(label.getText());
                    } catch (Exception ignored) {
                    }
                });
                count += 1;
                anchorPane.getChildren().addAll(label, button);
            }
        }
    }
}
