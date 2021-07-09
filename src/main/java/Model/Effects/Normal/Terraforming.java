package Model.Effects.Normal;

import Model.*;
import Model.Effects.Effect;
import Controller.Game;
import View.GUI.GamePlay;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOutUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Terraforming extends Effect {

    private CardRectangle selectedFieldSpellRectangle = null;

    public Terraforming(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        box.getChildren().add(gridPane);

        ArrayList<CardRectangle> fieldSpellRectangles = new ArrayList<>();

        for (int i = 0; i < game.getCurrentUser().getBoard().getGraveYard().size(); i++) {
            if (game.getCurrentUser().getBoard().getGraveYard().get(i).getCardType() == Type.FIELD) {
                CardRectangle rectangle = new CardRectangle();
                rectangle.setWidth(90);
                rectangle.setHeight(150);
                rectangle.setFill(new ImagePattern(game.getCurrentUser().getBoard().getGraveYard().get(i).getCardImage()));
                rectangle.setRelatedCard(game.getCurrentUser().getBoard().getGraveYard().get(i));
                rectangle.setOnMouseClicked(event1 -> {
                    if (selectedFieldSpellRectangle != null) selectedFieldSpellRectangle.setStroke(Color.TRANSPARENT);
                    selectedFieldSpellRectangle = rectangle;
                    selectedFieldSpellRectangle.setStroke(Color.GOLD);
                });
                fieldSpellRectangles.add(rectangle);
            }
        }
        int z = 0;
        outer:
        for (int i = 0; ; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    gridPane.add(fieldSpellRectangles.get(z), j, i);
                    z++;
                } catch (Exception e) {
                    break outer;
                }
            }
        }

        Button add = new Button("add");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Game.mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        add.setOnMouseClicked(event -> {
            if (selectedFieldSpellRectangle == null) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Error !",
                        "No Card is Selected!");
            } else {
                new FadeOutUp(box).play();
                popupStage.hide();
                Card fieldSpell = selectedFieldSpellRectangle.getRelatedCard();
                game.getCurrentUser().getBoard().getDeckZone().remove(fieldSpell);
                game.getCurrentUser().getBoard().getCardsInHand().add(fieldSpell);
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
                game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
            }
        });
        box.getChildren().add(add);
        popupStage.show();
        new FadeIn(box).play();
        return true;
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        for (Card card : game.getCurrentUser().getBoard().getDeckZone()) {
            if (card instanceof Spell) {
                if (card.getCardType() == Type.FIELD) {
                    return true;
                }
            }
        }
        return false;
    }
}
