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
    User winnerOfDuel = null;
    int numberOfRounds;
    int round = 1;
    int turn = 1;
    Phase currentPhase = Phase.DRAW;
    Card selectedCard;
    Card normalSummonOrSetCard = null;
    ArrayList<Card> putOnMonsterZoneCards = new ArrayList<>();
    ArrayList<Card> putOnSpellTrapZoneCards = new ArrayList<>();
    ArrayList<Card> attackedCards = new ArrayList<>();
    ArrayList<Card> setPositionedCards = new ArrayList<>();
    Spell activatedRitualCard = null;



    public Game(User loggedUser, User rivalUser, int numberOfRounds, Scanner scanner) {
        this.loggedUser = loggedUser;
        this.rivalUser = rivalUser;
        currentUser = loggedUser;
        this.numberOfRounds = numberOfRounds;
        this.scanner = scanner;
        loggedUser.setLifePoint(8000);
        rivalUser.setLifePoint(8000);
        loggedUser.setMaxLifePoint(0);
        rivalUser.setMaxLifePoint(0);
        loggedUser.setNumberOfWinsInGame(0);
        rivalUser.setNumberOfWinsInGame(0);
    }

    public void run() {
        while (round <= numberOfRounds) {
            if (numberOfRounds == 3) {
                if (loggedUser.getNumberOfWinsInGame() == 2 || rivalUser.getNumberOfWinsInGame() == 2) {
                    break;
                }
            }
            if (round == 1) {
                resetPlayersAttributes(loggedUser);
            } else {
                if (winnerOfDuel == loggedUser) {
                    resetPlayersAttributes(rivalUser);
                } else {
                    resetPlayersAttributes(loggedUser);
                }
            }
            playFirstTurn();
            turn++;
            while (winnerOfDuel == null) {
                playTurn();
                turn++;
            }
            finishRound();
            round++;
        }
        finishGame();
    }

    private void finishRound() {
        if (loggedUser.getMaxLifePoint() < loggedUser.getLifePoint()) {
            loggedUser.setMaxLifePoint(loggedUser.getLifePoint());
        }
        if (rivalUser.getMaxLifePoint() < rivalUser.getLifePoint()) {
            rivalUser.setMaxLifePoint(rivalUser.getLifePoint());
        }
        winnerOfDuel.setNumberOfWinsInGame(winnerOfDuel.getNumberOfWinsInGame() + 1);
        User loserOfDuel;
        if (winnerOfDuel == loggedUser) {
            loserOfDuel = rivalUser;
        } else {
            loserOfDuel = loggedUser;
        }
        System.out.println(winnerOfDuel.getUsername() + " won the game and the score is: " + winnerOfDuel.getNumberOfWinsInGame() + "-" + loserOfDuel.getNumberOfWinsInGame());
    }
    private void finishGame() {
        User winner;
        User loser;
        if (loggedUser.getNumberOfWinsInGame() > rivalUser.getNumberOfWinsInGame()) {
            winner = loggedUser;
            loser = rivalUser;
        } else {
            winner = rivalUser;
            loser = loggedUser;
        }
        System.out.println(winner.getUsername() + " won the whole match with score: " + winner.getNumberOfWinsInGame() + "-" + loser.getNumberOfWinsInGame());
        /* todo score and money
        * score of winner is ( numberOfRounds * 1000 )
        * money of winner is ( numberOfRounds * ( 1000 + winner.getMaxLifePoint ) )
        * loser gets no score
        * money of loser if ( numberOfRounds * 100 )
        * numberOfRounds is either 1 or 3
        */
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
        winnerOfDuel = null;
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
        board1.getAllCards().addAll(board1.getDeck().getMainDeck().getCardsInMainDeck());
        board1.getAllCards().addAll(board1.getDeck().getSideDeck().getCardsInSideDeck());
        board2.getAllCards().addAll(board2.getDeck().getMainDeck().getCardsInMainDeck());
        board2.getAllCards().addAll(board2.getDeck().getSideDeck().getCardsInSideDeck());
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

    private void changeTurn() {
        currentUser = getOpponentOfCurrentUser();
        //setAndSummonedCards.clear();
        attackedCards.clear();
        normalSummonOrSetCard = null;
        putOnMonsterZoneCards.clear();
        setPositionedCards.clear();
    }

    private void select(Matcher matcher) {
        if (matcher.find()) {
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
                }
            } else if (matcher.group(10) != null) {
                selectedCard = currentUser.getBoard().getFieldZone();
            } else if (matcher.group(11) != null) {
                selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
            } else if (matcher.group(12) != null) {
                selectedCard = getOpponentOfCurrentUser().getBoard().getFieldZone();
            } else {
                System.out.println("invalid selection");
            }
        } else {
            System.out.println("invalid command");
        }
    }



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
            if (input.equals("select -d")) {
                deselectCard();
            } else if (input.startsWith("select")) {
                select(Regex.getMatcher(input, Regex.selectCard));
            } else if (input.equals("next phase")) {
                return;
            } else if (input.equals("show graveyard")) {
                showGraveyard();
            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
                showSelectedCard();
            } else if (input.equals("surrender")) {
                winnerOfDuel = getOpponentOfCurrentUser();
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
            if (input.equals("select -d")) {
                deselectCard();
            } else if (input.startsWith("select")) {
                select(Regex.getMatcher(input, Regex.selectCard));
            } else if (input.equals("next phase")) {
                if (activatedRitualCard != null) {
                    System.out.println("you should ritual summon right now");
                    continue;
                } else {
                    return;
                }
            } else if (input.equals("summon")) {
                summon();
            } else if (input.equals("set")) {
                set();
            } else if (input.matches(Regex.setPositionAttackDeffence)) {
                setPositionAttackDefense(input);
            } else if (input.equals("flip-summon")) {
                flipSummon();
            } else if (input.equals("show graveyard")) {
                showGraveyard();
            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
                showSelectedCard();
            } else if (input.equals("surrender")) {
                winnerOfDuel = getOpponentOfCurrentUser();
                return;
            } else {
                System.out.println("invalid command");
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
        if (selectedCard.getName().equals("Beast King Barbaros")) {
            System.out.println("do you want to summon this card with \"0\" or \"2\" or \"3\"  tributes?");
            String answer;
            while (true) {
                answer = scanner.nextLine();
                answer = editSpaces(answer);
                if (answer.equals("0")) {
                    if (normalSummonOrSetCard != null) {
                        System.out.println("you already summoned/set on this turn");
                    } else if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                        System.out.println("monster card zone is full");
                    } else {
                        ((Monster) selectedCard).setAttackPower(1900);
                        addMonsterFromHandToMonsterZone(selectedCard, true, true);
                        System.out.println("summoned successfully");
                        normalSummonOrSetCard = selectedCard;
                        selectedCard = null;
                    }
                } else if (answer.equals("2")) {
                    if (normalSummonOrSetCard != null) {
                        System.out.println("you already summoned/set on this turn");
                    } else if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                        System.out.println("there are not enough cards for tribute");
                    } else {
                        tributeSummon(2, false);
                    }
                } else if (answer.equals("3")) {
                    if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                        System.out.println("there are not enough cards for tribute");
                    } else {
                        // todo are cards that enemy control monsters or everything also here we are moving cards to grave yard so they should de active
                        System.out.println("\"attack\" or \"defence\"?");
                        String answer1 = scanner.nextLine();
                        while (!answer1.equals("attack") && !answer1.equals("defence") && !answer1.equals("cancel")) {
                            System.out.println("type \"attack\" or \"defence\" or cancel");
                            answer1 = scanner.nextLine();
                        }
                        if (answer1.equals("cancel")) {
                            System.out.println("action canceled");
                            return;
                        } else if (answer1.equals("attack")) {
                            tributeSummon(3, true);
                        } else {
                            tributeSet(3, true);
                        }
                        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                            if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                                getOpponentOfCurrentUser().getBoard().getGraveYard().add(getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i));
                                getOpponentOfCurrentUser().getBoard().getMonstersZone().set(i, null);
                            }
                        }
                        for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().size(); i++) {
                            if (getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i) != null) {
                                getOpponentOfCurrentUser().getBoard().getGraveYard().add(getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().get(i));
                                getOpponentOfCurrentUser().getBoard().getSpellsAndTrapsZone().set(i, null);
                            }
                        }
                        if (getOpponentOfCurrentUser().getBoard().getFieldZone() != null) {
                            Card zoneSpell = getOpponentOfCurrentUser().getBoard().getFieldZone();
                            getOpponentOfCurrentUser().getBoard().getGraveYard().add(zoneSpell);
                            getOpponentOfCurrentUser().getBoard().setFieldZone(null);
                        }
                        System.out.println("destroyed all cards that opponent control");
                    }
                } else if (answer.equals("cancel")) {
                    System.out.println("action canceled");
                    return;
                } else {
                    System.out.println("type \"0\" or \"2\" or \"3\" or cancel");
                }
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
                if (selectedCard.getName().equals("Terratiger, the Empowered Warrior")) {
                    if (currentUser.getBoard().numberOfMonstersOnBoard() < 5) {
                        activateTerratiger();
                    }
                }
                normalSummonOrSetCard = selectedCard;
                selectedCard = null;

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
    private void activateTerratiger() {
        System.out.println("do you want to summon a level 4 or less monster from your hand in defence position? (\"yes\"/\"no\")");
        while (true) {
            String answer = scanner.nextLine();
            answer = editSpaces(answer);
            if (answer.equals("yes")) {
                System.out.println("type number of the monster you want to summon");
                while (true) {
                    String number = scanner.nextLine();
                    number = editSpaces(number);
                    if (number.matches("\\d+")) {
                        int n = Integer.parseInt(number);
                        if (n > 0 && n <= currentUser.getBoard().getCardsInHand().size()) {
                            Card card = currentUser.getBoard().getCardsInHand().get(n - 1);
                            if (!(card instanceof Monster)) {
                                System.out.println("select a monster");
                            } else if (((Monster) card).getLevel() > 4) {
                                System.out.println("monster's level is more than 4");
                            } else {
                                addMonsterFromHandToMonsterZone(card, true, false);
                                System.out.println("special summon successful");
                                return;
                            }
                        } else {
                            System.out.println("type a correct number");
                        }
                    } else if (number.equals("cancel")) {
                        return;
                    } else {
                        System.out.println("type a number or cancel");
                    }
                }
            } else if (answer.equals("no")) {
                return;
            } else {
                System.out.println("type yes or no");
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
        putOnMonsterZoneCards.add(monsterCard);
    }
    private void ritualSummon(Monster monster) {
    } // todo
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
        for (int i = 0; i < monstersToTribute.size(); i++) {
            tributeMonster(monstersToTribute.get(i));
        }
        addMonsterFromHandToMonsterZone(selectedCard, true, true);
        if (!isSpecial) {
            normalSummonOrSetCard = selectedCard;
        }
        System.out.println("summoned successfully");
        selectedCard = null;
    }
    private void tributeSet(int tributeNumber, boolean isSpecial) {
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
        for (int i = 0; i < monstersToTribute.size(); i++) {
            tributeMonster(monstersToTribute.get(i));
        }
        addMonsterFromHandToMonsterZone(selectedCard, true, true);
        if (!isSpecial) {
            normalSummonOrSetCard = selectedCard;
        }
        System.out.println("set successfully");
        selectedCard = null;
    }
    private void tributeMonster(Card monsterCard) {
        for (int i = 0; i < currentUser.getBoard().getMonstersZone().size(); i++) {
            if (currentUser.getBoard().getMonstersZone().get(i) == monsterCard) {
                currentUser.getBoard().getMonstersZone().set(i, null);
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
                        addMonsterFromHandToMonsterZone(selectedCard, true, true);
                        System.out.println("summoned successfully");
                        selectedCard = null;
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
    private void set() {
        if (activatedRitualCard != null) {
            System.out.println("you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!currentUser.getBoard().getCardsInHand().contains(selectedCard)) {
            System.out.println("you can’t summon this card");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        if (selectedCard instanceof Monster) {
            Monster monster = (Monster) selectedCard;

            if (normalSummonOrSetCard != null) {
                System.out.println("you already summoned/set on this turn");
                return;
            }
            if (monster.getLevel() <= 4) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() == 5) {
                    System.out.println("monster card zone is full");
                } else {
                    addMonsterFromHandToMonsterZone(selectedCard, false, false);
                    System.out.println("set successfully");
                    normalSummonOrSetCard = selectedCard;
                    selectedCard = null;
                }
            } else if (monster.getLevel() == 5 || monster.getLevel() == 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 1) {
                    System.out.println("there are not enough cards for tribute");
                } else {
                    tributeSet(1, false);
                }
            } else if (monster.getLevel() > 6) {
                if (currentUser.getBoard().numberOfMonstersOnBoard() < 2) {
                    System.out.println("there are not enough cards for tribute");
                } else {
                    tributeSet(2, false);
                }
            }
        } else {
            // todo selected card is instance of spell or trap
            if (currentUser.getBoard().numberOfSpellAndTrapsOnBoard() == 5) {
                System.out.println("spell card zone is full");
                return;
            }
            addSpellOrTrapFromHandToZone(selectedCard, false);
            System.out.println("set successfully");
            selectedCard = null;
        }
    }
    private void addSpellOrTrapFromHandToZone(Card SpellOrTrapZone, boolean isOccupied) {
        currentUser.getBoard().getCardsInHand().remove(SpellOrTrapZone);
        for (Card card : currentUser.getBoard().getSpellsAndTrapsZone()) {
            if (card == null) {
                card = SpellOrTrapZone;
            }
        }
        SpellOrTrapZone.setOccupied(isOccupied);
        putOnSpellTrapZoneCards.add(SpellOrTrapZone);
    }
    private void setPositionAttackDefense(String input) {
        if (activatedRitualCard != null) {
            System.out.println("you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            System.out.println("you can’t change this card position");
            return;
        }
        if (!selectedCard.getOccupied()) {
            System.out.println("you can’t change this card position");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        String[] inputSplit = input.split("\\s");
        String attackOrDefense = inputSplit[2];
        if ((selectedCard.getAttackPosition() && attackOrDefense.equals("attack")) || (!selectedCard.getAttackPosition() && attackOrDefense.equals("defense"))) {
            System.out.println("this card is already in the wanted position");
            return;
        }
        if (setPositionedCards.contains(selectedCard)) {
            System.out.println("you already changed this card position in this turn");
            return;
        }
        selectedCard.setAttackPosition(attackOrDefense.equals("attack"));
        System.out.println("monster card position changed successfully");
        setPositionedCards.add(selectedCard);
        selectedCard = null;
    }
    private void flipSummon() {
        if (activatedRitualCard != null) {
            System.out.println("you should ritual summon right now");
            return;
        }
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            System.out.println("you can’t change this card position");
            return;
        }
        if (!(currentPhase == Phase.MAIN_ONE || currentPhase == Phase.MAIN_TWO)) {
            System.out.println("action not allowed in this phase");
            return;
        }
        if ((!selectedCard.getAttackPosition() && !selectedCard.getOccupied()) || putOnMonsterZoneCards.contains(selectedCard)) {
            System.out.println("you can’t flip summon this card");
            return;
        }
        selectedCard.setAttackPosition(true);
        selectedCard.setOccupied(true);
        System.out.println("flip summoned successfully");
        // todo flip effects
    }



    private void battlePhaseRun() {
        currentPhase = Phase.BATTLE;
        System.out.println(Phase.BATTLE);
        String input;
        while (true) {
            printBoard();
            input = scanner.nextLine();
            input = editSpaces(input);
            if (input.equals("select -d")) {
                deselectCard();
            } else if (input.startsWith("select")) {
                select(Regex.getMatcher(input, Regex.selectCard));
            } else if (input.equals("next phase")) {
                return;
            } else if (input.matches(Regex.attack)) {
                if (attack(input)) {
                    return;
                }
            } else if (input.equals("show graveyard")) {
                showGraveyard();
            } else if (input.equals("attack direct")) {
                if (directAttack()) {
                    return;
                }
            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
                showSelectedCard();
            } else if (input.equals("surrender")) {
                winnerOfDuel = getOpponentOfCurrentUser();
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }
    private boolean attack(String input) { // return true if duel has winner and false if duel does not have winner
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return false;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            System.out.println("you can’t attack with this card");
            return false;
        }
        if (!(currentPhase == Phase.BATTLE)) {
            System.out.println("you can’t do this action in this phase");
            return false;
        }
        if (attackedCards.contains(selectedCard)) {
            System.out.println("this card already attacked");
            return false;
        }
        String[] inputSplit = input.split("\\s");
        int enemyCardNumber = Integer.parseInt(inputSplit[1]);
        Card enemyCard = getOpponentOfCurrentUser().getBoard().getMonstersZone().get(enemyCardNumber - 1);
        Monster enemyMonster = (Monster) enemyCard;
        Monster selectedMonster = (Monster) selectedCard;
        if (enemyCard == null) {
            System.out.println("there is no card to attack here");
            return false;
        }
        if (enemyMonster.getName().equals("Texchanger")) {
            // todo
        }
        if (enemyMonster.getName().equals("Marshmallon")) {
            return attackMarshmallon(selectedMonster, enemyMonster);
        }
        if (enemyMonster.getName().equals("Exploder Dragon")) {
            return attackExploderDragon(selectedMonster, enemyMonster);
        }
        if (enemyMonster.getName().equals("The Calculator")) {
            int attackPower = 0;
            for (int i = 0; i < getOpponentOfCurrentUser().getBoard().getMonstersZone().size(); i++) {
                if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i) != null) {
                    if (getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i).getOccupied()) {
                        attackPower += ((Monster) getOpponentOfCurrentUser().getBoard().getMonstersZone().get(i)).getLevel();
                    }
                }
            }
            attackPower *= 300;
            enemyMonster.setAttackPower(attackPower);
        }
        // todo fight effects
        if (enemyCard.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > enemyMonster.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getAttackPower());
                System.out.println("your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage");
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    System.out.println("Yomi Ship destroyed your monster");
                } else {
                    attackedCards.add(selectedMonster);
                }
                getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
                if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
                    winnerOfDuel = currentUser;
                    return true;
                }
                return false;
            } else if (selectedMonster.getAttackPower() == enemyMonster.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getAttackPower());
                System.out.println("Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy deffend position
            if (selectedMonster.getAttackPower() > enemyMonster.getDefencePower()) {
                addMonsterFromMonsterZoneToGraveyard(enemyMonster, getOpponentOfCurrentUser());
                if (enemyMonster.getOccupied()) {
                    System.out.println("the defense position monster is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    System.out.println("opponent’s monster card was " + enemyMonster.getName() + " and the defense position monster is destroyed");
                }
                if (enemyMonster.getName().equals("Yomi Ship")) {
                    addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                    System.out.println("Yomi Ship destroyed your monster");
                } else {
                    attackedCards.add(selectedMonster);
                }
                return false;
            } else if (selectedMonster.getAttackPower() == enemyMonster.getDefencePower()) {
                if (enemyMonster.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    enemyMonster.setOccupied(true);
                    System.out.println("opponent’s monster card was " + enemyMonster.getName() + " and no card is destroyed");
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - enemyMonster.getDefencePower());

                if (enemyMonster.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    enemyMonster.setOccupied(true);
                    System.out.println("opponent’s monster card was " + enemyMonster.getName() + " and no card is destroyed and you received " + damage + " battle damage");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }
    private boolean attackExploderDragon(Monster selectedMonster, Monster ExploderDragon) {
        if (ExploderDragon.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > ExploderDragon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                System.out.println("your opponent’s monster is destroyed and your opponent receives " + 0 + " battle damage");
                System.out.println("Exploder Dragon destroyed your monster");
                return false;
            } else if (selectedMonster.getAttackPower() == ExploderDragon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                System.out.println("both you and your opponent monster cards are destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - ExploderDragon.getAttackPower());
                System.out.println("Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy deffend position
            if (selectedMonster.getAttackPower() > ExploderDragon.getDefencePower()) {
                addMonsterFromMonsterZoneToGraveyard(ExploderDragon, getOpponentOfCurrentUser());
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                if (ExploderDragon.getOccupied()) {
                    System.out.println("the defense position monster is destroyed");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName() + " and the defense position monster is destroyed");
                }
                System.out.println("Exploder Dragon destroyed your monster");
                return false;
            } else if (selectedMonster.getAttackPower() == ExploderDragon.getDefencePower()) {
                if (ExploderDragon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName() + " and no card is destroyed");
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - ExploderDragon.getDefencePower());

                if (ExploderDragon.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    ExploderDragon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + ExploderDragon.getName() + " and no card is destroyed and you received " + damage + " battle damage");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }
    private boolean attackMarshmallon(Monster selectedMonster, Monster Marshmallon) {
        if (Marshmallon.getAttackPosition()) { // enemy attack position
            if (selectedMonster.getAttackPower() > Marshmallon.getAttackPower()) {
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getAttackPower());
                System.out.println("no card is destroyed and your opponent receives " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
                if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
                    winnerOfDuel = currentUser;
                    return true;
                }
                return false;
            } else if (selectedMonster.getAttackPower() == Marshmallon.getAttackPower()) {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                System.out.println("Your monster card is destroyed and no one receives damage");
                attackedCards.add(selectedMonster);
                return false;
            } else {
                addMonsterFromMonsterZoneToGraveyard(selectedMonster, currentUser);
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getAttackPower());
                System.out.println("Your monster card is destroyed and you received " + damage + " battle damage");
                attackedCards.add(selectedMonster);
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        } else { // enemy deffend position
            if (selectedMonster.getAttackPower() > Marshmallon.getDefencePower()) {
                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName() + " and no card is destroyed");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                attackedCards.add(selectedMonster);
                return false;
            } else if (selectedMonster.getAttackPower() == Marshmallon.getDefencePower()) {
                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName() + " and no card is destroyed");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                attackedCards.add(selectedMonster);
                return false;
            } else {
                int damage = Math.abs(selectedMonster.getAttackPower() - Marshmallon.getDefencePower());

                if (Marshmallon.getOccupied()) {
                    System.out.println("no card is destroyed and you received " + damage + " battle damage");
                } else {
                    Marshmallon.setOccupied(true);
                    System.out.println("opponent’s monster card was " + Marshmallon.getName() + " and no card is destroyed and you received " + damage + " battle damage");
                    currentUser.setLifePoint(currentUser.getLifePoint() - 1000);
                    System.out.println("you received 1000 damage from Marshmallon");
                }
                currentUser.setLifePoint(currentUser.getLifePoint() - damage);
                attackedCards.add(selectedMonster);
                if (currentUser.getLifePoint() <= 0) {
                    winnerOfDuel = getOpponentOfCurrentUser();
                    return true;
                }
                return false;
            }
        }
    }
    private void addMonsterFromMonsterZoneToGraveyard(Card monsterCard, User owner) {
        for (Card card : owner.getBoard().getMonstersZone()) {
            if (card == monsterCard) {
                card = null;
            }
        }
        owner.getBoard().getGraveYard().add(monsterCard);
    }
    private boolean directAttack() { // returns true if duel has a winner and false if the fuel has no winner
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return false;
        }
        if (!currentUser.getBoard().getMonstersZone().contains(selectedCard)) {
            System.out.println("you can’t attack with this card");
            return false;
        }
        if (!(currentPhase == Phase.BATTLE)) {
            System.out.println("you can’t do this action in this phase");
            return false;
        }
        if (attackedCards.contains(selectedCard)) {
            System.out.println("this card already attacked");
            return false;
        }
        if (getOpponentOfCurrentUser().getBoard().numberOfMonstersOnBoard() > 0) {
            System.out.println("you can’t attack the opponent directly");
            return false;
        }
        // todo effect
        attackedCards.add(selectedCard);
        int damage = ((Monster) selectedCard).getAttackPower();
        getOpponentOfCurrentUser().setLifePoint(getOpponentOfCurrentUser().getLifePoint() - damage);
        System.out.println("you opponent receives " + damage + " battle damage");
        if (getOpponentOfCurrentUser().getLifePoint() <= 0) {
            winnerOfDuel = currentUser;
            return true;
        } else {
            return false;
        }
    }



    private void mainPhaseTwoRun() {
        currentPhase = Phase.MAIN_TWO;
        System.out.println(Phase.MAIN_TWO);
        printBoard();
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            input = editSpaces(input);
            if (input.equals("select -d")) {
                deselectCard();
            } else if (input.startsWith("select")) {
                select(Regex.getMatcher(input, Regex.selectCard));
            } else if (input.equals("next phase")) {
                if (activatedRitualCard != null) {
                    System.out.println("you should ritual summon right now");
                } else {
                    return;
                }
            } else if (input.equals("summon")) {
                summon();
            } else if (input.equals("set")) {
                set();
            } else if (input.matches(Regex.setPositionAttackDeffence)) {
                setPositionAttackDefense(input);
            } else if (input.equals("show graveyard")) {
                showGraveyard();
            } else if (input.equals("flip-summon")) {
                flipSummon();
            } else if (input.equals("card show --selected") || input.equals("card show -s")) {
                showSelectedCard();
            } else if (input.equals("surrender")) {
                winnerOfDuel = getOpponentOfCurrentUser();
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }
    private void activateEffect() {} // todo
    // todo activate in enemy turn
    // todo ritual activation and tribute and summon and inactive spell :|
    // todo all the spell cards with special summon and and you should special summon right now to all methods
    // todo chain
    // todo cheat
    // todo AI
    // todo standby phase
    private void showGraveyard() {
        if (currentUser.getBoard().getGraveYard().size() == 0) {
            System.out.println("graveyard empty");
        } else {
            int number = 1;
            for (Card card : currentUser.getBoard().getGraveYard()) {
                System.out.println(number + ". " + card.getName() + ":" + card.getDescription());
                number++;
            }
        }
        System.out.println("type back to return to game");
        while (true) {
            if (scanner.nextLine().equals("back")) {
                return;
            }
        }
    }
    private void showSelectedCard() {
        if (selectedCard == null) {
            System.out.println("no card is selected yet");
            return;
        }
        if (getOpponentOfCurrentUser().getBoard().getAllCards().contains(selectedCard) && !selectedCard.getOccupied()) {
            System.out.println("card is not visible");
            return;
        }
        System.out.println(selectedCard.getName() + ":" + selectedCard.getDescription());
    }


    private void endPhaseRun() {
        currentPhase = Phase.END;
        System.out.println(Phase.END);
        changeTurn();
        System.out.println("its " + currentUser.getNickName() + "’s turn");
    }
}
