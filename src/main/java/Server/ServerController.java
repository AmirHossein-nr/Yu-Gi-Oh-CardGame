package Server;

import Model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerController {

    private static HashMap<String, User> loggedInUsers = new HashMap<>();

    public static synchronized boolean register(String username, String password, String nickName) throws IOException {
        User user = User.getUserByUsername(username);
        if (user != null) return false;
        user = User.getUserByNickname(nickName);
        if (user != null) return false;
        user = new User(username, password, nickName);
        user.setMoney(100000);
        User.allUsers.add(user);
        return true;
    }

    public static boolean login(String username, String password, ObjectOutputStream objectOutputStream) throws IOException {
        for (User user : User.allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                loggedInUsers.put(token, user);
                objectOutputStream.writeUTF("true " + token);
                objectOutputStream.flush();
                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                return true;
            }
        }
        objectOutputStream.writeUTF("false");
        objectOutputStream.flush();
        return false;
    }

    public static boolean logout(String token) {
        if (loggedInUsers.containsKey(token)) {
            loggedInUsers.remove(token);
            return true;
        } else {
            return false;
        }
    }

    public static boolean buyCard(String price, String token) {
        Integer cardPrice = Integer.parseInt(price);
        User user = loggedInUsers.get(token);
        if (user.getMoney() < cardPrice) return false;

        user.setMoney(user.getMoney() - cardPrice);
        return true;
    }
}