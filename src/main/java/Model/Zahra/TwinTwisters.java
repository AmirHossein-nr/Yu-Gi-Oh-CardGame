package Model.Zahra;

import Model.*;

public class TwinTwisters extends Spell {

    public TwinTwisters() {
        super("Twin Twisters", Type.SPELL);
    }

    public void specialAction (Card oneCartInHand, Card firstRivalCart, Card secondRivalCart, Board rivalBoard, Board currentBoard) {
        currentBoard.getCardsInHand().remove(oneCartInHand);
        currentBoard.getGraveYard().add(oneCartInHand);
        if (firstRivalCart instanceof Spell || firstRivalCart instanceof Trap) {
            rivalBoard.getSpellsAndTrapsZone().remove(firstRivalCart);
            rivalBoard.getGraveYard().add(firstRivalCart);
        }
        if (secondRivalCart instanceof Spell || secondRivalCart instanceof Trap) {
            rivalBoard.getSpellsAndTrapsZone().remove(secondRivalCart);
            rivalBoard.getGraveYard().add(secondRivalCart);
        }
    }

    public void specialAction (Card oneCartInHand, Card rivalCart, Board rivalBoard, Board currentBoard) {
        currentBoard.getCardsInHand().remove(oneCartInHand);
        currentBoard.getGraveYard().add(oneCartInHand);
        if (rivalCart instanceof Spell || rivalCart instanceof Trap) {
            rivalBoard.getSpellsAndTrapsZone().remove(rivalCart);
            rivalBoard.getGraveYard().add(rivalCart);
        }
    }
}
