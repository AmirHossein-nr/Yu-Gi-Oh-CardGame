package View.Menu;

import Controller.Regex;
import Model.User;

import java.util.regex.Matcher;

public class LoginMenu extends Menu {


    public LoginMenu() {
        super("Login Menu", null);
        this.getSubMenus().add(new MainMenu(this));
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);
        Matcher matcher;
        if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
            this.menuEnter(matcher.group(1));
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else if (Regex.getMatcher(input, Regex.loginUser).find()) {
            boolean flag = login(Regex.getMatcher(input, Regex.loginUser));
            if (!flag)
                this.execute();
            else {
                System.out.println("user logged in successfully!");
                this.getSubMenus().get(0).execute();
            }
            this.getSubMenus().get(0).execute();
        } else if (Regex.getMatcher(input, Regex.createUser).find()) {
            register(Regex.getMatcher(input, Regex.createUser));
            this.execute();
        } else {
            System.out.println("invalid command");
            this.execute();
        }
    }

    public void register(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(2);
            String nickname = matcher.group(6);
            String password = matcher.group(4);

            createUser(username, password, nickname);
        }
    }

    private void createUser(String username, String password, String nickname) {
        User user = User.getUserByUsername(username);
        if (user != null) {
            System.out.println("user with username " + username + " already exists");
            return;
        }
        user = User.getUserByNickname(nickname);
        if (user != null) {
            System.out.println("user with nickname " + nickname + " already exists");
            return;
        }
        user = new User(username, password, nickname);
        user.setMoney(100000);
        System.out.print("user created successfully!\n");
    }

    public boolean loginUser(Matcher matcher) {
        return login(matcher);
    }

    private boolean login(Matcher matcher) {
        if (matcher.find()) {
            String username = matcher.group(2);
            String password = matcher.group(4);
            User user = User.getUserByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                System.out.println("Username and password didn't match!");
                return false;
            }
            loggedUser = user;
            return true;
        }
        return false;
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
