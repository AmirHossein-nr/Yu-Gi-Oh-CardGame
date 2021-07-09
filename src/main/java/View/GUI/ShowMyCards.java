package View.GUI;

import Controller.JsonController;
import Model.Card;
import Model.CardRectangle;
import Model.User;
import View.Menu.ImportExport;
import View.Menu.Shop;
import animatefx.animation.BounceInLeft;
import animatefx.animation.BounceOutRight;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class ShowMyCards extends Application {

    AnchorPane root;

    Stage mainStage;
    public static User loggedUser;
    public static ArrayList<Card> selectedCards;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loggedUser = new User("mammad", "123", "asghar");
        new Shop();
        loggedUser.getAllCards().add(Shop.getAllCards().get(5));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showMyCards.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        mainStage = primaryStage;
        primaryStage.show();
        updateView();
    }

    private void updateView() {

    }
}
