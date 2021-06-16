package Model.Zahra;

import Model.*;

public class MagnumShield extends Spell {

    public MagnumShield() {
        super("Magnum Shield", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.WARRIOR) {
                Monster monster = (Monster) card;
                if (monster.getAttackPosition()){
                    monster.setAttackPower(monster.getDefencePower() + monster.getAttackPower());
                } else {
                    monster.setDefencePower(monster.getDefencePower() + monster.getAttackPower());
                }
            }
        }
    }
}
