package View.GUI;

import View.Menu.LoginMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class SignUpAndLoginGraphic extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            URL fxmlURL = getClass().getResource("/fxml/signUpAndLogin.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            LoginMenu.mainStage = primaryStage;
            primaryStage.setTitle("Yu-Gi-OH!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
