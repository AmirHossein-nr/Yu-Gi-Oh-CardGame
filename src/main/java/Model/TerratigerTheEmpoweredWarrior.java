package Model;

public class TerratigerTheEmpoweredWarrior extends Monster{

    public TerratigerTheEmpoweredWarrior() {
        super("Terratiger, the Empowered Warrior", Type.WARRIOR);
    }

    public void specialAction (boolean isNormalSummon, Monster monster, Board currentBoard) {
        if (isNormalSummon) {
            for (Card card : currentBoard.getCardsInHand()) {
                if (card == monster) {
                    if (monster.getLevel() <= 4) {
                        monster.setAttackPosition(false);
                        currentBoard.getMonstersZone().add(monster);
                    }
                }
            }
        }
    }
}
