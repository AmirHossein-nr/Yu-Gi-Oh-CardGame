package View.Menu;

import Controller.Regex;
import Model.*;
import View.GUI.GamePlay;
import View.GUI.MainMenuGraphic;
import View.GUI.ShopGraphic;
import com.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;


public class Shop extends Menu {
    private static ArrayList<Card> allCards;
    public static Stage mainStage;

    static {
        allCards = new ArrayList<>();
    }

    public Shop() {
        super("Shop Menu", null);
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

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
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
        String input = scanner.nextLine();
        input = editSpaces(input);
        runShopMenu(input);
    }

    private void runShopMenu(String input) {

        Matcher matcher;
        if ((matcher = Regex.getMatcher(input, Regex.buyCardInShop)).find()) {

            Card finalCard = getFinalCard(matcher);

            if (finalCard == null) {
                System.out.println("there is no card with this name");
                this.execute();
            } else if (loggedUser.getMoney() < finalCard.getPrice()) {
                System.out.println("not enough money");
                this.execute();
            } else {
                buyCardForUser(finalCard);
                this.execute();
            }
        } else if ((Regex.getMatcher(input, Regex.showAllInShop)).find()) {

            showAllCards();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if (Regex.getMatcher(input, Regex.menuEnter).find()) {
            this.menuEnter(input);
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            showName();
            this.execute();
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
    }

    public void showAllCards() {
        sortAllCards();
        for (Card card : allCards) {
            System.out.println(card.toString());
            System.out.println("-----------------------");
        }
    }

    private void buyCardForUser(Card finalCard) {
        loggedUser.setMoney(loggedUser.getMoney() - finalCard.getPrice());
        Card card = null;
        if (finalCard instanceof Monster) {
            Monster finalMonster = (Monster) finalCard;
            card = (Monster) finalMonster.clone();
        } else if (finalCard instanceof Spell) {
            Spell finalSpell = (Spell) finalCard;
            card = (Spell) finalSpell.clone();
        } else if (finalCard instanceof Trap) {
            Trap finalTrap = (Trap) finalCard;
            card = (Trap) finalTrap.clone();
        }
        loggedUser.getAllCards().add(card);
    }

    public Card getFinalCard(Matcher matcher) {
        Card finalCard = null;
        String name = matcher.group(1);
        name = name.trim();
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                finalCard = card;
                break;
            }
        }
        return finalCard;
    }

    private static void sortAllCards() {
        sortCards(allCards);
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

    public void backToShopMenu(ActionEvent actionEvent) throws Exception {
        ShopGraphic shopGraphic = new ShopGraphic();
        shopGraphic.start(mainStage);
    }

    public void alexandriteDragon(ActionEvent actionEvent) {
        Card card = new Card("Alexandrite Dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void axeRaider(ActionEvent actionEvent) {
        Card card = new Card("Axe Raider", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void babyDragon(ActionEvent actionEvent) {
        Card card = new Card("Baby dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void battleOx(ActionEvent actionEvent) {
        Card card = new Card("Battle OX", Type.BEAST_WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void battleWarrior(ActionEvent actionEvent) {
        Card card = new Card("Battle warrior", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void beastKingBarbaros(ActionEvent actionEvent) {
        Card card = new Card("Beast King Barbaros", Type.BEAST_WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void blueEyeWhiteDragon(ActionEvent actionEvent) {
        Card card = new Card("Blue-Eyes white dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void bitron(ActionEvent actionEvent) {
        Card card = new Card("Bitron", Type.CYBERSE);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void commandKnight(ActionEvent actionEvent) {
        Card card = new Card("Command Knight", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void crabTurtle(ActionEvent actionEvent) {
        Card card = new Card("Crab Turtle", Type.AQUA);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void crawlingDragon(ActionEvent actionEvent) {
        Card card = new Card("Crawling dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void curtainOfTheDarkOnes(ActionEvent actionEvent) {
        Card card = new Card("Curtain of the dark ones", Type.SPELL_CASTER);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkBlade(ActionEvent actionEvent) {
        Card card = new Card("Dark Blade", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkMagician(ActionEvent actionEvent) {
        Card card = new Card("Dark magician", Type.SPELL_CASTER);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void exploderDragon(ActionEvent actionEvent) {
        Card card = new Card("Exploder Dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void feralImp(ActionEvent actionEvent) {
        Card card = new Card("Feral Imp", Type.FIEND);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void fireyarou(ActionEvent actionEvent) {
        Card card = new Card("Fireyarou", Type.PYRO);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void flameManipulator(ActionEvent actionEvent) {
        Card card = new Card("Flame manipulator", Type.SPELL_CASTER);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void gateGuardian(ActionEvent actionEvent) {
        Card card = new Card("Gate Guardian", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void haniwa(ActionEvent actionEvent) {
        Card card = new Card("Haniwa", Type.ROCK);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void hearldOfCreation(ActionEvent actionEvent) {
        Card card = new Card("Herald of Creation", Type.SPELL_CASTER);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void heroOfTheEast(ActionEvent actionEvent) {
        Card card = new Card("Hero of the east", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void HornImp(ActionEvent actionEvent) {
        Card card = new Card("Horn Imp", Type.FIEND);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void leotron(ActionEvent actionEvent) {
        Card card = new Card("Leotron", Type.CYBERSE);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void manEaterBug(ActionEvent actionEvent) {
        Card card = new Card("Man-Eater Bug", Type.INSECT);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void marshmallon(ActionEvent actionEvent) {
        Card card = new Card("Marshmallon", Type.FAIRY);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mirageDragon(ActionEvent actionEvent) {
        Card card = new Card("Mirage Dragon", Type.DRAGON);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void scanner(ActionEvent actionEvent) {
        Card card = new Card("Scanner", Type.MACHINE);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void silverFang(ActionEvent actionEvent) {
        Card card = new Card("Silver Fang", Type.BEAST);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void skullGuardian(ActionEvent actionEvent) {
        Card card = new Card("Skull Guardian", Type.WARRIOR);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void slotMachine(ActionEvent actionEvent) {
        Card card = new Card("Slot Machine", Type.MACHINE);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void spiralSerpent(ActionEvent actionEvent) {
        Card card = new Card("Spiral Serpent", Type.SEA_SERPENT);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void advancedRitualArt(ActionEvent actionEvent) {
        Card card = new Card("Advanced Ritual Art", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void blackPendant(ActionEvent actionEvent) {
        Card card = new Card("Black Pendant", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void callOfTheHaunted(ActionEvent actionEvent) {
        Card card = new Card("Call of The Haunted", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void chanceOfHeart(ActionEvent actionEvent) {
        Card card = new Card("Change of Heart", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void closedForest(ActionEvent actionEvent) {
        Card card = new Card("Closed Forest", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void darkHole(ActionEvent actionEvent) {
        Card card = new Card("Dark Hole", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void forest(ActionEvent actionEvent) {
        Card card = new Card("Forest", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void harpiesFeatherDuster(ActionEvent actionEvent) {
        Card card = new Card("Harpie's Feather Duster", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magicCylinder(ActionEvent actionEvent) {
        Card card = new Card("Magic Cylinder", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magicJammer(ActionEvent actionEvent) {
        Card card = new Card("Magic Jamamer", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void magnumShield(ActionEvent actionEvent) {
        Card card = new Card("Magnum Shield", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void messengerOfPeace(ActionEvent actionEvent) {
        Card card = new Card("Messenger of peace", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mindCrush(ActionEvent actionEvent) {
        Card card = new Card("Mind Crush", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mirrorForce(ActionEvent actionEvent) {
        Card card = new Card("Mirror Force", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void monsterReborn(ActionEvent actionEvent) {
        Card card = new Card("Monster Reborn", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void mysticalSpaceTyphoon(ActionEvent actionEvent) {
        Card card = new Card("Mystical space typhoon", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void negateAttack(ActionEvent actionEvent) {
        Card card = new Card("Negate Attack", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void potOfGreed(ActionEvent actionEvent) {
        Card card = new Card("Pot of Greed", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void pajgeki(ActionEvent actionEvent) {
        Card card = new Card("Raigeki", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void ringOfDefense(ActionEvent actionEvent) {
        Card card = new Card("Ring of defense", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void solemnWarning(ActionEvent actionEvent) {
        Card card = new Card("Solemn Warning", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void spellAbsorption(MouseEvent mouseEvent) {
        Card card = new Card("Spell Absorption", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void supplySquad(ActionEvent actionEvent) {
        Card card = new Card("Supply Squad", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void swordOfDark(ActionEvent actionEvent) {
        Card card = new Card("Sword of dark destruction", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void swordsOfRevealingLight(ActionEvent actionEvent) {
        Card card = new Card("Swords of Revealing Light", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void terraforming(ActionEvent actionEvent) {
        Card card = new Card("Terraforming", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void torrentialTribute(ActionEvent actionEvent) {
        Card card = new Card("Torrential Tribute", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void twinTwisters(ActionEvent actionEvent) {
        Card card = new Card("Twin Twisters", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void timeSeal(ActionEvent actionEvent) {
        Card card = new Card("Time Seal", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void trapHole(ActionEvent actionEvent) {
        Card card = new Card("Trap Hole", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void umiruka(ActionEvent actionEvent) {
        Card card = new Card("Umiiruka", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void unitedWeStand(ActionEvent actionEvent) {
        Card card = new Card("United We Stand", Type.SPELL);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void vanityEmptiness(ActionEvent actionEvent) {
        Card card = new Card("Vanity's Emptiness", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void wallOfRevealingLight(ActionEvent actionEvent) {
        Card card = new Card("Wall of Revealing Light", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void yami(ActionEvent actionEvent) {
        Card card = new Card("Yami", Type.TRAP);
        if (card.getPrice() > loggedUser.getMoney()) {
            createAlert();
        }
        else {
            loggedUser.getAllCards().add(card);
            loggedUser.setMoney(loggedUser.getMoney() - card.getPrice());
        }
    }

    public void createAlert () {
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
}

