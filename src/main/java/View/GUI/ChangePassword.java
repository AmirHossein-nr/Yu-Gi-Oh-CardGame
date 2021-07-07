package View.GUI;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePassword {

    public static Stage mainStage;
    public static User loggedUser;
    @FXML
    public TextField oldPassword;
    @FXML
    public TextField newPassword;


    public void setNewPassword(ActionEvent actionEvent) throws Exception {
        if (loggedUser == null)
            return;
        if (!loggedUser.getPassword().equals(oldPassword.getText())) {
            String header = "Incorrect Password", content = "current password is invalid";
            ChangePasswordGraphic.createAlert(Alert.AlertType.ERROR, header, content);
            ProfileGraphic profileGraphic = new ProfileGraphic();
            profileGraphic.start(mainStage);
            return;
        }
        else if (loggedUser.getPassword().equals(oldPassword.getText())) {
            if (!newPassword.getText().equals(""))
                loggedUser.setPassword(newPassword.getText());
        }
        String header = "Done", content = "password changed successfully!";
        ChangePasswordGraphic.createAlert(Alert.AlertType.INFORMATION, header,content);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);

    }
}
