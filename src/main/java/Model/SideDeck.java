package Model;

import java.util.ArrayList;
import java.util.Collections;

public class SideDeck {
    private ArrayList<Card> cardsInSideDeck;
    private Boolean isActive;

    public SideDeck(Boolean isActive) {
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
        for (int i = cardsInSideDeck.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= cardsInSideDeck.size() - 2; j++) {
                if (cardsInSideDeck.get(j).getName().compareTo(cardsInSideDeck.get(j + 1).getName()) > 0) {
                    Collections.swap(cardsInSideDeck, j, j + 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Main Deck :\n" +
                printMonsters() + "\nSpells and Traps :\n" +
                printSpellsTraps();
    }
}
