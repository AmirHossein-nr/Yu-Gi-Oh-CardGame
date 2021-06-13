package Model.Zahra;

import Model.Board;
import Model.Card;
import Model.Monster;
import Model.Type;

public class ExploderDragon extends Monster {

    public ExploderDragon() {
        super("Exploder Dragon", Type.DRAGON);
    }

    public void specialAction (Monster exploderDragon, Monster rivalMonster, Board currentBoard, Board rivalBoard) {
        for (Card card : currentBoard.getGraveYard()) {
            if (card == exploderDragon) {
                rivalBoard.getGraveYard().add(rivalMonster);
            }
        }
    }
}
