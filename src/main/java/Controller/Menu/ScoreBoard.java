package Controller.Menu;

import Model.User;
import View.GUI.MainMenuGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class ScoreBoard extends Menu {
    public static Stage mainStage;

    public ScoreBoard() {
        super("ScoreBoard Menu", null);
    }

    public ScoreBoard(Menu parentMenu) {
        super("ScoreBoard Menu", parentMenu);
    }

    @Override
    public void execute() {

    }

    private void showScoreBoard() {
        int rank = 1;
        System.out.println(rank + "- " + User.getAllUsers().get(0));
        for (int i = 1; i < User.getAllUsers().size(); i++) {
            if (User.getAllUsers().get(i).getScore() < User.getAllUsers().get(i - 1).getScore()) {
                System.out.println(++rank + "- " + User.getAllUsers().get(i));
            } else {
                System.out.println(rank + "- " + User.getAllUsers().get(i));
            }
        }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    @FXML
    public void back(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
