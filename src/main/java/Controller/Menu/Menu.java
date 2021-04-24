package Controller.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parentMenu;
    private ArrayList<Menu> subMenus;
    protected static Scanner scanner;

    public Menu(String name, Menu parentMenu){
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }
}
