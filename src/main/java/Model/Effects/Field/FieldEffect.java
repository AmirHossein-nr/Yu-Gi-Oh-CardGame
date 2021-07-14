package Model.Effects.Field;

import Model.Card;
import Model.Effects.Effect;
import Client.Controller.Game;

import java.util.ArrayList;

public abstract class FieldEffect extends Effect {

    protected ArrayList<Card> effectedMonsterCards = new ArrayList<>();

    public FieldEffect(Card card) {
        super(card);
        speed = 1;
    }

    public ArrayList<Card> getEffectedMonsterCards() {
        return effectedMonsterCards;
    }

    public abstract void addCardUnderEffect(Card card);

    public abstract void deActive();

    public abstract boolean activate(Game game);

    public void addToChain(Game game) {
        if (canBeActivated(game)) {
            TheOwner = game.getCurrentUser();
            game.getChain().add(card);
            card.setOccupied(true);
            if (game.getCurrentUser().getBoard().getCardsInHand().contains(card)) {
                game.addSpellOrTrapFromHandToZone(card, true);
            }
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }
    public abstract boolean canBeActivated(Game game);
}
