package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Client.Controller.Game;
import Client.View.GUI.GamePlay;
import javafx.scene.control.Alert;

public class Raigeki extends Effect {

    public Raigeki(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
            if (game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                Card card = game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i);
                game.addMonsterFromMonsterZoneToGraveyard(card, game.getOpponentOfCurrentUser());
            }
        }
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
        game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
        return true;
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        return true;
    }
}
