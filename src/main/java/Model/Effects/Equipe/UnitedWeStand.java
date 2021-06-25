package Model.Effects.Equipe;

import Model.Card;
import Model.Effects.Effect;
import Model.Monster;
import Model.Type;
import View.Menu.Game.Game;

public class UnitedWeStand extends Effect {

    public UnitedWeStand(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("select number on the monster in monster zone to equip");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return;
                } else if (answer.matches("\\d+")) {
                    int number = Integer.parseInt(answer);
                    if (number > 0 && number < 6) {
                        if (game.getCurrentUser().getBoard().getMonstersZone().get(number - 1) != null) {
                            Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(number - 1);
                            int damage = 0;
                            for (int i = 0; i < 5; i++) {
                                if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null && game.getCurrentUser().getBoard().getMonstersZone().get(i).getOccupied()) {
                                    damage += 800;
                                }
                            }
                            monster.setDefencePower(monster.getDefencePower() + damage);
                            monster.setAttackPower(monster.getAttackPower() + damage);
                            game.getCurrentUser().getBoard().getSpellMonsterEquip().put(card, monster);
                            break;
                        } else {
                            System.out.println("there is no monster here");
                        }
                    } else {
                        System.out.println("enter correct number");
                    }
                } else {
                    System.out.println("enter a number");
                }
            }
            System.out.println("spell activated");
        } else {
            System.out.println("preparations of this spell are not done yet");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                return true;
            }
        }
        return false;
    }
}
