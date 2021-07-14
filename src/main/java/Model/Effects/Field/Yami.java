package Model.Effects.Field;

import Model.Card;
import Model.Monster;
import Model.Type;
import Client.Controller.Game;
import Client.View.GUI.GamePlay;
import javafx.scene.control.Alert;

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
        for (int i = 0; i < 5; i++) {
            if (game.getCurrentUser().getBoard().getMonstersZone() != null) {
                Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i);
                doAction(monster);
            }
        }
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
        return true;
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
