package Model.Effects.Normal;

import Model.*;
import Model.Effects.Effect;
import Client.Controller.Game;
import Client.View.GUI.GamePlay;
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

public class MonsterReborn extends Effect {

    private CardRectangle selectedCardInGraveYard = null;
    private boolean isMyGraveyard;

    public MonsterReborn(Card card) {
        super(card);
        speed = 1;
    }

    @Override
    public boolean activate(Game game) {
        HBox box = new HBox(200);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        box.getChildren().add(gridPane);
        Button summon = new Button("Summon");
        Button myGraveyard = new Button("my graveyard");
        Button enemyGraveyard = new Button("enemy graveyard");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Game.mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        myGraveyard.setOnMouseClicked(event -> {
            isMyGraveyard = true;
            selectedCardInGraveYard = null;
            ArrayList<CardRectangle> monsterRectangles = new ArrayList<>();
            for (int i = 0; i < game.getCurrentUser().getBoard().getGraveYard().size(); i++) {
                if (game.getCurrentUser().getBoard().getGraveYard().get(i) instanceof Monster) {
                    CardRectangle rectangle = new CardRectangle();
                    rectangle.setWidth(90);
                    rectangle.setHeight(150);
                    rectangle.setFill(new ImagePattern(game.getCurrentUser().getBoard().getGraveYard().get(i).getCardImage()));
                    rectangle.setRelatedCard(game.getCurrentUser().getBoard().getGraveYard().get(i));
                    rectangle.setOnMouseClicked(event1 -> {
                        if (selectedCardInGraveYard != null) selectedCardInGraveYard.setStroke(Color.TRANSPARENT);
                        selectedCardInGraveYard = rectangle;
                        selectedCardInGraveYard.setStroke(Color.GOLD);
                    });
                    monsterRectangles.add(rectangle);
                }
            }
            int z = 0;
            outer:
            for (int i = 0; ; i++) {
                for (int j = 0; j < 10; j++) {
                    try {
                        gridPane.add(monsterRectangles.get(z), j, i);
                        z++;
                    } catch (Exception e) {
                        break outer;
                    }
                }
            }
        });
        enemyGraveyard.setOnMouseClicked(event -> {
            isMyGraveyard = false;
            selectedCardInGraveYard = null;
            ArrayList<CardRectangle> monsterRectangles = new ArrayList<>();
            for (int i = 0; i < game.getOpponentOfCurrentUser().getBoard().getGraveYard().size(); i++) {
                if (game.getOpponentOfCurrentUser().getBoard().getGraveYard().get(i) instanceof Monster) {
                    CardRectangle rectangle = new CardRectangle();
                    rectangle.setWidth(60);
                    rectangle.setHeight(100);
                    rectangle.setFill(new ImagePattern(game.getOpponentOfCurrentUser().getBoard().getGraveYard().get(i).getCardImage()));
                    rectangle.setRelatedCard(game.getOpponentOfCurrentUser().getBoard().getGraveYard().get(i));
                    rectangle.setOnMouseClicked(event1 -> {
                        if (selectedCardInGraveYard != null) selectedCardInGraveYard.setStroke(Color.TRANSPARENT);
                        selectedCardInGraveYard = rectangle;
                        selectedCardInGraveYard.setStroke(Color.GOLD);
                    });
                    monsterRectangles.add(rectangle);
                }
            }
            int z = 0;
            outer:
            for (int i = 0; ; i++) {
                for (int j = 0; j < 10; j++) {
                    try {
                        gridPane.add(monsterRectangles.get(z), j, i);
                        z++;
                    } catch (Exception e) {
                        break outer;
                    }
                }
            }
        });
        summon.setOnMouseClicked(event -> {
            if (selectedCardInGraveYard == null) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Error !",
                        "No Card is Selected!");
            } else {
                Card monsterCard = selectedCardInGraveYard.getRelatedCard();
                if ((card.getName().equals("Gate Guardian") || card.getCardType() == Type.RITUAL) && !game.getSpecialSummonedCards().contains(card)) {
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Summon unSuccessful !",
                            "should be special summoned first");
                } else {
                    new FadeOutUp(box).play();
                    popupStage.hide();
                    if (isMyGraveyard) {
                        game.getCurrentUser().getBoard().getGraveYard().remove(monsterCard);
                        game.getCurrentUser().getBoard().getCardsInHand().add(monsterCard);
                    } else {
                        game.getOpponentOfCurrentUser().getBoard().getAllCards().remove(monsterCard);
                        game.getOpponentOfCurrentUser().getBoard().getGraveYard().remove(monsterCard);
                        game.getCurrentUser().getBoard().getAllCards().add(monsterCard);
                        game.getCurrentUser().getBoard().getCardsInHand().add(monsterCard);
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Attack or Defence");
                    alert.setContentText("Do u want to summon in Attack position?");
                    alert.getButtonTypes().set(0, ButtonType.YES);
                    alert.getButtonTypes().set(1, ButtonType.NO);
                    if (alert.showAndWait().get() == ButtonType.YES) {
                        game.addMonsterFromHandToMonsterZone(monsterCard, true, true);
                    } else {
                        game.addMonsterFromHandToMonsterZone(monsterCard, true, false);
                    }
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "spell activated");
                    game.addSpellOrTrapFromZoneToGraveyard(card, game.getCurrentUser());
                }
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().add(summon);
        vBox.getChildren().add(myGraveyard);
        vBox.getChildren().add(enemyGraveyard);
        box.getChildren().add(vBox);
        popupStage.show();
        new FadeIn(box).play();
        return true;
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getChain().size() != 0) {
            return false;
        }
        if (game.getCurrentUser().getBoard().numberOfMonstersOnBoard() == 5) {
            return false;
        }
        for (Card card : game.getCurrentUser().getBoard().getGraveYard()) {
            if (card instanceof Monster) {
                return true;
            }
        }
        for (Card card : game.getOpponentOfCurrentUser().getBoard().getGraveYard()) {
            if (card instanceof Monster) {
                return true;
            }
        }
        return false;
    }
}
