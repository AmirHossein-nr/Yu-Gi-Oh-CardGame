package View.Menu;

import View.GUI.Login;
import View.GUI.Register;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class SignUpAndLoginMenu {
    public static Stage mainStage;

    public void openRegisterMenu(ActionEvent actionEvent) throws Exception {
        RegisterMenu.mainStage = mainStage;
        Register register = new Register();
        register.start(mainStage);
    }

    public void openLoginMenu(ActionEvent actionEvent) throws Exception {
        Login login = new Login();
        login.start(mainStage);
    }
}
