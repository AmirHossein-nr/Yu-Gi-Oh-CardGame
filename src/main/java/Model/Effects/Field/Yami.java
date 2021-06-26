package Model.Effects.Field;

import Model.Card;
import Model.Monster;
import Model.Type;
import Controller.Game;

public class Yami extends FieldEffect {

    public Yami(Card card) {
        super(card);
    }

    @Override
    public void addCardUnderEffect(Card card) {
        Monster monster = (Monster) card;
        doAction(monster);
    }

    @Override
    public void deActive() {
        for (int i = 0; i < effectedMonsterCards.size(); i++) {
            if (((Monster) effectedMonsterCards.get(i)).getMonsterType() == Type.FIELD
                    || ((Monster) effectedMonsterCards.get(i)).getMonsterType() == Type.SPELL_CASTER) {
                ((Monster) effectedMonsterCards.get(i)).setAttackPower(((Monster) effectedMonsterCards.get(i))
                        .getAttackPower() - 200);
                ((Monster) effectedMonsterCards.get(i)).setDefencePower(((Monster) effectedMonsterCards.get(i))
                        .getDefencePower() - 200);
            } else if (((Monster) effectedMonsterCards.get(i)).getMonsterType() == Type.FAIRY) {
                ((Monster) effectedMonsterCards.get(i)).setAttackPower(((Monster) effectedMonsterCards.get(i))
                        .getAttackPower() + 200);
                ((Monster) effectedMonsterCards.get(i)).setDefencePower(((Monster) effectedMonsterCards.get(i))
                        .getDefencePower() + 200);
            }
        }
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            for (int i = 0; i < 5; i++) {
                if (game.getCurrentUser().getBoard().getMonstersZone() != null) {
                    Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i);
                    doAction(monster);
                }
            }
            System.out.println("spell activated");
            return true;
        } else {
            System.out.println("preparations of this spell are not done yet");
            return false;
        }
    }

    private void doAction(Monster monster) {
        if (monster.getMonsterType() == Type.FIELD
                || monster.getMonsterType() == Type.SPELL_CASTER) {
            effectedMonsterCards.add(monster);
            monster.setAttackPower(monster.getAttackPower() + 200);
            monster.setDefencePower(monster.getDefencePower() + 200);
        } else if (monster.getMonsterType() == Type.FAIRY) {
            effectedMonsterCards.add(monster);
            monster.setAttackPower(monster.getAttackPower() - 200);
            monster.setDefencePower(monster.getDefencePower() - 200);
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        return true;
    }
}
