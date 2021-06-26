package Model.Effects.Equipe;

import Model.Card;
import Model.Monster;
import Model.Type;
import Controller.Game;

public class SwordOfDarkDestruction extends EquipEffect {

    public SwordOfDarkDestruction(Card card) {
        super(card);
    }

    @Override
    public void deActive() {
        monster.setDefencePower(monster.getDefencePower() + 200);
        monster.setAttackPower(monster.getAttackPower() - 400);
    }

    @Override
    public boolean activate(Game game) {
        if (canBeActivated(game)) {
            System.out.println("select number of the Spellcaster or Fiend monster in monster zone to equip");
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
                            if (monster.getMonsterType() == Type.FIEND || monster.getMonsterType() == Type.SPELL_CASTER) {
                                monster.setDefencePower(monster.getDefencePower() - 200);
                                monster.setAttackPower(monster.getAttackPower() + 400);
                                game.getCurrentUser().getBoard().getSpellMonsterEquip().put(card, monster);
                                this.monster = monster;
                                break;
                            } else {
                                System.out.println("not a Fiend or SpellCaster monster");
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
                if (((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getMonsterType() == Type.FIEND) {
                    return true;
                }
                if (((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getMonsterType() == Type.SPELL_CASTER) {
                    return true;
                }
            }
        }
        return false;
    }
}
