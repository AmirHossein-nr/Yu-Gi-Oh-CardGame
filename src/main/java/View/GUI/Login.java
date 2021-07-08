package View.GUI;

import View.Menu.LoginMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        LoginMenu.mainStage = primaryStage;
    }

    public static void createAlert (Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.setHeaderText(header);
        ImageView imageView = new ImageView("/images/alertHeader.png");
        alert.setGraphic(imageView);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("/Css/dialogs.css");
        dialogPane.getStyleClass().add("myDialog");
        alert.show();
    }
}
