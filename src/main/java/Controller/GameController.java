package Controller;

import View.Menu.LoginMenu;
import View.Menu.Menu;

import java.util.Scanner;

public class GameController {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        Menu.setScanner(this.scanner);
        Menu loginMenu = new LoginMenu();
        loginMenu.execute();
        // RUN THE MENUS CODE
    }



}
