package Model.Zahra;

import Model.Board;
import Model.Spell;
import Model.Type;

public class Terraforming extends Spell {

    public Terraforming() {
        super("Terraforming", Type.SPELL);
    }

    public void specialAction (Spell spell, Board currentBoard) {
        if (spell.getIcon() == Icon.FIELD) {
            currentBoard.getCardsInHand().add(spell);
        }
    }
}
