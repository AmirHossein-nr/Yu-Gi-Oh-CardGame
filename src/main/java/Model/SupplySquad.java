package Model;

public class SupplySquad extends Spell{
    MainDeck mainDeck;
    public SupplySquad() {
        super("Supply Squad", Type.SPELL);
    }

    public void specialAction (int destroyedMonstersInTurn, User currentUser, Board currentBoard) {
        if (destroyedMonstersInTurn >= 1) {
            for (Deck deck : currentUser.getDecks()) {
                if (deck.getActive()) {
                    mainDeck = deck.getMainDeck();
                }
            }
            Card card = mainDeck.getCardsInMainDeck().get(mainDeck.getCardsInMainDeck().size() - 1);
            currentBoard.getCardsInHand().add(card);
        }
    }
}
