package Model.Zahra;

import Model.*;

public class ChangeOfHeart extends Spell {

    public ChangeOfHeart() {
        super("Change Of Heart", Type.SPELL);
    }

    public void specialAction (Monster rivalMonster, Board rivalBoard, Board currentBoard) {
        //cats in control are those in the field
        for (Card monster : rivalBoard.getMonstersZone()) {
            if (monster == rivalMonster) {
                rivalBoard.getMonstersZone().remove(rivalMonster);
                currentBoard.getMonstersZone().add(rivalMonster);
            }
        }
    }
}
