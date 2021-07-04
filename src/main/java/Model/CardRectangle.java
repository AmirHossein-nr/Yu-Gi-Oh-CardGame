package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class CardRectangle extends Rectangle {

    private Card relatedCard;

    public CardRectangle() {
        super();
    }


    public void fillCard(boolean inHand) {
        if (this.getRelatedCard() != null) {
            if (inHand || this.getRelatedCard().getOccupied()) {
                this.setFill(new ImagePattern(relatedCard.getCardImage()));
            } else {
                if (!this.getRelatedCard().getAttackPosition()) {
                    this.setFill(new ImagePattern(new Image(getClass().getResource("/images/backCard.jpg").toExternalForm())));
                } else {
                    this.setFill(new ImagePattern(new Image(getClass().getResource("/images/bkCard.jpg").toExternalForm())));
                }
            }
        }
    }

    public void setRelatedCard(Card relatedCard) {
        this.relatedCard = relatedCard;
    }

    public Card getRelatedCard() {
        return relatedCard;
    }
}
