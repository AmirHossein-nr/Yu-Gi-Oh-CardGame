package View.Menu;

import Model.*;
import View.GUI.DeckGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

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
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public EditDeckMenu() {
        super("Edit Deck", null);
        // --------------- Test ---------------
//        deck = new Deck(new MainDeck(false), new SideDeck(false));
//        loggedUser = new User("amirHossein", "1234", "amir");
//        new Shop(null);
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(10));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(15));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(14));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(13));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(25));
//        loggedUser.getAllCards().add(Shop.getAllCards().get(35));
        // --------------- Test ---------------
    }

    public void showAllCards() {
        allCardsPane.setHgap(10);
        allCardsPane.setVgap(10);
        for (Card card : loggedUser.getAllCards()) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(170);
            imageView.setImage(card.getCardImage());
            dragControl(imageView);
            imageViews.add(imageView);
        }
        updateCards();
    }

    @FXML
    public void addCardsToMain() {
        mainDeck.setVgap(10);
        mainDeck.setHgap(10);
        for (Card card : cards) {
            if (deck.getMainDeck().getCardsInMainDeck().size() >= 60) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Main Deck Is Full !");
                alert.show();
                return;
            }
            if (checkCards(card)) return;
            deck.getMainDeck().getCardsInMainDeck().add(card);
            addImageView(card, mainDeck);
        }
        cards.clear();
        updateCards();
    }

    public void updateCards() {
        allCardsPane.getChildren().removeIf(node -> !(node instanceof Button || node instanceof Label));
        for (ImageView imageView : imageViews) {
            imageView.setFitWidth(100);
            imageView.setFitHeight(170);
            dragControl(imageView);
            allCardsPane.getChildren().add(imageView);
        }
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
            if (deck.getSideDeck().getCardsInSideDeck().size() >= 15) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("Side Deck Is Full !");
                alert.show();
                return;
            }
            if (checkCards(card)) return;
            deck.getSideDeck().getCardsInSideDeck().add(card);
            addImageView(card, sideDeck);
        }
        cards.clear();
        updateCards();
    }

    @FXML
    public void goBack(ActionEvent event) throws Exception {
        new DeckGraphic().start(mainStage);
    }

    private void addImageView(Card card, FlowPane sideDeck) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(170);
        imageView.setImage(card.getCardImage());
        for (ImageView view : imageViews) {
            if (view.getImage() == imageView.getImage()) {
                imageViews.remove(view);
                break;
            }
        }
        sideDeck.getChildren().add(imageView);
    }

    private boolean checkCards(Card card) {
        int counter = 0;
        for (Card myCard : deck.getMainDeck().getCardsInMainDeck()) {
            if (myCard.getName().equals(card.getName()))
                counter++;
            if (counter >= 3)
                break;
        }
        for (Card myCard : deck.getSideDeck().getCardsInSideDeck()) {
            if (myCard.getName().equals(card.getName()))
                counter++;
            if (counter >= 3)
                break;
        }

        if (counter >= 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("There Are Already Three Instances Of This Card In this Deck !");
            alert.show();
            return true;
        }
        return false;
    }

}
