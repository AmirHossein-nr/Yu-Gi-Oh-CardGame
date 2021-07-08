package View.GUI;

import Model.User;
import View.Menu.ScoreBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class ScoreBoardGraphic extends Application {

    public static User loggedUser = ScoreBoard.getLoggedUser();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scoreBoard.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 800, 610);
        primaryStage.setScene(scene);
        createLabelsFirst(anchorPane);
        createLabelsSecond(anchorPane);
        User.sortAllUsers();
        createLabelsThird(anchorPane);
        createLabelsFourth(anchorPane);
        primaryStage.setTitle("Yu-Gi-OH!");
        primaryStage.show();
        ScoreBoard.mainStage = primaryStage;
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

    private void labelMakerSecond(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6, Label label7, Label label8, Label label9, Label label10) {
        label1.setLayoutY(10);
        label2.setLayoutY(40);
        label3.setLayoutY(70);
        label4.setLayoutY(100);
        label5.setLayoutY(130);
        label6.setLayoutY(160);
        label7.setLayoutY(190);
        label8.setLayoutY(220);
        label9.setLayoutY(250);
        label10.setLayoutY(280);
    }

    private void labelMakerFirst(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6, Label label7, Label label8, Label label9, Label label10) {
        label2.setLayoutX(5);
        label1.setLayoutX(5);
        label3.setLayoutX(5);
        label4.setLayoutX(5);
        label5.setLayoutX(5);
        label6.setLayoutX(5);
        label7.setLayoutX(5);
        label8.setLayoutX(5);
        label9.setLayoutX(5);
        label10.setLayoutX(5);
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

    private void labelMakerThird(Label label11, Label label12, Label label13, Label label14, Label label15, Label label16, Label label17, Label label18, Label label19, Label label20) {
        label11.setLayoutY(310);
        label12.setLayoutY(340);
        label13.setLayoutY(370);
        label14.setLayoutY(400);
        label15.setLayoutY(430);
        label16.setLayoutY(460);
        label17.setLayoutY(490);
        label18.setLayoutY(520);
        label19.setLayoutY(550);
        label20.setLayoutY(580);
    }

    public void createLabelsThird(AnchorPane anchorPane) {
        Label label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;
        try {
            label1 = new Label(User.getAllUsers().get(0).getNickName() + "        " + User.getAllUsers().get(0).getScore());
        }catch (Exception e) {
            label1 = new Label("-         -");
        }
        try {
            label2 = new Label(User.getAllUsers().get(1).getNickName() + "        " + User.getAllUsers().get(1).getScore());
        }catch (Exception e) {
            label2 = new Label("-         -");
        }
        try {
            label3 = new Label(User.getAllUsers().get(2).getNickName() + "        " + User.getAllUsers().get(2).getScore());
        }catch (Exception e) {
            label3 = new Label("-         -");
        }
        try {
            label4 = new Label(User.getAllUsers().get(3).getNickName() + "        " + User.getAllUsers().get(3).getScore());
        }catch (Exception e) {
            label4 = new Label("-         -");
        }
        try {
            label5 = new Label(User.getAllUsers().get(4).getNickName() + "        " + User.getAllUsers().get(4).getScore());
        }catch (Exception e) {
            label5 = new Label("-         -");
        }
        try {
            label6 = new Label(User.getAllUsers().get(5).getNickName() + "        " + User.getAllUsers().get(5).getScore());
        }catch (Exception e) {
            label6 = new Label("-         -");
        }
        try  {
            label7 = new Label(User.getAllUsers().get(6).getNickName() + "        " + User.getAllUsers().get(6).getScore());
        }catch (Exception e) {
            label7 = new Label("-         -");
        }
        try {
            label8 = new Label(User.getAllUsers().get(7).getNickName() + "        " + User.getAllUsers().get(7).getScore());
        }catch (Exception e) {
            label8 = new Label("-         -");
        }
        try {
            label9 = new Label(User.getAllUsers().get(8).getNickName() + "        " + User.getAllUsers().get(8).getScore());
        }catch (Exception e) {
            label9 = new Label("-         -");
        }
        try {
            label10 = new Label(User.getAllUsers().get(9).getNickName() + "        " + User.getAllUsers().get(9).getScore());
        }catch (Exception e) {
            label10 = new Label("-         -");
        }

        labelMakerFourth(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        labelMakerSecond(label1, label2, label3, label4, label5, label6, label7, label8, label9, label10);
        searchPlayer(label1, label2, label3, label4, label5);
        searchPlayer(label6, label7, label8, label9, label10);
        anchorPane.getChildren().addAll(label1, label2, label3, label4, label5);
        anchorPane.getChildren().addAll(label6, label7, label8, label9, label10);
    }

    private void labelMakerFourth(Label label1, Label label2, Label label3, Label label4, Label label5, Label label6, Label label7, Label label8, Label label9, Label label10) {
        label2.setLayoutX(50);
        label1.setLayoutX(50);
        label3.setLayoutX(50);
        label4.setLayoutX(50);
        label5.setLayoutX(50);
        label6.setLayoutX(50);
        label7.setLayoutX(50);
        label8.setLayoutX(50);
        label9.setLayoutX(50);
        label10.setLayoutX(50);
    }

    public void createLabelsFourth(AnchorPane anchorPane) {
        Label label11, label12, label13, label14, label15, label16, label17, label18, label19, label20;
        try {
            label11 = new Label(User.getAllUsers().get(10).getNickName() + "        " + User.getAllUsers().get(10).getScore());
        }catch (Exception e) {
            label11 = new Label("-         -");
        }
        try {
            label12 = new Label(User.getAllUsers().get(11).getNickName() + "        " + User.getAllUsers().get(11).getScore());
        }catch (Exception e) {
            label12 = new Label("-         -");
        }
        try {
            label13 = new Label(User.getAllUsers().get(12).getNickName() + "        " + User.getAllUsers().get(12).getScore());
        }catch (Exception e) {
            label13 = new Label("-         -");
        }
        try {
            label14 = new Label(User.getAllUsers().get(13).getNickName() + "        " + User.getAllUsers().get(13).getScore());
        }catch (Exception e) {
            label14 = new Label("-         -");
        }
        try {
            label15 = new Label(User.getAllUsers().get(14).getNickName() + "        " + User.getAllUsers().get(14).getScore());
        }catch (Exception e) {
            label15 = new Label("-         -");
        }
        try {
            label16 = new Label(User.getAllUsers().get(15).getNickName() + "        " + User.getAllUsers().get(15).getScore());
        }catch (Exception e) {
            label16 = new Label("-         -");
        }
        try  {
            label17 = new Label(User.getAllUsers().get(16).getNickName() + "        " + User.getAllUsers().get(16).getScore());
        }catch (Exception e) {
            label17 = new Label("-         -");
        }
        try {
            label18 = new Label(User.getAllUsers().get(17).getNickName() + "        " + User.getAllUsers().get(17).getScore());
        }catch (Exception e) {
            label18 = new Label("-         -");
        }
        try {
            label19 = new Label(User.getAllUsers().get(18).getNickName() + "        " + User.getAllUsers().get(18).getScore());
        }catch (Exception e) {
            label19 = new Label("-         -");
        }
        try {
            label20 = new Label(User.getAllUsers().get(19).getNickName() + "        " + User.getAllUsers().get(19).getScore());
        }catch (Exception e) {
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
            label1.setStyle("-fx-border-width: 3");
            label1.setStyle("-fx-border-color: orange");
            label1.setStyle("-fx-border-style: solid");
        }
        if (label2.getText().contains(loggedUser.getNickName())) {
            label2.setStyle("-fx-border-color: orange");
            label2.setStyle("-fx-border-width: 3");
            label2.setStyle("-fx-border-style: solid");
        }
        if (label3.getText().contains(loggedUser.getNickName())) {
            label3.setStyle("-fx-border-color: orange");
            label3.setStyle("-fx-border-style: solid");
            label3.setStyle("-fx-border-width: 3");
        }
        if (label4.getText().contains(loggedUser.getNickName())) {
            label4.setStyle("-fx-border-color: orange");
            label4.setStyle("-fx-border-style: solid");
            label4.setStyle("-fx-border-width: 3");
        }
        if (label5.getText().contains(loggedUser.getNickName())) {
            label5.setStyle("-fx-border-color: orange");
            label5.setStyle("-fx-border-style: solid");
            label5.setStyle("-fx-border-width: 3");
        }
    }
}
