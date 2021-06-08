package Model;

public class SpellAbsorption extends Spell{

    public SpellAbsorption() {
        super("Spell Absorption", Type.SPELL);
    }

    public void specialAction (boolean isAnySpellIsActive, User currentUser) {
        if (isAnySpellIsActive) {
            currentUser.setLifePoint(currentUser.getLifePoint() + 500);
        }
    }
}
