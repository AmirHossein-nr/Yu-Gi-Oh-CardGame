package Client.Controller.Menu;

import Model.User;
import Client.View.GUI.*;
import Server.ServerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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

    private boolean login(String username, String password) throws IOException, ClassNotFoundException {
        getObjectOutputStream().writeUTF("login" + " " + username + " " + password);
        getObjectOutputStream().flush();
        String result = getObjectInputStream().readUTF();

        if (result.equals("false")) {
            String header = "No Match";
            String content = "Username and password didn't match!";
            Login.createAlert(Alert.AlertType.ERROR, header, content);
            return false;
        } else {
            String[] split = result.split(" ");
            token = split[1];
            loggedUser = (User) getObjectInputStream().readObject();
            loggedUser.setAvatar(new Image(Objects.requireNonNull(getClass().getResource
                    ("/images/character.png")).toExternalForm()));
        }
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
