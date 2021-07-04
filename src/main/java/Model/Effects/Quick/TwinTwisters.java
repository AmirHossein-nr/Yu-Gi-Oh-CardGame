package Model.Effects.Quick;

import Controller.Game;
import Model.Card;
import Model.Effects.Effect;
import Model.Spell;
import Model.Trap;
import Model.User;

public class TwinTwisters extends Effect {

    private User owner;
    private User enemy;

    public TwinTwisters(Card card) {
        super(card);
        speed = 2;
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
            System.out.println("enter the number of the spell or trap in enemy's zone to destroy.(you can destroy max 2 cards)");
            outer:
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
                    destroyedCardsNumber++;
                    if (destroyedCardsNumber == 1) {
                        if (enemy.getBoard().numberOfSpellAndTrapsOnBoard() != 0) {
                            System.out.println("do you want to destroy one more card?(Y/N)");
                            while (true) {
                                String answer = editSpaces(scanner.nextLine());
                                if (answer.equals("Y")) {
                                    System.out.println("enter a number");
                                    continue outer;
                                } else if (answer.equals("N")) {
                                    break outer;
                                } else {
                                    System.out.println("enter Y or N");
                                }
                            }
                        }
                    }
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
        if (owner.getBoard().getCardsInHand().contains(card)) {
            if (owner.getBoard().getCardsInHand().size() < 2) {
                return false;
            }
        } else {
            if (owner.getBoard().getCardsInHand().size() == 0) {
                return false;
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
