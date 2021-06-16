package Model.Zahra;

import Model.Board;
import Model.Card;
import Model.Trap;
import Model.Type;

public class MindCrush extends Trap {

    public MindCrush() {
        super("Mind Crush", Type.TRAP);
    }

    public void specialAction (String name, Board rivalBoard) {
        int deletedCarts = 0;
        for (Card card : rivalBoard.getCardsInHand()) {
            if (card.getName().equals(name)) {
                rivalBoard.getCardsInHand().remove(card);
                rivalBoard.getGraveYard().add(card);
                deletedCarts++;
            }
        }

        if (deletedCarts == 0) {
            //todo delete a cart
        }
    }
}
