package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Controller.Game;

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
        System.out.println("spell activated");
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
