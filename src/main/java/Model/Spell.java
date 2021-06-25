package Model;

import Model.Effects.Effect;
import Model.Effects.Equipe.BlackPendant;
import Model.Effects.Equipe.MagnumShield;
import Model.Effects.Equipe.SwordOfDarkDestruction;
import Model.Effects.Equipe.UnitedWeStand;
import Model.Effects.Field.ClosedForest;
import Model.Effects.Field.Forest;
import Model.Effects.Field.Umiiruka;
import Model.Effects.Field.Yami;
import Model.Effects.Normal.*;
import Model.Effects.Ritual.Ritual;
import Model.Zahra.Icon;

public class Spell extends Card {

    private String status;
    private Icon icon;
    private Effect effect;

    public Spell(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    public Effect getEffect() {
        return effect;
    }

    // todo
    public void giveEffect() {
        // todo check names
        if (this.getName().equals("Closed Forest")) {
            setEffect(new ClosedForest(this));
        } else if (this.getName().equals("Forest")) {
            setEffect(new Forest(this));
        } else if (this.getName().equals("Umiiruka")) {
            setEffect(new Umiiruka(this));
        } else if (this.getName().equals("Yami")) {
            setEffect(new Yami(this));
        } else if (this.getName().equals("DarkHole")) {
            setEffect(new DarkHole(this));
        } else if (this.getName().equals("Harpie's Feather Duster")) {
            setEffect(new HarpiesFeatherDuster(this));
        } else if (this.getName().equals("Monster Reborn")) {
            setEffect(new MonsterReborn(this));
        } else if (this.getName().equals("Pot of Greed")) {
            setEffect(new PotOfGreed(this));
        } else if (this.getName().equals("Raigeki")) {
            setEffect(new Raigeki(this));
        } else if (this.getName().equals("Terraforming")) {
            setEffect(new Terraforming(this));
        } else if (this.getName().equals("Advanced Ritual Art")) {
            setEffect(new Ritual(this));
        } else if (this.getName().equals("Black Pendant")) {
            setEffect(new BlackPendant(this));
        } else if (this.getName().equals("Magnum Shield")) {
            setEffect(new MagnumShield(this));
        } else if (this.getName().equals("Sword of Dark Destruction")) {
            setEffect(new SwordOfDarkDestruction(this));
        } else if (this.getName().equals("United We Stand")) {
            setEffect(new UnitedWeStand(this));
        }
    }

    @Override
    public Object clone() {
        Spell spell = new Spell(this.getName(),this.getCardType());
        spell.setDescription(this.getDescription());
        spell.setPrice(this.getPrice());
        spell.setStatus(this.getStatus());
        spell.setAttackPosition(this.getAttackPosition());
        spell.setOccupied(this.getOccupied());
        spell.setSelected(this.getSelected());
        return spell;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() +
                "\nSpell" +
                "\nType: " + this.getCardType() +
                "\nDescription: " + this.getDescription();
    }
}
