import java.util.ArrayList;

public class Dealer {

    Deck theDeck;
    ArrayList<Card> dealersHand;

    public Dealer() {
        theDeck = new Deck();
    }

    public ArrayList<Card> dealHand() {
        if(theDeck.size() <= 34) theDeck.newDeck();

        ArrayList<Card> newHand = new ArrayList<>(3);
        for(int i = 0; i < 3; i++) {
            newHand.add(theDeck.get(theDeck.size() - 1));
            theDeck.remove(theDeck.size() - 1);
        }
        return newHand;
    }

    public Deck getTheDeck() {
        return theDeck;
    }

    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }

    public void setDealersHand(ArrayList<Card> dealersHand) {
        this.dealersHand = dealersHand;
    }
}
