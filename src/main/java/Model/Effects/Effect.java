package Model.Effects;

import Model.Card;
import Client.Controller.Game;
import Model.User;

import java.util.Scanner;

public abstract class Effect {

    protected static Scanner scanner;

    protected Card card;
    protected int speed;
    protected User TheOwner;


    public Effect(Card card) {
        this.card = card;
    }

    public static void setScanner(Scanner scanner) {
        Effect.scanner = scanner;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Card getCard() {
        return card;
    }

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
            System.out.println("ADD TO CHAIN");
            System.out.println("preparations of this spell are not done yet");
        }
    }
    public abstract boolean canBeActivated(Game game);

//    if (game.getChain().size() != 0 && ((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
//        return false;
//    }

    public void finalActivate(Game game) {
        if (activate(game)) {
            game.getCurrentUser().setLifePoint(game.getCurrentUser().getLifePoint() + 500 * game.getCurrentUser().getBoard().getActivatedSpellAbsorptions().size());
            game.getOpponentOfCurrentUser().setLifePoint(game.getOpponentOfCurrentUser().getLifePoint() + 500 * game.getOpponentOfCurrentUser().getBoard().getActivatedSpellAbsorptions().size());
        } else {
            System.out.println("FINAL ACTIVATE");
        }
    }

    protected static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
}
