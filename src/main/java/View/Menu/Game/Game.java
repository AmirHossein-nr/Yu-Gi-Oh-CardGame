package View.Menu.Game;

import Controller.Regex;
import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {
    Scanner scanner;

    User loggedUser;
    User rivalUser;
    User currentUser;
    User winnerOfDuel;
    int numberOfRounds;
    int round = 1;
    int turn = 1;
    Phase currentPhase = Phase.DRAW;
    Card selectedCard;
    Card normalSummonOrSetCard = null;
    //private ArrayList<Card> setAndSummonedCards = new ArrayList<>();
    ArrayList<Card> attackedCards = new ArrayList<>();
    Spell activatedRitualCard = null;



    public Game(User loggedUser, User rivalUser, int numberOfRounds, Scanner scanner) {
        this.loggedUser = loggedUser;
        this.rivalUser = rivalUser;
        currentUser = loggedUser;
        this.numberOfRounds = numberOfRounds;
        this.scanner = scanner;
        loggedUser.setLifePoint(8000);
        rivalUser.setLifePoint(8000);
    }

    public void run() {
        while (round <= numberOfRounds) {
            resetPlayersAttributes(currentUser);
            playFirstTurn();
            turn++;
            while (winnerOfDuel == null) {
                playTurn();
                turn++;
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

    private void shuffleDeckZones() {
        Collections.shuffle(currentUser.getBoard().getDeckZone());
        Collections.shuffle(rivalUser.getBoard().getDeckZone());
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

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public void deselectCard() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
        } else {
            setSelectedCard(null);
            System.out.println("card deselected");
        }
    }

    ////////
    private void changeTurn() {
        currentUser = getOpponentOfCurrentUser();
        //setAndSummonedCards.clear();
        attackedCards.clear();
        normalSummonOrSetCard = null;
    }
    ////////

    private void drawPhaseRun() {
        currentPhase = Phase.DRAW;
        System.out.println(Phase.DRAW);
        printBoard();
        if (!canCurrentUserDraw()) {
            winnerOfDuel = getOpponentOfCurrentUser();
            return;
        } else {
            drawCard(currentUser);
        }
        String input;
        while (true) {
            input = scanner.nextLine();
            input = editSpaces(input);
            if (Regex.getMatcher(input, Regex.selectCard).find()) {
                // todo
            } else if (input.equals("select -d")) {
                deselectCard();
            } else if (input.equals("next phase")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }
    private boolean canCurrentUserDraw() {
        if (currentUser.getBoard().getDeckZone().size() != 0) {
            return true;
        } else {
            return false;
        }
    }
    private void drawCard(User user) {
        Card card;
        card = user.getBoard().getDeckZone().get(0);
        user.getBoard().getDeckZone().remove(0);
        user.getBoard().getCardsInHand().add(card);
        System.out.println("new card added to the hand :" + card.getName());
    }



    private void standbyPhaseRun() {
        currentPhase = Phase.STANDBY;
        System.out.println(Phase.STANDBY);
        printBoard();

    }



    private void mainPhaseOneRun() {
        currentPhase = Phase.MAIN_ONE;
        System.out.println(Phase.MAIN_ONE);
        printBoard();
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            input = editSpaces(input);
            if (Regex.getMatcher(input, Regex.selectCard).find()) {
                // todo
            } else if (input.equals("select -d")) {
                deselectCard();
            } else if (input.equals("next phase")) {
                if (activatedRitualCard != null) {
                    System.out.println("you should ritual summon right now");
                    continue;
                } else {
                    return;
                }
            } else if (input.equals("summon")) {

            }
        }
    }
    private void summon() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!(selectedCard instanceof Monster) || !currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            System.out.println("you can’t summon this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        Monster monster = (Monster) selectedCard;
        if (activatedRitualCard != null) {
            if (monster.getCardType() == Type.RITUAL) {
                ritualSummon(monster);
            } else {
                System.out.println("you should ritual summon right now");
            }
            return;
        }
        if (selectedCard.getName().equals("Gate Guardian")) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 3) {
                System.out.println("there are not enough cards for tribute");
            } else {
                tributeSummon(3, true);
                return;
            }
        }
        if (selectedCard.getName().equals("The Tricky")) {
            System.out.println("do you want to normal summon or special summon? (answer with \"normal\"/\"special\")");
            String answer;
            while (true) {
                answer = scanner.nextLine();
                answer = editSpaces(answer);
                if (answer.equals("normal")) {
                    if (normalSummonOrSetCard != null) {
                        System.out.println("you already summoned/set on this turn");
                        return;
                    } else {
                        if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                            System.out.println("there are not enough cards for tribute");
                            return;
                        } else {
                            tributeSummon(1, false);
                            return;
                        }
                    }
                } else if (answer.equals("special")) {
                    if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                        System.out.println("monster card zone is full");
                        return;
                    }
                    if (currentUser.getBoard().getCardsInHand().size() < 2) {
                        System.out.println("there are not enough cards for tribute");
                        return;
                    }
                    specialSummonTheTricky();
                    return;
                } else if (answer.equals("cancel")) {
                    System.out.println("action canceled");
                    return;
                } else {
                    System.out.println("please type normal or special (or cancel)");
                }
            }
        }
        if (normalSummonOrSetCard != null) {
            System.out.println("you already summoned/set on this turn");
            return;
        }
        if (monster.getLevel() <= 4) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                System.out.println("monster card zone is full");
            } else {
                addMonsterFromHandToMonsterZone(selectedCard, true, true);
                System.out.println("summoned successfully");
            }
        } else if (monster.getLevel() == 5 || monster.getLevel() == 6) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                System.out.println("there are not enough cards for tribute");
            } else {
                tributeSummon(1, false);
            }
        } else if (monster.getLevel() > 6) {
            if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                System.out.println("there are not enough cards for tribute");
            } else {
                tributeSummon(2, false);
            }
        }
    }
    private void addMonsterFromHandToMonsterZone(Card monsterCard, Boolean isOccupied, Boolean isAttackPosition) {
        currentUser.getBoard().getCardsInHand().remove(monsterCard);
        for (Card card : currentUser.getBoard().getMonstersZone()) {
            if (card == null) {
                card = monsterCard;
            }
        }
        monsterCard.setOccupied(isOccupied);
        monsterCard.setAttackPosition(isAttackPosition);
    }
    // todo
    private void ritualSummon(Monster monster) {
    }
    private void tributeSummon(int tributeNumber, boolean isSpecial) {
        System.out.println("enter number of " + tributeNumber + " cards in monster zone to tribute (or cancel)");
        ArrayList<Card> monstersToTribute = new ArrayList<>();
        String numberString;
        while (monstersToTribute.size() < tributeNumber) {
            numberString = scanner.nextLine();
            numberString = editSpaces(numberString);
            if (numberString.equals("cancel")) {
                System.out.println("action canceled");
                return;
            } else if (!numberString.matches("\\d+")) {
                System.out.println("enter a number");
            } else {
                int number = Integer.parseInt(numberString);
                if (number < 1 || number > 5) {
                    System.out.println("enter a correct number");
                } else if (monstersToTribute.contains(currentUser.getBoard().getMonstersZone().get(number - 1))) {
                    System.out.println("This card is already selected");
                } else if (currentUser.getBoard().getMonstersZone().get(number - 1) == null) {
                    System.out.println("there is no monster on this address");
                } else {
                    monstersToTribute.add(currentUser.getBoard().getMonstersZone().get(number - 1));
                }
            }
        }
        for (Card card : monstersToTribute) {
            tributeMonster(card);
        }
        addMonsterFromHandToMonsterZone(selectedCard, true, true);
        if (!isSpecial) {
            normalSummonOrSetCard = selectedCard;
        }
        System.out.println("summoned successfully");
    }
    private void tributeMonster(Card monsterCard) {
        for (Card card : currentUser.getBoard().getMonstersZone()) {
            if (card == monsterCard) {
                card = null;
                currentUser.getBoard().getGraveYard().add(monsterCard);
            }
        }
    }
    private void specialSummonTheTricky() {
        System.out.println("Enter the number of card in your hand to tribute (or cancel)");
        String numberString;
        while (true) {
            numberString = scanner.nextLine();
            numberString = editSpaces(numberString);
            if (numberString.matches("\\d+")) {
                int number = Integer.parseInt(numberString);
                if (number >= 1 && number <= currentUser.getBoard().getCardsInHand().size()) {
                    Card cardToTribute = currentUser.getBoard().getCardsInHand().get(number - 1);
                    if (cardToTribute == selectedCard) {
                        System.out.println("You cant tribute this card select another card");
                    } else {
                        currentUser.getBoard().getCardsInHand().remove(cardToTribute);
                        currentUser.getBoard().getGraveYard().add(cardToTribute);
                        currentUser.getBoard().getCardsInHand().remove(selectedCard);
                        for (Card card : currentUser.getBoard().getMonstersZone()) {
                            if (card == null) {
                                card = selectedCard;
                            }
                        }
                        System.out.println("summoned successfully");
                        return;
                    }
                } else {
                    System.out.println("please enter a correct number");
                }
            } else if (numberString.equals("cancel")) {
                System.out.println("action canceled");
                return;
            } else {
                System.out.println("enter a number");
            }
        }
    }



    private void battlePhaseRun() {
        currentPhase = Phase.BATTLE;
        System.out.println(Phase.BATTLE);
        printBoard();

    }



    private void mainPhaseTwoRun() {
        currentPhase = Phase.MAIN_TWO;
        System.out.println(Phase.MAIN_TWO);
        printBoard();

    }



    private void endPhaseRun() {
        currentPhase = Phase.END;
        System.out.println(Phase.END);
        changeTurn();
        System.out.println("its " + currentUser.getNickName() + "’s turn");
    }
}
