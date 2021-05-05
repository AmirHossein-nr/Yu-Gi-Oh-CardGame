package Model;

import Model.User;

import java.util.ArrayList;

public class Deck {
    private String name;
    private final MainDeck mainDeck;
    private final SideDeck sideDeck;
    private boolean isActive;


    public Deck(MainDeck mainDeck, SideDeck sideDeck) {
        this.mainDeck = mainDeck;
        this.sideDeck = sideDeck;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public MainDeck getMainDeck() {
        return mainDeck;
    }

    public SideDeck getSideDeck() {
        return sideDeck;
    }

    public static Deck getDeckByDeckName (String name, User user) {
        for (Deck deck: user.getDecks()) {
            if (deck.getName().equals(name))
                return deck;
        }
        return null;
    }

}
