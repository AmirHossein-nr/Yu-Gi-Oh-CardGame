package Model;

public class TheCalculator extends Monster{
    private Card card;

    public TheCalculator() {
        super("The Calculator", Type.THUNDER);
    }

    public void specialAction (User currentUser, Board currentBoard, Monster theCalculator) {
        theCalculator = (Texchanger) card;
        int attackPower = 0;
        for (Card card : currentBoard.getMonstersZone()) {
            if (card instanceof Monster) {
                if (card.getOccupied()) {
                    attackPower += ((Monster) card).getAttackPower();
                }
            }
        }
        theCalculator.setAttackPower(attackPower * 300);
    }
}
