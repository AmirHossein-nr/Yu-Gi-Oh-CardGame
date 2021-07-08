package View.Menu;

import Model.User;
import View.GUI.Register;
import View.GUI.SignUpAndLoginGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterMenu {
    public static Stage mainStage;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField nicknameTextField;
    @FXML
    public TextField passwordTextField;

    String username, nickname, password;


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
            String header = "Existing User";
            String content = "user with nickname " + nickname + " already exists";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
            return;
        }
        if (username.equals("") || password.equals("") || nickname.equals("")) {
            String header = "Fill Error";
            String content = "fill all text fields!";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
            return;
        }
        user = new User(username, password, nickname);
        user.setMoney(100000);
        String header = "Register Done";
        String content = "user created successfully!\n";
        Register.createAlert(Alert.AlertType.INFORMATION, header, content);
    }

    public void register(ActionEvent actionEvent) throws Exception {
        username = usernameTextField.getText();
        nickname = nicknameTextField.getText();
        password = passwordTextField.getText();
        createUser(username, password, nickname);
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }

    public void back(ActionEvent actionEvent) throws Exception {
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }

    public void backToFirstPage(ActionEvent actionEvent) throws Exception {
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }
}
