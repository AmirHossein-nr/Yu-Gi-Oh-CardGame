package View.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CardMakerGraphics extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardMakerMonsterOrSpellTrap.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        primaryStage.show();
        mainStage = primaryStage;
    }

    public void openMonsterCreatingScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardMaker.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        mainStage.show();
    }

    public void openSpellTrapCreatingScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cardMakerSpellTrap.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        mainStage.show();
    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        new MainMenuGraphic().start(mainStage);
    }
}
