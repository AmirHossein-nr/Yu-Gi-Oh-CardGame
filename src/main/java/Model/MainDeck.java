package Model;

import java.util.ArrayList;

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

        for (Card card : cardsInMainDeck) {
            if (card instanceof Monster) {
                System.out.println(card.getName() + ": " + card.getDescription());
            }
        }
        //todo Alphabetically
        return "";
    }

    public String printSpellsTraps() {

        for (Card card : cardsInMainDeck) {
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

    @Override
    public String toString() {
        return "Main Deck :\n" +
                printMonsters() + "\nSpells and Traps :\n" +
                printSpellsTraps();
    }

}
