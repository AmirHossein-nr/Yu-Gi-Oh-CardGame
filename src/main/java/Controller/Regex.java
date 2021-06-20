package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static String menuEnter = "^menu enter (.*)";
    public static String menuExit = "^menu exit$";
    public static String showCurrentMenu = "^menu show-current$";
    public static String createUser = "^user create(?=.*( --username (\\w+)))(?=.*( --password (\\w+)))(?=.*( --nickname (\\w+)))";
    public static String loginUser = "^user login(?=.*( --username (\\w+)))(?=.*( --password (\\w+)))";
    public static String userLogout = "^user logout$";
    public static String showScoreBoard = "^scoreboard show$";
    public static String changeNickname = "(^profile change --nickname (\\w+)$)|(^profile change -n (\\w+)$)";
    public static String changePassword = "^profile change(?=.*( --password))(?=.*( --current (\\w+)))(?=.*( --new (\\w+)))";
    public static String showCard = "^card show (\\w+)$";
    public static String createDeck = "^deck create (\\w+)$";
    public static String deleteDeck = "^deck delete (\\w+)$";
    public static String activateDeck = "^deck set-activate (\\w+)$";
    public static String addCardToDeck = "^deck add-card(?=.*( --card (\\w+[\\-\\'\\,]*\\s*\\w*\\s*\\w*\\s*\\w*\\s*\\w*)))(?=.*( --deck (\\w+)))(?=.*( --side))?";
    public static String removeCardFromDeck = "^deck rm-card(?=.*( --card (\\w+[\\-\\'\\,]*\\s*\\w*\\s*\\w*\\s*\\w*\\s*\\w*)))(?=.*( --deck (\\w+)))(?=.*( --side))?";
    public static String showAllDecks = "^deck show (--all|-a)$";
    public static String buyCardInShop = "^shop buy (.*)";
    public static String showAllInShop = "^shop show --all$";
    public static String newGame = "^duel (?=.*(--new))(?=.*(--second-player (\\w+)))(?=.*(--rounds (\\d+)))";
    public static String showOneDeck = "^deck show(?=.*( --deck-name (\\w+)))(?=.*( --side))?";
    public static String showCards = "^deck show --cards";
    public static String selectCard = "(?:select -(?:-monster|m) (\\d+)|" + //group 1
            "select -(?:-monster|m) (\\d+) -(?:-opponent|-p)|" + //group 2
            "select -(?:-opponent|-p) -(?:-monster|m) (\\d+)|" + //group 3
            "select -(?:-monster|m) -(?:-opponent|-p) (\\d+)|" + //group 4
            "select -(?:-spell|s) (\\d+)|" + //group 5
            "select -(?:-spell|s) (\\d+) -(?:-opponent|-p)|" + //group 6
            "select -(?:-opponent|-p) -(?:-spell|s) (\\d+)|" + // group 7
            "select -(?:-spell|s) -(?:-opponent|-p) (\\d+)|" + //group 8
            "select -(?:-hand|h) (\\d+)" + //group 9
            "(select -(?:-field|f))|" + //group 10
            "(select -(?:-field|f) -(?:-opponent|-p))|" + //group 11
            "(select -(?:-opponent|-p) -(?:-field|f))|" + //group 12
            "select .+)";
    public static String setPositionAttackDeffence = "set (--position|-p) (attack|defense)";
    public static String attack = "attack (\\d+)";

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
