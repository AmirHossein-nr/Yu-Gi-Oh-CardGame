package View.Menu;

import Controller.Regex;
import Model.*;
import View.Menu.Game.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class Duel extends Menu {

    public Duel(Menu parentMenu) {
        super("Duel Menu", parentMenu);
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        Matcher matcher;
        if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
            this.menuEnter(matcher.group(1));
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else if ((matcher = Regex.getMatcher(input, Regex.newGame)).find()) {
            User rivalUser = User.getUserByUsername(matcher.group(3));
            if (rivalUser == null) {
                System.out.println("there is no player with this username");
                this.execute();
            } else if (!hasActiveDeck(loggedUser)) {
                System.out.println(loggedUser.getUsername() + " has no active deck");
                this.execute();
            } else if (!hasActiveDeck(rivalUser)) {
                System.out.println(rivalUser.getUsername() + " has no active deck");
                this.execute();
            } else if (!isValid(loggedUser)) {
                System.out.println(loggedUser.getUsername() + "’s deck is invalid");
                this.execute();
            } else if (!isValid(rivalUser)) {
                System.out.println(rivalUser.getUsername() + "’s deck is invalid");
                this.execute();
            } else if (Integer.parseInt(matcher.group(5)) != 1 && Integer.parseInt(matcher.group(5)) != 3) {
                System.out.println("number of rounds is not supported");
                this.execute();
            }
            int numberOfRounds = Integer.parseInt(matcher.group(5));
            new Game(loggedUser, rivalUser, numberOfRounds, scanner).run();
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
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
}
