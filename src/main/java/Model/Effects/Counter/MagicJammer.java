package Model.Effects.Counter;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Trap;
import Model.User;

public class MagicJammer extends Effect {

    private User owner;
    private User enemy;

    // this is the card in chain that has to be destroyed
    private Card cardToDestroy;

    public MagicJammer(Card card) {
        super(card);
        speed = 3;
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            int destroyedCardsNumber = 0;
            System.out.println("enter the number of card in your hand to destroy");
            while (true) {
                String numberString = editSpaces(scanner.nextLine());
                if (numberString.equals("cancel")) {
                    System.out.println("canceled");
                    return false;
                } else if (!numberString.matches("\\d+")) {
                    System.out.println("enter a number");
                } else {
                    int number = Integer.parseInt(numberString);
                    if (number < 0 || number > owner.getBoard().getCardsInHand().size()) {
                        System.out.println("enter a correct number");
                        continue;
                    }
                    Card cardToTribute = enemy.getBoard().getCardsInHand().get(number - 1);
                    owner.getBoard().getCardsInHand().remove(cardToTribute);
                    owner.getBoard().getGraveYard().add(cardToTribute);
                    break;
                }
            }
            game.addSpellOrTrapFromZoneToGraveyard(cardToDestroy, enemy);
            System.out.println("trap activated");
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
        // check Mirage Dragon
        for (int i = 0; i < 5; i++) {
            Card enemyMonster = enemy.getBoard().getMonstersZone().get(i);
            if (enemyMonster != null) {
                if (enemyMonster.getOccupied()) {
                    if (enemyMonster.getName().equals("Mirage Dragon")) {
                        return false;
                    }
                }
            }
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
        if (owner.getBoard().getCardsInHand().size() == 0) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (enemy.getBoard().getSpellsAndTrapsZone().get(i) != null) {
                break;
            }
        }
        if (game.getChain().size() != 0) {
            if (game.getChain().get(game.getChain().size() - 1) instanceof Spell) {
                if (enemy.getBoard().getAllCards().contains(game.getChain().get(game.getChain().size() - 1))) {
                    cardToDestroy = game.getChain().get(game.getChain().size() - 1);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
