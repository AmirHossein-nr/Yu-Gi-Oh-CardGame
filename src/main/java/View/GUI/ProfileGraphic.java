package View.GUI;

import View.Menu.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class ProfileGraphic extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlURL = getClass().getResource("/fxml/profile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        Profile profile = new Profile();
        loader.setController(profile);
        Group root = loader.load();
//        Label username = new Label("___");
//        Label password = new Label("***");
//        Label nickname = new Label("*&&");
//        username.setLocation(386, 47);
//        password.setLocation(386,17);
//        nickname.setLocation(386, 107);
//        root.getChildren().add((Node) username);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.show();
        Profile.mainStage = primaryStage;
    }
}
