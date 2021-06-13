package Model.Zahra;

import Model.*;

public class YomiShip extends Monster {
    private Card card;

    public YomiShip() {
        super("Yomi Ship", Type.AQUA);
    }

    public void specialAction (User currentUser, User rivalUser, Board currentBoard, Board rivalBoard) {
        Card monster = (Monster) card;
        if (monster.getAttackPosition()) {
            //todo set action
        }
    }
}
