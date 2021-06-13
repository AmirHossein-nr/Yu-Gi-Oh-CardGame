package Model.Zahra;

import Model.Board;
import Model.Card;
import Model.Spell;
import Model.Type;

public class DarkHole extends Spell {

    public DarkHole() {
        super("Dak Hole", Type.SPELL);
    }

    public void specialAction (Board rivalBoard) {
        for (Card monster : rivalBoard.getMonstersZone()) {
            rivalBoard.getMonstersZone().remove(monster);
            rivalBoard.getGraveYard().add(monster);
        }
    }
}
