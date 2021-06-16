package Model.Zahra;

import Model.Monster;
import Model.Type;

public class ManEaterBug extends Monster {

    public ManEaterBug() {
        super("Man-Eater Bug", Type.INSECT);
    }

    public void specialAction (Monster monster) {
        if (!monster.getOccupied()) {
            if (monster.getNewOccupationState()) {
                //todo select rival monster and destroy it!
            }
        }
        monster.setOccupied(false);
    }
}
