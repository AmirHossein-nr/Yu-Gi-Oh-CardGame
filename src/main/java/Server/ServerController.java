package Server;

import Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerController {

    private static ArrayList<User> allUsers = new ArrayList<>();
    private static HashMap<String, User> loggedInUsers = new HashMap<>();

    public static synchronized boolean register(String username, String password, String nickName) throws IOException {
        User user = User.getUserByUsername(username);
        if (user != null) return false;
        user = User.getUserByNickname(nickName);
        if (user != null) return false;
        user = new User(username, password, nickName);
        user.setMoney(100000);
        allUsers.add(user);
        return true;
    }

    public static boolean login(String username, String password) throws IOException {
        for (User user : allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                loggedInUsers.put(token, user);
                ServerMain.objectOutputStream.writeUTF("true");
                ServerMain.objectOutputStream.flush();
                ServerMain.objectOutputStream.writeUTF(token);
                ServerMain.objectOutputStream.flush();
                return true;
            }
        }
        ServerMain.objectOutputStream.writeUTF("false");
        ServerMain.objectOutputStream.flush();
        return false;
    }

    public static boolean buyCard(String price, String token) {
        Integer cardPrice = Integer.parseInt(price);
        User user = loggedInUsers.get(token);
        if (user.getMoney() < cardPrice) return false;

        user.setMoney(user.getMoney() - cardPrice);
        return true;
    }
}