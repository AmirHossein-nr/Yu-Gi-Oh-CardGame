package Model.Effects.Ritual;

import Model.*;
import Model.Effects.Effect;
import Controller.Game;
import View.GUI.GamePlay;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Ritual extends Effect {


    public Ritual(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        game.setActivatedRitualCard((Spell) game.getSelectedCard());
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
        return true;
    }

    @Override
    public void addToChain(Game game) {
        if (canBeActivated(game)) {
            TheOwner = game.getCurrentUser();
            game.getChain().add(card);
            card.setOccupied(true);
            if (game.getCurrentUser().getBoard().getCardsInHand().contains(card)) {
                game.addSpellOrTrapFromHandToZone(card, true);
            }
        } else {
            System.out.println("there is no way you could ritual summon a monster");
        }
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        User currentUser = game.getCurrentUser();
        ArrayList<Monster> ritualMonsters = new ArrayList<>();
        for (Card card : currentUser.getBoard().getCardsInHand()) {
            if (card instanceof Monster && card.getCardType() == Type.RITUAL) {
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
                for (int j = i + 1; i < 5; i++) {
                    int lvl2 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(j)).getLevel();
                    if (lvl1 + lvl2 >= level) {
                        return true;
                    }
                    for (int k = j + 1; i < 5; i++) {
                        int lvl3 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(k)).getLevel();
                        if (lvl1 + lvl2 + lvl3 >= level) {
                            return true;
                        }
                        for (int l = k + 1; i < 5; i++) {
                            int lvl4 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(l)).getLevel();
                            if (lvl1 + lvl2 + lvl3 + lvl4 >= level) {
                                return true;
                            }
                            for (int m = l + 1; i < 5; i++) {
                                int lvl5 = ((Monster) game.getCurrentUser().getBoard().getMonstersZone().get(m)).getLevel();
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
