package View.Menu;


import java.util.ArrayList;

public class MainMenu extends Menu {

    public MainMenu(Menu parentMenu) {
        super("Main Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new Duel(this));
        subMenus.add(new Deck(this));
        subMenus.add(new ScoreBoard(this));
        subMenus.add(new Profile(this));
        subMenus.add(new Shop(this));
        subMenus.add(new ImportExport(this));
        this.setSubMenus(subMenus);
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
