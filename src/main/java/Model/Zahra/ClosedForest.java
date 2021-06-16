package Model.Zahra;

import Model.*;

public class ClosedForest extends Spell {

    public ClosedForest() {
        super("Closed Forest", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        int monstersInGaveYard = 0;
        for (Card card : currentBoard.getGraveYard()) {
            if (card instanceof Monster) {
                monstersInGaveYard++;
            }
        }
        Monster monster;
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.BEAST) {
                monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() + 100 * monstersInGaveYard);
            }
        }
    }
}
