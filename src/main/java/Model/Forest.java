package Model;

public class Forest extends  Spell {

    public Forest() {
        super("Forest", Type.SPELL);
    }

    public void specialAction (Board currentBoard) {
        Monster monster;
        for (Card card : currentBoard.getMonstersZone()) {
            if (card.getCardType() == Type.INSECT || card.getCardType() == Type.BEAST || card.getCardType() == Type.BEAST_WARRIOR) {
                monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() + 200);
                monster.setDefencePower(monster.getDefencePower() + 200);
            }
        }
    }
}
