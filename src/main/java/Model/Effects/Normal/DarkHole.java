package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import View.Menu.Game.Game;

public class DarkHole extends Effect {

    public DarkHole(Card card) {
        super(card);
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    Card card = game.getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i);
                    game.getOpponentOfCurrentUser().getBoard().getGraveYard().add(card);
                    game.getOpponentOfCurrentUser().getBoard().getMonstersZone().set(i, null);
                }
            }
            for (int i = 0; i < game.getCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    Card card = game.getCurrentUser().getBoard().getMonstersZone().get(i);
                    game.getCurrentUser().getBoard().getGraveYard().add(card);
                    game.getCurrentUser().getBoard().getMonstersZone().set(i, null);
                }
            }
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
        return true;
    }
}
