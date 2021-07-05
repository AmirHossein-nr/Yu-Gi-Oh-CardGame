package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Objects;


public class CardRectangle extends Rectangle {

    private Card relatedCard;

    public CardRectangle() {
        super();
    }


    public void fillCard(boolean inHand) {
        if (this.getRelatedCard() != null) {
            if (inHand)
                this.setFill(new ImagePattern(relatedCard.getCardImage()));

            if (!(this.getRelatedCard() instanceof Monster)) {
                if (this.getRelatedCard().getOccupied()) {
                    this.setFill(new ImagePattern(relatedCard.getCardImage()));
                } else {
                    this.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                            .getResource("/images/backCard.jpg"))
                            .toExternalForm())));
                }
            }

            else if (this.getRelatedCard().getOccupied()) {
                if (this.getRelatedCard().getAttackPosition()) {
                    if (this.getRotate() > 0)
                        this.rotateProperty().set(0);
                } else
                    this.rotateProperty().set(90);
                this.setFill(new ImagePattern(relatedCard.getCardImage()));
            } else {
                if (this.getRelatedCard().getAttackPosition()) {
                    if (this.getRotate() > 0)
                        this.rotateProperty().set(0);
                    this.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                            .getResource("/images/backCard.jpg"))
                            .toExternalForm())));
                } else {
                    try {
                        this.rotateProperty().set(90);
                        this.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass()
                                .getResource("/images/bkCard.png"))
                                .toExternalForm())));
                    } catch (Exception ignored) {
                    }
                }
            }
        } else {
            this.setRotate(0);
            this.setFill(Color.TRANSPARENT);
            this.setRotate(0);
        }
    }

    public void setRelatedCard(Card relatedCard) {
        this.relatedCard = relatedCard;
    }

    public Card getRelatedCard() {
        return relatedCard;
    }
}
