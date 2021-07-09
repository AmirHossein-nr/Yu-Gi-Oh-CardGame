package Model.Effects;

import Controller.Game;
import Model.Card;

public class FalseEffect extends Effect {

    public FalseEffect(Card card) {
        super(card);
    }

    @Override
    public boolean activate(Game game) {
        return false;
    }

    @Override
    public boolean canBeActivated(Game game) {
        return false;
    }
}
