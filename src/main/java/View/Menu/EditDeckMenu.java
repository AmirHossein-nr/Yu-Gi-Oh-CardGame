package View.Menu;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class EditDeckMenu extends Menu {

    public static Stage mainStage;
    public static Deck deck;

    @FXML
    public FlowPane allCardsPane;
    public FlowPane mainDeck;
    public FlowPane sideDeck;

    private ArrayList<Card> cards = new ArrayList<>();

    public EditDeckMenu() {
        super("Edit Deck", null);
        // --------------- Test ---------------
        deck = new Deck(new MainDeck(false), new SideDeck(false));
        loggedUser = new User("amirHossein", "1234", "amir");
        new Shop(null);
        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
        // --------------- Test ---------------
    }

    public void showAllCards() {
        allCardsPane.setHgap(10);
        allCardsPane.setVgap(10);
        for (Card card : loggedUser.getAllCards()) {
            if (cards.contains(card)) continue;
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(170);
            imageView.setImage(card.getCardImage());
            dragControl(imageView);
            allCardsPane.getChildren().add(imageView);
        }
    }

    @FXML
    public void addCardsToMain() {
        mainDeck.setVgap(10);
        mainDeck.setHgap(10);
        for (Card card : cards) {
            deck.getMainDeck().getCardsInMainDeck().add(card);
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(170);
            imageView.setImage(card.getCardImage());
            mainDeck.getChildren().add(imageView);
        }
        cards.clear();
        showAllCards();
    }

    private void dragControl(ImageView imageView) {
        imageView.setOnMouseClicked(event -> {
            if (!(imageView.getEffect() instanceof DropShadow)) {
                imageView.setEffect(new DropShadow());
                cards.add(loggedUser.getCardByImage(imageView.getImage()));
            } else {
                imageView.setEffect(null);
                cards.remove(loggedUser.getCardByImage(imageView.getImage()));
            }
        });
    }

    @FXML
    public void addCardsToSide(MouseEvent mouseEvent) {
        sideDeck.setVgap(10);
        sideDeck.setHgap(10);

        for (Card card : cards) {
            deck.getSideDeck().getCardsInSideDeck().add(card);
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(170);
            imageView.setImage(card.getCardImage());
            sideDeck.getChildren().add(imageView);
        }
        cards.clear();
        showAllCards();
    }
}
