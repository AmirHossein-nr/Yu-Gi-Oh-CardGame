package View.GUI;

import Model.*;
import Controller.Menu.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DuelMenuGraphic extends Application {

    public static User loggedUser;
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        MainMenu.mainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/duelMenu.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        createOtherPlayers(root);
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        scene.getStylesheets().add("/Css/duelMenu.css");
        primaryStage.show();
    }

    public void createOtherPlayers(AnchorPane anchorPane) {
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
                        GamePlay.rivalUser = User.getUserByNickname(label.getText());
                        GamePlay.loggedUser = loggedUser;
                        mainStage.close();
                        mainStage = new Stage();
                        gamePlay.start(mainStage);
                    } catch (Exception ignored) {
                    }
                });
                count += 1;
                anchorPane.getChildren().addAll(label, button);
            }
        }
    }

}
