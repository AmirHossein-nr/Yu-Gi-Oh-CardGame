package Model;

import Model.Effects.Continuous.MessengerOfPeace;
import Model.Effects.Continuous.SpellAbsorption;
import Model.Effects.Continuous.SupplySquad;
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
import Model.Effects.FalseEffect;

public class Spell extends Card {

    private String status;
    private Effect effect;

    public Spell(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String getStatus() {
        return status;
    }

    public Effect getEffect() {
        return effect;
    }


    public void giveEffect() {
        if (this.getName().equals("Closed Forest")) {
            setEffect(new ClosedForest(this));
        } else if (this.getName().equals("Forest")) {
            setEffect(new Forest(this));
        } else if (this.getName().equals("Umiiruka")) {
            setEffect(new Umiiruka(this));
        } else if (this.getName().equals("Yami")) {
            setEffect(new Yami(this));
        } else if (this.getName().equals("Dark Hole")) {
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
        } else if (this.getName().equals("Messenger of Peace")) {
            setEffect(new MessengerOfPeace(this));
        } else if (this.getName().equals("Spell Absorption")) {
            setEffect(new SpellAbsorption(this));
        } else if (this.getName().equals("Supply Squad")) {
            setEffect(new SupplySquad(this));
        } else if (this.getName().equals("Twin Twisters")) {
            setEffect(new FalseEffect(this));
        } else if (this.getName().equals("Mystical space typhoon")) {
            setEffect(new FalseEffect(this));
        } else {
            setEffect(new FalseEffect(this));
        }
    }

    @Override
    public Object clone() {
        Spell spell = new Spell(this.getName(), this.getCardType());
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
