package Model.Effects;

import Controller.Game;
import Model.Card;

public class falseEffect extends Effect {

    public falseEffect(Card card) {
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
