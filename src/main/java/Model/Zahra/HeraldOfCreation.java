package Model.Zahra;

import Model.Board;
import Model.Card;
import Model.Monster;
import Model.Type;

public class HeraldOfCreation extends Monster {
    private Card card;

    public HeraldOfCreation() {
        super("Herald Of Creation", Type.SPELL_CASTER);
    }

    public void specialAction (Card toRemove, Monster toAdd, Board currentBoard) {
        for (Card card : currentBoard.getCardsInHand()) {
            if (card.equals(toRemove)) {
                currentBoard.getCardsInHand().remove(toRemove);
            }
        }
        for (Card card : currentBoard.getGraveYard()) {
            if (card.equals(toAdd)) {
                if (toAdd.getLevel() >= 7) {
                    currentBoard.getCardsInHand().add(toAdd);
                }
            }
        }
    }
}
