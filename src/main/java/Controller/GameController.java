package Controller;

import Controller.Menu.Menu;

import java.util.Scanner;

public class GameController {
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        Menu.setScanner(this.scanner);

        // RUN THE MENUS CODE
    }
}
