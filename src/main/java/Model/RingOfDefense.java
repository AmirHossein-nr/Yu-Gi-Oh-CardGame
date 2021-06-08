package Model;

public class RingOfDefense extends Spell{

    public RingOfDefense() {
        super("Ring Of Defense", Type.SPELL);
    }

    public void specialAction (Board rivalBoard) {
        for (Card card : rivalBoard.getSpellsAndTrapsZone()) {
            if (card instanceof Trap) {
                if (card.getOccupied() && ((Trap) card).getIsDestructive()) {
                    ((Trap) card).setDestructive(false);
                }
            }
        }
    }
}
