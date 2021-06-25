import Model.*;
import Model.Zahra.SideDeck;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;

public class TestBoard {
    @Test
    public void testBoard() {
        Board board = new Board();
        board.setDeck(new Deck(new MainDeck(true), new SideDeck(true)));
        board.setFieldZone(new Card("Field", Type.FIELD));
        board.setSpellMonsterEquip(new HashMap<>());
        Assertions.assertNotNull(board.getDeck());
        Assertions.assertNotNull(board.getDeckZone());
        Assertions.assertNotNull(board.getAllCards());
        Assertions.assertNotNull(board.getFieldZone());
        Assert.assertEquals(board.getFieldZone().getCardType(), Type.FIELD);
        Assertions.assertNotNull(board.getGraveYard());
    }

    @Test(expected = NullPointerException.class)
    public void testNullInput() {
        Board board = new Board();
        board.setZones();
    }

    @Test
    public void testGettingInfoFromBoard() {
        Board board = new Board();
        Deck deck = new Deck(new MainDeck(true), new SideDeck(true));
        deck.setName("Test");
        board.setDeck(deck);
        board.setFieldZone(new Card("Field", Type.FIELD));
        board.setSpellMonsterEquip(new HashMap<>());
        Assert.assertEquals(board.getDeck().getName(), "Test");
        Assert.assertEquals(board.numberOfMonstersOnBoard(), 0);
        Assert.assertEquals(board.numberOfSpellAndTrapsOnBoard(), 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBound() {
        Board board = new Board();
        board.addCardFromDeckToHand(1);
    }
}
