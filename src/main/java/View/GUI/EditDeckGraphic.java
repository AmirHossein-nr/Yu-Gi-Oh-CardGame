package View.GUI;

import View.Menu.EditDeckMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class EditDeckGraphic extends Application {

    SplitPane root;
    EditDeckMenu controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editDeck.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        controller = (EditDeckMenu) loader.getController();
        primaryStage.show();
        controller.showAllCards();
    }
}
