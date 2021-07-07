package View.Menu;

import Controller.Regex;
import Model.*;
import View.GUI.GamePlay;
import View.GUI.MainMenuGraphic;
import com.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    public void openSpellTrapShopFirst(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/spellTrapShopFirst.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("YU-GI-OH");
        mainStage.setScene(scene);
        scene.getStylesheets().add("src/main/resources/Css/backgroundOpacity.css");
        mainStage.show();
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
        String css = this.getClass().getResource("/Css/shop.css").toExternalForm();
        scene.getStylesheets().add(css);
        mainStage.show();
    }

    public void alexandriteDragon(ActionEvent actionEvent) {
        //loggedUser.getAllCards().add(new Card())
    }
}

