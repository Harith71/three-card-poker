import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class MyTest {

	@Test
	void deckConstructor() {
		Deck d = new Deck();
		assertEquals(52, d.size(), "Imporper amount of cards in deck when initialized");
	}

	@Test
	void deckNewDeck() {
		Deck d = new Deck();
		d.remove(1);
		d.remove(10);
		d.newDeck();
		assertEquals(52, d.size(), "new deck doesnt have 52 cards");
	}

	@Test
	void deckCardValues() {
		Deck d = new Deck();
		for(int i = 0; i < d.size(); i++) {
			if(d.get(i).getValue() < 2 || d.get(i).getValue() > 14) {
				assertEquals(1, 2, "Card in deck contains invalid value");
			}
		}
		assertEquals(1, 1);
	}

	@Test
	void deckCardSuits() {
		Deck d = new Deck();
		for(int i = 0; i < d.size(); i++) {
			char s = d.get(i).getSuit();
			if(s != 'C' && s != 'D' && s != 'S' && s != 'H') {
				assertEquals(1, 2, "Card in deck contains invalid suit");
			}
		}
		assertEquals(1, 1);
	}

	@Test
	void dealerConstructor() {
		Dealer d = new Dealer();
		assertEquals(52, d.getTheDeck().size(), "Dealer constructor doesn't create deck with 52 cards");
	}

	@Test
	void dealerDealHand() {
		Dealer d = new Dealer();
		ArrayList<Card> hand = d.dealHand();
		assertEquals(3, hand.size(), "dealHand doesn't give 3 cards");
	}

	@Test
	void dealerDeckAfterDealHand() {
		Dealer d = new Dealer();
		ArrayList<Card> hand = d.dealHand();
		assertEquals(49, d.getTheDeck().size(), "Cards dont get removed from deck when dealt");
	}

	@Test
	void dealerDeckAfterMultipleDealHand() {
		Dealer d = new Dealer();
		ArrayList<Card> hand = d.dealHand();
		hand = d.dealHand();
		assertEquals(46, d.getTheDeck().size(), "Cards dont get removed from deck when dealt");
	}

	@Test
	void dealerSetHand() {
		Dealer d = new Dealer();
		ArrayList<Card> hand = d.dealHand();
		d.setDealersHand(hand);
		assertEquals(hand.get(0).getValue(), d.getDealersHand().get(0).getValue(), "Dealers hand isn't set correctly");
	}

	@Test
	void dealerNewDeck() {
		Dealer d = new Dealer();
		ArrayList<Card> hand = d.dealHand();
		for(int i = 1; i < 6; i++) {
			hand = d.dealHand();
		}
		hand = d.dealHand();
		assertEquals(49, d.getTheDeck().size(),"New deck is created when there is less than 34 cards");
	}

}
