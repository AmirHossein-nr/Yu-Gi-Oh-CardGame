package Model;

public class Suijin extends Monster{
    private boolean isUsedForFirstTime = true;

    public Suijin() {
        super("Suijin", Type.AQUA);
    }

    public void specialAction (Monster rivalMonster) {
        if (isUsedForFirstTime) {
            rivalMonster.setAttackPower(0);
            this.isUsedForFirstTime = false;
        }
    }
}
