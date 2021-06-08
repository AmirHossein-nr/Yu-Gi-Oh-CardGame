package Model;

public class Yami extends Spell{

    public Yami() {
        super("Yami", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        //all monsters contains monsters in current board
        Monster monster;
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.FAIRY) {
                monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() - 200);
                monster.setDefencePower(monster.getDefencePower() - 200);
            } else if (card.getCardType() == Type.FIEND || card.getCardType() == Type.SPELL_CASTER) {
                monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() + 200);
                monster.setDefencePower(monster.getDefencePower() + 200);
            }
        }
    }
}
