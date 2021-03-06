package Model;


import com.google.gson.annotations.Expose;
import javafx.scene.image.Image;

public class Card {
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private Boolean isOccupied;
    @Expose
    private Boolean newOccupationState;
    @Expose
    private Boolean isAttackPosition;
    @Expose
    private Boolean isSelected;
    @Expose
    private Type cardType;
    @Expose
    private Integer price;
    @Expose
    private boolean isUserMade = false;

    private Image cardImage;

    public Card(String name, Type type) {
        setName(name);
        setCardType(type);
        setOccupied(false);
        setSelected(false);
        setAttackPosition(false);
    }


    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
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

    public void setUserMade(boolean userMade) {
        isUserMade = userMade;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public void setNewOccupationState(Boolean newOccupationState) {
        this.newOccupationState = newOccupationState;
    }

    public void setAttackPosition(Boolean attackPosition) {
        isAttackPosition = attackPosition;
    }

    public Boolean getNewOccupationState() {
        return newOccupationState;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public boolean getUserMade() {
        return isUserMade;
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
