package Model.Animation;

import Client.Controller.Game;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class CoinToss extends Transition {
    ImageView imageView;
    Game game;
    Label label;
    HBox root;
    Stage popUp;
    int random;

    public CoinToss(ImageView imageView, Game game, Label label, HBox root, Stage popUp) {
        this.imageView = imageView;
        this.label = label;
        this.game = game;
        this.root = root;
        this.popUp = popUp;
        random = Math.abs(new Random().nextInt() % 10);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setImage(new Image(Objects.requireNonNull(Game.class.getResource
                ("/images/Gold/Gold_1.png")).toExternalForm()));
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(850));
    }

    @Override
    protected void interpolate(double v) {
        play(v);
    }

    private synchronized void play(double v) {
        int frame = (int) Math.floor(v * 30);
        if (v == 0) imageView.setImage(new Image(Objects.requireNonNull(Game.class.getResource
                ("/images/Gold/Gold_1.png")).toExternalForm()));
        else {
            try {
                imageView.setImage(new Image(Objects.requireNonNull(Game.class.getResource("/images/Gold/Gold_"
                        + frame + ".png"))
                        .toExternalForm()));
                if (frame == 1) {
                    if (random % 2 == 0) {
                        label.setText(game.loggedUser.getUsername() + " Starts The Game !");
                        game.currentUser = game.loggedUser;
                        game.originalCurrentUser = game.currentUser;
                        this.stop();
                        return;
                    }
                }
                if (frame == 11) {
                    if (random % 2 == 1) {
                        label.setText(game.rivalUser.getUsername() + " Starts The Game !");
                        game.currentUser = game.rivalUser;
                        game.originalCurrentUser = game.currentUser;
                        this.stop();
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }
}
