/**
 * One object of this class represents a hand of playing cards held by a player
 * Cards are added one at a time with the take method and removed one at a time with the play method while the inspect method and the string conversion let the current contents be examined without changing the hand
 *
 * @author Ryan Dennis
 * @author Atharv Dua
 */
class Hand {
    public static final int MAX_CARDS = 50;

    private Card[] myCards;
    private int numCards;

    /**
     * constructs an empty hand with room for the maximum number of cards
     */
    public Hand() {
        myCards = new Card[MAX_CARDS];
        resetHand();
    }

    /**
     * removes all cards from the hand leaving it empty
     */
    public void resetHand() {
        for (int i = 0; i < numCards; i++) {
            myCards[i] = null;
        }
        numCards = 0;
    }

    /**
     * adds a copy of the given card to the hand if the hand is not already full and the card is valid
     *
     * @param card the card to add to the hand
     * @return true unless the hand was already full
     */
    public boolean takeCard(Card card) {
        if (numCards == MAX_CARDS) {
            return false;
        }
        if (card != null && !card.isErrorFlag()) {
            myCards[numCards] = new Card(card);
            numCards++;
        }
        return true;
    }

    /**
     * removes and returns the most recently taken card from the hand
     *
     * @return the removed card or an invalid card if the hand was empty
     */
    public Card playCard() {
        if (numCards == 0) {
            return new Card('?', Card.Suit.spades);
        }
        numCards--;
        Card played = myCards[numCards];
        myCards[numCards] = null;
        return played;
    }

    /**
     * @return a non-null string representation of the hand and its cards
     */
    public String toString() {
        if (numCards == 0) {
            return "Hand = ( )";
        }
        StringBuilder text = new StringBuilder("Hand = ( ");
        for (int i = 0; i < numCards; i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(myCards[i]);
        }
        return text.append(" )").toString();
    }

    /**
     * @return the number of cards currently in the hand
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * @param k the position of the card to inspect
     * @return a copy of the card at the given position or an invalid card if the position is out of bounds
     */
    public Card inspectCard(int k) {
        if (k < 0 || k >= numCards) {
            return new Card('?', Card.Suit.spades);
        }
        return new Card(myCards[k]);
    }
}
