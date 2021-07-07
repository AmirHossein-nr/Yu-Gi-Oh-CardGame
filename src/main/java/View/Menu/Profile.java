package View.Menu;

import Controller.Regex;
import Model.User;
import View.GUI.MainMenuGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;

public class Profile extends Menu {
    public static Stage mainStage;
    @FXML
    public Label profileUsername;
    @FXML
    public Label profileNickname;
    @FXML
    public Label profilePassword;

    String username, nickname, password;

    public void initialize () {
        initializeLabels();
    }

    public void initializeLabels (){
        if (loggedUser == null) {
            profileUsername.setText("guest");
            profileNickname.setText("not logged in yet");
            return;
        }
        profileUsername.setText(loggedUser.getUsername());
        profileNickname.setText(loggedUser.getNickName());
    }

    public Profile () {
        super("Profile Menu", null);
    }

    public Profile(Menu parentMenu) {
        super("Profile Menu", parentMenu);
    }

    @Override
    public void execute() {
        String input = scanner.nextLine();
        input = editSpaces(input);

        Matcher matcher;
        if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
            this.menuEnter(matcher.group(1));
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
        } else if ((matcher = Regex.getMatcher(input, Regex.changeNickname)).find()) {
            String nickName;
            if (matcher.group(2) != null) {
                nickName = matcher.group(2);
            } else {
                nickName = matcher.group(4);
            }
            User user = User.getUserByNickname(nickName);
            if (user != null) {
                System.out.println("user with nickname " + nickName + " already exists");
                this.execute();
            } else {
                loggedUser.setNickName(nickName);
                System.out.println("nickname changed successfully!");
                this.execute();
            }
        } else if ((matcher = Regex.getMatcher(input, Regex.changePassword)).find()) {
            String oldPassWord = matcher.group(3), newPassWord = matcher.group(5);
            if (!loggedUser.getPassword().equals(oldPassWord)) {
                System.out.println("current password is invalid");
                this.execute();
            } else if (oldPassWord.equals(newPassWord)) {
                System.out.println("please enter a new password");
                this.execute();
            } else {
                loggedUser.setPassword(newPassWord);
                System.out.println("password changed successfully!");
                this.execute();
            }
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    public void backToMainMenu(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);

    }
}
