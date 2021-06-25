package Model.Effects.Normal;

import Model.*;
import Model.Effects.Effect;
import View.Menu.Game.Game;

import java.util.ArrayList;

public class PotOfGreed extends Effect {

    public PotOfGreed(Card card) {
        super(card);
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            game.drawCard(game.getCurrentUser());
            game.drawCard(game.getCurrentUser());
            System.out.println("spell activated");
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0 && ((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
            return false;
        }
        if (game.getCurrentUser().getBoard().getDeckZone().size() >= 2) {
            return true;
        }
        return false;
    }
}
