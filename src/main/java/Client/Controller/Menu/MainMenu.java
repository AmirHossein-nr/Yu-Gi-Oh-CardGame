package Client.Controller.Menu;

import Client.View.GUI.*;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    public static Stage mainStage;

    public MainMenu() {
        super("Main Menu", null);
    }

    public MainMenu(Menu parentMenu) {
        super("Main Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new Duel(this));
        subMenus.add(new DeckMenu(this));
        subMenus.add(new ScoreBoard(this));
        subMenus.add(new Profile(this));
        subMenus.add(new Shop(this));
        subMenus.add(new ImportExport(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public void execute() {
    }

    public void openProfileMenu(ActionEvent actionEvent) throws Exception {
        openProfile();
    }

    public void openProfile() throws Exception {
        ProfileGraphic profileGraphic = new ProfileGraphic();
        profileGraphic.start(mainStage);
    }

    public void openScoreBoard(ActionEvent actionEvent) throws Exception {
        ScoreBoardGraphic scoreBoardGraphic = new ScoreBoardGraphic();
        scoreBoardGraphic.start(mainStage);
    }

    public void openShopMenu(ActionEvent actionEvent) throws Exception {
        ShopGraphic shopGraphic = new ShopGraphic();
        shopGraphic.start(mainStage);
    }

    public void openCardCreator(ActionEvent actionEvent) throws Exception {
        CardMakerGraphics cardMakerGraphics = new CardMakerGraphics();
        cardMakerGraphics.start(mainStage);
    }

    public void backToFirstPage(ActionEvent actionEvent) throws Exception {
        loggedUser = null;
        getObjectOutputStream().writeUTF("logout" + " " + token);
        token = null;
        getObjectOutputStream().flush();
        SignUpAndLoginGraphic signUpAndLoginGraphic = new SignUpAndLoginGraphic();
        signUpAndLoginGraphic.start(mainStage);
    }

    public void openDeckMenu(MouseEvent mouseEvent) throws Exception {
        new DeckGraphic().start(mainStage);
    }

    public void openDuelMenu(ActionEvent actionEvent) throws Exception {
        getObjectOutputStream().writeUTF("duel");
        getObjectOutputStream().flush();
        DuelMenuGraphic.users = (ArrayList) getObjectInputStream().readObject();
        DuelMenuGraphic.loggedUser = loggedUser;
        DuelMenuGraphic duelMenuGraphic = new DuelMenuGraphic();
        duelMenuGraphic.start(mainStage);
    }

    public void openImportMenu(ActionEvent actionEvent) throws Exception {
        new ImportExportGraphic().start(mainStage);
    }
}
