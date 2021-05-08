package Model;

import java.util.ArrayList;
import java.util.Collections;

public class MainDeck {
    private ArrayList<Card> cardsInMainDeck;
    private Boolean isActive;
    private Boolean isValid;

    public MainDeck(Boolean isActive) {
        new ArrayList<>();
        setActive(isActive);
    }

    //todo COMPLETE HERE !

    public String printMonsters() {
        sortCardsInMainDeck();
        for (Card card : cardsInMainDeck) {
            if (card instanceof Monster) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }

        return "";
    }

    public String printSpellsTraps() {
        sortCardsInMainDeck();
        for (Card card : cardsInMainDeck) {
            if (card instanceof Spell || card instanceof Trap) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }

        return "";
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCardsInMainDeck(ArrayList<Card> cardsInMainDeck) {
        this.cardsInMainDeck = cardsInMainDeck;
    }


    public void setValid(Boolean flag) {
        this.isValid = flag;
    }

    public ArrayList<Card> getCardsInMainDeck() {
        return cardsInMainDeck;
    }

    public Boolean getActive() {
        return isActive;
    }


    public Boolean getValid() {
        return isValid;
    }

    public void sortCardsInMainDeck() {
        for (int i = cardsInMainDeck.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= cardsInMainDeck.size() - 2; j++) {
                if (cardsInMainDeck.get(j).getName().compareTo(cardsInMainDeck.get(j + 1).getName()) > 0) {
                    Collections.swap(cardsInMainDeck, j, j + 1);
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
