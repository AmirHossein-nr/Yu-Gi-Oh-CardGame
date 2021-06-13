package Model.Zahra;

import Model.Monster;
import Model.Trap;
import Model.Type;
import Model.User;

public class MagicCylinder extends Trap {

    public MagicCylinder() {
        super("Magic Cylinder", Type.TRAP);
    }

    public void specialAction (User rivalUser, Monster rivalAttackingMonster) {
        rivalUser.setLifePoint(rivalUser.getLifePoint() - rivalAttackingMonster.getAttackPower());
        rivalAttackingMonster.setAttackPower(0);
    }
}
