package Model;

import View.Menu.Shop;

import java.util.ArrayList;
import java.util.Collections;

public class SideDeck {
    private ArrayList<Card> cardsInSideDeck;
    private Boolean isActive;

    public SideDeck(Boolean isActive) {
        cardsInSideDeck = new ArrayList<>();
        setActive(isActive);
    }

    public String printMonsters() {
        sortCardsInSideDeck();
        for (Card card : cardsInSideDeck) {
            if (card instanceof Monster) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }
        return "";
    }

    public String printSpellsTraps() {
        sortCardsInSideDeck();
        for (Card card : cardsInSideDeck) {
            if (card instanceof Spell || card instanceof Trap) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }

        return "";
    }


    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCardsInSideDeck(ArrayList<Card> cardsInSideDeck) {
        this.cardsInSideDeck = cardsInSideDeck;
    }


    public Boolean getActive() {
        return isActive;
    }

    public ArrayList<Card> getCardsInSideDeck() {
        return cardsInSideDeck;
    }

    public void sortCardsInSideDeck() {
        Shop.sortCards(cardsInSideDeck);
    }

    @Override
    public String toString() {
        return "Main Deck :\n" +
                printMonsters() + "\nSpells and Traps :\n" +
                printSpellsTraps();
    }
}
