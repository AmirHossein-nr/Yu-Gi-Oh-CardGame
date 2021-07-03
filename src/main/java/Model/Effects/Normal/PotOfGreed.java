package Model.Effects.Normal;

import Model.*;
import Model.Effects.Effect;
import Controller.Game;

public class PotOfGreed extends Effect {

    public PotOfGreed(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            game.drawCard(game.getCurrentUser());
            game.drawCard(game.getCurrentUser());
            System.out.println("spell activated");
            game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
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
        if (game.getCurrentUser().getBoard().getDeckZone().size() >= 2) {
            return true;
        }
        return false;
    }
}
