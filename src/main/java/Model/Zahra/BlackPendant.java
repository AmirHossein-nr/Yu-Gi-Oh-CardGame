package Model.Zahra;

import Model.*;

public class BlackPendant extends Spell {

    public BlackPendant() {
        super("Black Pendant", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        for (Card card : currentBoard.getMonstersZone()) {
            Monster monster = (Monster) card;
            if (monster.getEquippedWith() instanceof BlackPendant) {
                monster.setAttackPower(monster.getAttackPower() + 500);
            }
        }
    }
}
