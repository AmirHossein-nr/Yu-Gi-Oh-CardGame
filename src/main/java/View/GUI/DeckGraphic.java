package View.GUI;

import Controller.Menu.DeckMenu;
import Controller.Menu.EditDeckMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DeckGraphic extends Application {
    GridPane root;
    DeckMenu controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/deck.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/deck.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        EditDeckMenu.mainStage = primaryStage;
        controller = (DeckMenu) loader.getController();
        controller.updateView();
        primaryStage.show();
    }
}
