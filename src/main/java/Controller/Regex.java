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
    public static String addCardToDeck = "^deck add-card(?=.*( --card (\\w+)))(?=.*( --deck (\\w+)))(?=.*( --side))?";
    public static String removeCardFromDeck = "^deck rm-card(?=.*( --card (\\w+)))(?=.*( --deck (\\w+)))(?=.*( --side))?";
    public static String showAllDecks = "^deck show (--all|-a)$";
    public static String buyCardInShop = "^shop buy (.*)$";
    public static String showAllInShop = "^shop show --all$";
    public static String newGame = "^duel (?=.*(--new))(?=.*(--second-player (\\w+)))(?=.*(--rounds (\\d+)))";
    public static String showOneDeck = "^deck show(?=.*( --deck-name (\\w+)))(?=.*( --side))?";
    public static String showCards = "^deck show --cards";

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
