package Model;

import java.util.ArrayList;

public class User {

    private ArrayList<Deck> decks;
    private String username;
    private String password;
    private String nickName;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private long score;
    private long lifePoint;
    private long money;
    private boolean isArtificial;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return nickName +
                ": " + score;
    }
}
