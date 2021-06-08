package Model;

public class MirrorForce extends Trap {

    public MirrorForce() {
        super("Mirror Force", Type.TRAP);
    }

    public void specialAction (Board rivalBoard, Monster rivalAttackingMonster) {
        for (Card card : rivalBoard.getMonstersZone()) {
            if (card.getAttackPosition()) {
                rivalBoard.getGraveYard().add(card);
                rivalBoard.getMonstersZone().remove(card);
            }
        }
    }
}
