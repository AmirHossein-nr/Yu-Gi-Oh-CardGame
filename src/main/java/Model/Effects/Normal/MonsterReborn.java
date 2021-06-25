package Model.Effects.Normal;

import Model.*;
import Model.Effects.Effect;
import Model.Zahra.Icon;
import View.Menu.Game.Game;

import java.util.ArrayList;

public class MonsterReborn extends Effect {

    public MonsterReborn(Card card) {
        super(card);
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            outer:
            while (true) {
                System.out.println("do you want to special summon from enemy's graveyard? (Y/N/cancel)");
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return;
                } else if (answer.equals("Y") || answer.equals("N")) {
                    while (true) {
                        System.out.println("enter number of the monster in graveyard (or \"cancel\" or \"back\" to change graveyard)");
                        String numberString = editSpaces(scanner.nextLine());
                        if (numberString.equals("cancel")) {
                            System.out.println("canceled");
                            return;
                        } else if (numberString.matches("\\d+")) {
                            int number = Integer.parseInt(numberString);
                            ArrayList<Card> graveyard;
                            if (answer.equals("Y")) { //enemy graveyard
                                graveyard = game.getOpponentOfCurrentUser().getBoard().getGraveYard();
                            } else { //my graveyard
                                graveyard = game.getCurrentUser().getBoard().getGraveYard();
                            }
                            if (number < 0 || number > graveyard.size()) {
                                System.out.println("enter a correct number");
                                continue;
                            }
                            if (!(graveyard.get(number - 1) instanceof Monster)) {
                                System.out.println("this card is not a monster");
                                continue;
                            }
                            Card card = graveyard.get(number - 1);
                            if ((card.getName().equals("Gate Guardian") || card.getCardType() == Type.RITUAL) && !game.getSpecialSummonedCards().contains(card)) {
                                System.out.println("should be special summoned first");
                                continue;
                            }
                            System.out.println("defence or attack?");
                            while (true) {
                                String answer1 = editSpaces(scanner.nextLine());
                                if (answer1.equals("cancel")) {
                                    System.out.println("canceled");
                                    return;
                                } else if (answer1.equals("attack")) {
                                    card.setAttackPosition(true);
                                    break;
                                } else if (answer1.equals("defence")) {
                                    card.setAttackPosition(false);
                                    break;
                                } else {
                                    System.out.println("enter attack or defence or cancel");
                                }
                            }
                            if (answer.equals("Y")) {
                                game.getOpponentOfCurrentUser().getBoard().getAllCards().remove(card);
                                game.getCurrentUser().getBoard().getAllCards().add(card);
                            }
                            for (int i = 0; i < game.getCurrentUser().getBoard().getMonstersZone().size(); i++) {
                                if (game.getCurrentUser().getBoard().getMonstersZone().get(i) == null) {
                                    game.getCurrentUser().getBoard().getMonstersZone().set(i, card);
                                    break;
                                }
                            }
                            graveyard.remove(card);
                            card.setOccupied(true);
                            System.out.println("spell activated");
                        } else if (numberString.equals("back")) {
                            continue outer;
                        } else {
                            System.out.println("enter a number");
                        }
                    }
                } else {
                    System.out.println("enter Y or N or cancel");
                }
            }
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getCurrentUser().getBoard().numberOfMonstersOnBoard() == 5) {
            return false;
        }
        for (Card card : game.getCurrentUser().getBoard().getGraveYard()) {
            if (card instanceof Monster) {
                return true;
            }
        }
        for (Card card : game.getOpponentOfCurrentUser().getBoard().getGraveYard()) {
            if (card instanceof Monster) {
                return true;
            }
        }
        return false;
    }
}
