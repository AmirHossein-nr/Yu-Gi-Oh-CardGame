package Model.Effects.Counter;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Trap;
import Model.User;

public class MagicCylinder extends Effect {

    User owner;

    public MagicCylinder(Card card) {
        super(card);
        speed = 2;
    }

    @Override
    public boolean activate(Game game) {
        return false;
    }

    @Override
    public boolean canBeActivated(Game game) {
        // check Mirage Dragon
        if (game.getCurrentUser().getBoard().getAllCards().contains(card)) {
            return false;
        }
        if (!game.isDeclaredAttack()) {
            return false;
        }
        if (game.getChain().get(game.getChain().size() - 1) instanceof Spell) {
            if (((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                return false;
            }
        } else {
            if (((Trap) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                return false;
            }
        }
        return true;
    }
}
