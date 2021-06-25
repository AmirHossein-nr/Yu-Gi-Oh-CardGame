package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import View.Menu.Game.Game;

public class HarpiesFeatherDuster extends Effect {

    public HarpiesFeatherDuster(Card card) {
        super(card);
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().size(); i++) {
                if (game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i) != null) {
                    Card card = game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i);
                    game.getOpponentOfCurrentUser().getBoard().getGraveYard().add(card);
                    game.getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().set(i, null);
                }
            }
            System.out.println("spell activated");
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        return true;
    }
}