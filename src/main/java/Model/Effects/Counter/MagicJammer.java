package Model.Effects.Counter;

import Controller.Game;
import Model.*;
import Model.Effects.Effect;
import View.GUI.GamePlay;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOutUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MagicJammer extends Effect {

    private User owner;
    private User enemy;

    // this is the card in chain that has to be destroyed
    private Card cardToDestroy;

    private CardRectangle selectedCardForTribute= null;

    public MagicJammer(Card card) {
        super(card);
        speed = 3;
    }

    @Override
    public boolean activate(Game game) {
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        Label label = new Label();
        label.setText("tribute 1\ncard");
        box.getChildren().add(label);
        for (Card card : owner.getBoard().getCardsInHand()) {
            CardRectangle cardRectangle = new CardRectangle();
            cardRectangle.setRelatedCard(card);
            cardRectangle.setFill(new ImagePattern(card.getCardImage()));
            cardRectangle.setWidth(90);
            cardRectangle.setHeight(150);
            cardRectangle.setOnMouseClicked(event1 -> {
                if (selectedCardForTribute != null) selectedCardForTribute.setStroke(Color.TRANSPARENT);
                selectedCardForTribute = cardRectangle;
                selectedCardForTribute.setStroke(Color.GOLD);
            });
            box.getChildren().add(cardRectangle);
        }

        Button tribute = new Button("tribute");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Game.mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        tribute.setOnMouseClicked(event1 -> {
            if (selectedCardForTribute == null) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "tribute error", "no card is selected yet");
            } else {
                Card selectedCard = selectedCardForTribute.getRelatedCard();
                new FadeOutUp(box).play();
                popupStage.hide();
                owner.getBoard().getCardsInHand().remove(selectedCard);
                owner.getBoard().getGraveYard().add(selectedCard);
                game.addSpellOrTrapFromZoneToGraveyard(cardToDestroy, enemy);
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "activate effect message", "trap activated");
                game.addSpellOrTrapFromZoneToGraveyard(card, owner);
            }
        });
        box.getChildren().add(tribute);
        popupStage.show();
        new FadeIn(box).play();
        return true;
    }

    @Override
    public boolean canBeActivated(Game game) {
        if (game.getCurrentUser().getBoard().getAllCards().contains(card)) {
            owner = game.getCurrentUser();
            enemy = game.getOpponentOfCurrentUser();
        } else {
            owner = game.getOpponentOfCurrentUser();
            enemy = game.getCurrentUser();
        }
        // check Mirage Dragon
        for (int i = 0; i < 5; i++) {
            Card enemyMonster = enemy.getBoard().getMonstersZone().get(i);
            if (enemyMonster != null) {
                if (enemyMonster.getOccupied()) {
                    if (enemyMonster.getName().equals("Mirage Dragon")) {
                        return false;
                    }
                }
            }
        }
        if (game.getChain().size() != 0) {
            if (game.getChain().get(game.getChain().size() - 1) instanceof Spell) {
                if (((Spell) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                    return false;
                }
            } else {
                if (((Trap) game.getChain().get(game.getChain().size() - 1)).getEffect().getSpeed() > speed) {
                    return false;
                }
            }
        }
        if (owner.getBoard().getCardsInHand().size() == 0) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (enemy.getBoard().getSpellsAndTrapsZone().get(i) != null) {
                break;
            }
        }
        if (game.getChain().size() != 0) {
            if (game.getChain().get(game.getChain().size() - 1) instanceof Spell) {
                if (enemy.getBoard().getAllCards().contains(game.getChain().get(game.getChain().size() - 1))) {
                    cardToDestroy = game.getChain().get(game.getChain().size() - 1);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
