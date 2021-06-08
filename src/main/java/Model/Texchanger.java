package Model;

public class Texchanger extends Monster{
    boolean isFirstUse = true;
    private Card card;

    public Texchanger() {
        super("Texchanger", Type.CYBERSE);
    }

    public void specialAction () {
        Monster texchanger = (Texchanger) card;
        if (isFirstUse) {
            if (texchanger.getIsAttacked()) {
                texchanger.setIsAttacked(false);
            }
            isFirstUse = false;
        }

        //todo cyberse summon!
    }
}
