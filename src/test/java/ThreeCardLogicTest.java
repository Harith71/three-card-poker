import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;


public class ThreeCardLogicTest {

    @Test
    void sFlushEval() {
        Card c1 = new Card('C', 2);
        Card c2 = new Card('C', 3);
        Card c3 = new Card('C', 4);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(1, ThreeCardLogic.evalHand(hand), "EvalHand check for straight flush failed");
    }

    @Test
    void threeKindEval() {
        Card c1 = new Card('C', 4);
        Card c2 = new Card('D', 4);
        Card c3 = new Card('S', 4);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(2, ThreeCardLogic.evalHand(hand), "EvalHand check for straight flush failed");
    }

    @Test
    void straightEval() {
        Card c1 = new Card('C', 5);
        Card c2 = new Card('D', 6);
        Card c3 = new Card('S', 7);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(3, ThreeCardLogic.evalHand(hand), "EvalHand check for straight failed");
    }

    @Test
    void flushEval() {
        Card c1 = new Card('C', 5);
        Card c2 = new Card('C', 11);
        Card c3 = new Card('C', 8);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(4, ThreeCardLogic.evalHand(hand), "EvalHand check for flush failed");
    }

    @Test
    void pairEval() {
        Card c1 = new Card('D', 5);
        Card c2 = new Card('S', 5);
        Card c3 = new Card('H', 8);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(5, ThreeCardLogic.evalHand(hand), "EvalHand check for pair failed");
    }

    @Test
    void highCardEval() {
        Card c1 = new Card('D', 5);
        Card c2 = new Card('S', 14);
        Card c3 = new Card('H', 8);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertEquals(0, ThreeCardLogic.evalHand(hand), "EvalHand check for highCard failed");
    }

    @Test
    void straightFlushPPBet() {
        Card c1 = new Card('C', 2);
        Card c2 = new Card('C', 3);
        Card c3 = new Card('C', 4);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(bet * 40, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for straight flush");
    }

    @Test
    void threeKindPPBet() {
        Card c1 = new Card('C', 4);
        Card c2 = new Card('D', 4);
        Card c3 = new Card('S', 4);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(bet * 30, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for three of kind");
    }

    @Test
    void straightPPBet() {
        Card c1 = new Card('C', 5);
        Card c2 = new Card('D', 6);
        Card c3 = new Card('S', 7);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(bet * 6, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for straight");
    }

    @Test
    void flushPPBet() {
        Card c1 = new Card('D', 11);
        Card c2 = new Card('D', 9);
        Card c3 = new Card('D', 3);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(bet * 3, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for flush");
    }

    @Test
    void pairPPBet() {
        Card c1 = new Card('C', 6);
        Card c2 = new Card('D', 6);
        Card c3 = new Card('S', 11);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(bet, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for pair");
    }

    @Test
    void highCardPPBet() {
        Card c1 = new Card('C', 2);
        Card c2 = new Card('D', 6);
        Card c3 = new Card('S', 11);
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        int bet = 10;
        assertEquals(0, ThreeCardLogic.evalPPWinnings(hand, bet), "Incorrect winnings for highcard");
    }

    @Test
    void comparePWinHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('C', 7);
        Card p3 = new Card('C', 8);
        Card d1 = new Card('C', 6);
        Card d2 = new Card('D', 6);
        Card d3 = new Card('S', 11);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(2, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void compareDWinHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card d1 = new Card('C', 6);
        Card d2 = new Card('C', 7);
        Card d3 = new Card('C', 8);
        Card p1 = new Card('C', 6);
        Card p2 = new Card('D', 6);
        Card p3 = new Card('S', 11);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(1, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void compareTieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('C', 7);
        Card p3 = new Card('C', 8);
        Card d1 = new Card('C', 6);
        Card d2 = new Card('C', 7);
        Card d3 = new Card('C', 8);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(0, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }


    @Test
    void comparePairTieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('D', 6);
        Card p3 = new Card('C', 13);
        Card d1 = new Card('S', 6);
        Card d2 = new Card('H', 6);
        Card d3 = new Card('C', 9);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(2, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void comparePair2TieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('D', 6);
        Card p3 = new Card('C', 9);
        Card d1 = new Card('S', 6);
        Card d2 = new Card('H', 6);
        Card d3 = new Card('C', 9);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(0, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void compareSFTieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('C', 7);
        Card p3 = new Card('C', 8);
        Card d1 = new Card('S', 8);
        Card d2 = new Card('S', 7);
        Card d3 = new Card('S', 6);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(0, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void compareSF2TieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('C', 6);
        Card p2 = new Card('C', 7);
        Card p3 = new Card('C', 8);
        Card d1 = new Card('S', 9);
        Card d2 = new Card('S', 8);
        Card d3 = new Card('S', 7);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(1, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }

    @Test
    void compareThreeCardTieHand() {
        ArrayList<Card> pHand = new ArrayList<>();
        ArrayList<Card> dHand = new ArrayList<>();
        Card p1 = new Card('D', 8);
        Card p2 = new Card('S', 8);
        Card p3 = new Card('C', 8);
        Card d1 = new Card('H', 4);
        Card d2 = new Card('D', 4);
        Card d3 = new Card('S', 4);
        pHand.add(p1);
        pHand.add(p2);
        pHand.add(p3);
        dHand.add(d1);
        dHand.add(d2);
        dHand.add(d3);
        assertEquals(2, ThreeCardLogic.compareHands(dHand, pHand), "Incorrect winner for comparing hands");
    }


}
