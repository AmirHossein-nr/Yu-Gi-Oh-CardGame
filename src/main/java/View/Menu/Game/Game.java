package View.Menu.Game;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    Scanner scanner;

    User loggedUser;
    User rivalUser;
    User currentUser;
    User winnerOfDuel;
    int numberOfRounds;
    int round = 1;
    int turn = 1;
    Card selectedCard;


    public Game(User loggedUser, User rivalUser, int numberOfRounds, Scanner scanner) {
        this.loggedUser = loggedUser;
        this.rivalUser = rivalUser;
        currentUser = loggedUser;
        this.numberOfRounds = numberOfRounds;
        this.scanner = scanner;
    }

    public void run() {
        while (round <= numberOfRounds) {
            resetPlayersAttributes(currentUser);
            playFirstTurn();
            while (winnerOfDuel == null) {
                playTurn();
            }
            // todo
            round++;
        }
        // todo
    }

    private void playTurn() {
        drawPhaseRun();
        if (winnerOfDuel != null)
            return;
        standbyPhaseRun();
        if (winnerOfDuel != null)
            return;
        mainPhaseOneRun();
        if (winnerOfDuel != null)
            return;
        battlePhaseRun();
        if (winnerOfDuel != null)
            return;
        mainPhaseTwoRun();
        if (winnerOfDuel != null)
            return;
        endPhaseRun();
    }
    private void playFirstTurn() {
        System.out.println(Phase.DRAW);
        printBoard();
        System.out.println(Phase.STANDBY);
        printBoard();
        mainPhaseOneRun();
        endPhaseRun();
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
    private User getOpponentOfCurrentUser() {
        if (currentUser == loggedUser) {
            return rivalUser;
        } else {
            return loggedUser;
        }
    }
    static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }
    private void resetPlayersAttributes(User user) {
        setBoards(loggedUser, rivalUser);
        loggedUser.setLifePoint(8000);
        rivalUser.setLifePoint(8000);
        currentUser = user;
        shuffleDeckZones();
        for (int i = 0; i < 6; i++) {
            drawCard(loggedUser);
            drawCard(rivalUser);
        }
        turn = 1;
    }
    ////////
    private void changeTurn() {
        currentUser = getOpponentOfCurrentUser();
        setAndSummonedCards.clear();
        attackedCards.clear();
        summonedOrSetMonster = null;
    }
    private void shuffleDeckZones() {
        Collections.shuffle(currentUser.getBoard().getDeckZone());
        Collections.shuffle(rivalUser.getBoard().getDeckZone());
    }
    private void drawCard(User user) {
        Card card;
        card = user.getBoard().getDeckZone().get(0);
        user.getBoard().getDeckZone().remove(0);
        user.getBoard().getCardsInHand().add(card);
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
    private ArrayList<Card> setAndSummonedCards = new ArrayList<>();
    private ArrayList<Card> attackedCards = new ArrayList<>();
    ////////
    private void drawPhaseRun() {
        System.out.println(Phase.DRAW);
        printBoard();

    }
    private void standbyPhaseRun() {
        System.out.println(Phase.STANDBY);
        printBoard();

    }
    private void mainPhaseOneRun() {
        System.out.println(Phase.MAIN_ONE);
        printBoard();


    }
    private void battlePhaseRun() {
        System.out.println(Phase.BATTLE);
        printBoard();

    }
    private void mainPhaseTwoRun() {
        System.out.println(Phase.MAIN_TWO);
        printBoard();

    }
    private void endPhaseRun() {
        System.out.println(Phase.END);
        printBoard();

    }
}
