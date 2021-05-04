package Model;


public class Card {
    private String name;
    private String description;
    private Boolean isOccupied;
    private Boolean isAttackPosition;
    private Boolean isSelected;
    private Type cardType;
    private Integer price;


    public Card(String name, Type type) {
        setName(name);
        setCardType(type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardType(Type cardType) {
        this.cardType = cardType;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public void setAttackPosition(Boolean attackPosition) {
        isAttackPosition = attackPosition;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Type getCardType() {
        return cardType;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public Boolean getAttackPosition() {
        return isAttackPosition;
    }

    @Override
    public String toString() {
        return
                name + ':' + price;
    }
}
