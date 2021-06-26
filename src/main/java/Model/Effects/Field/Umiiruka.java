package Model.Effects.Field;

import Model.Card;
import Model.Monster;
import Model.Type;
import Controller.Game;

public class Umiiruka extends FieldEffect {

    public Umiiruka(Card card) {
        super(card);
    }

    @Override
    public void addCardUnderEffect(Card card) {
        Monster monster = (Monster) card;
        if (monster.getMonsterType() == Type.AQUA) {
            effectedMonsterCards.add(monster);
            monster.setAttackPower(monster.getAttackPower() + 500);
            monster.setDefencePower(monster.getDefencePower() - 400);
        }
    }

    @Override
    public void deActive() {
        for (int i = 0; i < effectedMonsterCards.size(); i++) {
            ((Monster) effectedMonsterCards.get(i)).setAttackPower(((Monster) effectedMonsterCards.get(i)).getAttackPower() - 500);
            ((Monster) effectedMonsterCards.get(i)).setDefencePower(((Monster) effectedMonsterCards.get(i)).getDefencePower() + 400);
        }
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            for (int i = 0; i < 5; i++) {
                if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i);
                    if (monster.getMonsterType() == Type.AQUA) {
                        effectedMonsterCards.add(monster);
                        monster.setAttackPower(monster.getAttackPower() + 500);
                        monster.setDefencePower(monster.getDefencePower() - 400);
                    }
                }
            }
            System.out.println("spell activated");
            return true;
        } else {
            System.out.println("preparations of this spell are not done yet");
            return false;
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        return true;
    }
}
