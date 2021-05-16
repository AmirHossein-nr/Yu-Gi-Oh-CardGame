package View.Menu;

import Controller.Regex;
import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class Duel extends Menu {

    private boolean isGameStarted = false;
    private User rivalUser;
    private User currentUser;
    private int numberOfRounds = 0;
    private boolean hasSummonedOrSet;
    private ArrayList<Card> setAndSummonedCards = new ArrayList<>();
    private ArrayList<Card> attackedCards = new ArrayList<>();
    private Phase phase;
    private boolean isFirstTurn;

    public Duel(Menu parentMenu) {
        super("Duel Menu", parentMenu);
    }

    @Override
    public void execute() {

        if (isGameStarted) {
            if (numberOfRounds == 1) {
                String input = scanner.nextLine();
                input = editSpaces(input);
                playGameWithOneRound(input);
            }
            else {
            }
        } else {
            String input = scanner.nextLine();
            input = editSpaces(input);
            Matcher matcher;
            if (Regex.getMatcher(input, Regex.menuExit).find()) {
                this.menuExit();
            } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
                this.menuEnter(matcher.group(1));
                this.execute();
            } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
                this.showName();
                this.execute();
            } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
                this.logoutUser();
            } else if ((matcher = Regex.getMatcher(input, Regex.newGame)).find()) {
                rivalUser = User.getUserByUsername(matcher.group(3));

                if (rivalUser == null) {
                    System.out.println("there is no player with this username");
                    this.execute();
                } else if (!hasActiveDeck(loggedUser)) {
                    System.out.println(loggedUser.getUsername() + " has no active deck");
                    this.execute();
                } else if (!hasActiveDeck(rivalUser)) {
                    System.out.println(rivalUser.getUsername() + " has no active deck");
                    this.execute();
                } else if (!isValid(loggedUser)) {
                    System.out.println(loggedUser.getUsername() + "’s deck is invalid");
                    this.execute();
                } else if (!isValid(rivalUser)) {
                    System.out.println(rivalUser.getUsername() + "’s deck is invalid");
                    this.execute();
                } else if (Integer.parseInt(matcher.group(5)) != 1 && Integer.parseInt(matcher.group(5)) != 3) {
                    System.out.println("number of rounds is not supported");
                    this.execute();
                }
                numberOfRounds = Integer.parseInt(matcher.group(5));
                setBoards(loggedUser, rivalUser);
                loggedUser.setLifePoint(8000);
                rivalUser.setLifePoint(8000);
                currentUser = loggedUser;
                phase = Phase.DRAW;
                shuffleDeckZones();
                isFirstTurn = true;
                isGameStarted = true;
                this.execute();
            } else {
                System.out.println("invalid command!");
                this.execute();
            }

            //TODO ai duel
        }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    private User getOpponentOfCurrentUser() {
        if (currentUser == loggedUser) {
            return rivalUser;
        } else {
            return loggedUser;
        }
    }

    private boolean hasActiveDeck(User user) {

        for (Deck deck : user.getDecks()) {
            if (deck.getActive())
                return true;
        }
        return false;
    }

    private boolean isValid(User user) {

        for (Deck deck : user.getDecks()) {
            if (deck.getActive()) {
                return deck.getMainDeck().getValid();
            }
        }
        return false;
    }

    private void setBoards(User user1, User user2) {
        Board board1 = new Board();
        Board board2 = new Board();

        for (Deck deck : user1.getDecks()) {
            if (deck.isActive() || deck.getValid()) {
                board1.setDeck(deck);
                break;
            }
        }
        board1.setZones();
        for (Deck deck : user2.getDecks()) {
            if (deck.isActive() || deck.getValid()) {
                board2.setDeck(deck);
                break;
            }
        }
        board2.setZones();
        user1.setBoard(board1);
        user2.setBoard(board2);
    }

    private void playGameWithOneRound(String input) {
        printBoard();

    }

    private void playGameWithThreeRound() {

    }

    private void printBoard() {

        StringBuilder board = new StringBuilder();
        board.append(getOpponentOfCurrentUser().getNickName()).append(":").append(getOpponentOfCurrentUser().getLifePoint()).append("\n");
        board.append("\t");
        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getCardsInHand().size(); i++) {
            board.append("c\t");
        }
        board.append("\n");
        board.append(getOpponentOfCurrentUser().getBoard().getDeckZone().size()).append("\n\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(3))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(1))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(0))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(2))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(4))).append("\n");
        board.append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(3))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(1))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(0))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(2))).append("\t");
        board.append(toStringInBoard(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(4))).append("\n");
        board.append(getOpponentOfCurrentUser().getBoard().getGraveYard().size()).append("\t\t\t\t\t\t");
        if (getOpponentOfCurrentUser().getBoard().getFieldZone() instanceof Spell) {
            board.append("O");
        } else {
            board.append("E");
        }
        board.append("\n\n--------------------------\n\n");
        if (currentUser.getBoard().getFieldZone() instanceof Spell) {
            board.append("O");
        } else {
            board.append("E");
        }
        board.append("\t\t\t\t\t\t").append(currentUser.getBoard().getGraveYard().size()).append("\n\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(4))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(2))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(0))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(1))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getMonstersZone().get(3))).append("\n");
        board.append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(4))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(2))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(0))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(1))).append("\t");
        board.append(toStringInBoard(currentUser.getBoard().getSpellsAndTrapsZone().get(3))).append("\n");
        board.append("\t\t\t\t\t\t").append(currentUser.getBoard().getDeckZone().size()).append("\n");
        board.append("\t");
        for (int i = 0; i < currentUser.getBoard().getCardsInHand().size(); i++) {
            board.append("c\t");
        }
        board.append("\n");
        board.append(currentUser.getNickName()).append(":").append(currentUser.getLifePoint()).append("\n");

    }

    private String toStringInBoard(Card card) {
        if (card instanceof Monster) {

            if (card == null) {
                return "E";
            } else if (card.getOccupied()) {
                if (card.getAttackPosition()) {
                    return "OO";
                } else {
                    return "DO";
                }
            } else {
                if (card.getAttackPosition()) {
                    return "OH";
                } else {
                    return "DH";
                }
            }
        } else {
            if (card == null) {
                return "E";
            } else if (card.getOccupied()) {
                return "O";
            } else {
                return "H";
            }
        }
    }

    private void changeTurn() {
        currentUser = getOpponentOfCurrentUser();
        setAndSummonedCards.clear();
        attackedCards.clear();
        hasSummonedOrSet = false;
    }

    private void shuffleDeckZones() {
        Collections.shuffle(currentUser.getBoard().getDeckZone());
        Collections.shuffle(rivalUser.getBoard().getDeckZone());
    }
}
