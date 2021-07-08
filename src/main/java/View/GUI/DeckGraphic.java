package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeckGraphic extends Application {
    GridPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/deck.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/deck.css");
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateDeck(Scene scene) {
        FlowPane flowPane = (FlowPane) scene.lookup("#deckContainer");

    }
}
