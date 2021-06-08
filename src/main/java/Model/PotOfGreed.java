package Model;

public class PotOfGreed extends Spell{
    Deck deck;
    public PotOfGreed() {
        super("Pot of Greed", Type.SPELL);
    }

    public void specialAction (User currentUser, Board currentBoard) {
        for (Deck d : currentUser.getDecks()) {
            if (d.getActive()) {
                deck = d;
            }
        }
        MainDeck mainDeck = deck.getMainDeck();
        Card firstCard = mainDeck.getCardsInMainDeck().get(mainDeck.getCardsInMainDeck().size() - 1);
        Card secondCard = mainDeck.getCardsInMainDeck().get(mainDeck.getCardsInMainDeck().size() - 2);
    }
}
