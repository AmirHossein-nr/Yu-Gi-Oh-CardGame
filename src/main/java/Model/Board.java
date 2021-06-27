package Model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private Deck deck;
    private ArrayList<Card> deckZone;
    private Card fieldZone;
    private ArrayList<Card> allCards;
    private ArrayList<Card> monstersZone;
    private ArrayList<Card> spellsAndTrapsZone;
    private ArrayList<Card> graveYard;
    private ArrayList<Card> cardsInHand;
    private HashMap<Card, Card> spellMonsterEquip = new HashMap<>();
    private HashMap<Card, ArrayList<Card>> commandKnights = new HashMap<>();
    private ArrayList<Card> activatedMessengerOfPeaces = new ArrayList<>();
    private ArrayList<Card> activatedSpellAbsorptions = new ArrayList<>();
    private ArrayList<Card> activatedSupplySquad = new ArrayList<>();
    private ArrayList<Card> suijinCards = new ArrayList<>();

    public Board() {

        allCards = new ArrayList<>();
        monstersZone = new ArrayList<>();
        monstersZone.add(null);
        monstersZone.add(null);
        monstersZone.add(null);
        monstersZone.add(null);
        monstersZone.add(null);
        spellsAndTrapsZone = new ArrayList<>();
        spellsAndTrapsZone.add(null);
        spellsAndTrapsZone.add(null);
        spellsAndTrapsZone.add(null);
        spellsAndTrapsZone.add(null);
        spellsAndTrapsZone.add(null);
        graveYard = new ArrayList<>();
        cardsInHand = new ArrayList<>();
        deckZone = new ArrayList<>();

    }

    public void setZones() {
        deckZone.addAll(deck.getMainDeck().getCardsInMainDeck());
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    public void setSpellMonsterEquip(HashMap<Card, Card> spellMonsterEquip) {
        this.spellMonsterEquip = spellMonsterEquip;
    }

    public ArrayList<Card> getSuijinCards() {
        return suijinCards;
    }

    public ArrayList<Card> getActivatedSpellAbsorptions() {
        return activatedSpellAbsorptions;
    }

    public ArrayList<Card> getActivatedSupplySquad() {
        return activatedSupplySquad;
    }

    public ArrayList<Card> getActivatedMessengerOfPeaces() {
        return activatedMessengerOfPeaces;
    }

    public HashMap<Card, ArrayList<Card>> getCommandKnights() {
        return commandKnights;
    }

    public HashMap<Card, Card> getSpellMonsterEquip() {
        return spellMonsterEquip;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public ArrayList<Card> getDeckZone() {
        return deckZone;
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public ArrayList<Card> getMonstersZone() {
        return monstersZone;
    }

    public ArrayList<Card> getSpellsAndTrapsZone() {
        return spellsAndTrapsZone;
    }

    public Card getFieldZone() {
        return fieldZone;
    }

    public Deck getDeck() {
        return deck;
    }

    public int numberOfMonstersOnBoard() {
        int number = 0;
        for (Card card : monstersZone) {
            if (card != null) {
                number++;
            }
        }
        return number;
    }

    public int numberOfSpellAndTrapsOnBoard() {
        int number = 0;
        for (Card card : spellsAndTrapsZone) {
            if (card != null) {
                number++;
            }
        }
        return number;
    }

    public void addCardFromDeckToHand(int number) {
        Card card = deckZone.get(number - 1);
        cardsInHand.add(card);
        deckZone.remove(number - 1);
    }
}
