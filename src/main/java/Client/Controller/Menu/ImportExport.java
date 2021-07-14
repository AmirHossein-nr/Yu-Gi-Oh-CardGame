package Client.Controller.Menu;

import Client.Controller.JsonController;
import Model.*;
import Client.View.GUI.ImportExportGraphic;
import Client.View.GUI.MainMenuGraphic;
import animatefx.animation.BounceInLeft;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
        super("import/Export Menu", null);
    }

    @Override
    public void execute() {
    }


    public static void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error !");
        alert.setContentText("Error While Executing File ! Please Try Again");
        alert.show();
    }

    @FXML
    public void importACard(ActionEvent actionEvent) {
        new Shop();
        final FileChooser f = new FileChooser();
        File file = f.showOpenDialog(mainStage);
        if (file != null) {
            Card card = JsonController.readCard(file.getPath());
            if (card != null) {
                cardImage.setImage(Shop.getCardByName(card.getName()).getCardImage());
                showCardStage.setStroke(Color.GREEN);
                loggedUser.getAllCards().add(card);
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
        new Shop();
        GridPane pane = new GridPane();
        pane.setMinWidth(500);
        pane.setMinHeight(300);
        root.setEffect(new GaussianBlur());

        pane.getChildren().add(new Label("Select !"));
        pane.setStyle("-fx-background-color: rgba(255,0,0,0.8);");
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(25));

        Gson gson = null;
        File file = null;
        FileWriter fileWriter = null;

        for (Card card : loggedUser.getAllCards()) {
            ImageView imageView = new ImageView();
            imageView.setImage(card.getCardImage());
            imageView.setFitWidth(70);
            imageView.setFitHeight(100);
            imageView.setOnMouseClicked(event -> {
                selectedCards.add(loggedUser.getCardByImage(imageView.getImage()));
            });
            pane.add(imageView, column, row);
            column++;
            if (column >= 6) {
                column = 0;
                row++;
            }
        }

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        Scene pause = new Scene(pane, Color.TRANSPARENT);
        pause.getStylesheets().add("/Css/Gameplay.css");
        Button button = new Button("!!");
        button.setOnAction(event -> {
            try {
                write();
                new BounceInLeft(pane).play();
                popupStage.close();
                root.setEffect(null);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        pane.add(button, column, row + 1);

        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(pause);
        popupStage.show();
    }


    private void write() throws URISyntaxException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson;
        FileWriter fileWriter = null;
        File file;
        for (Card card : selectedCards) {
            String path = "src/main/resources/Cards/" + card.getName() + ".Json";
            try {
                gson = builder.excludeFieldsWithoutExposeAnnotation().create();
                file = (new File(path));
                fileWriter = (new FileWriter(file));
                fileWriter.write(gson.toJson(card));
                fileWriter.flush();
                fileWriter.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful !");
                alert.setContentText("Export Successful !");
                alert.show();
            } catch (Exception e) {
                ImportExport.createAlert();
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) throws Exception {
        new MainMenuGraphic().start(mainStage);
    }
}
