package View.Menu;

import Controller.Regex;
import Model.User;
import View.GUI.*;
import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {

    public static Stage mainStage;
    @FXML
    public  TextField usernameTextField;
    @FXML
    public  TextField nicknameTextField;
    @FXML
    public  TextField passwordTextField;
    String username, nickname, password;


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
            String header = "Register Error";
            String content = "user with username " + username + " already exists";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
            return;
        }
        user = User.getUserByNickname(nickname);
        if (user != null) {
            String header = "Existed User";
            String content = "user with nickname " + nickname + " already exists";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
            return;
        }
        user = new User(username, password, nickname);
        user.setMoney(100000);
        String header = "Register Done";
        String content = "user created successfully!\n";
        Register.createAlert(Alert.AlertType.INFORMATION, header, content);
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

    public void register(ActionEvent actionEvent) throws Exception {
        username = usernameTextField.getText();
        nickname = nicknameTextField.getText();
        password = passwordTextField.getText();
        createUser(username, password, nickname);
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }

    public void login(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }

    public void back(ActionEvent actionEvent) throws Exception {
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }

    public void openRegisterMenu(ActionEvent actionEvent) throws Exception {
        Register register = new Register();
        register.start(mainStage);
    }

    public void openLoginMenu(ActionEvent actionEvent) throws Exception {
        Login login = new Login();
        login.start(mainStage);
    }

    public void backToFirstPage(ActionEvent actionEvent) throws Exception {
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }

    public void openProfileMenu(ActionEvent actionEvent) throws Exception {
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void openScoreBoard(ActionEvent actionEvent) throws Exception {
        ScoreBoardGraphic scoreBoardGraphic = new ScoreBoardGraphic();
        scoreBoardGraphic.start(mainStage);
    }

    public void openShopMenu(ActionEvent actionEvent) throws Exception {
        ShopGraphic shopGraphic = new ShopGraphic();
        shopGraphic.start(mainStage);
    }

}
