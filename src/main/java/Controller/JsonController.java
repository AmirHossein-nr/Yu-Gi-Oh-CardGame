package Controller;

import Model.*;
import View.Menu.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class JsonController {

    public static boolean writeCard(String cardName) {
        String path;
        FileWriter writer;
        try {
            Card myCard;
            path = "src/main/resources/Cards/" + cardName + ".Json";
            myCard = Shop.getCardByName(cardName);

            if (myCard == null) {
                return false;
            }
            Gson gson = new Gson();
            File file = new File(path);
            writer = new FileWriter(file);
            writer.write(gson.toJson(myCard));
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Card readCard(String cardName, String type) {
        return null;
    }


    public static Card readCard(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return gson.fromJson(bufferedReader, Card.class);
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
