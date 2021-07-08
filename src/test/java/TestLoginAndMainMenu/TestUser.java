package TestLoginAndMainMenu;

import Controller.Regex;
import View.Menu.LoginMenu;
import View.Menu.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TestUser {

    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(newOut);
    private final PrintStream old = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(printStream);
    }

    @Test
    public void creatUserTest() {

        setStreams();
        String command = "";
        command = "user create --username amirhossein --nickname amir --password 1234";

        Matcher matcher = Regex.getMatcher(command, Regex.createUser);
        Menu.setScanner(new Scanner(System.in));
        LoginMenu loginMenu = new LoginMenu();
//        loginMenu.register(matcher);

        assertOutputEquals("user created successfully!");

        command = "user create --username amirhossein --nickname asghra --password 1223134";
        matcher = Regex.getMatcher(command, Regex.createUser);
//        loginMenu.register(matcher);

        assertOutputEquals("user with username amirhossein already exists");
        loginValidTest(matcher, loginMenu);
        loginInvalidTest(matcher, loginMenu);
        reset();
    }


    protected void loginValidTest(Matcher matcher, LoginMenu loginMenu) {

        String command = "";

        command = "user login --password 1234 --username amirhossein";
        matcher = Regex.getMatcher(command, Regex.loginUser);

        //Assertions.assertTrue(loginMenu.loginUser(matcher));
    }

    private void loginInvalidTest(Matcher matcher, LoginMenu loginMenu) {
        String command = "";
        command = "user login --password --username gholi";
        matcher = Regex.getMatcher(command, Regex.loginUser);

        //Assertions.assertFalse(loginMenu.loginUser(matcher));
    }

    private void assertOutputEquals(String expected) {
        Assert.assertEquals(expected, newOut.toString().trim());
        newOut.reset();
    }

    @AfterEach
    public void reset() {
        System.out.flush();
        System.setOut(old);
    }
}
