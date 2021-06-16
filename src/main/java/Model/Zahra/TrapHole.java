package Model.Zahra;

import Model.Board;
import Model.Monster;
import Model.Trap;
import Model.Type;

public class TrapHole extends Trap {

    public TrapHole() {
        super("Trap Hole", Type.TRAP);
    }

    public void specialAction (boolean isNormalSummon, boolean isFlipSummon, Monster summonedMonster, Board rivalBoard) {
        if (isFlipSummon || isNormalSummon) {
            if (summonedMonster.getAttackPower() >= 1000) {
                rivalBoard.getGraveYard().add(summonedMonster);
                rivalBoard.getMonstersZone().remove(summonedMonster);
            }
        }
    }
}
