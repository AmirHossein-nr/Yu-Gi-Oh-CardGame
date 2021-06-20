package Controller;

import Model.*;
import View.Menu.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class JsonController {

    public static void writeCard(String cardName, String type) throws IOException {
        String path;
        FileWriter writer;
        try {
            if (type.equalsIgnoreCase("monster")) {
                path = "src/main/resources/monsters/" + cardName + ".Json";
                Monster card = (Monster) Shop.getCardByName(cardName);
                Gson gson = new Gson();
                writer = new FileWriter(path);
                gson.toJson(card);
            } else if (type.equalsIgnoreCase("spell")) {
                path = "src/main/resources/spells/" + cardName + ".Json";
                Spell card = (Spell) Shop.getCardByName(cardName);
                Gson gson = new Gson();
                writer = new FileWriter(path);
                gson.toJson(card);
            } else {
                path = "src/main/resources/traps/" + cardName + ".Json";
                Trap card = (Trap) Shop.getCardByName(cardName);
                Gson gson = new Gson();
                writer = new FileWriter(path);
                gson.toJson(card);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Card readCard(String cardName, String type) {
        if (type.equalsIgnoreCase("monster")) {
            return readMonsterCard("src/main/resources/monsters/" + cardName + ".JSON");
        } else if (type.equalsIgnoreCase("spell")) {
            return readSpellCard("src/main/resources/spells/" + cardName + ".JSON");
        } else {
            return readTrapCard("src/main/resources/traps/" + cardName + ".JSON");
        }
    }


    private static Monster readMonsterCard(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return gson.fromJson(bufferedReader, Monster.class);
        } catch (Exception fileNotFoundException) {
            return null;
        }
    }

    private static Spell readSpellCard(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return gson.fromJson(bufferedReader, Spell.class);
        } catch (Exception fileNotFoundException) {
            return null;
        }
    }

    private static Trap readTrapCard(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return gson.fromJson(bufferedReader, Trap.class);
        } catch (Exception fileNotFoundException) {
            return null;
        }
    }


    public static void writeAllUsers() {
        User.getAllUsers().forEach(user -> writeToJson("src/main/resources/users/" + user.getUsername() + ".JSON", user));
    }


    public static void readAllUsers() {
        Arrays.stream(Objects.requireNonNull(new File("src/main/resources/users").listFiles())).filter(Objects::nonNull)
                .forEach(f -> User.addUser(Objects.requireNonNull
                        (readUser("src/main/resources/users/" + f.getName()))));
    }


    public static void writeToJson(String address, Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(address);
            fileWriter.write(gson.toJson(object));
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    private static User readUser(String address) {
        try {
            FileReader fileReader = new FileReader(address);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return gson.fromJson(bufferedReader, User.class);
        } catch (Exception fileNotFoundException) {
            return null;
        }
    }
}
