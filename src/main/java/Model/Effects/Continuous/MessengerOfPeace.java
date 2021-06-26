package Model.Effects.Continuous;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;

public class MessengerOfPeace extends Effect {

    public MessengerOfPeace(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            game.getCurrentUser().getBoard().getActivatedMessengerOfPeaces().add(card);
            System.out.println("spell activated");
            return true;
        } else {
            System.out.println("preparations of this spell are not done yet");
            return false;
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        return true;
    }
}
