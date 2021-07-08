package View.GUI;

import View.Menu.DeckMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DeckGraphic extends Application {
    GridPane root;
    DeckMenu controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/deck.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/deck.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        controller = (DeckMenu) loader.getController();
        primaryStage.show();
    }

    public void makeANewBox() {
        for (Deck deck : user.getDecks()) {
            TextArea deckDescription = new TextArea(deck.toString());
            deckDescription.setEditable(false);
            deckDescription.setPrefHeight(150);
            deckDescription.getStyleClass().add("deck-description");

            Button editButton = new Button("Edit");
            editButton.getStyleClass().addAll("default-button", "edit-button");
            editButton.setOnMouseClicked(event -> setEditDeckScene(deck));
            Button deleteButton = new Button("Delete");
            deleteButton.getStyleClass().addAll("default-button", "delete-button");
            deleteButton.setOnMouseClicked(event -> ViewUtility.showConfirmationAlert("Deck", "Delete Deck", "Are you sure you want to delete this deck?", "No", "Yes", new Listener() {
                @Override
                public void onConfirm() {
                    controller.deleteDeck(deck.getName());
                    updateDeckScene(deckScene, user);
                }

                @Override
                public void onCancel() {
                }
            }));
            HBox buttonContainer = new HBox(5, editButton, deleteButton);
            buttonContainer.setPrefWidth(300);

            Button activateButton = new Button("Activate Deck");
            activateButton.getStyleClass().addAll("default-button", "activate-button");
            if (deck.equals(user.getActiveDeck())) activateButton.setDisable(true);
            activateButton.setOnMouseClicked(event -> {
                user.setActiveDeck(deck);
                updateDeckScene(deckScene, user);
            });

            VBox container = new VBox(7, deckDescription, buttonContainer, activateButton);
            if (deck.equals(user.getActiveDeck())) container.setId("active-deck-container");
            container.setPrefWidth(250);
            container.setPrefHeight(290);
            decksContainer.getChildren().add(container);
        }
        VBox addBox = new VBox();
        addBox.setPrefWidth(250);
        addBox.setPrefHeight(287);
        addBox.setAlignment(Pos.TOP_CENTER);

        Button addButton = new Button("+");
        addButton.setId("add-button");
        addButton.setPrefWidth(250);
        addButton.setPrefHeight(287);
        addButton.setCursor(Cursor.HAND);
        addButton.setOnMouseClicked(event -> showPromptAlert("Create new Deck",
                "Enter the deck name:",
                "Deck name",
                "Create",
                new PromptListener() {
                    @Override
                    public void onOk(String input) {
                        controller.createDeck(input);
                        updateDeckScene(deckScene, user);
                    }

                    @Override
                    public void onCancel() {
                    }
                }));
        addBox.getChildren().add(addButton);
        decksContainer.getChildren().add(addBox);
    }
    public static void showPromptAlert(String title, String message, String label, String okText, PromptListener listener) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(message);
        textInputDialog.setContentText(label);
        textInputDialog.getDialogPane().getStyleClass().add("game-alert");
        textInputDialog.getDialogPane().getStylesheets().add(ViewUtility.class.getResource("/css/alert.css").toExternalForm());

        textInputDialog.getDialogPane().getButtonTypes().clear();
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.NO);
        ButtonType confirmButtonType = new ButtonType(okText, ButtonBar.ButtonData.OK_DONE);
        textInputDialog.getDialogPane().getButtonTypes().add(cancelButtonType);
        textInputDialog.getDialogPane().getButtonTypes().add(confirmButtonType);

        Optional<String> result = textInputDialog.showAndWait();
        String input = result.orElse(null);
        if (input != null) listener.onOk(input);
        else listener.onCancel();
    }
}
