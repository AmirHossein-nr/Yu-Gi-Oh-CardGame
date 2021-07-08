package View.Menu;

import Controller.Regex;
import Model.*;
import Model.SideDeck;
import View.GUI.MainMenuGraphic;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;

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
        loggedUser = new User("Amirhossein", "123", "amir");
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        Matcher matcher;
        if ((matcher = Regex.getMatcher(input, Regex.createDeck)).find()) {
            String name = matcher.group(1);
            if (name != null) {
                boolean flag = false;
                for (Deck deck : loggedUser.getDecks()) {
                    if (deck.getName().equals(name)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("deck with name " + name + " already exists");
                } else {
                    Deck deck = new Deck(new MainDeck(false), new SideDeck(false));
                    deck.setName(name);
                    loggedUser.getDecks().add(deck);
                }
                this.execute();
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.deleteDeck)).find()) {

            this.execute();
        } else if ((matcher = Regex.getMatcher(input, Regex.activateDeck)).find()) {
            String name = matcher.group(1);
            boolean flag = false;
            if (name != null) {
                for (Deck deck : loggedUser.getDecks()) {
                    if (deck.getName().equals(name)) {
                        deck.setActive(true);
                        deck.getMainDeck().setActive(true);
                        System.out.println("deck activated successfully");
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("deck with name " + name + " does not exist");
                }
                this.execute();
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.addCardToDeck)).find()) {
            String deckName = matcher.group(4);
            String cardName = matcher.group(2);
            String isSide = matcher.group(5);
            boolean flag = false;
            cardName = cardName.trim();

            for (Card card : loggedUser.getAllCards()) {
                if (card.getName().equals(cardName)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("card with name " + cardName + " does not exist");
                this.execute();
            } else {
                flag = false;
                for (Deck deck : loggedUser.getDecks()) {
                    if (deck.getName().equals(deckName)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("deck with name " + deckName + " does not exist");
                    this.execute();
                }
            }
            Deck deck = Deck.getDeckByDeckName(deckName, loggedUser);

            if (deck.getMainDeck().getCardsInMainDeck().size() == 60) {
                System.out.println("main deck is full");
                this.execute();
            } else if (deck.getSideDeck().getCardsInSideDeck().size() == 15) {
                System.out.println("side deck is full");
                this.execute();
            }

            Card card = null;
            if (Shop.getCardByName(cardName) instanceof Monster)
                card = (Monster) ((Monster) Shop.getCardByName(cardName)).clone();
            else if (Shop.getCardByName(cardName) instanceof Spell)
                card = (Spell) ((Spell) Shop.getCardByName(cardName)).clone();
            else if (Shop.getCardByName(cardName) instanceof Trap)
                card = (Trap) ((Trap) Shop.getCardByName(cardName)).clone();

            int counter = 0;
            for (Card myCard : deck.getMainDeck().getCardsInMainDeck()) {
                if (myCard.getName().equals(cardName))
                    counter++;
                if (counter >= 3)
                    break;
            }
            for (Card myCard : deck.getSideDeck().getCardsInSideDeck()) {
                if (myCard.getName().equals(cardName))
                    counter++;
                if (counter >= 3)
                    break;
            }

            if (counter >= 3) {
                System.out.println("there are already three cards with name " + cardName + " in deck " + deckName);
                this.execute();
            }
            int j = 0;
            for (Card myCard : loggedUser.getAllCards()) {
                if (myCard.getName().equals(cardName))
                    j++;
            }
            // We Added here (it wasn't in Document)!
            if (j <= counter) {
                System.out.println("You have Used all of Your Cards :" + cardName + " that You Bought !");
                this.execute();
            }
            if (isSide == null) {
                deck.getMainDeck().getCardsInMainDeck().add(card);
            } else {
                deck.getSideDeck().getCardsInSideDeck().add(card);
            }
            this.execute();
        } else if ((matcher = Regex.getMatcher(input, Regex.removeCardFromDeck)).find()) {
            String cardName = matcher.group(2);
            String deckName = matcher.group(4);
            boolean flag = false;
            Deck deck = Deck.getDeckByDeckName(deckName, loggedUser);
            Card card = Shop.getCardByName(cardName);


            for (Deck myDeck : loggedUser.getDecks()) {
                if (!myDeck.getName().equals(deckName)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("deck with name " + deckName + " does not exist");
                this.execute();
            }

            if (matcher.group(5) == null) {

                if (!deck.getMainDeck().getCardsInMainDeck().contains(card)) {
                    System.out.println("card with name " + cardName + " does not exist in main deck");
                    this.execute();
                }
            } else {
                if (!deck.getSideDeck().getCardsInSideDeck().contains(card)) {
                    System.out.println("card with name " + cardName + " does not exist in side deck");
                    this.execute();
                }
            }
            if (matcher.group(5) == null) {

                for (Card myCard : deck.getMainDeck().getCardsInMainDeck()) {
                    if (myCard.getName().equals(cardName))
                        deck.getMainDeck().getCardsInMainDeck().remove(card);
                }

            } else {
                for (Card myCard : deck.getSideDeck().getCardsInSideDeck()) {
                    if (myCard.getName().equals(cardName))
                        deck.getSideDeck().getCardsInSideDeck().remove(card);
                }
            }
            System.out.println("card removed form deck successfully");
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showAllDecks).find()) {
            printAllDecks(loggedUser);
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showOneDeck).find()) {
            if (Deck.getDeckByDeckName(matcher.group(2), loggedUser) == null) {
                System.out.println("deck with name " + matcher.group(2) + " does not exist");
                this.execute();
            }
            Deck deck = Deck.getDeckByDeckName(matcher.group(2), loggedUser);
            if (matcher.group(3) == null) {
                System.out.println("Deck :" + matcher.group(2));
                System.out.println(deck.getMainDeck().toString());
                this.execute();
            } else {
                System.out.println("Deck :" + matcher.group(2));
                System.out.println(deck.getSideDeck().toString());
                this.execute();
            }
        } else if (Regex.getMatcher(input, Regex.showCards).find()) {
            for (Card card : loggedUser.getAllCards()) {
                System.out.println(card.toString());
            }
            loggedUser.sortAllCardsOfUser();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
            this.menuEnter(matcher.group(1));
            this.execute();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
            this.execute();
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
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

    @FXML
    public void editDeck() {

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

    private void printAllDecks(User user) {
        Deck activeDeck = null;
        for (Deck deck : user.getDecks()) {
            if (deck.isActive()) {
                activeDeck = deck;
                break;
            }
        }
        System.out.println("Decks: ");
        System.out.println("Active deck: ");
        if (activeDeck != null)
            System.out.println(activeDeck.toString());

        Deck otherDeck = null;
        for (Deck deck : user.getDecks()) {
            if (!deck.isActive()) {
                otherDeck = deck;
                break;
            }
        }
        System.out.println("Other decks :");
        if (otherDeck != null)
            for (Deck deck : user.getDecks()) {
                if (!deck.isActive())
                    System.out.println(deck.toString());
            }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    @FXML
    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenuGraphic().start(mainStage);
    }
}
