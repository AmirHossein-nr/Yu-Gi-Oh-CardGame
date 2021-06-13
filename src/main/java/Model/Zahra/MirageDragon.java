package Model.Zahra;
import Model.*;

public class MirageDragon extends Monster{
    Card card;

    public MirageDragon() {
        super("Mirage Dragon", Type.DRAGON);
    }

    public void specialAction (Board rivalBoard) {
        Card mirageDragon = (MirageDragon) card;

        if (mirageDragon.getOccupied()) {
            for (Card card : rivalBoard.getSpellsAndTrapsZone()) {
                if (card instanceof Trap) {
                    ((Trap) card).setCanBeActive(false);
                }
            }
        }
    }
}
