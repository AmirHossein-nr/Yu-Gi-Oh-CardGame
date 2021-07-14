package Model.Effects;

import Client.Controller.Game;
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
