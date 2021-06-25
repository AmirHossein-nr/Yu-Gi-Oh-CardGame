package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Zahra.Icon;
import View.Menu.Game.Game;

public class Terraforming extends Effect {

    public Terraforming(Card card) {
        super(card);
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("enter number of field spell in your deck");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return;
                } else if (!answer.matches("\\d+")) {
                    System.out.println("enter a number");
                } else {
                    int number = Integer.parseInt(answer);
                    Card card = game.getCurrentUser().getBoard().getDeckZone().get(number - 1);
                    if (card instanceof Spell) {
                        if (((Spell) card).getIcon() == Icon.FIELD) {
                            game.getCurrentUser().getBoard().addCardFromDeckToHand(number - 1);
                            System.out.println("spell activated");
                            return;
                        }
                    }
                    System.out.println("select a field spell");
                }
            }
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        for (Card card : game.getCurrentUser().getBoard().getDeckZone()) {
            if (card instanceof Spell) {
                if (((Spell) card).getIcon() == Icon.FIELD) {
                    return true;
                }
            }
        }
        return false;
    }
}
