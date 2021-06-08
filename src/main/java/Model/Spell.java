package Model;

public class Spell extends Card {

    private String status;
    private Icon icon;
    public Spell(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
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
