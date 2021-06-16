package Model;

public class Monster extends Card {

    private Type monsterType;
    private Attribute attribute;
    private int attackPower;
    private int defencePower;
    private int level;
    private boolean canBeAttacked;
    private boolean isAttacked;
    private Card equippedWith;

    public Monster(String name, Type cardType) {
        super(name, cardType);
        setCanBeAttacked(true);
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public Object clone() {
        Monster monster = new Monster(getName(),getCardType());
        monster.setAttribute(this.getAttribute());
        monster.setPrice(this.getPrice());
        monster.setDescription(this.getDescription());
        monster.setMonsterType(this.getMonsterType());
        monster.setLevel(this.getLevel());
        monster.setOccupied(this.getOccupied());
        monster.setDefencePower(this.getDefencePower());
        monster.setAttackPower(this.getAttackPower());
        monster.setSelected(this.getSelected());
        monster.setAttackPosition(this.getAttackPosition());
        return monster;
    }

    public void setMonsterType(Type monsterType) {
        this.monsterType = monsterType;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void setIsAttacked(boolean isAttacked) {
        this.isAttacked = isAttacked;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setDefencePower(int defencePower) {
        this.defencePower = defencePower;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public void setEquippedWith(Card equippedWith) {
        this.equippedWith = equippedWith;
    }

    public Card getEquippedWith() {
        return equippedWith;
    }

    public boolean getCanBeAttacked () {
        return canBeAttacked;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public Type getMonsterType() {
        return monsterType;
    }

    public int getLevel() {
        return level;
    }

    public boolean getIsAttacked () {
        return isAttacked;
    }


    @Override
    public String toString() {

        return "Name: " + this.getName() +
                "\nLevel: " + level +
                "\nType: " + this.getCardType() +
                "\nATK: " + attackPower +
                "\nDEF: " + defencePower +
                "\nDescription: " + this.getDescription();
    }
}