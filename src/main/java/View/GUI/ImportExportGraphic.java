package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ImportExportGraphic extends Application {
    public Stage mainStage;

    AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ImportExport.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        mainStage = primaryStage;
        primaryStage.show();
    }

}
