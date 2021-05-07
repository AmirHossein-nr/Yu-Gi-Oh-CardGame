package Model;

import java.util.ArrayList;

public class SideDeck {
    private ArrayList<Card> cardsInSideDeck;
    private Boolean isActive;

    public SideDeck(Boolean isActive) {
        setActive(isActive);
    }

    public String printMonsters() {

        for (Card card : cardsInSideDeck) {
            if (card instanceof Monster) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }
        //todo Alphabetically
        return "";
    }

    public String printSpellsTraps() {

        for (Card card : cardsInSideDeck) {
            if (card instanceof Spell || card instanceof Trap) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }
        //todo Alphabetically

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

    @Override
    public String toString() {
        return "Main Deck :\n" +
                printMonsters() + "\nSpells and Traps :\n" +
                printSpellsTraps();
    }
}
