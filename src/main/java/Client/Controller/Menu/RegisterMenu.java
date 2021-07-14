package Client.Controller.Menu;

import Client.View.GUI.Register;
import Client.View.GUI.SignUpAndLoginGraphic;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterMenu extends Menu {
    public static Stage mainStage;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField nicknameTextField;
    @FXML
    public TextField passwordTextField;

    String username, nickname, password;

    public RegisterMenu() {
        super("registerMenu", null);
    }

    private boolean createUser(String username, String password, String nickname) throws IOException {
        getObjectOutputStream().writeUTF("register" + " " + username + " " + password + " " + nickname);
        getObjectOutputStream().flush();
        String result = getObjectInputStream().readUTF();

        if (result.equals("true")) {
            new User(username, password, nickname);
            String header = "Register Done";
            String content = "user created successfully!\n";
            Register.createAlert(Alert.AlertType.INFORMATION, header, content);
            return true;
        } else if (result.equals("false")) {
            String header = "Register Error";
            String content = "an Error Occurred! change Username and nickname (or Fill The Empty Boxes) and Try Again!";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
        } else {
            String header = "Network Error";
            String content = "an Error Occurred while Reading Data From Server!Please Try Again!";
            Register.createAlert(Alert.AlertType.ERROR, header, content);
        }
        return false;
    }

    public void register(ActionEvent actionEvent) throws Exception {
        username = usernameTextField.getText();
        nickname = nicknameTextField.getText();
        password = passwordTextField.getText();
        if (createUser(username, password, nickname)) {
            SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
            signUpAndLoginGraphic.start(mainStage);
        }
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
