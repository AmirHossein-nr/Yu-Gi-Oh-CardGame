package Client.Controller.Menu;

import Client.View.GUI.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Profile extends Menu {
    public static Stage mainStage;
    @FXML
    public Label profileUsername;
    @FXML
    public Label profileNickname;
    @FXML
    public Label profilePassword;
    @FXML
    public ImageView profileImage;

    String username, nickname, password;

    public void initialize () {
        if (loggedUser == null)
            return;
        initializeLabels();
    }

    public void initializeLabels (){
        if (loggedUser == null) {
            profileUsername.setText("guest");
            profileNickname.setText("not logged in yet");
            return;
        }
        profileImage.setImage(loggedUser.getAvatar());
        profileUsername.setText(loggedUser.getUsername());
        profileNickname.setText(loggedUser.getNickName());
        profilePassword.setText(loggedUser.getPassword());
    }

    public Profile () {
        super("Profile Menu", null);
    }

    public Profile(Menu parentMenu) {
        super("Profile Menu", parentMenu);
    }

    @Override
    public void execute() {

    }


    public void backToMainMenu(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);

    }

    public void changeNickname(ActionEvent actionEvent) throws Exception {
        ChangeNickname.loggedUser = loggedUser;
        ChangeNicknameGraphic changeNickname = new ChangeNicknameGraphic();
        changeNickname.start(mainStage);
    }

    public void changePassword(ActionEvent actionEvent) throws Exception {
        ChangePassword.loggedUser = loggedUser;
        ChangePasswordGraphic changePasswordGraphic = new ChangePasswordGraphic();
        changePasswordGraphic.start(mainStage);
    }

    public void set1(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Chara001.dds.png").toExternalForm());
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set2(ActionEvent actionEvent) {
    }

    public void set3(ActionEvent actionEvent) {
    }

    public void set4(ActionEvent actionEvent) {
    }

    public void set5(ActionEvent actionEvent) {
    }

    public void set6(ActionEvent actionEvent) {
    }

    public void set7(ActionEvent actionEvent) {
    }

    public void set8(ActionEvent actionEvent) {
    }

    public void set9(ActionEvent actionEvent) {
    }

    public void set10(ActionEvent actionEvent) {
    }

    public void set11(ActionEvent actionEvent) {
    }

    public void set12(ActionEvent actionEvent) {
    }

    public void openChangeProfileScene(ActionEvent actionEvent) throws Exception {
        ChangeProfileSceneController.loggedUser = loggedUser;
        ChangeProfileScene changeProfileScene = new ChangeProfileScene();
        changeProfileScene.start(mainStage);
        ChangeProfileSceneController.mainStage = mainStage;
    }

//    public void setProfile (Image image) throws Exception {
//        if (loggedUser == null) {
//            profileImage.setImage(new Image(getClass().getResource("/images/character.png").toExternalForm()));
//            ProfileGraphic profileGraphic = new ProfileGraphic();
//            profileGraphic.start(mainStage);
//            return;
//        }
//        profileImage.setImage(image);
//        ProfileGraphic profileGraphic = new ProfileGraphic();
//        profileGraphic.start(mainStage);
//    }
}
