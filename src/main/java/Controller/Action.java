package Controller;

import Model.*;

public class Action {
    //effect    //condition
    private String name;
    int monstersInField ;

    private Card card;

    public Action (Card card) {
        this.card = card;
    }

    public void setName(Card card) {
        this.name = card.getName();
    }

    public String getName() {
        return name;
    }

    public void activate (User currentUser, User rivalUser, Board currentBoard, Board rivalBoard) {
        name = card.getName();
        if (name.equals("Command Knight")) {
                for (Card card : currentBoard.getMonstersZone()) {
                    Monster monster = (Monster) card;
                    monster.setAttackPower(monster.getAttackPower() + 400);
                }
                if (monstersInField > 1) {
                    Monster monster = (Monster) card;
                    monster.setCanBeAttacked(false);
                }
        }
        else if (name.equals("Yomi Ship")) {
            //todo funtion of yomi ship
        }
        else if (name.equals("Suijin")) {
            Monster monster = new Monster("Suijin", Type.EFFECT);
            activateSuijin(monster, currentUser, rivalUser);
        }
        else if (name.equals("Crab Turtle")) {

        }
        else if (name.equals("Skull Guardian")) {

        }
    }

    public void activateSuijin (Monster monster, User currentUser, User rivalUser) {
        // todo just for one time ...

        if (isOccupied) {
            Monster rivalMonster = (Monster) card;
            rivalMonster.setAttackPower(rivalMonster.getAttackPower());
        }
    }
}
