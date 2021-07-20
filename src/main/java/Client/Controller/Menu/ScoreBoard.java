package Client.Controller.Menu;

import Model.User;
import Client.View.GUI.MainMenuGraphic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class ScoreBoard extends Menu {
    public static Stage mainStage;

    ArrayList<User> scoreboardUsers;
    boolean[] isOnline;

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

    public void refresh(AnchorPane anchorPane) throws IOException, ClassNotFoundException {
        getObjectOutputStream().writeUTF("scoreboard");
        getObjectOutputStream().flush();
        scoreboardUsers = (ArrayList) getObjectInputStream().readObject();
        isOnline = (boolean[]) getObjectInputStream().readObject();
        createLabelsFirst(anchorPane);
        createLabelsSecond(anchorPane);
        createLabelsThird(anchorPane);
        createLabelsFourth(anchorPane);
    }

    public void createLabelsFirst(AnchorPane anchorPane) {
        Label label1 = new Label("1");
        Label label2 = new Label("2");
        Label label3 = new Label("3");
        Label label4 = new Label("4");
        Label label5 = new Label("5");
        Label label6 = new Label("6");
        Label label7 = new Label("7");
        Label label8 = new Label("8");
        Label label9 = new Label("9");
        Label label10 = new Label("10");
        labelMakerFirst(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        labelMakerSecond(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        anchorPane.getChildren().addAll(label1, label2, label3, label4, label5);
        anchorPane.getChildren().addAll(label6, label7, label8, label9, label10);
    }

    private void labelMakerSecond(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6,
                                  Label label7, Label label8, Label label9, Label label10) {
        label1.setLayoutY(10);
        label2.setLayoutY(60);
        label3.setLayoutY(90);
        label4.setLayoutY(120);
        label5.setLayoutY(150);
        label6.setLayoutY(180);
        label7.setLayoutY(210);
        label8.setLayoutY(240);
        label9.setLayoutY(270);
        label10.setLayoutY(300);
    }

    private void labelMakerFirst(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6,
                                 Label label7, Label label8, Label label9, Label label10) {
        label2.setLayoutX(260);
        label1.setLayoutX(260);
        label3.setLayoutX(260);
        label4.setLayoutX(260);
        label5.setLayoutX(260);
        label6.setLayoutX(260);
        label7.setLayoutX(260);
        label8.setLayoutX(260);
        label9.setLayoutX(260);
        label10.setLayoutX(260);
    }

    public void createLabelsSecond(AnchorPane anchorPane) {
        Label label11 = new Label("11");
        Label label12 = new Label("12");
        Label label13 = new Label("13");
        Label label14 = new Label("14");
        Label label15 = new Label("15");
        Label label16 = new Label("16");
        Label label17 = new Label("17");
        Label label18 = new Label("18");
        Label label19 = new Label("19");
        Label label20 = new Label("20");
        labelMakerFirst(label11, label13, label12, label14, label15, label16, label17, label18, label19, label20);
        labelMakerThird(label11, label12, label13, label14, label15, label16, label17, label18, label19, label20);
        anchorPane.getChildren().addAll(label11, label12, label13, label14, label15);
        anchorPane.getChildren().addAll(label16, label17, label18, label19, label20);
    }

    private void labelMakerThird(Label label11, Label label12, Label label13, Label label14, Label label15,
                                 Label label16, Label label17, Label label18, Label label19, Label label20) {
        label11.setLayoutY(330);
        label12.setLayoutY(370);
        label13.setLayoutY(390);
        label14.setLayoutY(420);
        label15.setLayoutY(450);
        label16.setLayoutY(480);
        label17.setLayoutY(510);
        label18.setLayoutY(540);
        label19.setLayoutY(570);
        label20.setLayoutY(600);
    }

    public void createLabelsThird(AnchorPane anchorPane) {
        Label label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;
        try {
            label1 = new Label(scoreboardUsers.get(0).getNickName() + "        " + scoreboardUsers.get(0).getScore());
            if (isOnline[0]) {
                label1 = new Label(label1.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label1 = new Label("-         -");
        }
        try {
            label2 = new Label(scoreboardUsers.get(1).getNickName() + "        " + scoreboardUsers.get(1).getScore());
            if (isOnline[1]) {
                label2 = new Label(label2.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label2 = new Label("-         -");
        }
        try {
            label3 = new Label(scoreboardUsers.get(2).getNickName() + "        " + scoreboardUsers.get(2).getScore());
            if (isOnline[2]) {
                label3 = new Label(label3.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label3 = new Label("-         -");
        }
        try {
            label4 = new Label(scoreboardUsers.get(3).getNickName() + "        " + scoreboardUsers.get(3).getScore());
            if (isOnline[3]) {
                label4 = new Label(label4.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label4 = new Label("-         -");
        }
        try {
            label5 = new Label(scoreboardUsers.get(4).getNickName() + "        " + scoreboardUsers.get(4).getScore());
            if (isOnline[4]) {
                label5 = new Label(label5.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label5 = new Label("-         -");
        }
        try {
            label6 = new Label(scoreboardUsers.get(5).getNickName() + "        " + scoreboardUsers.get(5).getScore());
            if (isOnline[5]) {
                label6 = new Label(label6.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label6 = new Label("-         -");
        }
        try {
            label7 = new Label(scoreboardUsers.get(6).getNickName() + "        " + scoreboardUsers.get(6).getScore());
            if (isOnline[6]) {
                label7 = new Label(label7.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label7 = new Label("-         -");
        }
        try {
            label8 = new Label(scoreboardUsers.get(7).getNickName() + "        " + scoreboardUsers.get(7).getScore());
            if (isOnline[7]) {
                label8 = new Label(label8.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label8 = new Label("-         -");
        }
        try {
            label9 = new Label(scoreboardUsers.get(8).getNickName() + "        " + scoreboardUsers.get(8).getScore());
            if (isOnline[8]) {
                label9 = new Label(label9.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label9 = new Label("-         -");
        }
        try {
            label10 = new Label(scoreboardUsers.get(9).getNickName() + "        " + scoreboardUsers.get(9).getScore());
            if (isOnline[9]) {
                label10 = new Label(label10.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label10 = new Label("-         -");
        }

        labelMakerFourth(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        labelMakerSecond(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        searchPlayer(label1, label2, label3, label4, label5);
        searchPlayer(label6, label7, label8, label9, label10);
        anchorPane.getChildren().addAll(label1, label2, label3, label4, label5);
        anchorPane.getChildren().addAll(label6, label7, label8, label9, label10);
    }

    private void labelMakerFourth(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6,
                                  Label label7, Label label8, Label label9, Label label10) {
        label2.setLayoutX(305);
        label1.setLayoutX(305);
        label3.setLayoutX(305);
        label4.setLayoutX(305);
        label5.setLayoutX(305);
        label6.setLayoutX(305);
        label7.setLayoutX(305);
        label8.setLayoutX(305);
        label9.setLayoutX(305);
        label10.setLayoutX(305);
    }

    public void createLabelsFourth(AnchorPane anchorPane) {
        Label label11, label12, label13, label14, label15, label16, label17, label18, label19, label20;
        try {
            label11 = new Label(scoreboardUsers.get(10).getNickName() + "        " + scoreboardUsers.get(10).getScore());
            if (isOnline[10]) {
                label11 = new Label(label11.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label11 = new Label("-         -");
        }
        try {
            label12 = new Label(scoreboardUsers.get(11).getNickName() + "        " + scoreboardUsers.get(11).getScore());
            if (isOnline[11]) {
                label12 = new Label(label12.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label12 = new Label("-         -");
        }
        try {
            label13 = new Label(scoreboardUsers.get(12).getNickName() + "        " + scoreboardUsers.get(12).getScore());
            if (isOnline[12]) {
                label13 = new Label(label13.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label13 = new Label("-         -");
        }
        try {
            label14 = new Label(scoreboardUsers.get(13).getNickName() + "        " + scoreboardUsers.get(13).getScore());
            if (isOnline[13]) {
                label14 = new Label(label14.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label14 = new Label("-         -");
        }
        try {
            label15 = new Label(scoreboardUsers.get(14).getNickName() + "        " + scoreboardUsers.get(14).getScore());
            if (isOnline[14]) {
                label15 = new Label(label15.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label15 = new Label("-         -");
        }
        try {
            label16 = new Label(scoreboardUsers.get(15).getNickName() + "        " + scoreboardUsers.get(15).getScore());
            if (isOnline[15]) {
                label16 = new Label(label16.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label16 = new Label("-         -");
        }
        try {
            label17 = new Label(scoreboardUsers.get(16).getNickName() + "        " + scoreboardUsers.get(16).getScore());
            if (isOnline[16]) {
                label17 = new Label(label17.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label17 = new Label("-         -");
        }
        try {
            label18 = new Label(scoreboardUsers.get(17).getNickName() + "        " + scoreboardUsers.get(17).getScore());
            if (isOnline[17]) {
                label18 = new Label(label18.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label18 = new Label("-         -");
        }
        try {
            label19 = new Label(scoreboardUsers.get(18).getNickName() + "        " + scoreboardUsers.get(18).getScore());
            if (isOnline[18]) {
                label19 = new Label(label19.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label19 = new Label("-         -");
        }
        try {
            label20 = new Label(scoreboardUsers.get(19).getNickName() + "        " + scoreboardUsers.get(19).getScore());
            if (isOnline[19]) {
                label20 = new Label(label20.getText() + "  " + "ONLINE");
            }
        } catch (Exception e) {
            label20 = new Label("-         -");
        }
        labelMakerFourth(label11, label13, label12, label14, label15, label16, label17, label18, label19, label20);
        labelMakerThird(label11, label12, label13, label14, label15, label16, label17, label18, label19, label20);
        searchPlayer(label11, label12, label13, label14, label15);
        searchPlayer(label16, label17, label18, label19, label20);
        anchorPane.getChildren().addAll(label11, label12, label13, label14, label15);
        anchorPane.getChildren().addAll(label16, label17, label18, label19, label20);
    }

    public void searchPlayer(Label label1, Label label2, Label label3, Label label4, Label label5) {
        if (label1.getText().contains(loggedUser.getNickName())) {
            label1.setId("selected");
        }
        if (label2.getText().contains(loggedUser.getNickName())) {
            label2.setId("selected");
        }
        if (label3.getText().contains(loggedUser.getNickName())) {
            label3.setId("selected");
        }
        if (label4.getText().contains(loggedUser.getNickName())) {
            label4.setId("selected");
        }
        if (label5.getText().contains(loggedUser.getNickName())) {
            label5.setId("selected");
        }
    }
}
