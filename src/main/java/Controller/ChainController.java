package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ChainController {

    private Game game;
    private ArrayList<Card> chain;
    private Scanner scanner;

    private User currentUser;
    private User opponentInChain;
    private Card selectedCard = null;

    public ChainController(Game game, Scanner scanner) {
        this.game = game;
        chain = game.getChain();
        this.scanner = scanner;
        currentUser = game.getOpponentOfCurrentUser();
        opponentInChain = game.getCurrentUser();
    }

    public void run() {
        outer:
        while (true) {
            if (canActivate(currentUser)) {
                System.out.println("now it will be " + currentUser.getUsername() + "'s turn");
                printBoard();
                System.out.println("do you want to activate your trap and spell? (Y/N)");
                while (true) {
                    String answer = editSpaces(scanner.nextLine());
                    if (answer.equals("Y")) {
                        break;
                    } else if (answer.equals("N")) {
                        break outer;
                    } else {
                        System.out.println("enter Y or N");
                    }
                }
                playTurnInChain();
                changeTurnInChain();
            } else {
                break;
            }
        }
        if (chain.size() == 0) {
            return;
        }
        Collections.reverse(chain);
        for (int i = 0; i < chain.size(); i++) {
            Card card = chain.get(i);
            if (card instanceof Spell) {
                ((Spell) card).getEffect().finalActivate(game);
            } else { // so its a trap
                ((Trap) card).getEffect().finalActivate(game);
            }
        }
        chain.clear();
    }

    private void playTurnInChain() {
        String input;
        while (true) {
            System.out.println("enter cancel if you don't want to activate");
            input = scanner.nextLine();
            input = editSpaces(input);
            if (input.equals("select -d")) {
                deselectCard();
            } else if (input.startsWith("select")) {
                select(Regex.getMatcher(input, Regex.selectCard));
            } else if (input.equals("next phase")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("show graveyard")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("surrender")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("summon")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("set")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.matches(Regex.setPositionAttackDefence)) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("flip-summon")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.matches(Regex.attack)) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("attack direct")) {
                System.out.println("it's not your turn to play this kind of moves");
            } else if (input.equals("activate effect")) {
                activateEffectInChain();
            } else if (input.equals("cancel")) {
                System.out.println("canceled");
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    private void activateEffectInChain() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (selectedCard instanceof Monster) {
            System.out.println("activate effect is not for monster cards.");
            return;
        }
        if (!currentUser.getBoard().getAllCards().contains(selectedCard)) {
            System.out.println("This card is not yours");
            return;
        }
        if (currentUser.getBoard().getSpellsAndTrapsZone().contains(selectedCard) && selectedCard.getOccupied()) {
            System.out.println("you have already activated this card");
            return;
        }
        if (selectedCard instanceof Spell) {
            if (((Spell) selectedCard).getEffect().getSpeed() == 1) {
                System.out.println("preparations of this spell are not done yet");
                return;
            }
            if (!((Spell) selectedCard).getEffect().canBeActivated(game)) {
                System.out.println("preparations of this spell are not done yet");
                return;
            }
        } else { // so its a trap
            if (!((Trap) selectedCard).getEffect().canBeActivated(game)) {
                System.out.println("preparations of this trap are not done yet");
                return;
            }
        }

        if (selectedCard instanceof Spell) {
            ((Spell) selectedCard).getEffect().addToChain(game);
        } else { // so its a trap
            ((Trap) selectedCard).getEffect().addToChain(game);
        }
    }

    private boolean canActivate(User user) {
        for (int i = 0; i < 5; i++) {
            Card card = user.getBoard().getSpellsAndTrapsZone().get(i);
            if (card != null) {
                if (!card.getOccupied()) {
                    if (card instanceof Spell) {
                        if (((Spell) card).getEffect().canBeActivated(game)) {
                            return true;
                        }
                    } else { // so its a trap
                        if (((Trap) card).getEffect().canBeActivated(game)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void changeTurnInChain() {
        User user = currentUser;
        currentUser = opponentInChain;
        opponentInChain = user;
    }

    static String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

    public void deselectCard() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
        } else {
            selectedCard = null;
            System.out.println("card deselected");
        }
    }

    private void select(Matcher matcher) {
        if (matcher.find()) {
            try {
                if (matcher.group(1) != null) {
                    int number = Integer.parseInt(matcher.group(1));
                    selectedCard = currentUser.getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(2) != null) {
                    int number = Integer.parseInt(matcher.group(2));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(3) != null) {
                    int number = Integer.parseInt(matcher.group(3));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(4) != null) {
                    int number = Integer.parseInt(matcher.group(4));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(number - 1);
                } else if (matcher.group(5) != null) {
                    int number = Integer.parseInt(matcher.group(5));
                    selectedCard = currentUser.getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(6) != null) {
                    int number = Integer.parseInt(matcher.group(6));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(7) != null) {
                    int number = Integer.parseInt(matcher.group(7));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(8) != null) {
                    int number = Integer.parseInt(matcher.group(8));
                    selectedCard = getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(number - 1);
                } else if (matcher.group(9) != null) {
                    int number = Integer.parseInt(matcher.group(9));
                    if (number <= currentUser.getBoard().getCardsInHand().size() && number > 0) {
                        selectedCard = currentUser.getBoard().getCardsInHand().get(number - 1);
                    } else {
                        System.out.println("invalid selection");
                        return;
                    }
                } else if (matcher.group(10) != null) {
                    selectedCard = currentUser.getBoard().getFieldZone();
                } else if (matcher.group(11) != null) {
                    selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
                } else if (matcher.group(12) != null) {
                    selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
                } else {
                    System.out.println("invalid selection");
                    return;
                }
                System.out.println("card Selected");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("invalid selection");
            }
        } else {
            System.out.println("invalid command");
        }
    }

    public User getOpponentOfCurrentUser() {
        return opponentInChain;
    }

    private void printBoard() {
        StringBuilder board = new StringBuilder();
        boardString(board);
        System.out.println(board);
    }

    public StringBuilder boardString(StringBuilder board) {
        board.append(getOpponentOfCurrentUser().getNickName()).append(":").append(getOpponentOfCurrentUser()
                .getLifePoint()).append("\n");
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
        return board;
    }

    private String toStringInBoard(Card card) {
        if (card == null) {
            return "E";
        }
        if (card instanceof Monster) {
            if (card.getOccupied()) {
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
            if (card.getOccupied()) {
                return "O";
            } else {
                return "H";
            }
        }
    }
}
