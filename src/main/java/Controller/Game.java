package Controller;

import Model.*;
import Model.Effects.Equipe.EquipEffect;
import Model.Effects.Field.FieldEffect;
import View.GUI.GamePlay;
import View.Menu.Shop;
import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {

    @FXML
    public Rectangle pauseButton;
    public Rectangle muteButton;
    public Rectangle surrenderButton;
    public Circle currentAvatar;
    public Circle rivalAvatar;
    public Rectangle drawPhasePlace;
    public Rectangle standByPhasePlace;
    public Rectangle mainPhase1Place;
    public Rectangle battlePhasePlace;
    public Rectangle mainPhase2Place;
    public Rectangle endPhasePlace;
    public CardRectangle currentCard1;
    public CardRectangle currentCard6;
    public CardRectangle currentCard5;
    public CardRectangle currentCard4;
    public CardRectangle currentCard3;
    public CardRectangle currentCard2;
    public Button nextPhaseButton;
    public Rectangle selectedCardImage;
    public Label currentUsername;
    public Label currentFullName;
    public Label rivalUsername;
    public Label rivalFullName;
    public CardRectangle currentMonster1;
    public CardRectangle currentMonster2;
    public CardRectangle currentMonster3;
    public CardRectangle currentMonster4;
    public CardRectangle currentMonster5;
    public CardRectangle currentSpell1;
    public CardRectangle currentSpell2;
    public CardRectangle currentSpell3;
    public CardRectangle currentSpell4;
    public CardRectangle currentSpell5;
    public CardRectangle rivalMonster1;
    public CardRectangle rivalMonster2;
    public CardRectangle rivalMonster3;
    public CardRectangle rivalMonster4;
    public CardRectangle rivalMonster5;
    public CardRectangle rivalSpell1;
    public CardRectangle rivalSpell2;
    public CardRectangle rivalSpell3;
    public CardRectangle rivalSpell4;
    public CardRectangle rivalSpell5;
    public Rectangle summon;
    public Rectangle setAttack;
    public Rectangle changePosition;
    public Rectangle nextButton;
    public Rectangle previousButton;
    public Rectangle graveYardIcon;
    public Label graveYardLabel;
    public Rectangle flipSummon;
    public Rectangle activateEffect;
    public ProgressBar rivalUserLifeBar;
    public ProgressBar currentUserLifeBar;
    public Rectangle attack;
    public Label currentUserLifePoint;
    public Label rivalLifePoint;
    public Rectangle directAttack;
    public CardRectangle currentFieldZone;
    public CardRectangle rivalFieldZone;

    public static Stage mainStage;

    private boolean mutePressed = false;
    public MediaPlayer player;
    private MediaPlayer theme;
    private CardRectangle selectedCardInGraveYard;
    private CardRectangle selectedCardForTribute;
    private CardRectangle selectedCardFromEnemy;
    private CardRectangle selectedCardToThrowAway;
    private int index = 0;
    Scanner scanner;
    boolean playingWithAi = false;
    public User loggedUser;
    public User rivalUser;
    public User currentUser;
    User winnerOfDuel = null;
    int numberOfRounds;
    int round = 1;
    int turn = 1;
    int temporaryValue = 0;
    Phase currentPhase = null;
    Card selectedCard;
    CardRectangle selectedRectangle;
    Card normalSummonOrSetCard = null;
    ArrayList<Card> putOnMonsterZoneCards = new ArrayList<>();
    ArrayList<Card> putOnSpellTrapZoneCards = new ArrayList<>();
    ArrayList<Card> attackedCards = new ArrayList<>();
    ArrayList<Card> setPositionedCards = new ArrayList<>();
    Spell activatedRitualCard = null;
    ArrayList<Card> chain = new ArrayList<>();
    ArrayList<Card> specialSummonedCards = new ArrayList<>();
    boolean canSpeedOneBeActivated = true;
    boolean timeSealActivated = false;
    boolean declaredAttack = false;
    boolean magicCylinderActivated = false;
    boolean negateAttackActivated = false;
    boolean mirrorForceActivated = false;
    boolean isSuijin = false;

    @FXML
    public void initialize() {
        initialiseLabelNames();
        setFillForImagesOnBoard();
        nextAndPreviousButtonsInitialize();
        clearTheWholeScene();
        initialiseAnimationsOfSelectCard();
        mouseDragControlling();
        onMouseHoverForCardsOnBoard();
        onMouseClickedForCardsInHand();
        onMouseClickedForCardsInDeck();
        onMouseClickedForEnemyCards();
        new FadeInDown(rivalAvatar).play();
        new FadeInUp(currentAvatar).play();
        nextPhaseButton.setOnMouseClicked(event -> nextPhase());
        drawPhasePlace.setFill(Color.GREEN);
        theme = new MediaPlayer(new Media(getClass().getResource("/music/themeMusic.mp3")
                .toExternalForm()));
        theme.setVolume(0.3);
        theme.setCycleCount(MediaPlayer.INDEFINITE);
        theme.play();
    }

    private void onMouseClickedForEnemyCards() {
        rivalMonster1.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalMonster1;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalMonster2.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalMonster2;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalMonster3.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalMonster3;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalMonster4.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalMonster4;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalMonster5.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalMonster5;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalSpell5.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalSpell5;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalSpell4.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalSpell4;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalSpell3.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalSpell3;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalSpell2.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalSpell2;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
        rivalSpell1.setOnMouseClicked(event -> {
            if (currentPhase == Phase.BATTLE) {
                if (selectedCardFromEnemy != null) selectedCardFromEnemy.setStroke(Color.TRANSPARENT);
                selectedCardFromEnemy = rivalSpell1;
                selectedCardFromEnemy.setStroke(Color.GOLDENROD);
            }
        });
    }

    private void setFillForImagesOnBoard() {
        surrenderButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You are Giving Up !!");
            alert.setContentText("Are You Sure ?");
            alert.getButtonTypes().set(0, ButtonType.YES);
            alert.getButtonTypes().set(1, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                try {
                    playSurrenderSound();
                    surrender();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                playDontGiveUpSound();
                alert.close();
            }
        });
        muteButton.setOnMouseClicked(event -> {
            if (!mutePressed) {
                try {
                    player.setMute(true);
                    theme.pause();
                    muteButton.setFill(new ImagePattern(new Image("/images/Icons/sound.png")));
                    mutePressed = true;
                } catch (Exception ignored) {
                }
            } else {
                try {
                    theme.play();
                    player.setMute(false);
                    muteButton.setFill(new ImagePattern(new Image("/images/Icons/Mute.png")));
                    mutePressed = false;
                } catch (Exception ignored) {
                }
            }
        });
        pauseButton.setFill(new ImagePattern(new Image("/images/Icons/_images_item_bg00.png")));
        muteButton.setFill(new ImagePattern(new Image("/images/Icons/mute.png")));
        surrenderButton.setFill(new ImagePattern(new Image("/images/Icons/surrender.png")));
        selectedCardImage.setFill(new ImagePattern(new Image("/images/backCard.jpg")));
        selectedCardImage.setOpacity(1);
        summon.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                .getResource("/images/Icons/normalSummon.png"))
                .toExternalForm())));
        summon.setOnMouseClicked(event -> {
            summon();
            printBoard();
        });
        attack.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                .getResource("/images/Icons/attack.png"))
                .toExternalForm())));
        attack.setOnMouseClicked(event -> {
            attack();
            printBoard();
            checkWinner();
        });
        setAttack.setFill(new ImagePattern(new Image("/images/Icons/setAttack.png")));
        setAttack.setOnMouseClicked(event -> {
            set();
            printBoard();
        });
        directAttack.setFill(new ImagePattern(new Image("/images/Icons/directAttack.png")));
        directAttack.setOnMouseClicked(event -> {
            playSwordSound();
            directAttack();
            printBoard();
            checkWinner();
        });
        changePosition.setFill(new ImagePattern(new Image("/images/Icons/changePosition.png")));
        changePosition.setOnMouseClicked(event -> showChangePositionPopUp());
        nextButton.setFill(new ImagePattern(new Image("/images/Icons/next.png")));
        previousButton.setFill(new ImagePattern(new Image("/images/Icons/previous.png")));
        pauseButton.setOnMouseClicked(event -> {
            playDryPopSound();
            GamePlay.pauseButtonExecution();
        });
        graveYardIcon.setFill(new ImagePattern(new Image("/images/Icons/graveYard.png")));
        graveYardOnClick();
        flipSummon.setFill(new ImagePattern(new Image("/images/Icons/flipSummon.png")));
        flipSummon.setOnMouseClicked(event -> {
            flipSummon();
            printBoard();
        });
        activateEffect.setFill(new ImagePattern(new Image("/images/Icons/activateEffect.png")));
        activateEffect.setOnMouseClicked(event -> {
            activateEffect();
            printBoard();
        });
    }

    private void graveYardOnClick() {
        graveYardIcon.setOnMouseClicked(event -> {
            HBox box = new HBox(50);
            box.setAlignment(Pos.TOP_LEFT);
            box.setPadding(new Insets(10));
            GridPane gridPane = new GridPane();
            gridPane.setLayoutX(0);
            gridPane.setLayoutY(0);
            box.getChildren().add(gridPane);
            ArrayList<CardRectangle> cardRectangles = new ArrayList<>();

            for (int i = 0; i < currentUser.getBoard().getGraveYard().size(); i++) {
                CardRectangle rectangle = new CardRectangle();
                rectangle.setWidth(90);
                rectangle.setHeight(150);
                rectangle.setFill(new ImagePattern(currentUser.getBoard().getGraveYard().get(i).getCardImage()));
                rectangle.setRelatedCard(currentUser.getBoard().getGraveYard().get(i));
                rectangle.setOnMouseClicked(event1 -> {
                    if (selectedCardInGraveYard != null) selectedCardInGraveYard.setStroke(Color.TRANSPARENT);
                    selectedCardInGraveYard = rectangle;
                    selectedCardInGraveYard.setStroke(Color.GOLD);
                });
                cardRectangles.add(rectangle);
            }
            int z = 0;

            outer:
            for (int i = 0; ; i++) {
                for (int j = 0; j < 13; j++) {
                    try {
                        gridPane.add(cardRectangles.get(z), j, i);
                        z++;
                    } catch (Exception e) {
                        break outer;
                    }
                }
            }

            Button close = new Button("Close !");
            Stage popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.initOwner(mainStage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Scene question = new Scene(box, Color.TRANSPARENT);
            question.getStylesheets().add("/Css/GamePlay.css");
            popupStage.setScene(question);
            close.setOnMouseClicked(event1 -> {
                selectedCardInGraveYard = null;
                new FadeOutUp(box).play();
                popupStage.hide();
            });
            box.getChildren().add(close);
            popupStage.show();
            new FadeIn(box).play();
        });
    }

    private void nextAndPreviousButtonsInitialize() {
        nextButton.setOnMouseClicked(event -> {
            if (index == currentUser.getBoard().getCardsInHand().size() - 6) return;
            if (currentUser.getBoard().getCardsInHand().size() > 6) {
                index++;
                showCardsInHand(index);
            }
        });
        previousButton.setOnMouseClicked(event -> {
            if (index == 0) return;
            if (currentUser.getBoard().getCardsInHand().size() > 6) {
                index--;
                showCardsInHand(index);
            }
        });
    }

    private void onMouseClickedForCardsInDeck() {
        currentMonster1.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentMonster1;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = selectedRectangle.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentMonster2.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentMonster2;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentMonster2.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentMonster3.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentMonster3;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentMonster3.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentMonster4.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentMonster4;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentMonster4.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentMonster5.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentMonster5;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentMonster5.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentSpell1.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentSpell1;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentSpell1.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentSpell2.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentSpell2;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentSpell2.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentSpell3.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentSpell3;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentSpell3.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentSpell4.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentSpell4;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentSpell4.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
        currentSpell5.setOnMouseClicked(event -> {
            try {
                selectedRectangle.setStroke(Color.TRANSPARENT);
                selectedRectangle = currentSpell5;
                selectedRectangle.setStroke(Color.GOLD);
                selectedCard = currentSpell5.getRelatedCard();
            } catch (Exception ignored) {
            }
        });
    }

    private void onMouseClickedForCardsInHand() {
        currentCard1.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard1;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard1.setStroke(Color.GOLD);
        });
        currentCard2.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard2;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard2.setStroke(Color.GOLD);
        });
        currentCard3.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard3;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard3.setStroke(Color.GOLD);
        });
        currentCard4.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard4;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard4.setStroke(Color.GOLD);
        });
        currentCard5.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard5;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard5.setStroke(Color.GOLD);
        });
        currentCard6.setOnMousePressed(event -> {
            if (selectedRectangle != null)
                selectedRectangle.setStroke(Color.TRANSPARENT);
            selectedRectangle = currentCard6;
            selectedCard = selectedRectangle.getRelatedCard();
            currentCard6.setStroke(Color.GOLD);
        });
    }

    private void mouseDragControlling() {
        dragEventForCards(currentCard1, currentCard2, currentCard3);
        dragEventForCards(currentCard4, currentCard5, currentCard6);
    }

    private void dragEventForCards(CardRectangle currentCard4, CardRectangle currentCard5, CardRectangle currentCard6) {
        double[] x = new double[3];
        double[] y = new double[3];
        x[0] = currentCard4.getX();
        y[0] = currentCard4.getY();
        x[1] = currentCard5.getX();
        y[1] = currentCard5.getY();
        x[2] = currentCard6.getX();
        y[2] = currentCard6.getY();

        currentCard4.setOnMouseDragged((MouseEvent me) -> {
            if (currentCard4.getFill() != Color.TRANSPARENT) {
                double diffX = me.getX() - currentCard4.getWidth() / 2;
                double diffY = me.getY() - currentCard4.getHeight() / 2;
                currentCard4.setX(diffX);
                currentCard4.setY(diffY);
            }
        });
        currentCard5.setOnMouseDragged((MouseEvent me) -> {
            if (currentCard5.getFill() != Color.TRANSPARENT) {
                double diffX = me.getX() - currentCard5.getWidth() / 2;
                double diffY = me.getY() - currentCard5.getHeight() / 2;
                currentCard5.setX(diffX);
                currentCard5.setY(diffY);
            }
        });
        currentCard6.setOnMouseDragged((MouseEvent me) -> {
            if (currentCard6.getFill() != Color.TRANSPARENT) {
                double diffX = me.getX() - currentCard6.getWidth() / 2;
                double diffY = me.getY() - currentCard6.getHeight() / 2;
                currentCard6.setX(diffX);
                currentCard6.setY(diffY);
            }
        });
        mouseReleaseControl(currentCard4, x[0], y[0]);
        mouseReleaseControl(currentCard5, x[1], y[1]);
        mouseReleaseControl(currentCard6, x[2], y[2]);
    }

    private void mouseReleaseControl(CardRectangle currentCard4, double x, double value) {
        currentCard4.setOnMouseReleased(event -> {
            if (currentCard4.getBoundsInParent().intersects(currentMonster1.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentMonster1.getFill() == Color.TRANSPARENT) {
                    currentMonster1.setFill(currentCard4.getFill());
                    currentMonster1.setRelatedCard(currentCard4.getRelatedCard());
                    currentUser.getBoard().getCardsInHand().remove(currentCard4.getRelatedCard());
                    currentCard4.setRelatedCard(null);
                    currentCard4.fillCard(false);
                    currentUser.getBoard().getMonstersZone().add(currentMonster1.getRelatedCard());
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentMonster2.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentMonster2.getFill() == Color.TRANSPARENT) {
                    currentMonster2.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentMonster2.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentMonster3.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentMonster3.getFill() == Color.TRANSPARENT) {
                    currentMonster3.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentMonster3.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentMonster4.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentMonster4.getFill() == Color.TRANSPARENT) {
                    currentMonster4.setFill(currentCard4.getFill());
                    currentUser.getBoard().getMonstersZone().remove(currentMonster4.getRelatedCard());
                    currentCard4.setFill(Color.TRANSPARENT);
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentMonster5.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentMonster5.getFill() == Color.TRANSPARENT) {
                    currentMonster5.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentMonster5.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentSpell1.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentSpell1.getFill() == Color.TRANSPARENT) {
                    currentSpell1.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentSpell1.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentSpell2.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentSpell2.getFill() == Color.TRANSPARENT) {
                    currentSpell2.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentSpell2.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentSpell3.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentSpell3.getFill() == Color.TRANSPARENT) {
                    currentSpell3.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentSpell3.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentSpell4.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentSpell4.getFill() == Color.TRANSPARENT) {
                    currentSpell4.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentSpell4.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            } else if (currentCard4.getBoundsInParent().intersects(currentSpell5.getBoundsInParent())) {
                if (currentCard4.getFill() != Color.TRANSPARENT && currentSpell5.getFill() == Color.TRANSPARENT) {
                    currentSpell5.setFill(currentCard4.getFill());
                    currentCard4.setFill(Color.TRANSPARENT);
                    currentUser.getBoard().getMonstersZone().remove(currentSpell5.getRelatedCard());
                    try {
                        currentUser.getBoard().getCardsInHand().remove(currentUser.getBoard().getCardsInHandRectangle()
                                .indexOf(currentCard4));
                    } catch (Exception ignored) {
                    }
                }
            }
            currentCard4.setX(x);
            currentCard4.setY(value);
        });
    }

    private void onMouseHoverForCardsOnBoard() {
        mouseHoverControlForCurrent(currentFieldZone);
        mouseHoverControlForCurrent(currentMonster1);
        mouseHoverControlForCurrent(currentMonster2);
        mouseHoverControlForCurrent(currentMonster3);
        mouseHoverControlForCurrent(currentMonster4);
        mouseHoverControlForCurrent(currentMonster5);
        mouseHoverControlForRival(rivalFieldZone);
        mouseHoverControlForRival(rivalMonster1);
        mouseHoverControlForRival(rivalMonster2);
        mouseHoverControlForRival(rivalMonster3);
        mouseHoverControlForRival(rivalMonster4);
        mouseHoverControlForRival(rivalMonster5);
        mouseHoverControlForCurrent(currentSpell1);
        mouseHoverControlForCurrent(currentSpell2);
        mouseHoverControlForCurrent(currentSpell3);
        mouseHoverControlForCurrent(currentSpell4);
        mouseHoverControlForCurrent(currentSpell5);
        mouseHoverControlForRival(rivalSpell1);
        mouseHoverControlForRival(rivalSpell2);
        mouseHoverControlForRival(rivalSpell3);
        mouseHoverControlForRival(rivalSpell4);
        mouseHoverControlForRival(rivalSpell5);
    }

    private void mouseHoverControlForCurrent(CardRectangle rectangle) {
        rectangle.setOnMouseEntered(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                selectedCardImage.setFill(new ImagePattern(rectangle.getRelatedCard().getCardImage()));
                new FlipInX(selectedCardImage).play();
            }
        });
        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                new FlipOutY(selectedCardImage).play();
                selectedCardImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                        .getResource("/images/backCard.jpg"))
                        .toExternalForm())));
                new FlipInY(selectedCardImage).play();
            }
        });
    }

    private void mouseHoverControlForRival(CardRectangle rectangle) {
        rectangle.setOnMouseEntered(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                selectedCardImage.setFill(rectangle.getFill());
                new FlipInX(selectedCardImage).play();
            }
        });
        rectangle.setOnMouseExited(event -> {
            if (rectangle.getFill() != Color.TRANSPARENT) {
                new FlipOutY(selectedCardImage).play();
                selectedCardImage.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                        .getResource("/images/backCard.jpg"))
                        .toExternalForm())));
                new FlipInY(selectedCardImage).play();
            }
        });
    }

    public void initialiseLabelNames() {
        try {
            rivalUsername.setText(getOpponentOfCurrentUser().getUsername());
            rivalFullName.setText(getOpponentOfCurrentUser().getNickName());
            currentUsername.setText(currentUser.getUsername());
            currentFullName.setText(currentUser.getNickName());
        } catch (Exception ignored) {
        }
    }

    private void initialiseAnimationsOfSelectCard() {
        currentCard1.setOnMouseEntered(event -> {
            selectedCardImage.setFill(currentCard1.getFill());
            new FlipInX(selectedCardImage).play();
        });
        setAnimations1(currentCard1, currentCard2, currentCard3);
        currentCard4.setOnMouseEntered(event -> {
            selectedCardImage.setFill(currentCard4.getFill());
            new FlipInY(selectedCardImage).play();
        });
        setAnimations1(currentCard4, currentCard5, currentCard6);
    }

    private void setAnimations1(Rectangle currentCard1, Rectangle currentCard2, Rectangle currentCard3) {
        currentCard1.setOnMouseExited(event -> {
            new FlipOutX(selectedCardImage).play();
            selectedCardImage.setFill(new ImagePattern(new Image("/images/backCard.jpg")));
            new FlipInX(selectedCardImage).play();
        });
        currentCard2.setOnMouseEntered(event -> {
            new FlipOutY(selectedCardImage).play();
            selectedCardImage.setFill(currentCard2.getFill());
            new FlipInY(selectedCardImage).play();
        });
        currentCard2.setOnMouseExited(event -> {
            new FlipOutX(selectedCardImage).play();
            selectedCardImage.setFill(new ImagePattern(new Image("/images/backCard.jpg")));
            new FlipInX(selectedCardImage).play();
        });
        currentCard3.setOnMouseEntered(event -> {
            new FlipOutY(selectedCardImage).play();
            selectedCardImage.setFill(currentCard3.getFill());
            new FlipInY(selectedCardImage).play();
        });
        currentCard3.setOnMouseExited(event -> {
            new FlipOutX(selectedCardImage).play();
            selectedCardImage.setFill(new ImagePattern(new Image("/images/backCard.jpg")));
            new FlipInX(selectedCardImage).play();
        });
        currentCard1.setCursor(Cursor.HAND);
        currentCard2.setCursor(Cursor.HAND);
        currentCard3.setCursor(Cursor.HAND);
        currentCard4.setCursor(Cursor.HAND);
        currentCard5.setCursor(Cursor.HAND);
        currentCard6.setCursor(Cursor.HAND);
    }

    private void clearTheWholeScene() {
        try {
            currentUserLifeBar.setProgress(currentUser.getLifePoint() / 8000.0);
            rivalUserLifeBar.setProgress(getOpponentOfCurrentUser().getLifePoint() / 8000.0);
            if (currentUser.getLifePoint() > 6000) {
                currentUserLifeBar.lookup(".bar").setStyle("-fx-background-color: Green");
                currentUserLifePoint.setStyle("-fx-text-fill: Green");
            } else if (currentUser.getLifePoint() > 4000 && currentUser.getLifePoint() <= 6000) {
                currentUserLifeBar.lookup(".bar").setStyle("-fx-background-color: Yellow");
                currentUserLifePoint.setStyle("-fx-text-fill: Yellow");
            } else if (currentUser.getLifePoint() <= 4000) {
                currentUserLifeBar.lookup(".bar").setStyle("-fx-background-color: RED");
                currentUserLifePoint.setStyle("-fx-text-fill: RED");
            }
            if (getOpponentOfCurrentUser().getLifePoint() > 6000) {
                rivalUserLifeBar.lookup(".bar").setStyle("-fx-background-color: GREEN");
                rivalLifePoint.setStyle("-fx-text-fill: Green");
            } else if (getOpponentOfCurrentUser().getLifePoint() > 4000 && getOpponentOfCurrentUser().getLifePoint() <= 6000) {
                rivalUserLifeBar.lookup(".bar").setStyle("-fx-background-color: Yellow");
                rivalLifePoint.setStyle("-fx-text-fill: Yellow");
            } else if (getOpponentOfCurrentUser().getLifePoint() <= 4000) {
                rivalUserLifeBar.lookup(".bar").setStyle("-fx-background-color: RED");
                rivalLifePoint.setStyle("-fx-text-fill: RED");
            }
            currentUserLifePoint.setText(String.valueOf(currentUser.getLifePoint()));
            rivalLifePoint.setText(String.valueOf(getOpponentOfCurrentUser().getLifePoint()));
            currentAvatar.setFill(new ImagePattern(currentUser.getAvatar()));
            rivalAvatar.setFill(new ImagePattern(getOpponentOfCurrentUser().getAvatar()));
        } catch (Exception ignored) {
        }
        currentFieldZone.setRelatedCard(null);
        currentFieldZone.fillCard(false);
        rivalFieldZone.setRelatedCard(null);
        rivalFieldZone.fillCard(false);
        currentCard1.setRelatedCard(null);
        currentCard1.fillCard(true);
        currentCard2.setRelatedCard(null);
        currentCard2.fillCard(true);
        currentCard3.setRelatedCard(null);
        currentCard3.fillCard(true);
        currentCard4.setRelatedCard(null);
        currentCard4.fillCard(true);
        currentCard5.setRelatedCard(null);
        currentCard5.fillCard(true);
        currentCard6.setRelatedCard(null);
        currentCard6.fillCard(true);
        currentSpell1.setRelatedCard(null);
        currentSpell1.fillCard(false);
        currentSpell2.setRelatedCard(null);
        currentSpell2.fillCard(false);
        currentSpell3.setRelatedCard(null);
        currentSpell3.fillCard(false);
        currentSpell4.setRelatedCard(null);
        currentSpell4.fillCard(false);
        currentSpell5.setRelatedCard(null);
        currentSpell5.fillCard(false);
        currentMonster1.setRelatedCard(null);
        currentMonster1.fillCard(false);
        currentMonster2.setRelatedCard(null);
        currentMonster2.fillCard(false);
        currentMonster3.setRelatedCard(null);
        currentMonster3.fillCard(false);
        currentMonster4.setRelatedCard(null);
        currentMonster4.fillCard(false);
        currentMonster5.setRelatedCard(null);
        currentMonster5.fillCard(false);
        rivalSpell1.setRelatedCard(null);
        rivalSpell1.fillCard(false);
        rivalSpell2.setRelatedCard(null);
        rivalSpell2.fillCard(false);
        rivalSpell3.setRelatedCard(null);
        rivalSpell3.fillCard(false);
        rivalSpell4.setRelatedCard(null);
        rivalSpell4.fillCard(false);
        rivalSpell5.setRelatedCard(null);
        rivalSpell5.fillCard(false);
        rivalMonster1.setRelatedCard(null);
        rivalMonster1.fillCard(false);
        rivalMonster2.setRelatedCard(null);
        rivalMonster2.fillCard(false);
        rivalMonster3.setRelatedCard(null);
        rivalMonster3.fillCard(false);
        rivalMonster4.setRelatedCard(null);
        rivalMonster4.fillCard(false);
        rivalMonster5.setRelatedCard(null);
        rivalMonster5.fillCard(false);
    }

    public void test() {
        User user1 = new User("amirhossein", "12345", "AmirHNR");
        User user2 = new User("mammad", "1234", "Mamali");
        user1.setAvatar(new Image(Objects.requireNonNull(getClass().getResource("/images/Characters/001.png"))
                .toExternalForm()));
        user2.setAvatar(new Image(Objects.requireNonNull(getClass().getResource("/images/Characters/002.png"))
                .toExternalForm()));
        Deck deck = new Deck(new MainDeck(true), new SideDeck(true));
        new Shop(null);
        for (int i = 55; i < 65; i++) {
            deck.getMainDeck().getCardsInMainDeck().add(Shop.getAllCards().get(i));
        }
        deck.setValid(true);
        deck.setActive(true);
        user1.getDecks().add(deck);
        user2.getDecks().add(deck);
        Board board1 = new Board();
        board1.setDeck(deck);
        user1.setBoard(board1);
        Board board2 = new Board();
        board2.setDeck(deck);
        user2.setBoard(board2);
        user1.getBoard().setZones();
        user2.getBoard().setZones();
        this.loggedUser = user1;
        this.currentUser = user1;
        this.rivalUser = user2;
        this.scanner = new Scanner(System.in);
        this.numberOfRounds = 3;
    }

    public Game() {

    }

    public Game(User loggedUser, User rivalUser, int numberOfRounds, Scanner scanner) {
        this.loggedUser = loggedUser;
        this.rivalUser = rivalUser;
        currentUser = loggedUser;
        this.numberOfRounds = numberOfRounds;
        this.scanner = scanner;
        loggedUser.setLifePoint(8000);
        rivalUser.setLifePoint(8000);
        loggedUser.setMaxLifePoint(0);
        rivalUser.setMaxLifePoint(0);
        loggedUser.setNumberOfWinsInGame(0);
        rivalUser.setNumberOfWinsInGame(0);
        if (rivalUser.getUsername().equalsIgnoreCase("ai")) {
            playingWithAi = true;
        }
    }

    public void setActivatedRitualCard(Spell activatedRitualCard) {
        this.activatedRitualCard = activatedRitualCard;
    }

    public void setMagicCylinderActivated(boolean magicCylinderActivated) {
        this.magicCylinderActivated = magicCylinderActivated;
    }

    public boolean isMagicCylinderActivated() {
        return magicCylinderActivated;
    }

    public boolean isNegateAttackActivated() {
        return negateAttackActivated;
    }

    public boolean isMirrorForceActivated() {
        return mirrorForceActivated;
    }

    public boolean isCanSpeedOneBeActivated() {
        return canSpeedOneBeActivated;
    }

    public void setCanSpeedOneBeActivated(boolean canSpeedOneBeActivated) {
        this.canSpeedOneBeActivated = canSpeedOneBeActivated;
    }

    public void setMirrorForceActivated(boolean mirrorForceActivated) {
        this.mirrorForceActivated = mirrorForceActivated;
    }

    public void setNegateAttackActivated(boolean negateAttackActivated) {
        this.negateAttackActivated = negateAttackActivated;
    }

    public void setDeclaredAttack(boolean declaredAttack) {
        this.declaredAttack = declaredAttack;
    }

    public boolean isDeclaredAttack() {
        return declaredAttack;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Spell getActivatedRitualCard() {
        return activatedRitualCard;
    }

    public ArrayList<Card> getSpecialSummonedCards() {
        return specialSummonedCards;
    }

    public ArrayList<Card> getChain() {
        return chain;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void startADuel() {
        if (round <= numberOfRounds) {
            if (numberOfRounds == 3) {
                if (loggedUser.getNumberOfWinsInGame() == 2 || rivalUser.getNumberOfWinsInGame() == 2) {
                    finishGame();
                    return;
                }
            }
            if (round == 1) {
                resetPlayersAttributes(loggedUser);
            } else {
                if (winnerOfDuel == loggedUser) {
                    resetPlayersAttributes(rivalUser);
                } else {
                    resetPlayersAttributes(loggedUser);
                }
            }
            printBoard();
//            finishRound();
//            round++;
//            if (numberOfRounds == 3 && round <= 3) {
//                takeABreak();
//            }
        } else {
            finishGame();
        }
    }

    public void run() {

        while (round <= numberOfRounds) {
            if (numberOfRounds == 3) {
                if (loggedUser.getNumberOfWinsInGame() == 2 || rivalUser.getNumberOfWinsInGame() == 2) {
                    break;
                }
            }
            if (round == 1) {
                resetPlayersAttributes(loggedUser);
            } else {
                if (winnerOfDuel == loggedUser) {
                    resetPlayersAttributes(rivalUser);
                } else {
                    resetPlayersAttributes(loggedUser);
                }
            }
//            playFirstTurn();

//            while (winnerOfDuel == null) {
//                playTurn();
//                turn++;
//            }
            finishRound();
            round++;

            if (numberOfRounds == 3 && round <= 3) {
                takeABreak();
            }
        }

    }

    private void takeABreak() {
        System.out.println("Here We Take A Break From Last Round And We Can Change Our Strategies !" +
                "\n (Type \"end\" to finish)");
        System.out.println("-----------------------------------------");
        String input;
        int numberOfCardInMainDeck;
        int numberOfCardInSideDeck;
        User firstUser = currentUser;

        while (true) {
            if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
                if (!currentUser.getUsername().equals(firstUser.getUsername())) break;

                currentUser = getOpponentOfCurrentUser();
                continue;
            }
            System.out.println("It's " + currentUser.getUsername() + "'s Turn");
            System.out.println("Enter Number Of Your Card in Main Deck : ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("end")) {
                if (!currentUser.getUsername().equals(firstUser.getUsername()))
                    break;

                currentUser = getOpponentOfCurrentUser();
                continue;
            }
            try {
                numberOfCardInMainDeck = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Wrong Number Format ! Try Again");
                continue;
            }
            System.out.println("Enter Number Of Your Card in Side Deck : ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("end")) {
                if (!currentUser.getUsername().equals(firstUser.getUsername()))
                    return;

                currentUser = getOpponentOfCurrentUser();
                continue;
            }
            try {
                numberOfCardInSideDeck = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Wrong Number Format ! Try Again");
                continue;
            }
            try {
                Card card = currentUser.getBoard().getDeck().getMainDeck().getCardsInMainDeck()
                        .get(numberOfCardInMainDeck);
                currentUser.getBoard().getDeck().getMainDeck().getCardsInMainDeck().set(
                        numberOfCardInMainDeck, currentUser.getBoard().getDeck().getSideDeck()
                                .getCardsInSideDeck().get(numberOfCardInSideDeck));
                currentUser.getBoard().getDeck().getSideDeck().getCardsInSideDeck().set(numberOfCardInMainDeck, card);
                System.out.println("Swapped Successfully!");
            } catch (Exception e) {
                System.out.println("A Problem Occurred While Swapping" +
                        "\n(Maybe The Number You Entered Was Out Of Deck!)\n" +
                        "PLease Try Again !");
            }
        }
    }

    private void checkWinner() {
        if (winnerOfDuel != null) {
            finishRound();
            return;
        }
        if (loggedUser.getLifePoint() <= 0) {
            winnerOfDuel = rivalUser;
            finishRound();
        } else if (rivalUser.getLifePoint() <= 0) {
            winnerOfDuel = loggedUser;
            finishRound();
        }
    }

    public void surrender() {
        winnerOfDuel = getOpponentOfCurrentUser();
        finishRound();
    }

    private void finishRound() {
        if (loggedUser.getMaxLifePoint() < loggedUser.getLifePoint()) {
            loggedUser.setMaxLifePoint(loggedUser.getLifePoint());
        }
        if (rivalUser.getMaxLifePoint() < rivalUser.getLifePoint()) {
            rivalUser.setMaxLifePoint(rivalUser.getLifePoint());
        }
        winnerOfDuel.setNumberOfWinsInGame(winnerOfDuel.getNumberOfWinsInGame() + 1);
        User loserOfDuel;
        if (winnerOfDuel == loggedUser) {
            loserOfDuel = rivalUser;
        } else {
            loserOfDuel = loggedUser;
        }
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Duel Ended",
                winnerOfDuel.getUsername() + " won the game and the score is: "
                        + winnerOfDuel.getNumberOfWinsInGame() + "-" + loserOfDuel.getNumberOfWinsInGame());
        round++;

        // starting a new duel and if game is finished finishing the game
        startADuel();
    }

    private void finishGame() {
        User winner;
        User loser;
        if (loggedUser.getNumberOfWinsInGame() > rivalUser.getNumberOfWinsInGame()) {
            winner = loggedUser;
            loser = rivalUser;
        } else {
            winner = rivalUser;
            loser = loggedUser;
        }
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Game Finished !",
                winner.getUsername() + " won the whole match with score: " + winner.getNumberOfWinsInGame()
                        + "-" + loser.getNumberOfWinsInGame());
        winner.setScore(winner.getScore() + numberOfRounds * 1000L);
        winner.setMoney(winner.getMoney() + numberOfRounds * (1000L + winner.getMaxLifePoint()));
        loser.setMoney(loser.getMoney() + numberOfRounds * 100L);

        // todo going back to duel menu
        System.out.println("todo going back to duel menu");
    }

    private void printBoard() {
        clearTheWholeScene();
        showCardsInHand(0);
        showCardsInMonsterZone();
        showCardsInSpellZone();
        arrangeRivalMonsters();
        arrangeRivalSpellTrap();
    }

    public StringBuilder boardString(StringBuilder board) {
        board.append(getOpponentOfCurrentUser().getNickName()).append(":").append(getOpponentOfCurrentUser()
                .getLifePoint()).append("\n");
        board.append("\t");
        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getCardsInHand().size(); i++) {
            board.append("c\t");
        }
        board.append("\n");
        board.append(getOpponentOfCurrentUser().getBoard().getDeckZone().size()).append("\n\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(3))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(1))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(0))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(2))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(4))).append("\n");
        board.append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(3))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(1))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(0))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(2))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(4))).append("\n");
        board.append(getOpponentOfCurrentUser().getBoard().getGraveYard().size()).append("\t\t\t\t\t\t");
        if (getOpponentOfCurrentUser().getBoard().getFieldZone() instanceof Spell) {
            board.append("O");
        } else {
            board.append("E");
        }
        board.append("\n\n--------------------------\n\n");
        if (currentUser.getBoard().getFieldZone() instanceof Spell) {
            board.append("O");
        } else {
            board.append("E");
        }
        board.append("\t\t\t\t\t\t").append(currentUser.getBoard().getGraveYard().size()).append("\n\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(4))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(2))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(0))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(1))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(3))).append("\n");
        board.append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(4))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(2))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(0))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(1))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(3))).append("\n");
        board.append("\t\t\t\t\t\t").append(currentUser.getBoard().getDeckZone().size()).append("\n");
        board.append("\t");
        for (int i = 0; i < currentUser.getBoard().getCardsInHand().size(); i++) {
            board.append("c\t");
        }
        board.append("\n");
        board.append(currentUser.getNickName()).append(":").append(currentUser.getLifePoint()).append("\n");
        return board;
    }

    private String toStringInBoard(Card card) {
        if (card == null) {
            return "E";
        }
        if (card instanceof Monster) {
            if (card.getOccupied()) {
                if (card.getAttackPosition()) {
                    return "OO";
                } else {
                    return "DO";
                }
            } else {
                if (card.getAttackPosition()) {
                    return "OH";
                } else {
                    return "DH";
                }
            }
        } else {
            if (card.getOccupied()) {
                return "O";
            } else {
                return "H";
            }
        }
    }

    public User getOpponentOfCurrentUser() {
        if (currentUser == loggedUser) {
            return rivalUser;
        } else {
            return loggedUser;
        }
    }

    static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    private void resetPlayersAttributes(User user) {
        clearTheWholeScene();
        attackedCards.clear();
        normalSummonOrSetCard = null;
        putOnMonsterZoneCards.clear();
        setPositionedCards.clear();
        specialSummonedCards.clear();
        setBoards(loggedUser, rivalUser);
        loggedUser.setLifePoint(8000);
        rivalUser.setLifePoint(8000);
        currentUser = user;
        shuffleDeckZones();
        drawPhasePlace.setFill(Color.GREEN);
        for (int i = 0; i < 6; i++) {
            drawCard(user);
        }
        showCardsInHand(0);
        winnerOfDuel = null;
        turn = 1;
        currentPhase = Phase.DRAW;
        drawPhasePlace.setFill(Color.GREEN);
        standByPhasePlace.setFill(Color.RED);
        mainPhase1Place.setFill(Color.RED);
        battlePhasePlace.setFill(Color.RED);
        mainPhase2Place.setFill(Color.RED);
        endPhasePlace.setFill(Color.RED);
    }

    private void showCardsInHand(int index) {
        try {
            currentCard1.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index));
        } catch (Exception ignored) {
            currentCard1.setRelatedCard(null);
        }
        currentCard1.fillCard(true);
        try {
            currentCard2.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index + 1));
        } catch (Exception ignored) {
            currentCard2.setRelatedCard(null);
        }
        currentCard2.fillCard(true);
        try {
            currentCard3.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index + 2));
        } catch (Exception ignored) {
            currentCard3.setRelatedCard(null);
        }
        currentCard3.fillCard(true);
        try {
            currentCard4.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index + 3));
        } catch (Exception ignored) {
            currentCard4.setRelatedCard(null);
        }
        currentCard4.fillCard(true);
        try {
            currentCard5.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index + 4));
        } catch (Exception ignored) {
            currentCard5.setRelatedCard(null);
        }
        currentCard5.fillCard(true);
        try {
            currentCard6.setRelatedCard(currentUser.getBoard().getCardsInHand().get(index + 5));
        } catch (Exception ignored) {
            currentCard6.setRelatedCard(null);
        }
        currentCard6.fillCard(true);
        try {
            currentFieldZone.setRelatedCard(currentUser.getBoard().getFieldZone());
        } catch (Exception ignored) {
            currentFieldZone.setRelatedCard(null);
        }
        currentFieldZone.fillCard(false);
    }

    private void showCardsInMonsterZone() {
        try {
            currentMonster1.setRelatedCard(currentUser.getBoard().getMonstersZone().get(0));
        } catch (Exception ignored) {
        }
        currentMonster1.fillCard(false);
        try {
            currentMonster2.setRelatedCard(currentUser.getBoard().getMonstersZone().get(1));
        } catch (Exception ignored) {
        }
        currentMonster2.fillCard(false);
        try {
            currentMonster3.setRelatedCard(currentUser.getBoard().getMonstersZone().get(2));
        } catch (Exception ignored) {
        }
        currentMonster3.fillCard(false);
        try {
            currentMonster4.setRelatedCard(currentUser.getBoard().getMonstersZone().get(3));
        } catch (Exception ignored) {
        }
        currentMonster4.fillCard(false);
        try {
            currentMonster5.setRelatedCard(currentUser.getBoard().getMonstersZone().get(4));
        } catch (Exception ignored) {
        }
        currentMonster5.fillCard(false);
    }

    private void showCardsInSpellZone() {
        try {
            currentSpell1.setRelatedCard(currentUser.getBoard().getSpellsAndTrapsZone().get(0));
        } catch (Exception ignored) {
        }
        currentSpell1.fillCard(false);
        try {
            currentSpell2.setRelatedCard(currentUser.getBoard().getSpellsAndTrapsZone().get(1));
        } catch (Exception ignored) {
        }
        currentSpell2.fillCard(false);
        try {
            currentSpell3.setRelatedCard(currentUser.getBoard().getSpellsAndTrapsZone().get(2));
        } catch (Exception ignored) {
        }
        currentSpell3.fillCard(false);
        try {
            currentSpell4.setRelatedCard(currentUser.getBoard().getSpellsAndTrapsZone().get(3));
        } catch (Exception ignored) {
        }
        currentSpell4.fillCard(false);
        try {
            currentSpell5.setRelatedCard(currentUser.getBoard().getSpellsAndTrapsZone().get(4));
        } catch (Exception ignored) {
        }
        currentSpell5.fillCard(false);
    }

    private void shuffleDeckZones() {
        Collections.shuffle(currentUser.getBoard().getDeckZone());
        Collections.shuffle(rivalUser.getBoard().getDeckZone());
    }

    private void setBoards(User user1, User user2) {
        Board board1 = new Board();
        Board board2 = new Board();

        for (Deck deck : user1.getDecks()) {
            if (deck.isActive() || deck.getValid()) {
                board1.setDeck(deck);
                break;
            }
        }
        board1.setZones();
        for (Deck deck : user2.getDecks()) {
            if (deck.isActive() || deck.getValid()) {
                board2.setDeck(deck);
                break;
            }
        }
        board2.setZones();
        user1.setBoard(board1);
        user2.setBoard(board2);
        board1.getAllCards().addAll(board1.getDeck().getMainDeck().getCardsInMainDeck());
        board1.getAllCards().addAll(board1.getDeck().getSideDeck().getCardsInSideDeck());
        board2.getAllCards().addAll(board2.getDeck().getMainDeck().getCardsInMainDeck());
        board2.getAllCards().addAll(board2.getDeck().getSideDeck().getCardsInSideDeck());
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    private void changeTurn() {
        playChangeTurnSound();
        currentUser = getOpponentOfCurrentUser();
        attackedCards.clear();
        normalSummonOrSetCard = null;
        putOnMonsterZoneCards.clear();
        setPositionedCards.clear();
        printBoard();
        turn++;
    }

    public void changeTurnInChain() {
        currentUser = getOpponentOfCurrentUser();
        printBoard();
    }

    private void arrangeRivalMonsters() {
        try {
            rivalMonster1.setRelatedCard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(0));
        } catch (Exception ignored) {
        }
        rivalMonster1.fillCard(false);
        try {
            rivalMonster2.setRelatedCard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(1));
        } catch (Exception ignored) {
        }
        rivalMonster2.fillCard(false);
        try {
            rivalMonster3.setRelatedCard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(2));
        } catch (Exception ignored) {
        }
        rivalMonster3.fillCard(false);
        try {
            rivalMonster4.setRelatedCard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(3));
        } catch (Exception ignored) {
        }
        rivalMonster4.fillCard(false);
        try {
            rivalMonster5.setRelatedCard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(4));
        } catch (Exception ignored) {
        }
        rivalMonster5.fillCard(false);
    }

    private void arrangeRivalSpellTrap() {
        try {
            rivalSpell1.setRelatedCard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(0));
        } catch (Exception ignored) {
        }
        rivalSpell1.fillCard(false);
        try {
            rivalSpell2.setRelatedCard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(1));
        } catch (Exception ignored) {
        }
        rivalSpell2.fillCard(false);
        try {
            rivalSpell3.setRelatedCard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(2));
        } catch (Exception ignored) {
        }
        rivalSpell3.fillCard(false);
        try {
            rivalSpell4.setRelatedCard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(3));
        } catch (Exception ignored) {
        }
        rivalSpell4.fillCard(false);
        try {
            rivalSpell5.setRelatedCard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(4));
        } catch (Exception ignored) {
        }
        rivalSpell5.fillCard(false);
        try {
            rivalFieldZone.setRelatedCard(getOpponentOfCurrentUser().getBoard().getFieldZone());
        } catch (Exception ignored) {
        }
        rivalFieldZone.fillCard(false);
    }

    public void drawPhaseRun() {
        currentPhase = Phase.DRAW;
        if (turn == 2) {
            for (int i = 0; i < 5; i++) {
                drawCard(currentUser);
            }
        }
        if (turn == 1) {
            showCardsInHand(0);
            return;
        }
        if (!canCurrentUserDraw()) {
            winnerOfDuel = getOpponentOfCurrentUser();
            finishRound();
            return;
        } else {
            drawCard(currentUser);
        }
        showCardsInHand(0);
    }

    private boolean canCurrentUserDraw() {
        if (currentUser.getBoard().getDeckZone().size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void drawCard(User user) {
        user.getBoard().addCardFromDeckToHand(1);
    }

    public void callStandByPhase() {
        standbyPhaseRun();
    }

    private void standbyPhaseRun() {
        currentPhase = Phase.STANDBY;
        heraldOfCreationActivation();
        if (payForMessengerOfPeace()) {
            checkWinner();
            return;
        }
        resetSupplySquads();
    }

    private void resetSupplySquads() {
        currentUser.getBoard().getActivatedSupplySquad().clear();
        for (int i = 0; i < 5; i++) {
            Card card = currentUser.getBoard().getSpellsAndTrapsZone().get(i);
            if (card != null) {
                if (card.getName().equals("Supply Squad")) {
                    if (card.getOccupied()) {
                        currentUser.getBoard().getActivatedSupplySquad().add(card);
                    }
                }
            }
            card = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i);
            if (card != null) {
                if (card.getName().equals("Supply Squad")) {
                    if (card.getOccupied()) {
                        getOpponentOfCurrentUser().getBoard().getActivatedSupplySquad().add(card);
                    }
                }
            }
        }
    }

    private boolean payForMessengerOfPeace() {// true if someone is dead and false if no one is dead
        for (int i = 0; i < currentUser.getBoard().getActivatedMessengerOfPeaces().size(); i++) {
            Card card = currentUser.getBoard().getActivatedMessengerOfPeaces().get(i);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You have Messenger of Peace");
            alert.setContentText("pay 100 LP or destroy Messenger of Peace. Do you want to pay?");
            alert.getButtonTypes().set(0, ButtonType.YES);
            alert.getButtonTypes().set(1, ButtonType.NO);
            if (alert.showAndWait().get() == ButtonType.YES) {
                currentUser.setLifePoint(currentUser.getLifePoint() - 100);
                printBoard();
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
            } else {
                addSpellOrTrapFromZoneToGraveyard(card, currentUser);
                printBoard();
            }
        }
        return false;
    }

    private void heraldOfCreationActivation() { // dar monster zone az i be badesh ro check kon ghabliash check shodan
        for (int i = 0; i < 5; i++) {
            Card card = currentUser.getBoard().getMonstersZone().get(i);
            if (card != null) {
                if (card.getName().equals("Herald of Creation")) {
                    if (card.getOccupied()) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("You have Herald of Creation");
                        alert.setContentText("do you want to activate your Herald of Creation?");
                        alert.getButtonTypes().set(0, ButtonType.YES);
                        alert.getButtonTypes().set(1, ButtonType.NO);
                        if (alert.showAndWait().get() == ButtonType.YES) {
                            activateHeraldOfCreation();
                            return;
                        } else {
                            alert.close();
                            return;
                        }
                    }
                }
            }
        }
    }
    private void activateHeraldOfCreation() { // false for stop activating and true for continue activating
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        box.getChildren().add(gridPane);
        ArrayList<CardRectangle> cardRectangles = new ArrayList<>();

        for (int i = 0; i < currentUser.getBoard().getGraveYard().size(); i++) {
            CardRectangle rectangle = new CardRectangle();
            rectangle.setWidth(90);
            rectangle.setHeight(150);
            rectangle.setFill(new ImagePattern(currentUser.getBoard().getGraveYard().get(i).getCardImage()));
            rectangle.setRelatedCard(currentUser.getBoard().getGraveYard().get(i));
            rectangle.setOnMouseClicked(event1 -> {
                if (selectedCardInGraveYard != null) selectedCardInGraveYard.setStroke(Color.TRANSPARENT);
                selectedCardInGraveYard = rectangle;
                selectedCardInGraveYard.setStroke(Color.GOLD);
            });
            cardRectangles.add(rectangle);
        }
        int z = 0;

        outer:
        for (int i = 0; ; i++) {
            for (int j = 0; j < 13; j++) {
                try {
                    gridPane.add(cardRectangles.get(z), j, i);
                    z++;
                } catch (Exception e) {
                    break outer;
                }
            }
        }

        Button close = new Button("Close");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        close.setOnMouseClicked(event1 -> {
            selectedCardInGraveYard = null;
            new FadeOutUp(box).play();
            popupStage.hide();
        });
        Button select = new Button("select");
        select.setOnMouseClicked(event2 -> {
            if (selectedCardInGraveYard != null) {
                if (selectedCardInGraveYard.getRelatedCard() instanceof Monster) {
                    if (((Monster) selectedCardInGraveYard.getRelatedCard()).getLevel() >= 7) {
                        currentUser.getBoard().getGraveYard().remove(selectedCardInGraveYard.getRelatedCard());
                        currentUser.getBoard().getCardsInHand().add(selectedCardInGraveYard.getRelatedCard());
                        printBoard();
                        new FadeOutUp(box).play();
                        popupStage.hide();
                    } else {
                        GamePlay.showAlert(Alert.AlertType.ERROR, "Error", "Wrong Card");
                    }
                } else {
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Error", "Wrong Card");
                }
            } else {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Error", "no card is selected");
            }
        });
        box.getChildren().add(close);
        box.getChildren().add(select);
        popupStage.showAndWait();
        new FadeIn(box).play();
    }

    private void mainPhaseOneRun() {
        currentPhase = Phase.MAIN_ONE;
        runMainPhase();
    }

    private void playErrorSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/error.mp3")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playAttackSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/attack.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playMyTurnSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/myTurn.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playChangeTurnSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/endTurn.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playSetSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/set.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playSummonSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/summon2.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playSurrenderSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/surrender.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playDirectAttackSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/directAttack.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playDontGiveUpSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/notGiveUp.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playTributeSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/tribute.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playDrawSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/draw.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playPopSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/pop.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playDryPopSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/dryPop.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void playSwordSound() {
        player = new MediaPlayer(new Media(Objects.requireNonNull(getClass()
                .getResource("/music/sword.wav")).toExternalForm()));
        player.setCycleCount(1);
        if (!mutePressed)
            player.play();
    }

    private void runMainPhase() {
        if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
            ((AI) currentUser).setOnBoard();
            printBoard();
        } else {
            printBoard();
            if (currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO) {
                if (activatedRitualCard != null) {
                    playErrorSound();
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Can't Execute !",
                            "You Should Ritual Summon Right Now!");
                } else return;
            }
//                if (input.equals("select -d")) {
//                    deselectCard();
//                } else if (input.startsWith("select")) {
//                    select(Regex.getMatcher(input, Regex.selectCard));
//                } else if (clickedPhase == Phase.MAIN_ONE || clickedPhase == Phase.MAIN_TWO) {
//                    if (activatedRitualCard != null) {
//                        System.out.println("you should ritual summon right now");
//                    } else {
//                        return;
//                    }
//                } else if (input.equals("summon")) {
//                    summon();
//                } else if (input.equals("set")) {
//                    set();
//                } else if (input.matches(Regex.setPositionAttackDefence)) {
//                    setPositionAttackDefense(input);
//                } else if (input.equals("flip-summon")) {
//                    flipSummon();
//                } else if (input.equals("show graveyard")) {
//                    showGraveyard();
//                } else if (input.equals("card show --selected") || input.equals("card show -s")) {
//                    showSelectedCard();
//                } else if (input.equals("surrender")) {
//                    winnerOfDuel = getOpponentOfCurrentUser();
//                    return;
//                } else if (input.matches(Regex.attack)) {
//                    if (attack(input)) {
//                        return;
//                    }
//                } else if (input.equals("attack direct")) {
//                    if (directAttack()) {
//                        return;
//                    }
//                } else if (input.equals("activate effect")) {
//                    activateEffect();
//                } else {
//                    System.out.println("invalid command");
//                }
        }

    }

    private void summon() {
        playSummonSound();
        if (selectedCard == null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error", "no card is selected yet");
            return;
        }
        if (!(selectedCard instanceof Monster) || !currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error", "you can’t summon this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Error!",
                    "action not allowed in this phase");
            return;
        }
        Monster monster = (Monster) selectedCard;
        if (activatedRitualCard != null) {
            if (monster.getCardType() == Type.RITUAL) {
                ritualSummon();
            } else {
                playPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Error!",
                        "you should ritual summon right now");
            }
            return;
        } else {
            if (monster.getCardType() == Type.RITUAL) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                        "you can’t summon this card");
                return;
            }
        }
        if (selectedCard.getName().equals("Gate Guardian")) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 3) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                        "there are not enough cards for tribute");
                return;
            } else {
                HBox box = new HBox(50);
                box.setAlignment(Pos.TOP_LEFT);
                box.setPadding(new Insets(10));
                Label label = new Label();
                label.setText("Attack or Defence?");
                box.getChildren().add(label);
                ChoiceBox<String> choiceBox = new ChoiceBox<>();
                choiceBox.getItems().addAll("Attack", "Defence");
                choiceBox.setStyle("-fx-text-fill: black");
                box.getChildren().add(choiceBox);
                Button select = new Button("select");
                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(mainStage);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                Scene question = new Scene(box, Color.TRANSPARENT);
                question.getStylesheets().add("/Css/GamePlay.css");
                popupStage.setScene(question);
                select.setOnMouseClicked(event -> {
                    try {
                        if (choiceBox.getValue().equals("Attack")) {
                            new FadeOutUp(box).play();
                            popupStage.hide();
                            doTributeSummonOrSet(3, true, true, true);
                        } else if (choiceBox.getValue().equals("Defence")) {
                            new FadeOutUp(box).play();
                            popupStage.hide();
                            doTributeSummonOrSet(3, true, true, false);
                        }
                    } catch (Exception e) {
                    }
                });
                box.getChildren().add(select);
                popupStage.show();
                new FadeIn(box).play();
                return;
            }
        }
        if (selectedCard.getName().equals("Beast King Barbaros")) {
            HBox box = new HBox(50);
            box.setAlignment(Pos.TOP_LEFT);
            box.setPadding(new Insets(10));
            Label label = new Label();
            label.setText("tribute\n0 or 2 or 3");
            box.getChildren().add(label);
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("0", "2", "3");
            choiceBox.setStyle("-fx-text-fill: black");
            box.getChildren().add(choiceBox);
            Button select = new Button("select");
            Stage popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.initOwner(mainStage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Scene question = new Scene(box, Color.TRANSPARENT);
            question.getStylesheets().add("/Css/GamePlay.css");
            popupStage.setScene(question);
            select.setOnMouseClicked(event -> {
                try {
                    if (choiceBox.getValue().equals("0")) {
                        new FadeOutUp(box).play();
                        popupStage.hide();
                        if (normalSummonOrSetCard != null) {
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                                    "you already summoned/set on this turn");
                        } else if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                                    "monster card zone is full");
                        } else {
                            ((Monster) selectedCard).setAttackPower(1900);
                            addMonsterFromHandToMonsterZone(selectedCard, true, true);
                            System.out.println("summoned successfully");
                            normalSummonOrSetCard = selectedCard;
                            selectedCard = null;
                        }
                    } else if (choiceBox.getValue().equals("2")) {
                        new FadeOutUp(box).play();
                        popupStage.hide();
                        if (normalSummonOrSetCard != null) {
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                                    "you already summoned/set on this turn");
                        } else if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                            playErrorSound();
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                                    "there are not enough cards for tribute");
                        } else {
                            doTributeSummonOrSet(2, false, true, true);
                        }
                    } else if (choiceBox.getValue().equals("3")) {
                        if (currentUser.getBoard().numberOfMonstersOnBoard() < 3) {
                            new FadeOutUp(box).play();
                            popupStage.hide();
                            playErrorSound();
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                                    "there are not enough cards for tribute");
                        } else {
                            System.out.println("\"attack\" or \"defence\"?");
                            label.setText("attack or\ndefence?");
                            choiceBox.getItems().clear();
                            choiceBox.getItems().addAll("Attack", "Defence");
                            select.setOnMouseClicked(event1 -> {
                                try {
                                    if (choiceBox.getValue().equals("Attack")) {
                                        doTributeSummonOrSet(3, true, true, true);
                                    } else if (choiceBox.getValue().equals("Defence")) {
                                        doTributeSummonOrSet(3, true, true, false);
                                    }
                                    if (choiceBox.getValue().equals("Attack") || choiceBox.getValue().equals("Defence")) {
                                        new FadeOutUp(box).play();
                                        popupStage.hide();
                                        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                                            if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                                                addMonsterFromMonsterZoneToGraveyard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i), getOpponentOfCurrentUser());
                                            }
                                        }
                                        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().size(); i++) {
                                            if (getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i) != null) {
                                                addSpellOrTrapFromZoneToGraveyard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i), getOpponentOfCurrentUser());
                                            }
                                        }
                                        if (getOpponentOfCurrentUser().getBoard().getFieldZone() != null) {
                                            Card zoneSpell = getOpponentOfCurrentUser().getBoard().getFieldZone();
                                            addMonsterFromMonsterZoneToGraveyard(zoneSpell, getOpponentOfCurrentUser());
                                        }
                                        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Beast King Barbaros effect!",
                                                "destroyed all cards that opponent control");
                                        printBoard();
                                    }
                                } catch (Exception e1) {
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                }
            });
            box.getChildren().add(select);
            popupStage.show();
            new FadeIn(box).play();
            return;
        }
        if (selectedCard.getName().equals("The Tricky")) {
            HBox box = new HBox(50);
            box.setAlignment(Pos.TOP_LEFT);
            box.setPadding(new Insets(10));
            Label label = new Label();
            label.setText("Normal or Special?");
            box.getChildren().add(label);
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("Normal", "Special");
            choiceBox.setStyle("-fx-text-fill: Black");
            box.getChildren().add(choiceBox);
            Button select = new Button("select");
            Stage popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.initOwner(mainStage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Scene question = new Scene(box, Color.TRANSPARENT);
            question.getStylesheets().add("/Css/GamePlay.css");
            popupStage.setScene(question);
            select.setOnMouseClicked(event -> {
                try {
                    if (choiceBox.getValue().equals("Normal")) {
                        new FadeOutUp(box).play();
                        popupStage.hide();
                        if (normalSummonOrSetCard != null) {
                            playErrorSound();
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                                    "you already summoned/set on this turn");
                            return;
                        } else {
                            if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                                playErrorSound();
                                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon UnSuccessful !",
                                        "there are not enough cards for tribute");
                                return;
                            } else {
                                doTributeSummonOrSet(1, false, true, true);
                                return;
                            }
                        }
                    } else if (choiceBox.getValue().equals("Special")) {
                        new FadeOutUp(box).play();
                        popupStage.hide();
                        if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                            playErrorSound();
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                                    "monster card zone is full");
                            return;
                        }
                        if (currentUser.getBoard().getCardsInHand().size() < 2) {
                            playErrorSound();
                            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon UnSuccessful !",
                                    "there are not enough cards for tribute");
                            return;
                        }
                        specialSummonTheTricky();
                        return;
                    }
                } catch (Exception e) {
                }
            });
            box.getChildren().add(select);
            popupStage.show();
            new FadeIn(box).play();
            return;
        }
        if (normalSummonOrSetCard != null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                    "you already summoned/set on this turn");
            return;
        }
        if (monster.getLevel() <= 4) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                        "monster card zone is full");
            } else {
                addMonsterFromHandToMonsterZone(selectedCard, true, true);
                if (selectedCard.getName().equals("Terratiger, the Empowered Warrior")) {
                    if (currentUser.getBoard().numberOfMonstersOnBoard() < 5) {
                        activateTerratiger();
                    }
                }
                normalSummonOrSetCard = selectedCard;
                selectedCard = null;
            }
        } else if (monster.getLevel() == 5 || monster.getLevel() == 6) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon UnSuccessful !",
                        "there are not enough cards for tribute");
            } else {
                doTributeSummonOrSet(1, false, true, true);
            }
        } else if (monster.getLevel() > 6) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon UnSuccessful !",
                        "there are not enough cards for tribute");
            } else {
                doTributeSummonOrSet(2, false, true, true);
            }
        }
    }

    private void activateTerratiger() {
        System.out.println("do you want to summon a level 4 or less monster from your hand in defence position?" +
                " (\"yes\"/\"no\")");
        while (true) {
            String answer = scanner.nextLine();
            answer = editSpaces(answer);
            if (answer.equals("yes")) {
                System.out.println("type number of the monster you want to summon");
                while (true) {
                    String number = scanner.nextLine();
                    number = editSpaces(number);
                    if (number.matches("\\d+")) {
                        int n = Integer.parseInt(number);
                        if (n > 0 && n <= currentUser.getBoard().getCardsInHand().size()) {
                            Card card = currentUser.getBoard().getCardsInHand().get(n - 1);
                            if (!(card instanceof Monster)) {
                                System.out.println("select a monster");
                            } else if (((Monster) card).getLevel() > 4) {
                                System.out.println("monster's level is more than 4");
                            } else {
                                addMonsterFromHandToMonsterZone(card, true, false);
                                System.out.println("special summon successful");
                                return;
                            }
                        } else {
                            System.out.println("type a correct number");
                        }
                    } else if (number.equals("cancel")) {
                        return;
                    } else {
                        System.out.println("type a number or cancel");
                    }
                }
            } else if (answer.equals("no")) {
                return;
            } else {
                System.out.println("type yes or no");
            }
        }
    }

    public void addMonsterFromHandToMonsterZone(Card monsterCard, Boolean isOccupied, Boolean isAttackPosition) {
        currentUser.getBoard().getCardsInHand().remove(monsterCard);
        for (int i = 0; i < currentUser.getBoard().getMonstersZone().size(); i++) {
            if (currentUser.getBoard().getMonstersZone().get(i) == null) {
                currentUser.getBoard().getMonstersZone().set(i, monsterCard);
//                ((FieldEffect) (((Spell) currentUser.getBoard().getFieldZone()).getEffect())).addCardUnderEffect(monsterCard);
                break;
            }
        }

        monsterCard.setOccupied(isOccupied);
        monsterCard.setAttackPosition(isAttackPosition);
        putOnMonsterZoneCards.add(monsterCard);

        if (isOccupied) {
            if (monsterCard.getName().equals("Command Knight")) {
                activateCommandKnight(monsterCard, currentUser);
            }
        }
    }

    private void ritualSummon() {
        System.out.println("enter number of cards in monster zone to tribute (or cancel)");
        ArrayList<Card> monstersToTribute = new ArrayList<>();
        String numbersString;
        while (true) {
            numbersString = scanner.nextLine();
            numbersString = editSpaces(numbersString);
            if (numbersString.equals("cancel")) {
                System.out.println("action canceled");
                return;
            } else if (!numbersString.matches("(\\d+ ?)+")) {
                System.out.println("enter numbers");
            } else {
                String[] numbersStringArray = numbersString.split("\\s");
                if (numbersStringArray.length > 5) {
                    System.out.println("enter at most 5 numbers");
                    continue;
                }
                for (String s : numbersStringArray) {
                    int number = Integer.parseInt(s);
                    if (number < 1 || number > 5) {
                        System.out.println("enter a correct number");
                    } else if (monstersToTribute.contains(currentUser.getBoard().getMonstersZone().get(number - 1))) {
                        System.out.println("This card is already selected");
                    } else if (currentUser.getBoard().getMonstersZone().get(number - 1) == null) {
                        System.out.println("there is no monster on this address");
                    } else {
                        monstersToTribute.add(currentUser.getBoard().getMonstersZone().get(number - 1));
                    }
                }
                int sumOfLevels = 0;
                for (Card card : monstersToTribute) {
                    sumOfLevels += ((Monster) card).getLevel();
                }
                if (sumOfLevels < ((Monster) selectedCard).getLevel()) {
                    System.out.println("selected monsters levels don’t match with ritual monster");
                    continue;
                }
                while (true) {
                    System.out.println("attack or defence (or cancel)");
                    String answer = scanner.nextLine();
                    answer = editSpaces(answer);
                    if (answer.equals("attack")) {
                        for (int i = 0; i < monstersToTribute.size(); i++) {
                            tributeMonster(monstersToTribute.get(i));
                        }
                        addMonsterFromHandToMonsterZone(selectedCard, true, true);
                        addSpellOrTrapFromZoneToGraveyard(activatedRitualCard, currentUser);
                        activatedRitualCard = null;
                        specialSummonedCards.add(selectedCard);
                        System.out.println("summoned successfully");
                        selectedCard = null;
                    } else if (answer.equals("defence")) {
                        for (int i = 0; i < monstersToTribute.size(); i++) {
                            tributeMonster(monstersToTribute.get(i));
                        }
                        addMonsterFromHandToMonsterZone(selectedCard, true, false);
                        addSpellOrTrapFromZoneToGraveyard(activatedRitualCard, currentUser);
                        activatedRitualCard = null;
                        specialSummonedCards.add(selectedCard);
                        System.out.println("summoned successfully");
                        selectedCard = null;
                    } else if (answer.equals("cancel")) {
                        System.out.println("canceled");
                        return;
                    }
                }

            }
        }
    }

    private void doTributeSummonOrSet(int tributeNumber, boolean isSpecial, boolean isSummon, boolean isAttack) {
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        Label label = new Label();
        label.setText("tribute " + tributeNumber + "\nmonsters");
        box.getChildren().add(label);
        ArrayList<CardRectangle> cardWeCanTribute = new ArrayList<>();
        ArrayList<CardRectangle> monsterZoneRectangles = new ArrayList<>();
        monsterZoneRectangles.add(currentMonster1);
        monsterZoneRectangles.add(currentMonster2);
        monsterZoneRectangles.add(currentMonster3);
        monsterZoneRectangles.add(currentMonster4);
        monsterZoneRectangles.add(currentMonster5);
        for (CardRectangle cardRectangle : monsterZoneRectangles) {
            if (cardRectangle.getRelatedCard() != null) {
                CardRectangle cardRectangle1 = new CardRectangle();
                cardRectangle1.setRelatedCard(cardRectangle.getRelatedCard());
                cardRectangle1.setFill(new ImagePattern(cardRectangle.getRelatedCard().getCardImage()));
                cardRectangle1.setWidth(90);
                cardRectangle1.setHeight(150);
                cardRectangle1.setOnMouseClicked(event1 -> {
                    if (selectedCardForTribute != null) selectedCardForTribute.setStroke(Color.TRANSPARENT);
                    selectedCardForTribute = cardRectangle1;
                    selectedCardForTribute.setStroke(Color.GOLD);
                    selectedCard = selectedCardForTribute.getRelatedCard();
                });
                box.getChildren().add(cardRectangle1);
            }
        }

        Button tribute = new Button("tribute");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        tribute.setOnMouseClicked(event1 -> {
            if (selectedCardForTribute == null) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon/Set Error", "no card is selected yet");
            } else {
                playTributeSound();
                tributeMonster(selectedCardForTribute.getRelatedCard());
                selectedCardForTribute = null;
                if (tributeNumber == 1) {
                    selectedCard = selectedRectangle.getRelatedCard();
                    if (isSummon) {
                        if (isAttack) {
                            addMonsterFromHandToMonsterZone(selectedCard, true, true);
                        } else {
                            addMonsterFromHandToMonsterZone(selectedCard, true, false);
                        }
                        playPopSound();
                        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Successful !",
                                "Summoned successfully");
                    } else {
                        addMonsterFromHandToMonsterZone(selectedCard, false, false);
                        playPopSound();
                        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Successful !",
                                "Set successfully");
                    }
                    if (!isSpecial) {
                        normalSummonOrSetCard = selectedCard;
                    } else {
                        specialSummonedCards.add(selectedCard);
                    }
                    selectedCard = null;
                    printBoard();
                    new FadeOutUp(box).play();
                    popupStage.hide();
                } else {
                    printBoard();
                    new FadeOutUp(box).play();
                    popupStage.hide();
                    doTributeSummonOrSet(tributeNumber - 1, isSpecial, isSummon, isAttack);
                }
            }
        });
        box.getChildren().add(tribute);
        popupStage.show();
        new FadeIn(box).play();
    }

    private void tributeMonster(Card monsterCard) {
        addMonsterFromMonsterZoneToGraveyard(monsterCard, currentUser);
    }

    private void specialSummonTheTricky() {
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        Label label = new Label();
        label.setText("tribute 1\ncards");
        box.getChildren().add(label);
        for (Card card : currentUser.getBoard().getCardsInHand()) {
            if (card == selectedCard) {
                continue;
            }
            CardRectangle cardRectangle = new CardRectangle();
            cardRectangle.setRelatedCard(card);
            cardRectangle.setFill(new ImagePattern(card.getCardImage()));
            cardRectangle.setWidth(90);
            cardRectangle.setHeight(150);
            cardRectangle.setOnMouseClicked(event1 -> {
                if (selectedCardForTribute != null) selectedCardForTribute.setStroke(Color.TRANSPARENT);
                selectedCardForTribute = cardRectangle;
                selectedCardForTribute.setStroke(Color.GOLD);
                selectedCard = selectedCardForTribute.getRelatedCard();
            });
            box.getChildren().add(cardRectangle);
        }

        Button tribute = new Button("tribute");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        tribute.setOnMouseClicked(event1 -> {
            if (selectedCardForTribute == null) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "tribute away error", "no card is selected yet");
            } else {
                new FadeOutUp(box).play();
                popupStage.hide();
                currentUser.getBoard().getCardsInHand().remove(selectedCard);
                currentUser.getBoard().getGraveYard().add(selectedCard);
                selectedCardForTribute = null;
                selectedCard = selectedRectangle.getRelatedCard();
                addMonsterFromHandToMonsterZone(selectedCard, true, true);
                selectedCard = null;
                selectedRectangle = null;
                printBoard();
            }
        });
        box.getChildren().add(tribute);
        popupStage.show();
        new FadeIn(box).play();
    }

    private void set() {
        playSetSound();
        if (activatedRitualCard != null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !", "you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !", "no card is selected yet");
            return;
        }
        if (!currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                    "you can’t set this card");
            return;
        }
        if (selectedCard.getName().equals("Gate Guardian")) {
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                    "you can’t set this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                    "action not allowed in this phase");
            return;
        }
        if (selectedCard instanceof Monster) {
            Monster monster = (Monster) selectedCard;

            if (normalSummonOrSetCard != null) {
                playPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                        "you already summoned/set on this turn");
                return;
            }
            if (monster.getLevel() <= 4) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                    playPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                            "monster card zone is full");
                } else {
                    addMonsterFromHandToMonsterZone(selectedCard, false, false);
                    normalSummonOrSetCard = selectedCard;
                    selectedCard = null;
                }
            } else if (monster.getLevel() == 5 || monster.getLevel() == 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                    playErrorSound();
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                            "there are not enough cards for tribute");
                } else {
                    doTributeSummonOrSet(1, false, false, false);
                }
            } else if (monster.getLevel() > 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                    playErrorSound();
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                            "there are not enough cards for tribute");
                } else {
                    doTributeSummonOrSet(2, false, false, false);
                }
            }
        } else {
            if (currentUser.getBoard().numberOfSpellAndTrapsOnBoard() == 5) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                        "Spell Card Zone Is Full");
                return;
            }
            addSpellOrTrapFromHandToZone(selectedCard, false);
            selectedCard = null;
        }
    }

    public void addSpellOrTrapFromHandToZone(Card spellOrTrap, boolean isOccupied) {
        currentUser.getBoard().getCardsInHand().remove(spellOrTrap);

        if (spellOrTrap.getCardType() == Type.FIELD) {
            if (currentUser.getBoard().getFieldZone() != null) {
                addSpellOrTrapFromZoneToGraveyard(currentUser.getBoard().getFieldZone(), currentUser);
            }
            currentUser.getBoard().setFieldZone(spellOrTrap);
            spellOrTrap.setOccupied(isOccupied);
            putOnSpellTrapZoneCards.add(spellOrTrap);
            return;
        }

        for (int i = 0; i < currentUser.getBoard().getSpellsAndTrapsZone().size(); i++) {
            if (currentUser.getBoard().getSpellsAndTrapsZone().get(i) == null) {
                currentUser.getBoard().getSpellsAndTrapsZone().set(i, spellOrTrap);
                break;
            }
        }
        spellOrTrap.setOccupied(isOccupied);
        putOnSpellTrapZoneCards.add(spellOrTrap);
    }

    private void showChangePositionPopUp() {
        VBox box = new VBox(40);

        box.getChildren().add(new Label("Select Your" +
                "\n Choice !"));
        box.setStyle("-fx-background-color: rgba(255,0,0,0.8);");
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(40));

        Rectangle changeToAttack = new Rectangle(80, 80, new ImagePattern(new Image("/images/Icons/" +
                "changeToAttack.png")));
        Rectangle changeToDefense = new Rectangle(80, 80, new ImagePattern(new Image("/images/Icons/" +
                "changeToDefense.png")));
        box.getChildren().add(changeToAttack);
        box.getChildren().add(changeToDefense);
        Button cancel = new Button("Cancel");
        box.getChildren().add(cancel);
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        popupStage.show();
        new BounceInLeft(box).play();

        cancel.setOnAction(event -> {
            BounceOutRight right = new BounceOutRight(box);
            right.setOnFinished(eventt -> {
                popupStage.hide();
            });
            right.play();
            mainStage.show();
        });
        changeToAttack.setOnMouseClicked(event -> {
            popupStage.hide();
            mainStage.show();
            setPositionAttackDefense("attack");
            printBoard();
        });
        changeToDefense.setOnMouseClicked(event -> {
            popupStage.hide();
            mainStage.show();
            setPositionAttackDefense("defense");
            printBoard();
        });
    }

    private void setPositionAttackDefense(String input) {
        if (changePosition()) return;
        if (!selectedCard.getOccupied()) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "you can’t change this card position");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "action not allowed in this phase");
            return;
        }
        if ((selectedCard.getAttackPosition() && input.equals("attack"))
                || (!selectedCard.getAttackPosition() && input.equals("defense"))) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "this card is already in the wanted position");
            return;
        }
        if (setPositionedCards.contains(selectedCard)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "you already changed this card position in this turn");
            return;
        }
        selectedCard.setAttackPosition(input.equals("attack"));
        playPopSound();
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Changed Successfully",
                "monster card position changed successfully");
        setPositionedCards.add(selectedCard);
        selectedCard = null;
    }

    private boolean changePosition() {
        if (activatedRitualCard != null) {
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you should ritual summon right now");
            return true;
        }
        if (selectedCard == null) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "no card is selected yet");
            return true;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you can’t change this card position");
            return true;
        }
        return false;
    }

    private void flipSummon() {

        if (activatedRitualCard != null) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "no card is selected yet");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        if (!(selectedCard instanceof Monster)) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you can’t flip summon this card");
            return;
        }
        if (!(!selectedCard.getAttackPosition() && !selectedCard.getOccupied())
                || putOnMonsterZoneCards.contains(selectedCard)) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you can’t flip summon this card");
            return;
        }
        selectedCard.setAttackPosition(true);
        selectedCard.setOccupied(true);
        if (selectedCard.getName().equals("Command Knight")) {
            activateCommandKnight(selectedCard, currentUser);
        }
        playDryPopSound();
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Changed Successfully",
                "flip summoned successfully");
        selectedCard = null;
    }

    private void activateCommandKnight(Card commandKnight, User owner) {
        ArrayList<Card> monsterCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (owner.getBoard().getMonstersZone().get(i) == commandKnight) {
                continue;
            }
            Monster monster = (Monster) owner.getBoard().getMonstersZone().get(i);
            monster.setAttackPower(monster.getAttackPower() + 400);
            monsterCards.add(monster);
        }
        owner.getBoard().getCommandKnights().put(commandKnight, monsterCards);
    }

    private void battlePhaseRun() {
        currentPhase = Phase.BATTLE;
        if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
            attack();
        } else {
            printBoard();
            if (currentPhase == Phase.BATTLE)
                return;
        }
    }

    private boolean attack() { // return true if duel has winner and false if duel does not have winner
        if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
            ArrayList<Card> cards = ((AI) currentUser).attack(getOpponentOfCurrentUser().getBoard());
            if (cards == null) return false;
            return doAttackAction(cards.get(1), (Monster) cards.get(0));
        } else {
            if (doAttack()) return false;
            if (selectedCardFromEnemy == null) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error !",
                        "No Enemy Card is Selected Yet ..!");
                return false;
            }
            Card enemyCard = selectedCardFromEnemy.getRelatedCard();
            Monster selectedMonster = (Monster) selectedCard;
            if (enemyCard == null) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error Occurred!",
                        "there is no card to attack here");
                return false;
            }
            if (getOpponentOfCurrentUser().getBoard().getActivatedMessengerOfPeaces().size() != 0) {
                if (((Monster) selectedCard).getAttackPower() >= 1500) {
                    playDryPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Something Happened While Attacking!",
                            "Messenger of Peace does not let you attack with this card");
                    return false;
                }
            }
            if (enemyCard.getName().equals("Command Knight") && getOpponentOfCurrentUser().getBoard()
                    .numberOfMonstersOnBoard() - getOpponentOfCurrentUser().getBoard().getCommandKnights().size() > 0) {
                if (enemyCard.getOccupied()) {
                    playErrorSound();
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error Occurred!",
                            "you can't attack this card yet");
                    return false;
                }
            }
            new ChainController(this, scanner).run();
            if (magicCylinderActivated) {
                setMagicCylinderActivated(false);
                playDryPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Something Happened While Attacking!",
                        "Magic Cylinder stopped the attack and attacker took " +
                                selectedMonster.getAttackPower() + "damage");
                currentUser.setLifePoint(currentUser.getLifePoint() - selectedMonster.getAttackPower());
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                } else {
                    return false;
                }
            }
            if (negateAttackActivated) {
                setNegateAttackActivated(false);
                playDryPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Something Happened While Attacking!",
                        "Negate Attack stopped the attack");
                return false;
            }
            if (mirrorForceActivated) {
                setMirrorForceActivated(false);
                playDryPopSound();
                GamePlay.showAlert(Alert.AlertType.WARNING, "Something Happened While Attacking!",
                        "Mirror Force stopped the attack and destroyed all attackers" +
                                " attack positioned monsters");
                return false;
            }
            playAttackSound();
            return doAttackAction(enemyCard, selectedMonster);
        }
    }

    public boolean doAttackAction(Card enemyCard, Monster selectedMonster) {
        Monster enemyMonster = (Monster) enemyCard;
        if (enemyMonster.getName().equals("Texchanger")) {
            // will do
        }
        if (enemyMonster.getName().equals("Marshmallon")) {
            return attackMarshmallon(selectedMonster, enemyMonster);
        }
        if (enemyMonster.getName().equals("Exploder Dragon")) {
            return attackExploderDragon(selectedMonster, enemyMonster);
        }
        if (enemyMonster.getName().equals("The Calculator")) {
            int attackPower = 0;
            for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i).getOccupied()) {
                        attackPower += ((Monster) getOpponentOfCurrentUser().getBoard().getMonstersZone()
                                .get(i)).getLevel();
                    }
                }
            }
            attackPower *= 300;
            enemyMonster.setAttackPower(attackPower);
        }
        if (enemyCard.getName().equalsIgnoreCase("suijin")) {
            if (!getOpponentOfCurrentUser().getBoard().getSuijinCards().contains(enemyMonster)) {
                suijinEffects(enemyMonster, selectedMonster);
            }
        }
        // before fight starts

        if (enemyCard.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > enemyMonster.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getAttackPower());
                playDryPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                        "your opponent’s monster is destroyed and your opponent receives " + damage
                                + " battle damage");
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    playDryPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "Yomi Ship destroyed your monster");
                } else {
                    attackedCards.add(selectedMonster);
                }
                getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
                if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
                    winnerOfDuel = currentUser;
                    return true;
                }
                return false;
            } else if (selectedMonster.getAttackPower() == enemyMonster.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                playDryPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                        "both you and your opponent monster cards are destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getAttackPower());
                playPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                        "Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy deffend position
            if (selectedMonster.getAttackPower() > enemyMonster.getDefencePower()) {
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                if (enemyMonster.getOccupied()) {
                    playPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "the defense position monster is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    playPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "opponent’s monster card was " + enemyMonster.getName()
                                    + " and the defense position monster is destroyed");
                }
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    playDryPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "Yomi Ship destroyed your monster");
                } else {
                    attackedCards.add(selectedMonster);
                }
                return false;
            } else if (selectedMonster.getAttackPower() == enemyMonster.getDefencePower()) {
                if (enemyMonster.getOccupied()) {
                    playPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "no card is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    playDryPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "opponent’s monster card was " + enemyMonster.getName() +
                                    " and no card is destroyed");
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getDefencePower());

                if (enemyMonster.getOccupied()) {
                    playDryPopSound();
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "no card is destroyed and you received " + damage + " battle damage");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Attack Information!",
                            "opponent’s monster card was " + enemyMonster.getName()
                                    + " and no card is destroyed and you received " + damage + " battle damage");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }

    private void suijinEffects(Monster enemyMonster, Monster selectedMonster) {
        System.out.println("(Asking From " + getOpponentOfCurrentUser() + " ) :");
        System.out.println("Do You Want To Enable Effect Of Your Suiji ?");
        String string;
        while (true) {
            string = scanner.nextLine().trim();
            if (string.equalsIgnoreCase("yes")) {
                activateSuijin(enemyMonster, selectedMonster);
                break;
            } else if (string.equalsIgnoreCase("no")) {
                System.out.println("Effect Doesn't Activated ...!");
                break;
            } else {
                System.out.println("Wrong Commnad ! Try Again With \"Yes\" or \"No\"");
            }
        }

    }

    private void activateSuijin(Monster enemyMonster, Monster selectedMonster) {
        temporaryValue = selectedMonster.getAttackPower();
        selectedMonster.setAttackPower(0);
        getOpponentOfCurrentUser().getBoard().getSuijinCards().add(enemyMonster);
        isSuijin = true;
    }


    private boolean attackExploderDragon(Monster selectedMonster, Monster ExploderDragon) {
        if (ExploderDragon.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > ExploderDragon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                System.out.println("your opponent’s monster is destroyed and your opponent receives " + 0
                        + " battle damage");
                System.out.println("Exploder Dragon destroyed your monster");
                return false;
            } else if (selectedMonster.getAttackPower() == ExploderDragon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - ExploderDragon.getAttackPower());
                System.out.println("Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy deffend position
            if (selectedMonster.getAttackPower() > ExploderDragon.getDefencePower()) {
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                if (ExploderDragon.getOccupied()) {
                    System.out.println("the defense position monster is destroyed");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName()
                            + " and the defense position monster is destroyed");
                }
                System.out.println("Exploder Dragon destroyed your monster");
                return false;
            } else if (selectedMonster.getAttackPower() == ExploderDragon.getDefencePower()) {
                if (ExploderDragon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName()
                            + " and no card is destroyed");
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - ExploderDragon.getDefencePower());

                if (ExploderDragon.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName()
                            + " and no card is destroyed and you received " + damage + " battle damage");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }

    private boolean attackMarshmallon(Monster selectedMonster, Monster Marshmallon) {
        if (Marshmallon.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > Marshmallon.getAttackPower()) {
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getAttackPower());
                System.out.println("no card is destroyed and your opponent receives " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
                if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
                    winnerOfDuel = currentUser;
                    return true;
                }
                return false;
            } else if (selectedMonster.getAttackPower() == Marshmallon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                System.out.println("Your monster card is destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getAttackPower());
                System.out.println("Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy defend position
            if (selectedMonster.getAttackPower() > Marshmallon.getDefencePower()) {
                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName()
                            + " and no card is destroyed");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                attackedCards.add(selectedMonster);
                return false;
            } else if (selectedMonster.getAttackPower() == Marshmallon.getDefencePower()) {
                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName()
                            + " and no card is destroyed");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getDefencePower());

                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName()
                            + " and no card is destroyed and you received " + damage + " battle damage");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }

    public void addMonsterFromMonsterZoneToGraveyard(Card monsterCard, User owner) {
        for (int i = 0; i < owner.getBoard().getMonstersZone().size(); i++) {
            if (owner.getBoard().getMonstersZone().get(i) == monsterCard) {
                owner.getBoard().getMonstersZone().set(i, null);
                break;
            }
        }
        owner.getBoard().getGraveYard().add(monsterCard);
        destroyCard(monsterCard);
        if (owner.getBoard().getActivatedSupplySquad().size() != 0) {
            if (owner.getBoard().getDeckZone().size() > 0) {
                drawCard(owner);
            }
        }
        printBoard();
    }

    public void addSpellOrTrapFromZoneToGraveyard(Card spellTrapCard, User owner) {
        if (spellTrapCard.getCardType() == Type.FIELD) {
            owner.getBoard().setFieldZone(null);
            owner.getBoard().getGraveYard().add(spellTrapCard);
            destroyCard(spellTrapCard);
            if (owner.getBoard().getActivatedSupplySquad().size() != 0) {
                if (owner.getBoard().getDeckZone().size() > 0) {
                    drawCard(owner);
                }
            }
            return;
        }
        for (int i = 0; i < owner.getBoard().getSpellsAndTrapsZone().size(); i++) {
            if (owner.getBoard().getSpellsAndTrapsZone().get(i) == spellTrapCard) {
                owner.getBoard().getSpellsAndTrapsZone().set(i, null);
                break;
            }
        }
        owner.getBoard().getGraveYard().add(spellTrapCard);
        destroyCard(spellTrapCard);
        if (owner.getBoard().getActivatedSupplySquad().size() != 0) {
            if (owner.getBoard().getDeckZone().size() > 0) {
                drawCard(owner);
            }
        }
    }

    private boolean directAttack() { // returns true if duel has a winner and false if the duel has no winner
        playDirectAttackSound();
        if (doAttack()) return false;
        if (getOpponentOfCurrentUser().getBoard().numberOfMonstersOnBoard() > 0) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "directAttack Error",
                    "you can’t attack the opponent directly");
            return false;
        }
        if (getOpponentOfCurrentUser().getBoard().getActivatedMessengerOfPeaces().size() != 0) {
            if (((Monster) selectedCard).getAttackPower() >= 1500) {
                playPopSound();
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "directAttack Error",
                        "Messenger of Peace does not let you attack wit this card");
                return false;
            }
        }
        Monster selectedMonster = (Monster) selectedCard;
        new ChainController(this, scanner).run();
        if (magicCylinderActivated) {
            setMagicCylinderActivated(false);
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "directAttack Error",
                    "Magic Cylinder stopped the attack and attacker took " +
                            selectedMonster.getAttackPower() + "damage");
            currentUser.setLifePoint(currentUser.getLifePoint() - selectedMonster.getAttackPower());
            if (currentUser.getLifePoint() <= 0) {
                winnerOfDuel = getOpponentOfCurrentUser();
                return true;
            } else {
                return false;
            }
        }
        if (negateAttackActivated) {
            setNegateAttackActivated(false);
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "directAttack Error",
                    "Negate Attack stopped the attack");
            return false;
        }
        if (mirrorForceActivated) {
            setMirrorForceActivated(false);
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "directAttack Error",
                    "Mirror Force stopped the attack and destroyed all attackers attack positioned monsters");
            return false;
        }

        // before attack starts

        attackedCards.add(selectedCard);
        int damage = ((Monster) selectedCard).getAttackPower();
        getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
        playDryPopSound();
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "directAttack Error",
                "you opponent receives " + damage + " battle damage");
        if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
            winnerOfDuel = currentUser;
            return true;
        } else {
            return false;
        }
    }

    private boolean doAttack() {
        if (selectedCard == null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error", "no card is selected yet");
            return true;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error", "you can’t attack with this card");
            return true;
        }
        if (!(currentPhase == Phase.BATTLE)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error", "you can’t do this action in this phase");
            return true;
        }
        if (attackedCards.contains(selectedCard)) {
            playPopSound();
            GamePlay.showAlert(Alert.AlertType.WARNING, "Attack Error", "this card already attacked");
            return true;
        }
        if (!selectedCard.getAttackPosition()) {
            playDryPopSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "Attack Error", "you can’t attack with this card");
            return true;
        }
        return false;
    }

    private void mainPhaseTwoRun() {
        currentPhase = Phase.MAIN_TWO;
        runMainPhase();
    }

    private void activateEffect() {
        if (activatedRitualCard != null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error!",
                    "you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "no card is selected yet");
            return;
        }
        if (!(selectedCard instanceof Spell)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "activate effect is only for spell cards.");
            return;
        }
        if (currentPhase != Phase.MAIN_ONE && currentPhase != Phase.MAIN_TWO) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "you can’t activate an effect in this phase");
            return;
        }
        if (currentUser.getBoard().getSpellsAndTrapsZone().contains(selectedCard) && selectedCard.getOccupied()) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "you have already activated this card");
            return;
        }
        if (selectedCard.getCardType() == Type.FIELD && currentUser.getBoard().getFieldZone()
                == selectedCard && selectedCard.getOccupied()) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "you have already activated this card");
            return;
        }
        if (selectedCard.getCardType() != Type.FIELD && currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            if (currentUser.getBoard().numberOfSpellAndTrapsOnBoard() == 5) {
                playErrorSound();
                GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "spell card zone is full");
                return;
            }
        }
        if (!currentUser.getBoard().getAllCards().contains(selectedCard)) {
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "This card is not yours");
            return;
        }
        ((Spell) selectedCard).giveEffect();

        if (!((Spell) selectedCard).getEffect().canBeActivated(this)) {
            System.out.println("MMMMMMMMMMMMMMMMMMMMM");
            playErrorSound();
            GamePlay.showAlert(Alert.AlertType.ERROR, "activate effect Error", "preparations of this spell are not done yet");
            return;
        }
        if (selectedCard.getCardType() == Type.FIELD) {
            if (currentUser.getBoard().getFieldZone() != null) {
                addSpellOrTrapFromZoneToGraveyard(currentUser.getBoard().getFieldZone(), currentUser);
            }
            currentUser.getBoard().setFieldZone(selectedCard);
        }

        ((Spell) selectedCard).getEffect().addToChain(this);
        ((Spell) chain.get(0)).getEffect().finalActivate(this);
        // activating the spells and chain
//        new ChainController(this, scanner).run();
    }

    private void endPhaseRun() {
        int number = currentUser.getBoard().getCardsInHand().size();
        if (number > 6) {
            number -= 6;
            throwAwayExtraCards(number);
        }
        currentPhase = Phase.END;
    }

    private void throwAwayExtraCards(int numberOfCards) {
        HBox box = new HBox(50);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPadding(new Insets(10));
        Label label = new Label();
        label.setText("throw " + numberOfCards + "\ncards");
        box.getChildren().add(label);
        ArrayList<CardRectangle> cardsInHand = new ArrayList<>();
        for (Card card : currentUser.getBoard().getCardsInHand()) {
            CardRectangle cardRectangle = new CardRectangle();
            cardRectangle.setRelatedCard(card);
            cardRectangle.setFill(new ImagePattern(card.getCardImage()));
            cardRectangle.setWidth(90);
            cardRectangle.setHeight(150);
            cardRectangle.setOnMouseClicked(event1 -> {
                if (selectedCardToThrowAway != null) selectedCardToThrowAway.setStroke(Color.TRANSPARENT);
                selectedCardToThrowAway = cardRectangle;
                selectedCardToThrowAway.setStroke(Color.GOLD);
                selectedCard = selectedCardToThrowAway.getRelatedCard();
            });
            box.getChildren().add(cardRectangle);
        }

        Button tribute = new Button("throw");
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(mainStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene question = new Scene(box, Color.TRANSPARENT);
        question.getStylesheets().add("/Css/GamePlay.css");
        popupStage.setScene(question);
        tribute.setOnMouseClicked(event1 -> {
            if (selectedCardToThrowAway == null) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "throw away error", "no card is selected yet");
            } else {
                currentUser.getBoard().getCardsInHand().remove(selectedCard);
                currentUser.getBoard().getGraveYard().add(selectedCard);
                selectedCardToThrowAway = null;
                selectedCard = null;
                printBoard();
                if (numberOfCards == 1) {
                    new FadeOutUp(box).play();
                    popupStage.hide();
                } else {
                    new FadeOutUp(box).play();
                    popupStage.hide();
                    throwAwayExtraCards(numberOfCards - 1);
                }
            }
        });
        box.getChildren().add(tribute);
        popupStage.show();
        new FadeIn(box).play();
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void destroyCard(Card card) {
        User owner;
        if (loggedUser.getBoard().getAllCards().contains(card)) {
            owner = loggedUser;
        } else {
            owner = rivalUser;
        }
        if (card instanceof Monster) {
            if (owner.getBoard().getSpellMonsterEquip().containsValue(card)) {
                for (Card card1 : owner.getBoard().getSpellMonsterEquip().keySet()) {
                    if (owner.getBoard().getSpellMonsterEquip().get(card1) == card) {
                        addSpellOrTrapFromZoneToGraveyard(card1, owner);
                        owner.getBoard().getSpellMonsterEquip().remove(card1);
                    }
                }
            }
            for (Card card1 : Shop.getAllCards()) {
                if (card.getName().equals(card1.getName())) {
                    ((Monster) card).setAttackPower(((Monster) card1).getAttackPower());
                    ((Monster) card).setDefencePower(((Monster) card1).getDefencePower());
                    break;
                }
            }
            if (owner.getBoard().getFieldZone() != null) {
                ((FieldEffect) ((Spell) owner.getBoard().getFieldZone()).getEffect()).getEffectedMonsterCards().remove(card);
            }
            if (card.getName().equals("Command Knight")) {
                if (owner.getBoard().getCommandKnights().containsKey(card)) {
                    ArrayList<Card> cardsToRemoveEffect = owner.getBoard().getCommandKnights().get(card);
                    for (int i = 0; i < cardsToRemoveEffect.size(); i++) {
                        ((Monster) cardsToRemoveEffect.get(i)).setAttackPower(((Monster) cardsToRemoveEffect.get(i)).getAttackPower() - 400);
                    }
                    owner.getBoard().getCommandKnights().remove(card);
                }
            }

        } else if (card instanceof Spell) {
            // destroy in chain
            Spell spell = (Spell) card;
            if (spell.getCardType() == Type.FIELD) {
                ((FieldEffect) spell.getEffect()).deActive();
                return;
            }
            if (owner.getBoard().getSpellMonsterEquip().containsKey(card)) {
                ((EquipEffect) ((Spell) card).getEffect()).deActive();
                owner.getBoard().getSpellMonsterEquip().remove(card);
            }
            if (card.getName().equals("Messenger of Peace")) {
                owner.getBoard().getActivatedMessengerOfPeaces().remove(card);
            } else if (card.getName().equals("SpellAbsorption")) {
                owner.getBoard().getActivatedSpellAbsorptions().remove(card);
            } else if (card.getName().equals("Supply Squad")) {
                owner.getBoard().getActivatedSupplySquad().remove(card);
            }

        } else if (card instanceof Trap) {
            // destroy in chain
        }
    }

    public boolean processText(String text) {
        Matcher matcher;
        if ((matcher = Regex.getMatcher(text, Regex.increaseMoney)).find()) {
            Integer amount = Integer.parseInt(matcher.group(2));
            getCurrentUser().setMoney(currentUser.getMoney() + amount);
            return true;
        }
        if ((matcher = Regex.getMatcher(text, Regex.increaseLife)).find()) {
            Integer amount = Integer.parseInt(matcher.group(2));
            getCurrentUser().setLifePoint(currentUser.getLifePoint() + amount);
            return true;
        }
        if ((matcher = Regex.getMatcher(text, Regex.setWinnerCheat)).find()) {
            String nickname = matcher.group(1);
            if (User.getUserByNickname(nickname) == null) return false;
            winnerOfDuel = User.getUserByNickname(nickname);
            return true;
        }
        return false;
    }

    @FXML
    public void nextPhase() {
        if (currentPhase == null) {
            initialiseLabelNames();
            currentPhase = Phase.DRAW;
            drawPhaseRun();
            drawPhasePlace.setFill(Color.GREEN);
        }
        if (currentPhase == Phase.DRAW) {
            playDrawSound();
            currentPhase = Phase.STANDBY;
            standByPhasePlace.setFill(Color.GREEN);
            standbyPhaseRun();
            drawPhasePlace.setFill(Color.RED);
        } else if (currentPhase == Phase.STANDBY) {
            playMyTurnSound();
            currentPhase = Phase.MAIN_ONE;
            mainPhase1Place.setFill(Color.GREEN);
            mainPhaseOneRun();
            standByPhasePlace.setFill(Color.RED);
        } else if (currentPhase == Phase.MAIN_ONE) {
            if (turn == 1) {
                currentPhase = Phase.END;
                endPhasePlace.setFill(Color.GREEN);
                endPhaseRun();
                mainPhase1Place.setFill(Color.RED);
                return;
            }
            currentPhase = Phase.BATTLE;
            battlePhasePlace.setFill(Color.GREEN);
            battlePhaseRun();
            mainPhase1Place.setFill(Color.RED);
        } else if (currentPhase == Phase.BATTLE) {
            currentPhase = Phase.MAIN_TWO;
            mainPhase2Place.setFill(Color.GREEN);
            mainPhaseTwoRun();
            battlePhasePlace.setFill(Color.RED);
        } else if (currentPhase == Phase.MAIN_TWO) {
            currentPhase = Phase.END;
            endPhasePlace.setFill(Color.GREEN);
            endPhaseRun();
            mainPhase2Place.setFill(Color.RED);
        } else {
            changeTurn();
            initialiseLabelNames();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Turn Changed!",
                    "its " + currentUser.getNickName() + "’s turn");
            currentPhase = Phase.DRAW;
            drawPhasePlace.setFill(Color.GREEN);
            endPhasePlace.setFill(Color.RED);
            drawPhaseRun();
        }
    }
}
