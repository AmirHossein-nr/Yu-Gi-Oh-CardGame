package View.Menu;

import Model.*;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.io.SerializablePermission;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Shop extends Menu {
    private static ArrayList<Card> allCards;

    static {
        allCards = new ArrayList<>();
    }

    public Shop(Menu parentMenu) {
        super("Shop Menu", parentMenu);

        final String monstersCsvFilePath = "src/main/resources/Monster.csv";
        final String spellsAndTrapsCsvFilePath = "src/main/resources/SpellTrap.csv";

        csvMonsterReader(monstersCsvFilePath);
        csvSpellReader(spellsAndTrapsCsvFilePath);

    }

    private void csvMonsterReader(String monstersCsvFilePath) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(monstersCsvFilePath));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<String[]> monsters = csvReader.readAll();
            for (String[] string : monsters) {
                String name = string[0];
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
}

