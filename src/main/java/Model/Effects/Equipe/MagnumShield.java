package Model.Effects.Equipe;

import Model.Card;
import Model.Monster;
import Model.Type;
import Controller.Game;
import View.Menu.Shop;

public class MagnumShield extends EquipEffect {

    public MagnumShield(Card card) {
        super(card);
    }

    @Override
    public void deActive() {
        Monster monster;
        for (Card card : Shop.getAllCards()) {
            if (card.getName().equals(this.monster.getName())) {
                monster = (Monster) card;
                int defence = monster.getDefencePower();
                int attack = monster.getAttackPower();
                this.monster.setAttackPower(this.monster.getAttackPower() - defence);
                this.monster.setDefencePower(this.monster.getDefencePower() - attack);
                return;
            }
        }
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("select number of the Warrior monster in monster zone to equip");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("cancel")) {
                    System.out.println("canceled");
                    return false;
                } else if (answer.matches("\\d+")) {
                    int number = Integer.parseInt(answer);
                    if (number > 0 && number < 6) {
                        if (game.getCurrentUser().getBoard().getMonstersZone().get(number - 1) != null) {
                            Monster monster = (Monster) game.getCurrentUser().getBoard().getMonstersZone().get(number - 1);
                            if (monster.getMonsterType() == Type.WARRIOR) {
                                int damage = monster.getAttackPower() + monster.getDefencePower();
                                monster.setAttackPower(damage);
                                monster.setDefencePower(damage);
                                game.getCurrentUser().getBoard().getSpellMonsterEquip().put(card, monster);
                                this.monster = monster;
                                break;
                            } else {
                                System.out.println("not a Warrior monster");
                            }
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
            return true;
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
        for (int i = 0; i < 5; i++) {
            if (game.getCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                if (((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getMonsterType() == Type.WARRIOR) {
                    return true;
                }
            }
        }
        return false;
    }
}
