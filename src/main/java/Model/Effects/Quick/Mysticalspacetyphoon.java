package Model.Effects.Quick;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Trap;
import Model.User;

public class Mysticalspacetyphoon extends Effect {

    private User owner;
    private User enemy;

    public Mysticalspacetyphoon(Card card) {
        super(card);
        speed = 2;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("enter the number of the spell or trap in enemy's zone to destroy");
            while (true) {
                String numberString = editSpaces(scanner.nextLine());
                if (numberString.equals("cancel")) {
                    System.out.println("canceled");
                    return false;
                } else if (!numberString.matches("\\d+")) {
                    System.out.println("enter a number");
                } else {
                    int number = Integer.parseInt(numberString);
                    if (number < 0 || number > 5) {
                        System.out.println("enter a correct number");
                        continue;
                    }
                    if (enemy.getBoard().getSpellsAndTrapsZone().get(number - 1) == null) {
                        System.out.println("there is no spell or trap in here");
                        continue;
                    }
                    game.addSpellOrTrapFromZoneToGraveyard(enemy.getBoard().getSpellsAndTrapsZone().get(number - 1), enemy);
                    break;
                }
            }
            System.out.println("spell activated");
            game.addSpellOrTrapFromZoneToGraveyard(card, owner);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getCurrentUser().getBoard().getAllCards().contains(card)) {
            owner = game.getCurrentUser();
            enemy = game.getOpponentOfCurrentUser();
        } else {
            owner = game.getOpponentOfCurrentUser();
            enemy = game.getCurrentUser();
        }
        if (game.getChain().size() != 0) {
            if (game.getChain().get(game.getChain().size() - 1) instanceof Spell) {
                if (((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                    return false;
                }
            } else {
                if (((Trap) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (enemy.getBoard().getSpellsAndTrapsZone().get(i) != null) {
                break;
            }
        }
        return true;
    }
}
