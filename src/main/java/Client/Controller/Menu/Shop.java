package Client.Controller.Menu;

import Model.*;
import Client.View.GUI.GamePlay;
import Client.View.GUI.MainMenuGraphic;
import Client.View.GUI.ShopGraphic;
import com.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Shop extends Menu {
    private static ArrayList<Card> allCards;
    public static Stage mainStage;

    static {
        allCards = new ArrayList<>();
    }

    public ImageView newImage;

    public Shop() {
        super("Shop Menu", null);
        final String monstersCsvFilePath = "src/main/resources/Monster.csv";
        final String spellsAndTrapsCsvFilePath = "src/main/resources/SpellTrap.csv";

        csvMonsterReader(monstersCsvFilePath);
        csvSpellReader(spellsAndTrapsCsvFilePath);
        iterateThroughArray();
    }

    public Shop(Menu parentMenu) {
        super("Shop Menu", parentMenu);

        final String monstersCsvFilePath = "src/main/resources/Monster.csv";
        final String spellsAndTrapsCsvFilePath = "src/main/resources/SpellTrap.csv";

        csvMonsterReader(monstersCsvFilePath);
        csvSpellReader(spellsAndTrapsCsvFilePath);
        iterateThroughArray();
    }

    private void csvMonsterReader(String monstersCsvFilePath) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(monstersCsvFilePath));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<String[]> monsters = csvReader.readAll();
            for (String[] string : monsters) {
                String name = string[0];
                name = name.trim();
                if (name.equals("Name"))
                    continue;
                Integer level = Integer.parseInt(string[1]);
                Attribute attribute = returnAttribute(string[2]);
                Type monsterType = returnType(string[3]);
                Type cardType = returnType(string[4]);
                Integer attack = Integer.parseInt(string[5]);
                Integer defense = Integer.parseInt(string[6]);
                String description = string[7];
                Integer price = Integer.parseInt(string[8]);
                Monster monster = new Monster(name, cardType);
                monster.setLevel(level);
                monster.setMonsterType(monsterType);
                monster.setAttackPower(attack);
                monster.setDefencePower(defense);
                monster.setDescription(description);
                monster.setPrice(price);
                monster.setAttribute(attribute);
                allCards.add(monster);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void csvSpellReader(String spellsAndTrapsCsvFilePath) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(spellsAndTrapsCsvFilePath));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<String[]> spellsAndTraps = csvReader.readAll();
            for (String[] strings : spellsAndTraps) {
                String name = strings[0];
                name = name.trim();
                if (name.equals("Name"))
                    continue;
                String type = strings[1];
                Type property = returnType(strings[2]);
                String description = strings[3];
                String status = strings[4];
                Integer price = Integer.parseInt(strings[5]);
                if (type.equals("Spell")) {
                    Spell spell = new Spell(name, property);
                    spell.setDescription(description);
                    spell.setStatus(status);
                    spell.setPrice(price);
                    allCards.add(spell);
                } else {
                    Trap trap = new Trap(name, property);
                    trap.setStatus(status);
                    trap.setDescription(description);
                    trap.setPrice(price);
                    allCards.add(trap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Type returnType(String input) {
        switch (input) {
            case "Warrior":
                return Type.WARRIOR;
            case "Normal":
                return Type.NORMAL;
            case "Effect":
                return Type.EFFECT;
            case "Fiend":
                return Type.FIEND;
            case "Aqua":
                return Type.AQUA;
            case "Beast":
                return Type.BEAST;
            case "Pyro":
                return Type.PYRO;
            case "Spellcaster":
                return Type.SPELL_CASTER;
            case "Thunder":
                return Type.THUNDER;
            case "Dragon":
                return Type.DRAGON;
            case "Ritual":
                return Type.RITUAL;
            case "Machine":
                return Type.MACHINE;
            case "Rock":
                return Type.ROCK;
            case "Insect":
                return Type.INSECT;
            case "Cyberse":
                return Type.CYBERSE;
            case "Fairy":
                return Type.FAIRY;
            case "Beast-Warrior":
                return Type.BEAST_WARRIOR;
            case "Sea Serpent":
                return Type.SEA_SERPENT;
            case "Continuous":
                return Type.CONTINUOUS;
            case "Quick-Play":
                return Type.QUICK_PLAY;
            case "Field":
                return Type.FIELD;
            case "Equip":
                return Type.EQUIP;
        }
        return null;
    }

    private Attribute returnAttribute(String input) {
        switch (input) {
            case "EARTH":
                return Attribute.EARTH;
            case "WATER":
                return Attribute.WATER;
            case "DARK":
                return Attribute.DARK;
            case "FIRE":
                return Attribute.FIRE;
            case "LIGHT":
                return Attribute.LIGHT;
            case "WIND":
                return Attribute.WIND;
        }
        return null;
    }


    private void iterateThroughArray() {
        for (Card card : allCards) {
            try {
                setImagesForMonsters(card, card.getName());
            } catch (Exception e) {
                setImagesForSpellTraps(card, card.getName());
            }
        }
    }

    private void setImagesForSpellTraps(Card card, String name) {
        try {
            if (name.equals("Magic Jammer")) {
                card.setCardImage(new Image(Objects.requireNonNull
                        (GamePlay.class.getResource("/images/SpellTrap/Magic Jammer.png")).toExternalForm()));
                return;
            }
            card.setCardImage(new Image(Objects.requireNonNull
                    (GamePlay.class.getResource("/images/SpellTrap/" + name
                            + ".jpg")).toExternalForm()));
        } catch (Exception e) {
            System.out.println(card.getName());
        }
    }

    private void setImagesForMonsters(Card card, String name) throws Exception {
        card.setCardImage(new Image(getClass().getResource("/images/monsters/" + name
                + ".jpg").toExternalForm()));
    }

    @Override
    public void execute() {
    }

    private void runShopMenu(String input) {
    }

    public void showAllCards() {
    }

    private void buyCardForUser(Card finalCard) {
    }


    public static void sortCards(ArrayList<Card> allCards) {
        for (int i = allCards.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= allCards.size() - 2; j++) {
                if (allCards.get(j).getName().compareTo(allCards.get(j + 1).getName()) > 0) {
                    Collections.swap(allCards, j, j + 1);
                }
            }
        }
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static Card getCardByName(String name) {
        name = name.trim();
        for (Card card : allCards) {
            if (card.getName().equalsIgnoreCase(name))
                return card;
        }
        return null;
    }

    public void backToMainMenu(ActionEvent actionEvent) throws Exception {
        MainMenuGraphic mainMenuGraphic = new MainMenuGraphic();
        mainMenuGraphic.start(mainStage);
    }

    @FXML
    public void openMonsterShopFirst(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monsterShopFirst.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openMonsterShopSecond() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monsterShopSecond.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("YU-GI-OH");
        mainStage.show();
    }

    public void openMonsterShopThird(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monsterShopThird.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openMonsterShopFourth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monsterShopFourth.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openMonsterShopFifth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/monsterShopFifth.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    public void openSpellTrapFirst(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopFirstPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openSpellTrapShopSecond(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopSecond.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openSpellTrapShopThird(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopThird.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openSpellTrapShopFourth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopFourth.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openSpellTrapShopFifth(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopFifth.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openMadeMonstersShop(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seeMadeMonstersShop.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        anchorPane.setId("anchorPane");
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        Button backButton = new Button("back");
        backButton.setLayoutY(800);
        backButton.setLayoutX(1200);
        Button button;
        int count = 0;
        for (Card card : Shop.getAllCards()) {
            if (card.getUserMade()) {
                ImageView imageView = new ImageView(getClass().getResource("/images/theTrump.jpg").toExternalForm());
                imageView.setFitWidth(152);
                imageView.setFitHeight(223);
                imageView.setLayoutY(225 * count + 50);
                imageView.setLayoutX(count * 200 + 10);
                count += 1;
                button = new Button("buy");
                button.setLayoutY(imageView.getLayoutY() + 233);
                button.setLayoutX(imageView.getLayoutX() + 50);
                button.setId("button");
                button.setCursor(Cursor.HAND);
                anchorPane.getChildren().addAll(imageView, button, backButton);
                button.setOnMouseClicked(event -> {
                    buyNewCard(event);
                });
            }
        }
        scene.getStylesheets().add("/Css/shopMenu.css");
        mainStage.show();
    }

    public void openMadeSpellTrapShop(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seeMadeSpellTrapShop.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        anchorPane.setId("anchorPane");
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        Button backButton = new Button("back");
        backButton.setLayoutX(1200);
        backButton.setLayoutY(800);
        Button button;
        int count = 0;
        for (Card card : Shop.getAllCards()) {
            if (card.getUserMade()) {
                ImageView imageView = new ImageView(getClass().getResource("/images/theTrump.jpg").toExternalForm());
                imageView.setFitHeight(223);
                imageView.setFitWidth(152);
                imageView.setLayoutY(225 * count + 50);
                imageView.setLayoutX(count * 200 + 10);
                count += 1;
                button = new Button("buy");
                button.setLayoutX(imageView.getLayoutX() + 50);
                button.setLayoutY(imageView.getLayoutY() + 233);
                button.setId("button");
                button.setCursor(Cursor.HAND);
                anchorPane.getChildren().addAll(imageView, button, backButton);
                button.setOnMouseClicked(event -> {
                    buyNewCard(event);
                });
            }
        }
        scene.getStylesheets().add("/Css/shopMenu.css");
        mainStage.show();
    }

    public void backToShopMenu(ActionEvent actionEvent) throws Exception {
        ShopGraphic shopGraphic = new ShopGraphic();
        shopGraphic.start(mainStage);
    }

    public void alexandriteDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Alexandrite Dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void axeRaider(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Axe Raider");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void babyDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Baby dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void battleOx(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Battle OX");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void battleWarrior(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Battle warrior");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void beastKingBarbaros(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Beast King Barbaros");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void blueEyeWhiteDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Blue-Eyes white dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void bitron(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Bitron");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void commandKnight(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Command Knight");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void crabTurtle(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Crab Turtle");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void crawlingDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Crawling dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void curtainOfTheDarkOnes(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Curtain of the dark ones");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkBlade(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Dark Blade");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkMagician(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Dark magician");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void exploderDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Exploder Dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void feralImp(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Feral Imp");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void fireyarou(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Fireyarou");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void flameManipulator(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Flame manipulator");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void gateGuardian(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Gate Guardian");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void haniwa(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Haniwa");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void hearldOfCreation(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Herald of Creation");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void heroOfTheEast(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Hero of the east");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void HornImp(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Horn Imp");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void leotron(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Leotron");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void manEaterBug(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Man-Eater Bug");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void marshmallon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Marshmallon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mirageDragon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Mirage Dragon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void scanner(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Scanner");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void silverFang(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Silver Fang");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void skullGuardian(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Skull Guardian");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void slotMachine(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Slot Machine");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void spiralSerpent(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Spiral Serpent");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void advancedRitualArt(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Advanced Ritual Art");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void blackPendant(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Black Pendant");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void callOfTheHaunted(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Call of The Haunted");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void chanceOfHeart(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Change of Heart");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void closedForest(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Closed Forest");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkHole(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Dark Hole");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void forest(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Forest");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void harpiesFeatherDuster(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Harpie's Feather Duster");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magicCylinder(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Magic Cylinder");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magicJammer(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Magic Jammer");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magnumShield(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Magnum Shield");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void messengerOfPeace(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Messenger of peace");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mindCrush(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Mind Crush");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mirrorForce(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Mirror Force");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void monsterReborn(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Monster Reborn");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mysticalSpaceTyphoon(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Mystical space typhoon");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void negateAttack(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Negate Attack");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void potOfGreed(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Pot of Greed");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void pajgeki(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Raigeki");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void ringOfDefense(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Ring of defense");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void solemnWarning(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Solemn Warning");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void spellAbsorption(MouseEvent mouseEvent) {
        Card card = Shop.getCardByName("Spell Absorption");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void supplySquad(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Supply Squad");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void swordOfDark(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Sword of dark destruction");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void swordsOfRevealingLight(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Swords of Revealing Light");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void terraforming(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Terraforming");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void torrentialTribute(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Torrential Tribute");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void twinTwisters(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Twin Twisters");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void timeSeal(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Time Seal");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void trapHole(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Trap Hole");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void umiruka(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Umiiruka");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void unitedWeStand(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("United We Stand");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void vanityEmptiness(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Vanity's Emptiness");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void wallOfRevealingLight(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Wall of Revealing Light");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void yami(ActionEvent actionEvent) {
        Card card = Shop.getCardByName("Yami");
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        } else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void createAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Money Error");
        alert.setContentText("not enough money");
        ImageView imageView = new ImageView("/images/alertHeader.png");
        alert.setGraphic(imageView);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("/Css/dialogs.css");
        dialogPane.getStyleClass().add("myDialog");
        alert.show();
    }

    public void buyNewCard(Event event) {
        Card card = null;
        for (Card card1 : getAllCards()) {
            if (card1.getUserMade()) {
                card = card1;
                break;
            }
        }
        try {
            if (card.getPrice() > loggedUser.getMoney()) {
                createAlert();
            } else {
                loggedUser.getAllCards().add(card);
                loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
            }
        } catch (Exception ignored) {
            newImage.setImage(null);
        }
    }
}

