package Model.Effects.Field;

import Client.Controller.Game;
import Model.Card;
import Model.Monster;
import Model.Type;
import Client.View.GUI.GamePlay;
import javafx.scene.control.Alert;

public class Forest extends FieldEffect {

    public Forest(Card card) {
        super(card);
    }

    @Override
    public void addCardUnderEffect(Card card) {
        Monster monster = (Monster) card;
        if (monster.getMonsterType() == Type.BEAST || monster.getMonsterType() == Type.BEAST_WARRIOR || monster.getMonsterType() == Type.INSECT) {
            effectedMonsterCards.add(monster);
            monster.setAttackPower(monster.getAttackPower() + 200);
            monster.setDefencePower(monster.getDefencePower() + 200);
        }
    }

    @Override
    public void deActive() {
        for (int i = 0; i < effectedMonsterCards.size(); i++) {
            ((Monster) effectedMonsterCards.get(i)).setAttackPower(((Monster) effectedMonsterCards.get(i)).getAttackPower() - 200);
            ((Monster) effectedMonsterCards.get(i)).setDefencePower(((Monster) effectedMonsterCards.get(i)).getDefencePower() - 200);
        }
    }

    @Override
    public boolean activate(Game game) {
        for (int i = 0; i < 5; i++) {
            if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i);
                if (monster.getMonsterType() == Type.BEAST || monster.getMonsterType() == Type.BEAST_WARRIOR || monster.getMonsterType() == Type.INSECT) {
                    effectedMonsterCards.add(monster);
                    monster.setAttackPower(monster.getAttackPower() + 200);
                    monster.setDefencePower(monster.getDefencePower() + 200);
                }
            }
        }
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
        return true;
    }

    @Override
    public boolean canBeActivated(Game game) {
        return true;
    }
}
