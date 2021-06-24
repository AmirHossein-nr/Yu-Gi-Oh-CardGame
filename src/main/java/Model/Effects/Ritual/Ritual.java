package Model.Effects.Ritual;

import Model.*;
import Model.Effects.Effect;
import View.Menu.Game.Game;

import java.util.ArrayList;

public class Ritual extends Effect {

    private boolean ritualSummoned = false; //todo

    public Ritual(Card card) {
        super(card);
    }

    public boolean isRitualSummoned() {
        return ritualSummoned;
    }

    public void setRitualSummoned(boolean ritualSummoned) {
        this.ritualSummoned = ritualSummoned;
    }

    @Override
    public void activate(Game game) {
        if (canBeActivated(game)) {
            game.setActivatedRitualCard((Spell) game.getSelectedCard());
            System.out.println("spell activated");
        } else {
            System.out.println("there is no way you could ritual summon a monster");
        }
    }

    @Override
    public void addToChain(Game game) {
        if (canBeActivated(game)) {
            game.getChain().add(card);
        } else {
            System.out.println("there is no way you could ritual summon a monster");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        User currentUser = game.getCurrentUser();
        ArrayList<Monster> ritualMonsters = new ArrayList<>();
        for (Card card : currentUser.getBoard().getCardsInHand()) {
            if (card instanceof Monster && card.getCardType() == Type.RITUAL) { // todo type ro az amir bepors
                ritualMonsters.add((Monster) card);
            }
        }
        if (ritualMonsters.size() == 0) {
            return false;
        }
        for (Monster monster : ritualMonsters) {
            int level = monster.getLevel();
            for (int i = 0; i < 5; i++) {
                int lvl1 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                if (lvl1 >= level) {
                    return true;
                }
                for (int j = 0; i < 5; i++) {
                    int lvl2 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                    if (lvl1 + lvl2 >= level) {
                        return true;
                    }
                    for (int k = 0; i < 5; i++) {
                        int lvl3 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                        if (lvl1 + lvl2 + lvl3 >= level) {
                            return true;
                        }
                        for (int l = 0; i < 5; i++) {
                            int lvl4 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                            if (lvl1 + lvl2 + lvl3 + lvl4 >= level) {
                                return true;
                            }
                            for (int m = 0; i < 5; i++) {
                                int lvl5 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                                if (lvl1 + lvl2 + lvl3 + lvl4 + lvl5 >= level) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
