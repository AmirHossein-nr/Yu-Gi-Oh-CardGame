package Client.View.GUI;

import Client.Controller.Menu.ImportExport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ImportExportGraphic extends Application {
    public Stage mainStage;
    AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImportExport.fxml"));
        root = loader.load();
        ImportExport importExport = loader.getController();
        importExport.graphic = this;
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        mainStage = primaryStage;
        primaryStage.show();
    }

}
