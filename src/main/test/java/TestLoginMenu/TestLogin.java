package TestLoginMenu;

import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestLogin {

    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    private void assertOutputEquals(String expected) {
        Assert.assertEquals(expected, outContent.toString().trim());
        outContent.reset();
    }
}
