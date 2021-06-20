package Model.Effects;

import Model.Card;
import View.Menu.Game.Game;

import java.util.Scanner;

public abstract class Effect {

    protected static Scanner scanner;

    protected Card card;

    public Effect(Card card) {
        this.card = card;
    }

    public static void setScanner(Scanner scanner) {
        Effect.scanner = scanner;
    }

    public Card getCard() {
        return card;
    }

    public abstract void activate(Game game);
    public abstract void addToChain(Game game);
    public abstract boolean canBeActivated(Game game);

    protected static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
}
