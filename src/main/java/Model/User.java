package Model;

import java.util.ArrayList;

public class User {

    private ArrayList<Deck> decks;
    private String username;
    private String password;
    private String nickName;
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private long score;
    private long lifePoint;
    private long money;
    private boolean isArtificial;

    static {
        allUsers = new ArrayList<>();
    }

    public User(String username, String password, String nickName) {
        this.username = username;
        this.password = password;
        setNickName(nickName);
        decks = new ArrayList<>();
        allUsers.add(this);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserByNickname(String nickName) {
        for (User user : allUsers) {
            if (user.getNickName().equals(nickName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nickName +
                ": " + score;
    }
}
