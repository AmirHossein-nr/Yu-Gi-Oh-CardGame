package View.Menu;

import Controller.Regex;
import Model.Card;
import Model.Deck;
import Model.MainDeck;
import Model.SideDeck;
import sun.plugin.security.JDK11ClassFileTransformer;

import java.util.regex.Matcher;

public class DeckMenu extends Menu {
    public DeckMenu(Menu parentMenu) {
        super("Deck Menu", parentMenu);
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
                    this.execute();
                } else {
                    Deck deck = new Deck(new MainDeck(false), new SideDeck(false));
                    loggedUser.getDecks().add(deck);
                    this.execute();
                }
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.deleteDeck)).find()) {
            String name = matcher.group(1);
            if (name != null) {
                boolean flag = false;
                for (Deck deck : loggedUser.getDecks()) {
                    if (deck.getName().equals(name)) {
                        for (Card card : deck.getMainDeck().getCardsInMainDeck()) {
                            loggedUser.getAllCards().add(card);
                        }
                        for (Card card : deck.getSideDeck().getCardsInSideDeck()) {
                            loggedUser.getAllCards().add(card);
                        }

                        loggedUser.getDecks().remove(deck);
                        System.out.println("deck deleted successfully");
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("deck with name " + name + " does not exist");
                }
                this.execute();
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.activateDeck)).find()) {
            String name = matcher.group(1);
            boolean flag = false;
            if (name != null) {
                for (Deck deck : loggedUser.getDecks()) {
                    if (deck.getName().equals(name)) {
                        deck.setActive(true);
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
            //todo : check if it's full or not!
            Deck deck = Deck.getDeckByDeckName(deckName, loggedUser);
            Card card = Shop.getCardByName(cardName);
            if (isSide == null) {
                deck.getMainDeck().getCardsInMainDeck().add(card);
                this.execute();
            } else {
                deck.getSideDeck().getCardsInSideDeck().add(card);
                this.execute();
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.removeCardFromDeck)).find()) {
            String cardName = matcher.group(2);
            String deckName = matcher.group(4);
            boolean flag = true;
            boolean finalFlag = true;
            for (Card card : loggedUser.getAllCards()) {
                if (!card.getName().equals(cardName)) {
                    System.out.println("card with name " + cardName + " does not exist");
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for (Deck deck : loggedUser.getDecks()) {
                    if (!deck.getName().equals(deckName)) {
                        System.out.println("deck with name " + deckName + " does not exist");
                        finalFlag = false;
                    }
                }
            }
            if (finalFlag) {
                //todo : remove card
            }
        }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
}
