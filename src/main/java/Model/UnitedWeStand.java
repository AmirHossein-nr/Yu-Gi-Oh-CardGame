package Model;

public class UnitedWeStand extends Spell{

    public UnitedWeStand() {
        super("United We Stand", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        for (Card card : currentBoard.getMonstersZone()) {
            Monster monster = (Monster) card;
            if (monster.getEquippedWith() instanceof UnitedWeStand) {
                if (monster.getOccupied()) {
                    monster.setAttackPower(monster.getAttackPower() + 800);
                    monster.setDefencePower(monster.getDefencePower() + 800);
                }
            }
        }
    }
}
