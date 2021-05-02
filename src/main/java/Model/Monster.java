package Model;

public class Monster extends Card {

    Type monsterType;
    Attribute attribute;
    int attackPower;
    int defencePower;
    int level;

    public Monster(String name, Type cardType) {
        super(name, cardType);
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    public void setMonsterType(Type monsterType) {
        this.monsterType = monsterType;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
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