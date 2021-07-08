package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class ChangePasswordGraphic extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlURL = getClass().getResource("/fxml/changePassword.fxml");
        Parent root = FXMLLoader.load(fxmlURL);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        primaryStage.setScene(scene);
        ChangePassword.mainStage = primaryStage;
        primaryStage.show();
    }

    public static void createAlert (Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        ImageView imageView = new ImageView("/images/alertHeader.png");
        alert.setGraphic(imageView);
        alert.setHeaderText(header);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("/Css/dialogs.css");
        dialogPane.getStyleClass().add("myDialog");
        alert.show();
    }
}
