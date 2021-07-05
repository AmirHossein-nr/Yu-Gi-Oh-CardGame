package Controller;

import Model.*;
import Model.Effects.Equipe.EquipEffect;
import Model.Effects.Field.FieldEffect;
import View.GUI.GamePlay;
import View.Menu.Shop;
import animatefx.animation.*;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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

    public static Stage mainStage;

    private Phase clickedPhase = null;
    Scanner scanner;
    boolean playingWithAi = false;
    User loggedUser;
    User rivalUser;
    User currentUser;
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
    boolean canSpeedOneBeActivated = false;
    boolean timeSealActivated = false;
    boolean declaredAttack = false;
    boolean magicCylinderActivated = false;
    boolean negateAttackActivated = false;
    boolean mirrorForceActivated = false;
    boolean isSuijin = false;
    private Timeline timeline = new Timeline();

    @FXML
    public void initialize() {
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
        setAttack.setFill(new ImagePattern(new Image("/images/Icons/setAttack.png")));
        setAttack.setOnMouseClicked(event -> {
            set();
            printBoard();
        });
        changePosition.setFill(new ImagePattern(new Image("/images/Icons/changePosition.png")));
        changePosition.setOnMouseClicked(event -> {
            showChangePositionPopUp();
        });
        pauseButton.setOnMouseClicked(event -> GamePlay.pauseButtonExecution());
        clearTheWholeScene();
        initialiseAnimationsOfSelectCard();
        mouseDragControlling();
        onMouseHoverForCardsOnBoard();
        onMouseClickedForCardsInHand();
        onMouseClickedForCardsInDeck();
        new FadeInDown(rivalAvatar).play();
        new FadeInUp(currentAvatar).play();
        nextPhaseButton.setOnMouseClicked(event -> nextPhase());
        drawPhasePlace.setFill(Color.GREEN);
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
        mouseHoverControl(currentMonster1);
        mouseHoverControl(currentMonster2);
        mouseHoverControl(currentMonster3);
        mouseHoverControl(currentMonster4);
        mouseHoverControl(currentMonster5);
        mouseHoverControl(rivalMonster1);
        mouseHoverControl(rivalMonster2);
        mouseHoverControl(rivalMonster3);
        mouseHoverControl(rivalMonster4);
        mouseHoverControl(rivalMonster5);
        mouseHoverControl(currentSpell1);
        mouseHoverControl(currentSpell2);
        mouseHoverControl(currentSpell3);
        mouseHoverControl(currentSpell4);
        mouseHoverControl(currentSpell5);
        mouseHoverControl(rivalSpell1);
        mouseHoverControl(rivalSpell2);
        mouseHoverControl(rivalSpell3);
        mouseHoverControl(rivalSpell4);
        mouseHoverControl(rivalSpell5);
    }

    private void mouseHoverControl(Rectangle rectangle) {
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
        rivalUsername.setText(getOpponentOfCurrentUser().getUsername());
        rivalFullName.setText(getOpponentOfCurrentUser().getNickName());
        currentUsername.setText(currentUser.getUsername());
        currentFullName.setText(currentUser.getNickName());
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
        // todo set cards null if needed
        currentCard1.setFill(Color.TRANSPARENT);
        currentCard2.setFill(Color.TRANSPARENT);
        currentCard3.setFill(Color.TRANSPARENT);
        currentCard4.setFill(Color.TRANSPARENT);
        currentCard5.setFill(Color.TRANSPARENT);
        currentCard6.setFill(Color.TRANSPARENT);
        currentSpell1.setFill(Color.TRANSPARENT);
        currentSpell2.setFill(Color.TRANSPARENT);
        currentSpell3.setFill(Color.TRANSPARENT);
        currentSpell4.setFill(Color.TRANSPARENT);
        currentSpell5.setFill(Color.TRANSPARENT);
        currentMonster1.setFill(Color.TRANSPARENT);
        currentMonster2.setFill(Color.TRANSPARENT);
        currentMonster3.setFill(Color.TRANSPARENT);
        currentMonster4.setFill(Color.TRANSPARENT);
        currentMonster5.setFill(Color.TRANSPARENT);
        rivalSpell1.setFill(Color.TRANSPARENT);
        rivalSpell2.setFill(Color.TRANSPARENT);
        rivalSpell3.setFill(Color.TRANSPARENT);
        rivalSpell4.setFill(Color.TRANSPARENT);
        rivalSpell5.setFill(Color.TRANSPARENT);
        rivalMonster1.setFill(Color.TRANSPARENT);
        rivalMonster2.setFill(Color.TRANSPARENT);
        rivalMonster3.setFill(Color.TRANSPARENT);
        rivalMonster4.setFill(Color.TRANSPARENT);
        rivalMonster5.setFill(Color.TRANSPARENT);
    }

    public void test() {
        User user1 = new User("amirhossein", "12345", "AmirHNR");
        User user2 = new User("mammad", "1234", "Mamali");
        Deck deck = new Deck(new MainDeck(true), new SideDeck(true));
        new Shop(null);
        for (int i = 0; i < 41; i++) {
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
        this.numberOfRounds = 1;
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
        System.out.println(winnerOfDuel.getUsername() + " won the game and the score is: "
                + winnerOfDuel.getNumberOfWinsInGame() + "-" + loserOfDuel.getNumberOfWinsInGame());
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
        System.out.println(winner.getUsername() + " won the whole match with score: " + winner.getNumberOfWinsInGame()
                + "-" + loser.getNumberOfWinsInGame());
        winner.setScore(winner.getScore() + numberOfRounds * 1000L);
        winner.setMoney(winner.getMoney() + numberOfRounds * (1000L + winner.getMaxLifePoint()));
        loser.setMoney(loser.getMoney() + numberOfRounds * 100L);
    }

    public void playTurn() {

        if (clickedPhase == Phase.DRAW)
            drawPhaseRun();
        if (winnerOfDuel != null)
            return;
        if (clickedPhase == Phase.STANDBY)
            standbyPhaseRun();
        if (winnerOfDuel != null)
            return;
        if (clickedPhase == Phase.MAIN_ONE)
            mainPhaseOneRun();
        if (winnerOfDuel != null)
            return;

        if (clickedPhase == Phase.BATTLE)
            battlePhaseRun();
        if (winnerOfDuel != null)
            return;

        if (clickedPhase == Phase.MAIN_TWO)
            mainPhaseTwoRun();
        if (winnerOfDuel != null)
            return;

        if (clickedPhase == Phase.END)
            endPhaseRun();
    }

    public void playFirstTurn() {
        printBoard();
        standbyPhaseRun();
        mainPhaseOneRun();
        endPhaseRun();
    }


    private void printBoard() {
        clearTheWholeScene();
        showCardsInHand();
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
        clickedPhase = Phase.DRAW;
        drawPhasePlace.setFill(Color.GREEN);
        for (int i = 0; i < 6; i++) {
            drawCard(user);
        }
        showCardsInHand();
        winnerOfDuel = null;
        turn = 1;
    }

    private void showCardsInHand() {
        //todo attack /defense image
        try {
            currentCard1.setRelatedCard(currentUser.getBoard().getCardsInHand().get(0));
        } catch (Exception ignored) {
            currentCard1.setRelatedCard(null);
        }
        currentCard1.fillCard(true);
        try {
            currentCard2.setRelatedCard(currentUser.getBoard().getCardsInHand().get(1));
        } catch (Exception ignored) {
            currentCard2.setRelatedCard(null);
        }
        currentCard2.fillCard(true);
        try {
            currentCard3.setRelatedCard(currentUser.getBoard().getCardsInHand().get(2));
        } catch (Exception ignored) {
            currentCard3.setRelatedCard(null);
        }
        currentCard3.fillCard(true);
        try {
            currentCard4.setRelatedCard(currentUser.getBoard().getCardsInHand().get(3));
        } catch (Exception ignored) {
            currentCard4.setRelatedCard(null);
        }
        currentCard4.fillCard(true);
        try {
            currentCard5.setRelatedCard(currentUser.getBoard().getCardsInHand().get(4));
        } catch (Exception ignored) {
            currentCard5.setRelatedCard(null);
        }
        currentCard5.fillCard(true);
        try {
            currentCard6.setRelatedCard(currentUser.getBoard().getCardsInHand().get(5));
        } catch (Exception ignored) {
            currentCard6.setRelatedCard(null);
        }
        currentCard6.fillCard(true);
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

    public void deselectCard() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
        } else {
            setSelectedCard(null);
            System.out.println("card deselected");
        }
    }

    private void changeTurn() {
        currentUser = getOpponentOfCurrentUser();
        attackedCards.clear();
        normalSummonOrSetCard = null;
        putOnMonsterZoneCards.clear();
        setPositionedCards.clear();
        printBoard();
        turn++;
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
    }

    private void select(Matcher matcher) {
        if (matcher.find()) {
            try {
                if (matcher.group(1) != null) {
                    int number = Integer.parseInt(matcher.group(1));
                    selectedCard = currentUser.getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(2) != null) {
                    int number = Integer.parseInt(matcher.group(2));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(3) != null) {
                    int number = Integer.parseInt(matcher.group(3));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(4) != null) {
                    int number = Integer.parseInt(matcher.group(4));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(5) != null) {
                    int number = Integer.parseInt(matcher.group(5));
                    selectedCard = currentUser.getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(6) != null) {
                    int number = Integer.parseInt(matcher.group(6));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(7) != null) {
                    int number = Integer.parseInt(matcher.group(7));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(8) != null) {
                    int number = Integer.parseInt(matcher.group(8));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(9) != null) {
                    int number = Integer.parseInt(matcher.group(9));
                    if (number <= currentUser.getBoard().getCardsInHand().size() && number > 0) {
                        selectedCard = currentUser.getBoard().getCardsInHand().get(number - 1);
                    } else {
                        System.out.println("invalid selection");
                        return;
                    }
                } else if (matcher.group(10) != null) {
                    selectedCard = currentUser.getBoard().getFieldZone();
                } else if (matcher.group(11) != null) {
                    selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
                } else if (matcher.group(12) != null) {
                    selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
                } else {
                    System.out.println("invalid selection");
                    return;
                }
                System.out.println("card Selected");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("invalid selection");
            }
        } else {
            System.out.println("invalid command");
        }
    }

    public void drawPhaseRun() {
        currentPhase = Phase.DRAW;
        if (turn == 2) {
            for (int i = 0; i < 5; i++) {
                drawCard(currentUser);
            }
        }

        if (!canCurrentUserDraw()) {
            winnerOfDuel = getOpponentOfCurrentUser();
            return;
        } else {
            drawCard(currentUser);
        }
        showCardsInHand();
//            if (input.equals("select -d")) {
//                deselectCard();
//            } else if (input.startsWith("select")) {
//                select(Regex.getMatcher(input, Regex.selectCard));
//            } else if (input.equals("next phase")) {
//                return;
//            } else if (input.equals("show graveyard")) {
//                showGraveyard();
//            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
//                showSelectedCard();
//            } else if (input.equals("surrender")) {
//                winnerOfDuel = getOpponentOfCurrentUser();
//                return;
//            } else if (input.equals("summon")) {
//                summon();
//            } else if (input.equals("set")) {
//                set();
//            } else if (input.matches(Regex.setPositionAttackDefence)) {
//                setPositionAttackDefense(input);
//            } else if (input.equals("flip-summon")) {
//                flipSummon();
//            } else if (input.matches(Regex.attack)) {
//                if (attack(input)) {
//                    return;
//                }
//            } else if (input.equals("attack direct")) {
//                if (directAttack()) {
//                    return;
//                }
//            } else if (input.equals("activate effect")) {
//                activateEffect();
//            } else {
//                System.out.println("invalid command");
//            }
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
//        heraldOfCreationActivation();
//        if (payForMessengerOfPeace()) {
//            standByPhasePlace.setFill(Color.RED);
//            return;
//        }
//        resetSupplySquads();
    }

    private void resetSupplySquads() {
        currentUser.getBoard().getActivatedSupplySquad().clear();
        for (int i = 0; i < 5; i++) {
            Card card = currentUser.getBoard().getSpellsAndTrapsZone().get(i);
            if (card.getName().equals("Supply Squad")) {
                if (card.getOccupied()) {
                    currentUser.getBoard().getActivatedSupplySquad().add(card);
                }
            }
            card = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i);
            if (card.getName().equals("Supply Squad")) {
                if (card.getOccupied()) {
                    getOpponentOfCurrentUser().getBoard().getActivatedSupplySquad().add(card);
                }
            }
        }
    }

    private boolean payForMessengerOfPeace() {// true for 0 LP or less and false for more than 0 LP
        for (int i = 0; i < currentUser.getBoard().getActivatedMessengerOfPeaces().size(); i++) {
            Card card = currentUser.getBoard().getActivatedMessengerOfPeaces().get(i);
            System.out.println("pay 100 LP or destroy Messenger of Peace: \"D\" for destroy and \"P\" to pay 100 LP");
            while (true) {
                String answer = editSpaces(scanner.nextLine());
                if (answer.equals("D")) {
                    addSpellOrTrapFromZoneToGraveyard(card, currentUser);
                } else if (answer.equals("P")) {
                    currentUser.setLifePoint(currentUser.getLifePoint() - 100);
                    if (currentUser.getLifePoint() <= 0) {
                        winnerOfDuel = getOpponentOfCurrentUser();
                        return true;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("enter D or P");
                }
            }
        }
        return false;
    }

    private void heraldOfCreationActivation() {
        outer:
        for (int i = 0; i < 5; i++) {
            Card card = currentUser.getBoard().getMonstersZone().get(i);
            if (card.getName().equals("Herald of Creation")) {
                System.out.println("do you want to activate your Herald of Creation? Y/N");
                while (true) {
                    String answer = editSpaces(scanner.nextLine());
                    if (answer.equals("N")) {
                        break outer;
                    } else if (answer.equals("Y")) {
                        System.out.println("enter number of a level 7 or more monster in your graveyard to bring to your hand");
                        while (true) {
                            String answer1 = editSpaces(scanner.nextLine());
                            if (answer1.equals("cancel")) {
                                System.out.println("canceled");
                                break outer;
                            }
                            if (answer1.matches("\\d+")) {
                                int number = Integer.parseInt(answer1);
                                if (number < 1 || number > currentUser.getBoard().getGraveYard().size()) {
                                    System.out.println("enter a correct number");
                                } else {
                                    if (!(currentUser.getBoard().getGraveYard().get(number - 1) instanceof Monster)) {
                                        System.out.println("wrong card!");
                                        continue;
                                    }
                                    Monster monster = (Monster) currentUser.getBoard().getGraveYard().get(number - 1);
                                    if (monster.getLevel() < 7) {
                                        System.out.println("level is less than 7");
                                        continue;
                                    }
                                    currentUser.getBoard().getGraveYard().remove(monster);
                                    currentUser.getBoard().getCardsInHand().add(monster);
                                    System.out.println("card added to your hand");
                                    break outer;
                                }
                            } else {
                                System.out.println("enter a number");
                            }
                        }
                    } else {
                        System.out.println("enter Y or N");
                    }
                }
            }
        }
    }

    private void mainPhaseOneRun() {
        currentPhase = Phase.MAIN_ONE;
        runMainPhase();
    }

    private void runMainPhase() {
        if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
            ((AI) currentUser).setOnBoard();
            printBoard();
        } else {
            printBoard();
            if (clickedPhase == Phase.MAIN_ONE || clickedPhase == Phase.MAIN_TWO) {
                if (activatedRitualCard != null) {
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
        if (selectedCard == null) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error", "no card is selected yet");
            return;
        }
        if (!(selectedCard instanceof Monster) || !currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error", "you can’t summon this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Error!",
                    "action not allowed in this phase");
            return;
        }
        Monster monster = (Monster) selectedCard;
        if (activatedRitualCard != null) {
            if (monster.getCardType() == Type.RITUAL) {
                ritualSummon();
            } else {
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Summon Error!",
                        "you should ritual summon right now");
            }
            return;
        } else {
            if (monster.getCardType() == Type.RITUAL) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                        "you can’t summon this card");
                return;
            }
        }
        if (selectedCard.getName().equals("Gate Guardian")) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 3) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error!",
                        "there are not enough cards for tribute");
            } else {
                tributeSummon(3, true);
                specialSummonedCards.add(selectedCard);
                return;
            }
        }
        if (selectedCard.getName().equals("Beast King Barbaros")) {
            System.out.println("do you want to summon this card with \"0\" or \"2\" or \"3\"  tributes?");
            String answer;
            while (true) {
                answer = scanner.nextLine();
                answer = editSpaces(answer);
                switch (answer) {
                    case "0":
                        if (normalSummonOrSetCard != null) {
                            System.out.println("you already summoned/set on this turn");
                        } else if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                            System.out.println("monster card zone is full");
                        } else {
                            ((Monster) selectedCard).setAttackPower(1900);
                            addMonsterFromHandToMonsterZone(selectedCard, true, true);
                            System.out.println("summoned successfully");
                            normalSummonOrSetCard = selectedCard;
                            selectedCard = null;
                        }
                        break;
                    case "2":
                        if (normalSummonOrSetCard != null) {
                            System.out.println("you already summoned/set on this turn");
                        } else if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                            System.out.println("there are not enough cards for tribute");
                        } else {
                            tributeSummon(2, false);
                        }
                        break;
                    case "3":
                        if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                            System.out.println("there are not enough cards for tribute");
                        } else {
                            System.out.println("\"attack\" or \"defence\"?");
                            String answer1 = scanner.nextLine();
                            while (!answer1.equals("attack") && !answer1.equals("defence") && !answer1.equals("cancel")) {
                                System.out.println("type \"attack\" or \"defence\" or cancel");
                                answer1 = scanner.nextLine();
                            }
                            if (answer1.equals("cancel")) {
                                System.out.println("action canceled");
                                return;
                            } else if (answer1.equals("attack")) {
                                tributeSummon(3, true);
                            } else {
                                tributeSet(3, true);
                            }
                            for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                                if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                                    getOpponentOfCurrentUser().getBoard().getGraveYard().add(getOpponentOfCurrentUser()
                                            .getBoard().getMonstersZone().get(i));
                                    getOpponentOfCurrentUser().getBoard().getMonstersZone().set(i, null);
                                }
                            }
                            for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().size(); i++) {
                                if (getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i) != null) {
                                    getOpponentOfCurrentUser().getBoard().getGraveYard().add(getOpponentOfCurrentUser()
                                            .getBoard().getSpellsAndTrapsZone().get(i));
                                    getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().set(i, null);
                                }
                            }
                            if (getOpponentOfCurrentUser().getBoard().getFieldZone() != null) {
                                Card zoneSpell = getOpponentOfCurrentUser().getBoard().getFieldZone();
                                getOpponentOfCurrentUser().getBoard().getGraveYard().add(zoneSpell);
                                getOpponentOfCurrentUser().getBoard().setFieldZone(null);
                            }
                            System.out.println("destroyed all cards that opponent control");
                        }
                        break;
                    case "cancel":
                        System.out.println("action canceled");
                        return;
                    default:
                        System.out.println("type \"0\" or \"2\" or \"3\" or cancel");
                        break;
                }
            }
        }
        if (selectedCard.getName().equals("The Tricky")) {
            System.out.println("do you want to normal summon or special summon? (answer with \"normal\"/\"special\")");
            String answer;
            while (true) {
                answer = scanner.nextLine();
                answer = editSpaces(answer);
                switch (answer) {
                    case "normal":
                        if (normalSummonOrSetCard != null) {
                            System.out.println("you already summoned/set on this turn");
                            return;
                        } else {
                            if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                                System.out.println("there are not enough cards for tribute");
                                return;
                            } else {
                                tributeSummon(1, false);
                                return;
                            }
                        }
                    case "special":
                        if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                            System.out.println("monster card zone is full");
                            return;
                        }
                        if (currentUser.getBoard().getCardsInHand().size() < 2) {
                            System.out.println("there are not enough cards for tribute");
                            return;
                        }
                        specialSummonTheTricky();
                        return;
                    case "cancel":
                        System.out.println("action canceled");
                        return;
                    default:
                        System.out.println("please type normal or special (or cancel)");
                        break;
                }
            }
        }
        if (normalSummonOrSetCard != null) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Error !",
                    "you already summoned/set on this turn");
            return;
        }
        if (monster.getLevel() <= 4) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
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
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Successfull !",
                        "there are not enough cards for tribute");
            } else {
                tributeSummon(1, false);
            }
        } else if (monster.getLevel() > 6) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Summon Successfull !",
                        "there are not enough cards for tribute");
            } else {
                tributeSummon(2, false);
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

    private void addMonsterFromHandToMonsterZone(Card monsterCard, Boolean isOccupied, Boolean isAttackPosition) {
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

    private void tributeSummon(int tributeNumber, boolean isSpecial) {
        if (doTributeSummon(tributeNumber, isSpecial, true)) return;
        System.out.println("summoned successfully");
        selectedCard = null;
    }

    private boolean doTributeSummon(int tributeNumber, boolean isSpecial, boolean isSummon) {
        System.out.println("enter number of " + tributeNumber + " cards in monster zone to tribute (or cancel)");
        ArrayList<Card> monstersToTribute = new ArrayList<>();
        String numberString;
        while (monstersToTribute.size() < tributeNumber) {
            numberString = scanner.nextLine();
            numberString = editSpaces(numberString);
            if (numberString.equals("cancel")) {
                System.out.println("action canceled");
                return true;
            } else if (!numberString.matches("\\d+")) {
                System.out.println("enter a number");
            } else {
                int number = Integer.parseInt(numberString);
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
        }
        for (int i = 0; i < monstersToTribute.size(); i++) {
            tributeMonster(monstersToTribute.get(i));
        }
        if (isSummon) {
            addMonsterFromHandToMonsterZone(selectedCard, true, true);
        } else {
            addMonsterFromHandToMonsterZone(selectedCard, false, false);
        }
        if (!isSpecial) {
            normalSummonOrSetCard = selectedCard;
        }
        return false;
    }

    private void tributeSet(int tributeNumber, boolean isSpecial) {
        if (doTributeSummon(tributeNumber, isSpecial, false)) return;
        System.out.println("set successfully");
        selectedCard = null;
    }

    private void tributeMonster(Card monsterCard) {
        for (int i = 0; i < currentUser.getBoard().getMonstersZone().size(); i++) {
            if (currentUser.getBoard().getMonstersZone().get(i) == monsterCard) {
                currentUser.getBoard().getMonstersZone().set(i, null);
                currentUser.getBoard().getGraveYard().add(monsterCard);
            }
        }
    }

    private void specialSummonTheTricky() {
        System.out.println("Enter the number of card in your hand to tribute (or cancel)");
        String numberString;
        while (true) {
            numberString = scanner.nextLine();
            numberString = editSpaces(numberString);
            if (numberString.matches("\\d+")) {
                int number = Integer.parseInt(numberString);
                if (number >= 1 && number <= currentUser.getBoard().getCardsInHand().size()) {
                    Card cardToTribute = currentUser.getBoard().getCardsInHand().get(number - 1);
                    if (cardToTribute == selectedCard) {
                        System.out.println("You cant tribute this card select another card");
                    } else {
                        currentUser.getBoard().getCardsInHand().remove(cardToTribute);
                        currentUser.getBoard().getGraveYard().add(cardToTribute);
                        addMonsterFromHandToMonsterZone(selectedCard, true, true);
                        System.out.println("summoned successfully");
                        selectedCard = null;
                        return;
                    }
                } else {
                    System.out.println("please enter a correct number");
                }
            } else if (numberString.equals("cancel")) {
                System.out.println("action canceled");
                return;
            } else {
                System.out.println("enter a number");
            }
        }
    }

    private void set() {
        if (activatedRitualCard != null) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !", "you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !", "no card is selected yet");
            return;
        }
        if (!currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                    "you can’t summon this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                    "action not allowed in this phase");
            return;
        }
        if (selectedCard instanceof Monster) {
            Monster monster = (Monster) selectedCard;

            if (normalSummonOrSetCard != null) {
                GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                        "you already summoned/set on this turn");
                return;
            }
            if (monster.getLevel() <= 4) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                    GamePlay.showAlert(Alert.AlertType.INFORMATION, "Set Error !",
                            "monster card zone is full");
                } else {
                    addMonsterFromHandToMonsterZone(selectedCard, false, false);
                    normalSummonOrSetCard = selectedCard;
                    selectedCard = null;
                }
            } else if (monster.getLevel() == 5 || monster.getLevel() == 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                            "there are not enough cards for tribute");
                } else {
                    tributeSet(1, false);
                }
            } else if (monster.getLevel() > 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                    GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                            "there are not enough cards for tribute");
                } else {
                    tributeSet(2, false);
                }
            }
        } else {
            if (currentUser.getBoard().numberOfSpellAndTrapsOnBoard() == 5) {
                GamePlay.showAlert(Alert.AlertType.ERROR, "Set Error !",
                        "Spell Card Zone Is Full");
                return;
            }
            addSpellOrTrapFromHandToZone(selectedCard, false);
            selectedCard = null;
        }
    }

    private void addSpellOrTrapFromHandToZone(Card spellOrTrap, boolean isOccupied) {
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
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "you can’t change this card position");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "action not allowed in this phase");
            return;
        }
        if ((selectedCard.getAttackPosition() && input.equals("attack"))
                || (!selectedCard.getAttackPosition() && input.equals("defense"))) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "this card is already in the wanted position");
            return;
        }
        if (setPositionedCards.contains(selectedCard)) {
            GamePlay.showAlert(Alert.AlertType.ERROR, "Error In Changing Position",
                    "you already changed this card position in this turn");
            return;
        }
        selectedCard.setAttackPosition(input.equals("attack"));
        GamePlay.showAlert(Alert.AlertType.INFORMATION, "Changed Successfully",
                "monster card position changed successfully");
        setPositionedCards.add(selectedCard);
        selectedCard = null;
    }

    private boolean changePosition() {
        if (activatedRitualCard != null) {
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you should ritual summon right now");
            return true;
        }
        if (selectedCard == null) {
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "no card is selected yet");
            return true;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            GamePlay.showAlert(Alert.AlertType.WARNING, "Select Error !",
                    "you can’t change this card position");
            return true;
        }
        return false;
    }

    private void flipSummon() {
        if (changePosition()) return;
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        if ((!selectedCard.getAttackPosition() && !selectedCard.getOccupied())
                || putOnMonsterZoneCards.contains(selectedCard)) {
            System.out.println("you can’t flip summon this card");
            return;
        }
        selectedCard.setAttackPosition(true);
        selectedCard.setOccupied(true);
        if (selectedCard.getName().equals("Command Knight")) {
            activateCommandKnight(selectedCard, currentUser);
        }
        System.out.println("flip summoned successfully");
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
            attack(" ");
        } else {
            printBoard();
            if (clickedPhase == Phase.BATTLE)
                return;

//                if (input.equals("select -d")) {
//                    deselectCard();
//                } else if (input.startsWith("select")) {
//                    select(Regex.getMatcher(input, Regex.selectCard));
//                } else if (input.equals("next phase")) {
//                    battlePhasePlace.setFill(Color.RED);
//                    return;
//                } else if (input.matches(Regex.attack)) {
//                    if (attack(input)) {
//                        battlePhasePlace.setFill(Color.RED);
//                        return;
//                    }
//                } else if (input.equals("show graveyard")) {
//                    showGraveyard();
//                } else if (input.equals("attack direct")) {
//                    if (directAttack()) {
//                        battlePhasePlace.setFill(Color.RED);
//                        return;
//                    }
//                } else if (input.equals("card show --selected") || input.equals("card show -s")) {
//                    showSelectedCard();
//                } else if (input.equals("surrender")) {
//                    winnerOfDuel = getOpponentOfCurrentUser();
//                    battlePhasePlace.setFill(Color.RED);
//                    return;
//                } else if (input.equals("summon")) {
//                    summon();
//                } else if (input.equals("set")) {
//                    set();
//                } else if (input.matches(Regex.setPositionAttackDefence)) {
//                    setPositionAttackDefense(input);
//                } else if (input.equals("flip-summon")) {
//                    flipSummon();
//                } else if (input.equals("activate effect")) {
//                    activateEffect();
//                } else {
//                    System.out.println("invalid command");
//                }

        }
    }

    private boolean attack(String input) { // return true if duel has winner and false if duel does not have winner
        if (playingWithAi && currentUser.getUsername().equalsIgnoreCase("ai")) {
            ArrayList<Card> cards = ((AI) currentUser).attack(getOpponentOfCurrentUser().getBoard());
            if (cards == null) return false;
            return doAttackAction(cards.get(1), (Monster) cards.get(0));
        } else {
            if (doAttack()) return false;
            String[] inputSplit = input.split("\\s");
            int enemyCardNumber = Integer.parseInt(inputSplit[1]);
            Card enemyCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(enemyCardNumber - 1);
            Monster selectedMonster = (Monster) selectedCard;
            if (enemyCard == null) {
                System.out.println("there is no card to attack here");
                return false;
            }
            if (getOpponentOfCurrentUser().getBoard().getActivatedMessengerOfPeaces().size() != 0) {
                if (((Monster) selectedCard).getAttackPower() >= 1500) {
                    System.out.println("Messenger of Peace does not let you attack wit this card");
                    return false;
                }
            }
            if (enemyCard.getName().equals("Command Knight") && getOpponentOfCurrentUser().getBoard().numberOfMonstersOnBoard() - getOpponentOfCurrentUser().getBoard().getCommandKnights().size() > 0) {
                if (enemyCard.getOccupied()) {
                    System.out.println("you cant attack this card yet");
                    return false;
                }
            }
            new ChainController(this, scanner).run();
            if (magicCylinderActivated) {
                setMagicCylinderActivated(false);
                System.out.println("Magic Cylinder stopped the attack and attacker took " + selectedMonster.getAttackPower() + "damage");
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
                System.out.println("Negate Attack stopped the attack");
                return false;
            }
            if (mirrorForceActivated) {
                setMirrorForceActivated(false);
                System.out.println("Mirror Force stopped the attack and destroyed all attackers attack positioned monsters");
                return false;
            }
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
                System.out.println("your opponent’s monster is destroyed and your opponent receives " + damage
                        + " battle damage");
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    System.out.println("Yomi Ship destroyed your monster");
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
                System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getAttackPower());
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
            if (selectedMonster.getAttackPower() > enemyMonster.getDefencePower()) {
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                if (enemyMonster.getOccupied()) {
                    System.out.println("the defense position monster is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    System.out.println("opponent’s monster card was " + enemyMonster.getName()
                            + " and the defense position monster is destroyed");
                }
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    System.out.println("Yomi Ship destroyed your monster");
                } else {
                    attackedCards.add(selectedMonster);
                }
                return false;
            } else if (selectedMonster.getAttackPower() == enemyMonster.getDefencePower()) {
                if (enemyMonster.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    System.out.println("opponent’s monster card was " + enemyMonster.getName() + " and no card is destroyed");
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getDefencePower());

                if (enemyMonster.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    enemyMonster.setOccupied(true);
                    if (enemyMonster.getName().equals("Command Knight")) {
                        activateCommandKnight(enemyMonster, getOpponentOfCurrentUser());
                    }
                    System.out.println("opponent’s monster card was " + enemyMonster.getName()
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
        } else { // enemy deffend position
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
        if (doAttack()) return false;
        if (getOpponentOfCurrentUser().getBoard().numberOfMonstersOnBoard() > 0) {
            System.out.println("you can’t attack the opponent directly");
            return false;
        }
        if (getOpponentOfCurrentUser().getBoard().getActivatedMessengerOfPeaces().size() != 0) {
            if (((Monster) selectedCard).getAttackPower() >= 1500) {
                System.out.println("Messenger of Peace does not let you attack wit this card");
                return false;
            }
        }
        Monster selectedMonster = (Monster) selectedCard;
        new ChainController(this, scanner).run();
        if (magicCylinderActivated) {
            setMagicCylinderActivated(false);
            System.out.println("Magic Cylinder stopped the attack and attacker took " + selectedMonster.getAttackPower() + "damage");
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
            System.out.println("Negate Attack stopped the attack");
            return false;
        }
        if (mirrorForceActivated) {
            setMirrorForceActivated(false);
            System.out.println("Mirror Force stopped the attack and destroyed all attackers attack positioned monsters");
            return false;
        }

        // before attack starts

        attackedCards.add(selectedCard);
        int damage = ((Monster) selectedCard).getAttackPower();
        getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
        System.out.println("you opponent receives " + damage + " battle damage");
        if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
            winnerOfDuel = currentUser;
            return true;
        } else {
            return false;
        }
    }

    private boolean doAttack() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return true;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            System.out.println("you can’t attack with this card");
            return true;
        }
        if (!(currentPhase == Phase.BATTLE)) {
            System.out.println("you can’t do this action in this phase");
            return true;
        }
        if (attackedCards.contains(selectedCard)) {
            System.out.println("this card already attacked");
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
            System.out.println("you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!(selectedCard instanceof Spell)) {
            System.out.println("activate effect is only for spell cards.");
            return;
        }
        if (currentPhase != Phase.MAIN_ONE && currentPhase != Phase.MAIN_TWO) {
            System.out.println("you can’t activate an effect on this turn");
            return;
        }
        if (currentUser.getBoard().getSpellsAndTrapsZone().contains(selectedCard) && selectedCard.getOccupied()) {
            System.out.println("you have already activated this card");
            return;
        }
        if (selectedCard.getCardType() == Type.FIELD && currentUser.getBoard().getFieldZone() == selectedCard && selectedCard.getOccupied()) {
            System.out.println("you have already activated this card");
            return;
        }
        if (selectedCard.getCardType() != Type.FIELD && currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            if (currentUser.getBoard().numberOfSpellAndTrapsOnBoard() == 5) {
                System.out.println("spell card zone is full");
                return;
            }
        }
        if (!currentUser.getBoard().getAllCards().contains(selectedCard)) {
            System.out.println("This card is not yours");
            return;
        }
        ((Spell) selectedCard).giveEffect();

        if (!((Spell) selectedCard).getEffect().canBeActivated(this)) {
            System.out.println("preparations of this spell are not done yet");
            return;
        }
        if (selectedCard.getCardType() == Type.FIELD) {
            if (currentUser.getBoard().getFieldZone() != null) {
                addSpellOrTrapFromZoneToGraveyard(currentUser.getBoard().getFieldZone(), currentUser);
            }
            currentUser.getBoard().setFieldZone(selectedCard);
        }

        ((Spell) selectedCard).getEffect().addToChain(this);
        // activating the spells and chain
        new ChainController(this, scanner).run();
    }

    private void showGraveyard() {
        if (currentUser.getBoard().getGraveYard().size() == 0) {
            System.out.println("graveyard empty");
        } else {
            int number = 1;
            for (Card card : currentUser.getBoard().getGraveYard()) {
                System.out.println(number + ". " + card.getName() + ":" + card.getDescription());
                number++;
            }
        }
        System.out.println("type back to return to game");
        while (true) {
            if (scanner.nextLine().equals("back")) {
                return;
            }
        }
    }

    private void showSelectedCard() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (getOpponentOfCurrentUser().getBoard().getAllCards().contains(selectedCard) && !selectedCard.getOccupied()) {
            System.out.println("card is not visible");
            return;
        }
        System.out.println(selectedCard.getName() + ":" + selectedCard.getDescription());
    }

    private void endPhaseRun() {
//        int number = currentUser.getBoard().getCardsInHand().size();
//        if (number > 6) {
//            number -= 6;
//            while (currentUser.getBoard().getCardsInHand().size() > 6) {
//                System.out.println("you have to throw away " + number + "cards");
//                String numberString = editSpaces(scanner.nextLine());
//                if (numberString.matches("\\d+")) {
//                    int number1 = Integer.parseInt(numberString);
//                    if (number1 < 1 || number1 > currentUser.getBoard().getCardsInHand().size()) {
//                        System.out.println("enter a correct number");
//                    } else {
//                        Card card = currentUser.getBoard().getCardsInHand().get(number1 - 1);
//                        currentUser.getBoard().getCardsInHand().remove(card);
//                        currentUser.getBoard().getGraveYard().add(card);
//                        number--;
//                    }
//                } else {
//                    System.out.println("enter a number");
//                }
//            }
//        }
        currentPhase = Phase.END;

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
            ((FieldEffect) ((Spell) owner.getBoard().getFieldZone()).getEffect()).getEffectedMonsterCards().remove(card);
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

    @FXML
    public void nextPhase() {
        if (currentPhase == null || clickedPhase == Phase.END) {
            initialiseLabelNames();
            currentPhase = Phase.DRAW;
            drawPhaseRun();
            drawPhasePlace.setFill(Color.RED);
        }
        if (currentPhase == Phase.DRAW) {
            currentPhase = Phase.STANDBY;
            standByPhasePlace.setFill(Color.GREEN);
            standbyPhaseRun();
            drawPhasePlace.setFill(Color.RED);
            clickedPhase = Phase.DRAW;
        } else if (currentPhase == Phase.STANDBY) {
            currentPhase = Phase.MAIN_ONE;
            mainPhase1Place.setFill(Color.GREEN);
            mainPhaseOneRun();
            standByPhasePlace.setFill(Color.RED);
            clickedPhase = Phase.STANDBY;
        } else if (currentPhase == Phase.MAIN_ONE) {
            currentPhase = Phase.BATTLE;
            battlePhasePlace.setFill(Color.GREEN);
            battlePhaseRun();
            mainPhase1Place.setFill(Color.RED);
            clickedPhase = Phase.MAIN_ONE;
        } else if (currentPhase == Phase.BATTLE) {
            currentPhase = Phase.MAIN_TWO;
            mainPhase2Place.setFill(Color.GREEN);
            mainPhaseTwoRun();
            battlePhasePlace.setFill(Color.RED);
            clickedPhase = Phase.BATTLE;
        } else if (currentPhase == Phase.MAIN_TWO) {
            currentPhase = Phase.END;
            endPhasePlace.setFill(Color.GREEN);
            endPhaseRun();
            mainPhase2Place.setFill(Color.RED);
            clickedPhase = Phase.MAIN_TWO;
        } else {
            changeTurn();
            initialiseLabelNames();
            GamePlay.showAlert(Alert.AlertType.INFORMATION, "Turn Changed!",
                    "its " + currentUser.getNickName() + "’s turn");
            currentPhase = Phase.DRAW;
            drawPhasePlace.setFill(Color.GREEN);
            endPhasePlace.setFill(Color.RED);
            drawPhaseRun();
            clickedPhase = Phase.END;
        }
    }
}
