package Model;

public class Deck {
    private final MainDeck mainDeck;
    private final SideDeck sideDeck;

    public Deck(MainDeck mainDeck, SideDeck sideDeck) {
        this.mainDeck = mainDeck;
        this.sideDeck = sideDeck;
    }

    public MainDeck getMainDeck() {
        return mainDeck;
    }

    public SideDeck getSideDeck() {
        return sideDeck;
    }

}
