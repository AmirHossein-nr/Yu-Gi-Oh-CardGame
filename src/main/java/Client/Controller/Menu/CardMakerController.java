package Client.Controller.Menu;

import Model.Monster;
import Model.Spell;
import Model.Type;
import Client.View.GUI.CardMakerGraphics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class CardMakerController {

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField levelTextField;
    @FXML
    public TextField monsterTypeTextField;
    @FXML
    public TextField cardTypeTextField;
    @FXML
    public TextField attackPowerTextField;
    @FXML
    public TextField defensePowerTextField;
    @FXML
    public TextArea descriptionTextField;
    @FXML
    public TextField typeTextField;
    @FXML
    public TextField iconTextField;
    public Label costLabel;
    public Label costLabel2;

    String name, level, monsterType, cardType, attackPower, defensePower, description, type, icon;

    public static Stage mainStage;

    public CardMakerController() {

    }

    public void createCard(ActionEvent actionEvent) {
        name = nameTextField.getText();
        level = levelTextField.getText();
        monsterType = monsterTypeTextField.getText();
        cardType = cardTypeTextField.getText();
        attackPower = attackPowerTextField.getText();
        defensePower = defensePowerTextField.getText();
        description = descriptionTextField.getText();
        costLabel.setText("Cost : " + ((Integer.parseInt(attackPower) / 10) + (Integer.parseInt(defensePower) / 13)));
        Type monsterType = returnType(monsterTypeTextField.getText());
        Type cardType = returnType(cardTypeTextField.getText());

        Monster card = new Monster(name, monsterType);
        card.setUserMade(true);
        card.setPrice((Integer.parseInt(attackPower) / 10) + (Integer.parseInt(defensePower) / 13));
        card.setLevel(Integer.parseInt(level));
        card.setCardType(cardType);
        card.setAttackPower(Integer.parseInt(attackPower));
        card.setDefencePower(Integer.parseInt(defensePower));
        card.setDescription(description);
        card.setPrice((Integer.parseInt(defensePower) + Integer.parseInt(attackPower)) / 2);
        card.setCardImage(new Image(getClass().getResource("/images/theTrump.jpg").toExternalForm()));
        new Shop();
        Shop.getAllCards().add(card);
    }

    public void createSpellTrapCard(ActionEvent actionEvent) {
        name = nameTextField.getText();
        type = typeTextField.getText();
        icon = iconTextField.getText();
        description = descriptionTextField.getText();
        costLabel2.setText("Cost : " + (3000));
        Type cardType = returnType(type);

        Spell card = new Spell(name, cardType);
        card.setUserMade(true);
        card.setDescription(description);
        card.setPrice(3000);
        card.setCardImage(new Image(getClass().getResource("/images/theTrump.jpg").toExternalForm()));
        Shop.getAllCards().add(card);
    }

    private Type returnType(String input) {
        switch (input) {
            case "warrior":
                return Type.WARRIOR;
            case "normal":
                return Type.NORMAL;
            case "effect":
                return Type.EFFECT;
            case "fiend":
                return Type.FIEND;
            case "aqua":
                return Type.AQUA;
            case "beast":
                return Type.BEAST;
            case "pyro":
                return Type.PYRO;
            case "sSpellcaster":
                return Type.SPELL_CASTER;
            case "thunder":
                return Type.THUNDER;
            case "dragon":
                return Type.DRAGON;
            case "ritual":
                return Type.RITUAL;
            case "machine":
                return Type.MACHINE;
            case "rock":
                return Type.ROCK;
            case "insect":
                return Type.INSECT;
            case "cyberse":
                return Type.CYBERSE;
            case "fairy":
                return Type.FAIRY;
            case "beast-warrior":
                return Type.BEAST_WARRIOR;
            case "sea serpent":
                return Type.SEA_SERPENT;
            case "continuous":
                return Type.CONTINUOUS;
            case "quick-play":
                return Type.QUICK_PLAY;
            case "field":
                return Type.FIELD;
            case "equip":
                return Type.EQUIP;
            case "spell":
                return Type.SPELL;
            case "trap":
                return Type.TRAP;
        }
        return null;
    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        new CardMakerGraphics().start(mainStage);
    }
}
