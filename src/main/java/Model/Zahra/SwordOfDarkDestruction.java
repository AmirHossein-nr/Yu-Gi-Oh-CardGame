package Model.Zahra;

import Model.*;

public class SwordOfDarkDestruction extends Spell {

    public SwordOfDarkDestruction() {
        super("Sword of Dark Destruction", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.FIEND || card.getCardType() == Type.SPELL_CASTER) {
                Monster monster = (Monster) card;
                if (monster.getEquippedWith() instanceof SwordOfDarkDestruction) {
                    monster.setAttackPower(monster.getAttackPower() + 400);
                    monster.setDefencePower(monster.getDefencePower() - 200);
                }
            }
        }
    }
}
