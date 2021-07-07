package View.GUI;

import View.Menu.LoginMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class Register extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlURL = getClass().getResource("/fxml/register.fxml");
        Parent root = FXMLLoader.load(fxmlURL);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.setScene(scene);
        primaryStage.show();
        LoginMenu.mainStage = primaryStage;
    }

    public static void createAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(header);
        alert.setContentText(content);
        ImageView imageView = new ImageView("/images/alertHeader.png");
        alert.setGraphic(imageView);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("/Css/dialogs.css");
        dialogPane.getStyleClass().add("myDialog");
        alert.show();
    }
}
