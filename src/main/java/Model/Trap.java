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
    public String toString() {
        return "Name: " + this.getName() +
                "\nTrap" +
                "\nType: " + this.getCardType() +
                "\nDescription: " + this.getDescription();
    }
}
