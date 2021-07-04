package Model;

import Model.Effects.Counter.MagicCylinder;
import Model.Effects.Counter.MagicJammer;
import Model.Effects.Counter.NegateAttack;
import Model.Effects.Effect;
import Model.Effects.NormalTrap.MirrorForce;
import Model.Effects.falseEffect;

public class Trap extends Card {

    private String status;
    private  Boolean canBeActive = true;
    private boolean isDestructive = false;
    private Effect effect;

    public Trap(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDestructive(boolean destructive) {
        isDestructive = destructive;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public boolean getIsDestructive () {
        return isDestructive;
    }

    public void setCanBeActive(Boolean canBeActive) {
        this.canBeActive = canBeActive;
    }

    public Boolean getCanBeActive() {
        return canBeActive;
    }

    public String getStatus() {
        return status;
    }

    public Effect getEffect() {
        return effect;
    }

    public void giveEffect() {
        if (this.getName().equals("Mirror Force")) {
            setEffect(new MirrorForce(this));
        } else if (this.getName().equals("Magic Cylinder")) {
            setEffect(new MagicCylinder(this));
        } else if (this.getName().equals("Negate Attack")) {
            setEffect(new NegateAttack(this));
        } else if (this.getName().equals("Magic Jammer")) {
            setEffect(new MagicJammer(this));
        } else {
            setEffect(new falseEffect(this));
        }
    }

    @Override
    public Object clone() {
        Trap trap = new Trap(this.getName(), this.getCardType());
        trap.setDescription(this.getDescription());
        trap.setPrice(this.getPrice());
        trap.setStatus(this.getStatus());
        trap.setAttackPosition(this.getAttackPosition());
        trap.setOccupied(this.getOccupied());
        trap.setSelected(this.getSelected());
        return trap;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() +
                "\nTrap" +
                "\nType: " + this.getCardType() +
                "\nDescription: " + this.getDescription();
    }
}
