package View.Menu;


import Controller.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;

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

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if (Regex.getMatcher(input, Regex.menuEnter).find()) {
            Matcher matcher = Regex.getMatcher(input, Regex.menuEnter);
            if (matcher.find()) {
                this.menuEnter(matcher.group(1));
            }
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
    }


    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
