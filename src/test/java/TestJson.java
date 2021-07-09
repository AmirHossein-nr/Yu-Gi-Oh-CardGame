import Controller.JsonController;
import Model.Board;
import Model.Card;
import Model.User;
import View.Menu.MainMenu;
import View.Menu.Menu;
import View.Menu.Shop;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Scanner;

public class TestJson {

    private void initialise() {
        User user1 = new User("amirhossein", "1234", "mammad");
        user1.setBoard(new Board());
        Menu.setScanner(new Scanner(System.in));
        Menu.setLoggedUser(user1);
    }

    @Test
    public void testReadCard() throws IOException {
        initialise();
        Shop shop = new Shop(new MainMenu(null));
        Card card = JsonController.readCard("mammad", "monster");
        Assertions.assertNull(card);
        card = JsonController.readCard("asghar", "spell");
        Assertions.assertNull(card);
        card = JsonController.readCard("jafar", "trap");
        Assertions.assertNull(card);
        JsonController.writeCard("The Tricky");
        card = JsonController.readCard("The Tricky", "monster");
        Assertions.assertNotNull(card);
    }
}
