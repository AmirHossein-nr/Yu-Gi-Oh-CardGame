package Model;

public class Umiiruka extends Spell{

    public Umiiruka() {
        super("Umiiruka", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.AQUA) {
                Monster monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() + 500);
                monster.setDefencePower(monster.getDefencePower() - 400);
            }
        }
    }
}
