import java.util.ArrayList;
import java.util.Collections;


public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand) {
        if(checkSFlush(hand)) return 1;
        if(checkThreeKind(hand)) return 2;
        if(checkStraight(hand)) return  3;
        if(checkFlush(hand)) return 4;
        if(checkPair(hand)) return 5;
        return 0;
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        if(bet == 0) return  0;
        int result = evalHand(hand);

        switch (result) {
            case 1:
                return 40 * bet;
            case 2:
                return 30 * bet;
            case 3:
                return 6 * bet;
            case 4:
                return 3 * bet;
            case 5:
                return bet;
            default:
                return 0;
        }
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerEval = evalHand(dealer);
        int playerEval = evalHand(player);
        ArrayList<Integer> playerCVals = orderedCards(player);
        ArrayList<Integer> dealerCvals = orderedCards(dealer);
        ////////////////////// asafasdgdsag
        //if(dealerEval == 0 && dealerCvals.get(2) < 12) return  -1; // check this in the main logic template remove from here after
        if(dealerEval == 0 || playerEval == 0) {
            if(playerEval > dealerEval) return 2;
            if(playerEval < dealerEval) return 1;
        } else {
            if (dealerEval < playerEval) return 1;
            if (dealerEval > playerEval) return 2;
        }

        if (playerEval != 5) {
            for(int i = 2; i >= 0; i--) {
                if(playerCVals.get(i) == dealerCvals.get(i)) continue;;
                if(playerCVals.get(i) > dealerCvals.get(i)) return 2;
                return 1;
            }
        } else {
            if(playerCVals.get(1) > dealerCvals.get(1)) return 2;
            if(playerCVals.get(1) < dealerCvals.get(1)) return 1;
            if(playerCVals.get(2) > dealerCvals.get(2)) return 2;
            if(playerCVals.get(2) < dealerCvals.get(2)) return 1;
            if(playerCVals.get(0) > dealerCvals.get(0)) return 2;
            if(playerCVals.get(0) < dealerCvals.get(0)) return 1;
        }

        return 0;
    }

    private static boolean checkSFlush(ArrayList<Card> hand) {
        if(checkFlush(hand) == false) return false;
        return checkStraight(hand);
    }

    private static boolean checkThreeKind(ArrayList<Card> hand) {
        int prevValue = hand.get(0).getValue();
        for(int i = 1; i < 3; i++) {
            if(hand.get(i).getValue() != prevValue) return  false;
        }

        return true;
    }

    private static boolean checkStraight(ArrayList<Card> hand) {
        ArrayList<Integer> cardValues = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            cardValues.add(hand.get(i).getValue());
        }

        Collections.sort(cardValues);
        int prevVal = cardValues.get(0);

        for (int i = 1; i < 3; i++) {
            if(cardValues.get(i) - prevVal != 1) return  false;
            prevVal = cardValues.get(i);
        }

        return  true;
    }

    private static boolean checkFlush(ArrayList<Card> hand) {
        char suit = hand.get(0).getSuit();
        if(hand.get(1).getSuit() != suit || hand.get(2).getSuit() != suit) {
            return false;
        }
        return true;
    }

    private static boolean checkPair(ArrayList<Card> hand) {
        int cOneSuit = hand.get(0).getValue();
        int cTwoSuit = hand.get(1).getValue();
        int cThreeSuit = hand.get(2).getValue();

        if(cThreeSuit == cTwoSuit || cThreeSuit == cOneSuit) return  true;
        if(cTwoSuit == cOneSuit) return true;
        return false;
    }

    private static ArrayList<Integer> orderedCards(ArrayList<Card> hand) {
        ArrayList<Integer> sortedCards = new ArrayList<>();
        for(Card c: hand) {
            sortedCards.add(c.getValue());
        }
        Collections.sort(sortedCards);
        return sortedCards;
    }

}
