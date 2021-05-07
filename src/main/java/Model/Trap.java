package Model;

public class Trap extends Card {

    private String status;

    public Trap(String name, Type type) {
        super(name, type);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
