package Model;

import Client.Controller.Menu.Shop;

import java.util.ArrayList;

public class MainDeck {
    private ArrayList<Card> cardsInMainDeck;
    private Boolean isActive;
    private Boolean isValid;

    public MainDeck(Boolean isActive) {
        cardsInMainDeck = new ArrayList<>();
        setActive(isActive);
        setValid(false);
    }


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
        if (cardsInMainDeck.size() >= 40)
            isValid = true;
        return isValid;
    }

    public void sortCardsInMainDeck() {
        Shop.sortCards(cardsInMainDeck);
    }

    @Override
    public String toString() {

        return "Main Deck :\n" +
                printMonsters() + "\nSpells and Traps :\n" +
                printSpellsTraps();
    }

}
