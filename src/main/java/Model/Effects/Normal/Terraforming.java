package Model.Effects.Normal;

import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Type;
import Controller.Game;

public class Terraforming extends Effect {

    public Terraforming(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("enter number of field spell in your deck");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return false;
                } else if (!answer.matches("\\d+")) {
                    System.out.println("enter a number");
                } else {
                    int number = Integer.parseInt(answer);
                    Card card = game.getCurrentUser().getBoard().getDeckZone().get(number - 1);
                    if (card instanceof Spell) {
                        if (card.getCardType() == Type.FIELD) {
                            game.getCurrentUser().getBoard().addCardFromDeckToHand(number - 1);
                            System.out.println("spell activated");
                            game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
                            return true;
                        }
                    }
                    System.out.println("select a field spell");
                }
            }
        } else {
            System.out.println("preparations of this spell are not done yet");
            return false;
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        for (Card card : game.getCurrentUser().getBoard().getDeckZone()) {
            if (card instanceof Spell) {
                if (card.getCardType() == Type.FIELD) {
                    return true;
                }
            }
        }
        return false;
    }
}
