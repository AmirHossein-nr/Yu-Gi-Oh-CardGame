package View.GUI;

import Controller.Menu.LoginMenu;
import Controller.Menu.RegisterMenu;
import Controller.Menu.SignUpAndLoginMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class SignUpAndLoginGraphic extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            SignUpAndLoginMenu.mainStage = primaryStage;
            RegisterMenu.mainStage = primaryStage;
            LoginMenu.mainStage = primaryStage;
            URL fxmlURL = getClass().getResource("/fxml/signUpAndLogin.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Yu-Gi-OH!");
            primaryStage.getIcons().add(new Image("/images/Icons/_images_item_bg00.png"));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
