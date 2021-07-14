package Client.View.GUI;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeNickname {
    public static User loggedUser;
    public static Stage mainStage;
    @FXML
    public TextField newNickname;

    public void setNewNickname(ActionEvent actionEvent) throws Exception {
        if (loggedUser == null) {
            return;
        }

        User user = User.getUserByNickname(newNickname.getText());
        if (user != null) {
            String header = "Existing User";
            String content = "user with nickname " + newNickname.getText() + " already exists";
            ChangeNicknameGraphic.createAlert(Alert.AlertType.ERROR, header, content);
            ProfileGraphic profileGraphic = new ProfileGraphic();
            profileGraphic.start(mainStage);
            return;
        }
        loggedUser.setNickName(newNickname.getText());
        String header = "Done", content = "nickname changed successfully!";
        ChangeNicknameGraphic.createAlert(Alert.AlertType.INFORMATION, header, content);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }
}
