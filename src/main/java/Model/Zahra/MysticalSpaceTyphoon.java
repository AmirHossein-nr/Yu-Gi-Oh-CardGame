package Model.Zahra;

import Model.*;

public class MysticalSpaceTyphoon extends Spell {

    public MysticalSpaceTyphoon() {
        super("Mystical space typhoon", Type.SPELL);
    }

    public void specialAction (Board rivalBoard, Card rivalCart) {
        if (rivalCart instanceof Spell || rivalCart instanceof Trap) {
            rivalBoard.getSpellsAndTrapsZone().remove(rivalCart);
            rivalBoard.getGraveYard().add(rivalCart);
        }
    }
}
