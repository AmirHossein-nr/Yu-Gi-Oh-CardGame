package View.Menu;

import Controller.Regex;
import Model.*;
import Controller.Game;
import View.GUI.MainMenuGraphic;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.regex.Matcher;

public class Duel extends Menu {

    public static Stage mainStage;

    public Duel () {
        super("Duel Menu", null);
    }

    public Duel(Menu parentMenu) {
        super("Duel Menu", parentMenu);
    }

    @Override
    public void execute() {
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    private boolean hasActiveDeck(User user) {

        for (Deck deck : user.getDecks()) {
            if (deck.getActive())
                return true;
        }
        return false;
    }

    private boolean isValid(User user) {

        for (Deck deck : user.getDecks()) {
            if (deck.getActive()) {
                return deck.getMainDeck().getValid();
            }
        }
        return false;
    }

    public void backToMainMenu(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }
}
