package View.Menu;

import Controller.JsonController;
import Controller.Regex;
import Model.*;
import View.GUI.ImportExportGraphic;
import View.GUI.ProfileGraphic;
import animatefx.animation.BounceInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class ImportExport extends Menu {
    public static Stage mainStage;
    @FXML
    public ImageView cardImage;
    public Rectangle showCardStage;
    public AnchorPane root;

    public ImportExportGraphic graphic;

    private ArrayList<Card> selectedCards = new ArrayList<>();

    public ImportExport(Menu parentMenu) {
        super("Import/Export Menu", parentMenu);
    }

    public ImportExport() {
        super("Import/Export Menu", null);
    }

    @Override
    public void execute() {
    }

    private void exportCard(String group) throws IOException {

    }

    private void importCard(String group) throws Exception {
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    public static void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error !");
        alert.setContentText("Error While Executing File ! Please Try Again");
        alert.show();
    }

    @FXML
    public void importACard(ActionEvent actionEvent) {
        final FileChooser f = new FileChooser();
        File file = f.showOpenDialog(mainStage);
        if (file != null) {

            Card card = JsonController.readCard(file.getPath());
            if (card != null) {
                loggedUser.getAllCards().add(card);
                cardImage.setImage(card.getCardImage());
                showCardStage.setStroke(Color.GREEN);
            } else {
                createAlert();
                showCardStage.setStroke(Color.TRANSPARENT);
            }
        }
    }

    int column = 0;
    int row = 0;

    @FXML
    public void exportACard(ActionEvent actionEvent) {
        GridPane pane = new GridPane();
        pane.setMinWidth(500);
        pane.setMinHeight(300);
        root.setEffect(new GaussianBlur());

        pane.getChildren().add(new Label("Select !"));
        pane.setStyle("-fx-background-color: rgba(255,0,0,0.8);");
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(25));

        for (Card card : loggedUser.getAllCards()) {
            ImageView imageView = new ImageView();
            imageView.setImage(card.getCardImage());
            imageView.setFitWidth(70);
            imageView.setFitHeight(100);
//            CardRectangle rectangle = new CardRectangle();
//            rectangle.setRelatedCard(card);
//            rectangle.fillCard(true);
            imageView.setOnMouseClicked(event -> {
                selectedCards.add(loggedUser.getCardByImage(imageView.getImage()));
                try {
                    if (!JsonController.writeCard(card.getName())) {
                        ImportExport.createAlert();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Successful !");
                        alert.setContentText("Export Successful !");
                        alert.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            pane.add(imageView, column, row);
            column++;
            if (column >= 6) {
                column = 0;
                row++;
            }
//            pane.getChildren().add(new Rectangle(80, 10, Color.TRANSPARENT));
        }
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene pause = new Scene(pane, Color.TRANSPARENT);
        pause.getStylesheets().add("/Css/Gameplay.css");

        popupStage.setScene(pause);
        popupStage.show();
        new BounceInLeft(pane).play();

    }
}
