package Model.Effects.Equipe;

import Model.Card;
import Model.Effects.Effect;
import Model.Monster;
import Controller.Game;

public abstract class EquipEffect extends Effect {

    protected Monster monster;

    public EquipEffect(Card card) {
        super(card);
        speed = 1;
    }

    public abstract void deActive();

    public abstract boolean activate(Game game);

    public void addToChain(Game game) {
        if (canBeActivated(game)) {
            game.getChain().add(card);
            card.setOccupied(true);
            System.out.println("spell activated");
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    public abstract boolean canBeActivated(Game game);
}
