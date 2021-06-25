package Model.Effects;

import Model.Card;
import Model.Spell;
import Model.Type;
import View.Menu.Game.Game;

import java.util.Scanner;

public abstract class Effect {

    protected static Scanner scanner;

    protected Card card;
    protected int speed;

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

    public abstract void activate(Game game);

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

//    if (game.getChain().size() != 0 && ((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
//        return false;
//    }

    protected static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
}
