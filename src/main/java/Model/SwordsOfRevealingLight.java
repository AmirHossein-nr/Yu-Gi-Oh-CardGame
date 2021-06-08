package Model;

public class SwordsOfRevealingLight extends Spell{
    int turn = 1;
    public SwordsOfRevealingLight() {
        super("Swords Of Revealing Light", Type.SPELL);
    }

    public void specialAction (Spell swordsOfRevealingLight, boolean isActive, Board rivalBoard, Board currentBoard) {
        if (isActive) {
            if (turn <= 3) {
                for (Card card : rivalBoard.getMonstersZone()) {
                    if (!card.getOccupied()) {
                        card.setOccupied(true);

                    }
                }
                turn += 1;
            }
        }
        if (turn == 3) {
            currentBoard.getGraveYard().add(swordsOfRevealingLight);
            currentBoard.getSpellsAndTrapsZone().remove(swordsOfRevealingLight);
        }
    }
}
