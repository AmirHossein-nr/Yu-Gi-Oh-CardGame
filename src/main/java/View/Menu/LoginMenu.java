package View.Menu;

import Controller.Regex;

public class LoginMenu extends Menu {


    public LoginMenu() {
        super("Login Menu", null);
        this.getSubMenus().add(new MainMenu(this));
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if (Regex.getMatcher(input, Regex.menuEnter).find()) {
            this.menuEnter(input);
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else if (Regex.getMatcher(input, Regex.loginUser).find()) {
            // login method
        } else if (Regex.getMatcher(input, Regex.createUser).find()) {
            // create method
            this.execute();
        } else {
            System.out.println("invalid command");
            this.execute();
        }
    }

    // login
    // register
    //
    // menu enter-----

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
