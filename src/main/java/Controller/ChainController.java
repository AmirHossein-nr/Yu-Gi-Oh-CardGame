package Controller;

import Model.*;
import View.GUI.GamePlay;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOutUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ChainController {

    private final Game game;
    private final ArrayList<Card> chain;

    private CardRectangle selectedCardInChain = null;
    private Card selectedCard = null;

    private final User originalCurrentUser;

    public ChainController(Game game) {
        this.game = game;
        chain = game.getChain();
        this.originalCurrentUser = game.getCurrentUser();
    }

    public void run() {
        game.setCanSpeedOneBeActivated(false);
        game.changeTurnInChain();
        if (canActivate(game.getCurrentUser())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("changing turn");
            alert.setContentText("now it will be " + game.getCurrentUser().getUsername() + "'s turn");
            alert.getButtonTypes().set(0, ButtonType.YES);
            alert.getButtonTypes().set(1, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                // todo
            } else {
                executeChain();
            }
        } else {
            executeChain();
        }
    }

    public void executeChain() {
        game.setCurrentUser(originalCurrentUser);
        game.printBoard();
        if (chain.size() == 0) {
            return;
        }
        Collections.reverse(chain);
        for (int i = 0; i < chain.size(); i++) {
            Card card = chain.get(i);
            if (card instanceof Spell) {
                ((Spell) card).getEffect().finalActivate(game);
            } else { // so its a trap
                ((Trap) card).getEffect().finalActivate(game);
            }
            game.printBoard();
        }
        chain.clear();
        game.setCanSpeedOneBeActivated(true);
    }

    private void playTurnInChain() {
//        HBox box = new HBox(50);
//        box.setAlignment(Pos.TOP_LEFT);
//        box.setPadding(new Insets(10));
//        ArrayList<CardRectangle> spellTrapZoneRectangles = new ArrayList<>();
//        spellTrapZoneRectangles.add(game.currentSpell1);
//        spellTrapZoneRectangles.add(game.currentSpell2);
//        spellTrapZoneRectangles.add(game.currentSpell3);
//        spellTrapZoneRectangles.add(game.currentSpell4);
//        spellTrapZoneRectangles.add(game.currentSpell5);
//        for (CardRectangle cardRectangle : spellTrapZoneRectangles) {
//            if (cardRectangle.getRelatedCard() != null) {
//                if (!cardRectangle.getRelatedCard().getOccupied()) {
//
//                }
//                CardRectangle cardRectangle1 = new CardRectangle();
//                cardRectangle1.setRelatedCard(cardRectangle.getRelatedCard());
//                cardRectangle1.setFill(new ImagePattern(cardRectangle.getRelatedCard().getCardImage()));
//                cardRectangle1.setWidth(90);
//                cardRectangle1.setHeight(150);
//                cardRectangle1.setOnMouseClicked(event1 -> {
//                    if (selectedCardForTribute != null) selectedCardForTribute.setStroke(Color.TRANSPARENT);
//                    selectedCardForTribute = cardRectangle1;
//                    selectedCardForTribute.setStroke(Color.GOLD);
//                    selectedCard = selectedCardForTribute.getRelatedCard();
//                });
//                box.getChildren().add(cardRectangle1);
//            }
//        }
//
//        Button tribute = new Button("tribute");
//        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
//        popupStage.initOwner(mainStage);
//        popupStage.initModality(Modality.APPLICATION_MODAL);
//        Scene question = new Scene(box, Color.TRANSPARENT);
//        question.getStylesheets().add("/Css/GamePlay.css");
//        popupStage.setScene(question);
//        tribute.setOnMouseClicked(event1 -> {
//            if (selectedCardForTribute == null) {
//                playErrorSound();
//                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon/Set Error", "no card is selected yet");
//            } else {
//                playTributeSound();
//                tributeMonster(selectedCardForTribute.getRelatedCard());
//                selectedCardForTribute = null;
//                if (tributeNumber == 1) {
//                    selectedCard = selectedRectangle.getRelatedCard();
//                    if (isSummon) {
//                        if (isAttack) {
//                            addMonsterFromHandToMonsterZone(selectedCard, true, true);
//                        } else {
//                            addMonsterFromHandToMonsterZone(selectedCard, true, false);
//                        }
//                        playPopSound();
//                        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Successful !",
//                                "Summoned successfully");
//                    } else {
//                        addMonsterFromHandToMonsterZone(selectedCard, false, false);
//                        playPopSound();
//                        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Successful !",
//                                "Set successfully");
//                    }
//                    if (!isSpecial) {
//                        normalSummonOrSetCard = selectedCard;
//                    } else {
//                        specialSummonedCards.add(selectedCard);
//                    }
//                    selectedCard = null;
//                    printBoard();
//                    new FadeOutUp(box).play();
//                    popupStage.hide();
//                } else {
//                    printBoard();
//                    new FadeOutUp(box).play();
//                    popupStage.hide();
//                    doTributeSummonOrSet(tributeNumber - 1, isSpecial, isSummon, isAttack);
//                }
//            }
//        });
//        box.getChildren().add(tribute);
//        popupStage.show();
//        new FadeIn(box).play();
    }

    private void activateEffectInChain() {
//        if (selectedCard == null) {
//            System.out.println("no card is selected yet");
//            return;
//        }
//        if (selectedCard instanceof Monster) {
//            System.out.println("activate effect is not for monster cards.");
//            return;
//        }
//        if (!currentUser.getBoard().getAllCards().contains(selectedCard)) {
//            System.out.println("This card is not yours");
//            return;
//        }
//        if (currentUser.getBoard().getSpellsAndTrapsZone().contains(selectedCard) && selectedCard.getOccupied()) {
//            System.out.println("you have already activated this card");
//            return;
//        }
//        if (selectedCard instanceof Spell) {
//            if (((Spell) selectedCard).getEffect().getSpeed() == 1) {
//                System.out.println("preparations of this spell are not done yet");
//                return;
//            }
//            if (!((Spell) selectedCard).getEffect().canBeActivated(game)) {
//                System.out.println("preparations of this spell are not done yet");
//                return;
//            }
//        } else { // so its a trap
//            if (!((Trap) selectedCard).getEffect().canBeActivated(game)) {
//                System.out.println("preparations of this trap are not done yet");
//                return;
//            }
//        }
//
//        if (selectedCard instanceof Spell) {
//            ((Spell) selectedCard).getEffect().addToChain(game);
//        } else { // so its a trap
//            ((Trap) selectedCard).getEffect().addToChain(game);
//        }
    }

    private boolean canActivate(User user) {
        for (int i = 0; i < 5; i++) {
            Card card = user.getBoard().getSpellsAndTrapsZone().get(i);
            if (card != null) {
                if (!card.getOccupied()) {
                    if (card instanceof Spell) {
                        if (((Spell) card).getEffect().canBeActivated(game)) {
                            return true;
                        }
                    } else { // so its a trap
                        if (((Trap) card).getEffect().canBeActivated(game)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
}
