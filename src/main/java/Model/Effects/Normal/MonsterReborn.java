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
            System.out.println("do you want to special summon from enemy's graveyard? (Y/N/cancel)");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return;
                } else if (answer.equals("Y") || answer.equals("N")) {
                    System.out.println("enter number of the monster in graveyard");
                    while (true) {
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
                            //todo
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
    public void addToChain(Game game) {
        if (canBeActivated(game)) {
            game.getChain().add(card);
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
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
