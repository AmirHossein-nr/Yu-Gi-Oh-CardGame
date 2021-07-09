package View.GUI;

import Controller.JsonController;
import Model.Card;
import Model.CardRectangle;
import Model.User;
import View.Menu.ImportExport;
import animatefx.animation.ZoomInUp;
import animatefx.animation.ZoomOutDown;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

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

    public void openStage(Button button, User loggedUser, ArrayList<Card> selectedCards) {
    }
}
