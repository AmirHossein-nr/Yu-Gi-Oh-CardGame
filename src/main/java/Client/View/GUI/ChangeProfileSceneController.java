package Client.View.GUI;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChangeProfileSceneController {
    public static Stage mainStage;
    public static User loggedUser;

    public ChangeProfileSceneController() {

    }

    public void chooseFromCharacters(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chooseFromCharacters.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void uploadImage(ActionEvent actionEvent) {
        final FileChooser f = new FileChooser();
        File file = f.showOpenDialog(mainStage);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            loggedUser.setAvatar(img);
            ProfileGraphic profileGraphic = new ProfileGraphic();
            try {
                profileGraphic.start(mainStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void set1(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set2(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds41.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set3(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds39.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set4(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds40.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set5(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds36.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set6(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds33.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set7(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds32.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set8(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds26.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set9(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds17.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set10(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds5.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set11(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds6.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void set12(ActionEvent actionEvent) throws Exception {
        Image image = new Image(getClass().getResource("/images/Characters/Chara001.dds8.png").toExternalForm());
        loggedUser.setAvatar(image);
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void backToProfile(ActionEvent actionEvent) throws Exception {
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }
}
