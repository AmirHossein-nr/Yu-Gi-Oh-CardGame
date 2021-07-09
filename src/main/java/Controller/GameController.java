package Controller;

import Model.Effects.Effect;
import Controller.Menu.LoginMenu;
import Controller.Menu.Menu;

import java.util.Scanner;

public class GameController {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        Effect.setScanner(this.scanner);
        Menu.setScanner(this.scanner);
        Menu loginMenu = new LoginMenu();
        loginMenu.execute();
    }

}
