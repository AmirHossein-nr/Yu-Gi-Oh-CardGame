package Controller;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class JsonController {

    private static JsonController controller = null;

    public static JsonController getInstance() {
        if (controller == null) {
            controller = new JsonController();
        }
        return controller;
    }

    public static void writeAllUsers() {
        User.getAllUsers().forEach(u -> writeToJson("src/main/resources/users" + u.getUsername() + ".json", u));
    }

    public static void readAllUsers() {
        Arrays.stream(Objects.requireNonNull
                (new File("src/main/resources/users").listFiles())).filter(Objects::nonNull)
                .forEach(u -> User.addUser(readAccount("src/main/resources/users/" + u.getName())));
    }

    private static User readAccount(String address) {
        try {
            FileReader reader = new FileReader(address);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(reader);
            return gson.fromJson(bufferedReader, User.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static void writeToJson(String address, Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter writer;
        try {
            writer = new FileWriter(address);
            writer.write(gson.toJson(object));
            writer.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred While Writing To File !\n Please Try Again");
        }
    }
}
