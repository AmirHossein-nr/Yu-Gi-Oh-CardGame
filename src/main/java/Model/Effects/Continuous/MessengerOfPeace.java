package Model.Effects.Continuous;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import View.GUI.GamePlay;
import javafx.scene.control.Alert;

public class MessengerOfPeace extends Effect {

    public MessengerOfPeace(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        game.getCurrentUser().getBoard().getActivatedMessengerOfPeaces().add(card);
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
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
