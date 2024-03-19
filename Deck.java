import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    public Deck() {
        newDeck();
    }

    public void newDeck() {
        clear();
        char[] suits = {'H', 'D', 'C', 'S'};
        for (int i = 0; i < 4; i++) {
            for (int m = 2; m < 15; m++) {
                Card c = new Card(suits[i] , m);
                add(c);
            }
        }
        shuffleDeck();
    }

    private void shuffleDeck() {
        Collections.shuffle(this);
    }
}
