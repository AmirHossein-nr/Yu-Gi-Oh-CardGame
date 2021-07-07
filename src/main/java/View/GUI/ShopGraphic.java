package View.GUI;

import View.Menu.Shop;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopGraphic extends Application {

    public ShopGraphic () {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("YU-GI-OH");
        primaryStage.setScene(scene);
        Shop.mainStage = primaryStage;
        primaryStage.show();
    }

}
