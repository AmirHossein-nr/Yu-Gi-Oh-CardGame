package Model;

public class HarpiesFeatherDuster extends Spell {

    public HarpiesFeatherDuster() {
        super("Harpie's Feather Duster", Type.SPELL);
    }

    public void specialAction (Board rivalBoard, Board currentBoard) {
        //cats in control are those in the field
        for (Card card : rivalBoard.getMonstersZone()) {
            rivalBoard.getSpellsAndTrapsZone().remove(card);
            currentBoard.getSpellsAndTrapsZone().add(card);
        }
    }
}
