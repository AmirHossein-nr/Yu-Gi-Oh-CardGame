package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Controller.Game;
import View.GUI.GamePlay;
import javafx.scene.control.Alert;

public class HarpiesFeatherDuster extends Effect {

    public HarpiesFeatherDuster(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().size(); i++) {
            if (game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i) != null) {
                Card card = game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i);
                game.addSpellOrTrapFromZoneToGraveyard(card, game.getOpponentOfCurrentUser());
            }
        }
        if (game.getOpponentOfCurrentUser().getBoard().getFieldZone() != null) {
            Card fieldCard = game.getOpponentOfCurrentUser().getBoard().getFieldZone();
            game.addSpellOrTrapFromZoneToGraveyard(fieldCard, game.getOpponentOfCurrentUser());
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
