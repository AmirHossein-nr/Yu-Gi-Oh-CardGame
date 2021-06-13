package Model.Zahra;
import Model.*;

public class CommandKnight extends Monster{

    private Card card;
    public int numberOfMonstersInField;

    public CommandKnight() {
        super("Command Knight", Type.WARRIOR);
    }

    public void specialAction (User currentUser, User rivalUser, Board currentBoard, Board rivalBoard) {
        Card commandKnight = (CommandKnight) card;
        if (commandKnight.getOccupied()) {
            for (Card card : currentBoard.getMonstersZone()) {
                Monster monster = (Monster) card;
                monster.setAttackPower(monster.getAttackPower() + 400);
            }
            if (numberOfMonstersInField > 1) {
                Monster monster = (Monster) card;
                monster.setCanBeAttacked(false);
            }
        }
    }
}
