package TestShopAndDeckMenu;

import Controller.Regex;
import Model.Card;
import Model.Monster;
import Model.User;
import View.Menu.LoginMenu;
import View.Menu.MainMenu;
import View.Menu.Menu;
import View.Menu.Shop;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TestShopMenu {

    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(newOut);
    private static final PrintStream old = System.out;

    private LoginMenu loginMenu;


    public void setStreams() {
        System.setOut(printStream);
    }


    @Test
    public void testShop() {

        initialize();
        setStreams();
        MainMenu mainMenu = (MainMenu) loginMenu.getSubMenus().get(0);
        Shop shop = (Shop) mainMenu.getSubMenus().get(4);

        shop.showAllCards();
        Assertions.assertNotNull(newOut);

    }

    @Test
    public void testCard() {

        initialize();
        MainMenu mainMenu = (MainMenu) loginMenu.getSubMenus().get(0);
        Shop shop = (Shop) mainMenu.getSubMenus().get(4);
        Matcher matcher;
        String string = "shop buy asghar";
        matcher = Regex.getMatcher(string, Regex.buyCardInShop);
        if (matcher.find()) {
            Card card = shop.getFinalCard(matcher);
            Assertions.assertNull(card);
        }

        string = "shop buy Battle OX";
        matcher = Regex.getMatcher(string, Regex.buyCardInShop);
        if (matcher.find()) {
            Card card = shop.getFinalCard(matcher);
            Assertions.assertNotNull(card);
            Assertions.assertTrue(card instanceof Monster);
        }

    }

    public void initialize() {
        User user = new User("amirhossein", "12345", "DarkKnight");
        Menu.setLoggedUser(user);
        Menu.setScanner(new Scanner(System.in));
        loginMenu = new LoginMenu();
    }

    private void assertOutputEquals(String expected) {
        Assertions.assertEquals(expected, newOut.toString().trim());
        newOut.reset();
    }

    @AfterAll
    static void reset() {
        System.out.flush();
        System.setOut(old);
    }

}
