package Model;

public class TorrentialTribute extends Trap {

    public TorrentialTribute() {
        super("Torrential Tribute", Type.TRAP);
    }

    public void specialAction (boolean isAnyRivalMonsterSummoned, Board rivalBoard) {
        if (isAnyRivalMonsterSummoned) {
            for (Card card : rivalBoard.getMonstersZone()) {
                rivalBoard.getGraveYard().add(card);
                rivalBoard.getMonstersZone().remove(card);
            }
        }
    }

}
