package Model;

import View.Menu.Shop;

import java.util.ArrayList;
import java.util.Collections;

public class User {

    private static ArrayList<User> allUsers;
    private ArrayList<Deck> decks;
    private ArrayList<Card> allCards;
    private String username;
    private String password;
    private String nickName;
    private long score;
    private int lifePoint;
    private long money;
    private boolean isArtificial;
    private Board board;

    static {
        allUsers = new ArrayList<>();
    }

    public User(String username, String password, String nickName) {
        this.username = username;
        this.password = password;
        setNickName(nickName);
        decks = new ArrayList<>();
        allCards = new ArrayList<>();
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

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Board getBoard() {
        return board;
    }

    public long getMoney() {
        return money;
    }

    public int getLifePoint() {
        return lifePoint;
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

    public long getScore() {
        return score;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
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

    public static void sortAllUsers() {
        for (int i = allUsers.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= allUsers.size() - 2; j++) {
                if (allUsers.get(j).score > allUsers.get(j + 1).score) {
                    Collections.swap(allUsers, j, j + 1);
                }
                if (allUsers.get(j).score == allUsers.get(j + 1).score) {
                    if (allUsers.get(j).getNickName().compareTo(allUsers.get(j + 1).getNickName()) > 0) {
                        Collections.swap(allUsers, j, j + 1);
                    }
                }
            }
        }
    }

    public void sortAllCardsOfUser() {
        Shop.sortCards(allCards);
    }

    @Override
    public String toString() {
        return nickName +
                ": " + score;
    }
}
