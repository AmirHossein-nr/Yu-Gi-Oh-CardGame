package Model;

public class BeastKingBarbaros extends Monster{
    private Card card;
    public BeastKingBarbaros() {
        super("Beast King Barbaros", Type.BEAST_WARRIOR);
    }

    public void specialAction (boolean isRitualSummon) {
        Monster beastKingBarbaros = (BeastKingBarbaros) card;
        if (!isRitualSummon) {
            beastKingBarbaros.setAttackPower(1900);

        }else {
            if (beastKingBarbaros.getOccupied()) {
                //todo destroy all rival cards!
            }
        }
    }
}
