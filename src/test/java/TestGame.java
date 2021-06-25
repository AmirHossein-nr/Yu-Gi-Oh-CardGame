import Model.*;
import Controller.Game;
import View.Menu.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Scanner;

public class TestGame {
    private User user1;
    private User user2;

    private void initialise() {
        user1 = new User("amirhossein", "1234", "mammad");
        user1.setBoard(new Board());
        user2 = new User("hossein", "1234", "asghar");
        user2.setBoard(new Board());
        Menu.setScanner(new Scanner(System.in));
        Menu.setLoggedUser(user1);
    }

    @Test
    public void testBoard() {
        initialise();
        StringBuilder board = new StringBuilder();
        Game game = new Game(user1, user2, 1, new Scanner(System.in));
        board = game.boardString(board);
        Assertions.assertNotNull(board);
        Assert.assertEquals(board.toString(),
                "asghar:8000\n" +
                        "\t\n" +
                        "0\n" +
                        "\tE\tE\tE\tE\tE\n" +
                        "\tE\tE\tE\tE\tE\n" +
                        "0\t\t\t\t\t\tE\n" +
                        "\n" +
                        "--------------------------\n" +
                        "\n" +
                        "E\t\t\t\t\t\t0\n" +
                        "\tE\tE\tE\tE\tE\n" +
                        "\tE\tE\tE\tE\tE\n" +
                        "\t\t\t\t\t\t0\n" +
                        "\t\n" +
                        "mammad:8000\n");
    }

    @Test
    public void testOpponent() {
        initialise();
        Game game = new Game(user1, user2, 1, new Scanner(System.in));
        Assert.assertEquals(game.getOpponentOfCurrentUser().getNickName(), user2.getNickName());
        Assert.assertEquals(game.getOpponentOfCurrentUser().getUsername(), user2.getUsername());
        Assert.assertEquals(game.getOpponentOfCurrentUser().getMoney(), user2.getMoney());
        Assert.assertEquals(game.getOpponentOfCurrentUser().getScore(), user2.getScore());
        Assertions.assertNotNull(game.getOpponentOfCurrentUser());
        Assertions.assertNull(game.getSelectedCard());
    }

    @Test
    public void testStandByPhase() {
        initialise();
        Game game = new Game(user1, user2, 3, new Scanner(System.in));
        game.callStandByPhase();
        Assert.assertEquals(game.getCurrentPhase(), Phase.STANDBY);
        Assertions.assertNotNull(game.getCurrentPhase());
        Assertions.assertNull(game.getSelectedCard());
    }

    @Test
    public void testCurrentUser() {
        initialise();
        Game game = new Game(user1, user2, 1, new Scanner(System.in));
        Assertions.assertNotNull(game.getCurrentUser());
        Assert.assertEquals(game.getCurrentUser().getUsername(), user1.getUsername());
        game.setSelectedCard(new Card("Sample", Type.SPELL));
        Assert.assertEquals(game.getSelectedCard().getName(), "Sample");
        Assert.assertEquals(game.getSelectedCard().getName(), "Sample");
        Assert.assertEquals(game.getSelectedCard().getCardType(), Type.SPELL);
    }

    @Test
    public void testChainInGame() {
        initialise();
        Game game = new Game(user1, user2, 1, new Scanner(System.in));
        Assertions.assertNotNull(game.getSpecialSummonedCards());
        Assertions.assertNotNull(game.getChain());
        Assert.assertEquals(game.getCurrentPhase(), Phase.DRAW);
    }

    @Test
    public void testSelectCard() {
        initialise();
        Game game = new Game(user1, user2, 1, new Scanner(System.in));
        Assertions.assertNull(game.getActivatedRitualCard());
        game.setSelectedCard(new Card("Test", Type.RITUAL));
        game.deselectCard();
        Assertions.assertNull(game.getSelectedCard());
    }
}
