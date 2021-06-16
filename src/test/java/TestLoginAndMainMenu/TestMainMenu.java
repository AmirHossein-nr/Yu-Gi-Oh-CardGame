package TestLoginAndMainMenu;

import Model.User;
import View.Menu.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestMainMenu {

    private static ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private static PrintStream printStream = new PrintStream(newOut);
    private static PrintStream old = System.out;


    private void initialise() {
        User user = new User("amirhossein", "1234", "mammad");
        Menu.setScanner(new Scanner(System.in));
        Menu.setLoggedUser(user);
    }


    @BeforeAll
    static void setStreams() {
        System.setOut(printStream);
    }

    @BeforeEach
    public void reset() {
        newOut.reset();
    }

    @Test
    public void invalidNavigations() {
        reset();
        initialise();
        setStreams();
        MainMenu mainMenu = new MainMenu(new LoginMenu());
        mainMenu.getSubMenus().add(new Duel(mainMenu));
        mainMenu.getSubMenus().add(new DeckMenu(mainMenu));
        mainMenu.menuEnter("mammad");
        assertOutputEquals("menu navigation is not possible");
        mainMenu.menuEnter("");
        assertOutputEquals("menu navigation is not possible");
        mainMenu.menuEnter("login menu");
        assertOutputEquals("menu navigation is not possible");
    }

    @Test
    public void subMenus() {
        MainMenu mainMenu = new MainMenu(new LoginMenu());

        Assertions.assertEquals(mainMenu, mainMenu.getSubMenus().get(0).getParentMenu());
    }

    private void assertOutputEquals(String expected) {
        Assert.assertEquals(expected, newOut.toString().trim());
        newOut.reset();
    }


}
