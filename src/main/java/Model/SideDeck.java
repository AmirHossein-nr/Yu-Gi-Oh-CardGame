package Model;

import java.util.ArrayList;

public class SideDeck {
    private ArrayList<Card> cardsInSideDeck;
    private Boolean isActive;

    public SideDeck(Boolean isActive) {
        setActive(isActive);
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
}
