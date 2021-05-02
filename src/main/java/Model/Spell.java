package Model;

public class Spell extends Card {

    private String status;
    public Spell(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() +
                "\nSpell" +
                "\nType: " + this.getCardType() +
                "\nDescription: " + this.getDescription();
    }
}
