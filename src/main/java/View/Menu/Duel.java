package View.Menu;

import Controller.Regex;
import Model.Deck;
import Model.User;

import java.util.regex.Matcher;

public class Duel extends Menu {
    public Duel(Menu parentMenu) {
        super("Duel Menu", parentMenu);
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        Matcher matcher ;
        if ((matcher= Regex.getMatcher(input, Regex.newGame)).find()){
            User rivalUser = null;
            for (User user : User.getAllUsers()) {
                if (user.getUsername().equals(matcher.group(3))){
                    rivalUser = user;
                    break;
                }
            }
            if (rivalUser == null){
                System.out.println("there is no player with this username");
                this.execute();
            }
            else if (!hasActiveDeck(loggedUser)){
                System.out.println(loggedUser.getUsername() + " has no active deck");
                this.execute();
            }
            else if (!hasActiveDeck(rivalUser)) {
                System.out.println(rivalUser.getUsername() + " has no active deck");
                this.execute();
            }
            else if (!isValid(loggedUser)){
                System.out.println(loggedUser.getUsername() + "’s deck is invalid");
                this.execute();
            }
            else if (!isValid(rivalUser)){
                System.out.println(rivalUser.getUsername() + "’s deck is invalid");
                this.execute();
            }
            else if (Integer.parseInt(matcher.group(5))!=1 && Integer.parseInt(matcher.group(5))!=3) {
                    System.out.println("number of rounds is not supported");
                    this.execute();
            }
            //TODO ai duel
            else {
                System.out.println("invalid command!");
                this.execute();
            }

        }

    }
    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
    private boolean hasActiveDeck (User user) {

        for (Deck deck : user.getDecks()) {
            if (deck.getMainDeck().getActive())
                return true;
        }
        return false;
    }
    private boolean isValid (User user){
        for (Deck deck : user.getDecks()) {
            if (deck.getMainDeck().getActive()) {
                if (deck.getMainDeck().getValid()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
