package View.Menu;

public class LoginMenu extends Menu {


    public LoginMenu() {
        super("Login Menu", null);
        this.getSubMenus().add(new MainMenu(this));
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
