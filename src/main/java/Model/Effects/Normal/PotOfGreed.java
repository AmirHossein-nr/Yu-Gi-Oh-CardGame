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
        try {
            game.drawCard(game.getCurrentUser());
        } catch (Exception e) {
        }
        try {
            game.drawCard(game.getCurrentUser());
        } catch (Exception e) {
        }
        System.out.println("spell activated");
        game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
        return true;
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
