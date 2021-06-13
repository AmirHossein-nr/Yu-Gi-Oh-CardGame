package Model.Zahra;

import Model.*;

public class CallOfTheHaunted extends Trap {

    public CallOfTheHaunted() {
        super("Call Of The Haunted", Type.TRAP);
    }

    public void specialAction (Board currentBoard, Monster monster) {
        for (Card card : currentBoard.getGraveYard()) {
            if (card == monster) {
                Monster monster1 = (Monster) card;
                currentBoard.getMonstersZone().add(monster);
                currentBoard.getGraveYard().remove(monster);
                monster.setAttackPosition(true);
                break;
            }
        }
    }
}
