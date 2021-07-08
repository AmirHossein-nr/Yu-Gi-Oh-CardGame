package View.GUI;

import View.Menu.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainMenuGraphic extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenu.mainStage = primaryStage;
        Shop.mainStage = primaryStage;
        CardMakerMenu.mainStage = primaryStage;
        Profile.mainStage = primaryStage;
        ScoreBoard.mainStage = primaryStage;
        DeckMenu.mainStage = primaryStage;
        Duel.mainStage = primaryStage;
        ImportExport.mainStage = primaryStage;
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.setScene(scene);
        primaryStage.show();
        LoginMenu.mainStage = primaryStage;
    }
}
