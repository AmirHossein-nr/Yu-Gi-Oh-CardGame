package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Controller.Game;

public class HarpiesFeatherDuster extends Effect {

    public HarpiesFeatherDuster(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
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
