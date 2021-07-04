package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Controller.Game;

public class DarkHole extends Effect {

    public DarkHole(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    Card card = game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i);
                    game.addMonsterFromMonsterZoneToGraveyard(card, game.getOpponentOfCurrentUser());
                }
            }
            for (int i = 0; i < game.getCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    Card card = game.getCurrentUser().getBoard().getMonstersZone().get(i);
                    game.addMonsterFromMonsterZoneToGraveyard(card, game.getCurrentUser());
                }
            }
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
        return true;
    }
}
