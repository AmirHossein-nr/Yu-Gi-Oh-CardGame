package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static String menuEnter = "^menu enter (\\w+)";
    public static String menuExit = "^menu exit$";
    public static String showCurrentMenu = "^menu show-current$";
    public static String createUser = "^$";
    public static String loginUser = "^$";
    public static String userLogout = "^user logout$";
    public static String showScoreBoard = "^scoreboard show$";
    public static String changeNickname = "(^profile change --nickname (\\w+)$)|(^profile change -n (\\w+)$)";
    public static String changePassword = " ";
    public static String showCard = "^card show (\\w+)$";
    public static String createDeck = "^deck create (\\w+)$";
    public static String deleteDeck = "^deck delete (\\w+)$";
    public static String activateDeck = "^deck set-activate (\\w+)$";
    public static String addCardToDeck = " ";
    public static String removeCardFromDeck = " ";
    public static String showAllDecks = "^deck show (--all|-a)$";

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
