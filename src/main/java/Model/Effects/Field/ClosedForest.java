package Model.Effects.Field;

import Model.Card;
import Model.Monster;
import Model.Type;
import Client.Controller.Game;
import Client.View.GUI.GamePlay;
import javafx.scene.control.Alert;

public class ClosedForest extends FieldEffect {

    private int damage = 0;

    public ClosedForest(Card card) {
        super(card);
    }

    @Override
    public void addCardUnderEffect(Card card) {
        Monster monster = (Monster) card;
        if (monster.getMonsterType() == Type.BEAST) {
            effectedMonsterCards.add(monster);
            monster.setAttackPower(monster.getAttackPower() + damage);
        }
    }

    @Override
    public void deActive() {
        for (int i = 0; i < effectedMonsterCards.size(); i++) {
            ((Monster) effectedMonsterCards.get(i)).setAttackPower(((Monster) effectedMonsterCards.get(i)).getAttackPower() - damage);
            ((Monster) effectedMonsterCards.get(i)).setDefencePower(((Monster) effectedMonsterCards.get(i)).getDefencePower() - damage);
        }
    }

    @Override
    public boolean activate(Game game) {
        for (Card card : game.getCurrentUser().getBoard().getGraveYard()) {
            if (card instanceof Monster) {
                damage++;
            }
        }
        damage *= 100;
        for (int i = 0; i < 5; i++) {
            if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i);
                if (monster.getMonsterType() == Type.BEAST) {
                    effectedMonsterCards.add(monster);
                    monster.setAttackPower(monster.getAttackPower() + damage);
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
