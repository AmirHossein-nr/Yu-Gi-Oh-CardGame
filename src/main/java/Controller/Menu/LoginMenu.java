package Controller.Menu;

import Model.User;
import View.GUI.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginMenu extends Menu {

    public static Stage mainStage;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField nicknameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField loginUsernameTextField;
    @FXML
    public TextField loginPasswordTextField;


    String username, nickname, password;


    public LoginMenu() {
        super("Login Menu", null);
        this.getSubMenus().add(new MainMenu(this));
    }

    @Override
    public void execute() {
    }


    private boolean login(String username, String password) {
        User user = User.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            String header = "No Match";
            String content = "Username and password didn't match!";
            Login.createAlert(Alert.AlertType.ERROR, header, content);
            return false;
        }
        loggedUser = user;
        return true;
    }


    public void login(ActionEvent actionEvent) throws Exception {
        String username = loginUsernameTextField.getText();
        String password = loginPasswordTextField.getText();
        if (!login(username, password)) {
            return;
        }
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }

    public void back(ActionEvent actionEvent) throws Exception {
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }




}
