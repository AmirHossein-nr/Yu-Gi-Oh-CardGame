package Client.Controller.Menu;

import Model.*;
import Model.SideDeck;
import Client.View.GUI.EditDeckGraphic;
import Client.View.GUI.MainMenuGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckMenu extends Menu {
    public static Stage mainStage;

    private int column = 0;
    private int row = 0;

    @FXML
    public GridPane pane;
    public Button backButton;
    public ScrollPane scrollPane;
    public GridPane gridPane;

    public DeckMenu(Menu parentMenu) {
        super("Deck Menu", parentMenu);
    }

    public DeckMenu() {
        super("Deck Menu", null);
    }

    @Override
    public void execute() {

    }

    @FXML
    public void createADeck() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Creating a New Deck");
        dialog.setContentText("Enter Deck Name : ");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/Css/dialogs.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        dialog.showAndWait();
        String name = dialog.getEditor().getText();
        if (name.isEmpty()) return;
        Deck deck = Deck.getDeckByDeckName(name, loggedUser);
        if (deck == null) {
            deck = new Deck(new MainDeck(false), new SideDeck(false));
            deck.setName(name);
            loggedUser.getDecks().add(deck);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error !");
            alert.setContentText("Deck With This Name Already Exists !");
            dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/Css/dialogs.css")).toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.show();
        }
        updateView();
    }

    public void editDeck(ActionEvent event) throws Exception {
        String name = ((Label) (((GridPane) ((Button) event.getSource()).getParent()).getChildren().get(0))).getText();
        String deckName = "";
        Pattern pattern = Pattern.compile("Deck Name : (\\w+)");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) deckName = matcher.group(1);
        EditDeckMenu.deck = Deck.getDeckByDeckName(deckName, loggedUser);
        new EditDeckGraphic().start(mainStage);
    }

    public void updateView() {
        pane.getChildren().clear();
        for (Deck deck : loggedUser.getDecks()) {
            GridPane box = new GridPane();
            if (!deck.getActive())
                box.setId("box");
            else
                box.setId("activatedBox");
            box.getStylesheets().add("/Css/box.css");
            box.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            Label nameLabel = new Label("Deck Name : " + deck.getName());
            box.add(nameLabel, 1, 0);
            Label mainDeck = new Label("Main Deck : " + loggedUser.getDeckByName(deck.getName()).getMainDeck()
                    .getCardsInMainDeck().size() + "/60");
            box.add(mainDeck, 0, 1);
            Label sideDeck = new Label("Side Deck : " + loggedUser.getDeckByName(deck.getName()).getSideDeck()
                    .getCardsInSideDeck().size() + "/15");
            box.add(sideDeck, 0, 2);
            Button edit = new Button("Edit Deck");
            edit.setOnAction(event -> {
                try {
                    editDeck(event);
                } catch (Exception ignored) {
                }
            });
            box.add(edit, 0, 4);
            Button delete = new Button("Delete Deck");
            delete.setOnMouseClicked(event -> deleteDeck(deck));
            box.add(delete, 2, 4);
            Button activate = new Button("Set Active");
            activate.setOnMouseClicked(event -> activateADeck(deck));
            box.add(activate, 1, 4);

            pane.add(box, column, row);
            column++;
            if (column % 2 == 0) {
                row++;
                column = 0;
            }
        }
        column = 0;
        row = 0;
    }

    public void deleteDeck(Deck deck) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Deleting ...");
        alert.setContentText("Are You Sure ?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/Css/dialogs.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.getButtonTypes().set(0, ButtonType.YES);
        alert.getButtonTypes().set(1, ButtonType.NO);
        if (alert.showAndWait().get() == ButtonType.YES) {
            loggedUser.getDecks().remove(deck);
            updateView();
        } else {
            alert.close();
        }
    }

    public void activateADeck(Deck deck) {
        for (Deck dec : loggedUser.getDecks()) {
            if (dec.getActive()) {
                dec.setActive(false);
                break;
            }
        }
        deck.setActive(true);
        deck.setValid(true);
        deck.getMainDeck().setActive(true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Activated !");
        alert.setContentText("Deck Activated Successfully!");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/Css/dialogs.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.show();
        updateView();
    }

    @FXML
    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenuGraphic().start(mainStage);
    }
}
