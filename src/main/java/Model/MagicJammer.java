package Model;

public class MagicJammer extends Trap {

    public MagicJammer() {
        super("Magic Jammer", Type.TRAP);
    }

    public void specialAction (Spell activatedSpell, Board currentBoard, Board rivalBoard, Card card) {
        for (Card card1 : currentBoard.getCardsInHand()) {
            if (card == card1) {
                currentBoard.getCardsInHand().remove(card);
                currentBoard.getGraveYard().add(card);
                activatedSpell.setOccupied(false);
                rivalBoard.getGraveYard().add(activatedSpell);
                rivalBoard.getSpellsAndTrapsZone().remove(activatedSpell);
                break;
            }
        }
    }
}
