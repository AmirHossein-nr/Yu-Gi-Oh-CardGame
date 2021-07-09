package Controller.Menu;

import Model.User;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parentMenu;
    private ArrayList<Menu> subMenus;
    protected static Scanner scanner;
    protected static User loggedUser;

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        subMenus = new ArrayList<>();
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public static void setLoggedUser(User loggedUser) {
        Menu.loggedUser = loggedUser;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public void setSubMenus(ArrayList<Menu> subMenus) {
        this.subMenus = subMenus;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Menu> getSubMenus() {
        return subMenus;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void execute() {
        Menu nextMenu = null;
    }

    public void menuExit() {
        if (parentMenu == null) {
            System.exit(1);
        } else {
            Menu nextMenu = this.getParentMenu();
            nextMenu.execute();
        }
    }

    public void menuEnter(String input) {
        if (loggedUser != null) {
            Menu nextMenu = null;
            for (Menu menu : subMenus) {
                if (menu.getName().equalsIgnoreCase(input)) {
                    nextMenu = menu;
                    break;
                }
            }
            if (nextMenu == null)
                System.out.println("menu navigation is not possible");
            else {
                System.out.println("You Entered Menu :" + nextMenu.getName());
                nextMenu.execute();
            }
        } else {
            System.out.println("please login first");
        }
    }

    public void showName() {
        System.out.println(this.name);
    }

    public void logoutUser() {
        if (loggedUser == null) {
            System.out.println("You are not even logged in!!!");
            this.execute();
        } else {
            loggedUser = null;
            Menu menu = this;
            while (menu.getParentMenu() != null) {
                menu = menu.getParentMenu();
            }
            menu.execute();
        }
    }

}
