package Model;

import Client.Controller.Menu.Shop;

import java.util.ArrayList;

public class AI extends User {

    Card selectedCard = null;

    public AI() {
        super("ai", "123", "AI");
        makeNewDeckForAi();
    }

    private void makeNewDeckForAi() {
        Shop shop = new Shop(null);
        Deck deck = new Deck(new MainDeck(true), new SideDeck(true));
        deck.setName("Ai Deck");
        deck.setValid(true);
        for (int i = 0; i < 40; i++) {
            Card card = Shop.getAllCards().get(i);
            if (card instanceof Spell) {
                Spell myCard = (Spell) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            } else if (card instanceof Trap) {
                Trap myCard = (Trap) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            } else {
                Monster myCard = (Monster) card;
                deck.getMainDeck().getCardsInMainDeck().add(myCard);
            }
        }
        for (int i = 40; i < 55; i++) {
            Card card = Shop.getAllCards().get(i);
            if (card instanceof Spell) {
                Spell myCard = (Spell) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            } else if (card instanceof Trap) {
                Trap myCard = (Trap) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            } else {
                Monster myCard = (Monster) card;
                deck.getSideDeck().getCardsInSideDeck().add(myCard);
            }
        }
        this.getDecks().add(deck);
    }

    private int counter = 1;

    public void setOnBoard() {
        if (counter >= 5) {
            counter = 1;
        }
        selectedCard = getBoard().getCardsInHand().get(counter - 1);
        if (selectedCard instanceof Spell) {
            Spell spell = (Spell) selectedCard;
            getBoard().getSpellsAndTrapsZone().add(counter, spell);
            counter++;
        } else if (selectedCard instanceof Trap) {
            Trap trap = (Trap) selectedCard;
            getBoard().getSpellsAndTrapsZone().add(counter, trap);
            counter++;
        } else {
            Monster monster = (Monster) selectedCard;
            monster.setAttackPosition(true);
            monster.setOccupied(true);
            getBoard().getMonstersZone().add(counter, monster);

            counter++;
        }
        getBoard().getCardsInHand().remove(selectedCard);
        selectedCard = null;
    }

    public ArrayList<Card> attack(Board rivalBoard) {
        Card myCard = getStrongestMonster();
        if (myCard == null) return null;
        Card enemyCard = null;
        int power = Integer.MAX_VALUE;
        for (Card card : rivalBoard.getMonstersZone()) {
            Monster monster = (Monster) card;
            if (monster != null && power > monster.getDefencePower()) {
                power = monster.getDefencePower();
                enemyCard = monster;
            }
        }
        if (enemyCard != null) {
            ArrayList<Card> list = new ArrayList<>();
            list.add(0, myCard);
            list.add(1, enemyCard);
            return list;
        } else {
            for (Card card : rivalBoard.getSpellsAndTrapsZone()) {
                if (card != null) {
                    enemyCard = card;
                    break;
                }
            }
            ArrayList<Card> list = new ArrayList<>();
            list.add(0, myCard);
            list.add(1, enemyCard);
            return list;
        }
    }

    private Monster getStrongestMonster() {
        int attack = -1;
        Monster selectedMonster = null;
        for (Card card : this.getBoard().getMonstersZone()) {
            Monster monster = (Monster) card;
            if (monster != null && monster.getAttackPower() > attack) {
                attack = monster.getAttackPower();
                selectedMonster = monster;
            }
        }
        return selectedMonster;
    }

}
