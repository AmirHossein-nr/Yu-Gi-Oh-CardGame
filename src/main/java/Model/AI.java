package Model;

import Model.Zahra.SideDeck;
import View.Menu.Shop;

public class AI {

    private User aiUser;

    public AI() {
        aiUser = new User("ai", "123", "AI");
        makeNewDeckForAi();

    }

    private void makeNewDeckForAi() {
        Deck deck = new Deck(new MainDeck(true), new SideDeck(true));
        deck.setName("Ai Deck");
        deck.setValid(true);
        for (int i = 0; i < 40; i++) {
            Card card = Shop.getAllCards().get(i);
            if (card.getCardType().equals(Type.SPELL)) {
                Spell myCard = (Spell) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            } else if (card.getCardType().equals(Type.TRAP)) {
                Trap myCard = (Trap) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            } else {
                Monster myCard = (Monster) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            }
        }
        for (int i = 40; i < 55; i++) {
            Card card = Shop.getAllCards().get(i);
            if (card.getCardType().equals(Type.SPELL)) {
                Spell myCard = (Spell) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            } else if (card.getCardType().equals(Type.TRAP)) {
                Trap myCard = (Trap) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            } else {
                Monster myCard = (Monster) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            }
        }
    }
}
