/**
 * One object of this class represents a single playing card having a rank and a suit
 * A card is created through one of the constructors or copied from an existing card then its rank and suit can be read with the getter methods or replaced with the set method which also determines whether the card is valid for use by equals and toString
 *
 * @author Ryan Dennis
 * @author Atharv Dua
 */
class Card implements CardInterface {


    private char rank;
    private Suit suit;
    private boolean errorFlag;

    /**
     * constructs a card with the default rank and suit
     */
    public Card() {
        set(DEFAULT_RANK, DEFAULT_SUIT);
    }

    /**
     * constructs a card with the given rank and suit
     *
     * @param rank the rank for the new card
     * @param suit the suit for the new card
     */
    public Card(char rank, Suit suit) {
        set(rank, suit);
    }

    /**
     * constructs a card that is a copy of the given card
     *
     * @param card the card to copy
     */
    public Card(Card card) {
        set(card.getRank(), card.getSuit());
    }

    /**
     * constructs a card with the given rank and the default suit
     *
     * @param rank the rank for the new card
     */
    public Card(char rank) {
        set(rank, Card.DEFAULT_SUIT);
    }

    /**
     * constructs a card with the default rank and the given suit
     *
     * @param suit the suit for the new card
     */
    public Card(Suit suit) {
        set(Card.DEFAULT_RANK, suit);
    }

    /**
     * sets the rank and suit of the card and returns true if the values that were just set were valid, false otherwise
     *
     * @param rank the rank value being set to the card
     * @param suit the suit value being set to the card
     * @return true if the values that were just set were valid, false otherwise
     */
    public boolean set(char rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        errorFlag = !isValid(rank, suit);
        return !errorFlag;
    }

    /**
     * @return the card's current rank
     */
    public char getRank() {
        return rank;
    }

    /**
     * @return the card's current suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @return true if the card has any invalid internal property values, false otherwise
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }

    /**
     * @param card a card object
     * @return true if the card objects have the same internal property values, false otherwise
     */
    public boolean equals(Card card) {
        return card != null
                && rank == card.rank
                && suit == card.suit
                && errorFlag == card.errorFlag;
    }

    /**
     * @return a non-null string representation of the card object
     */
    public String toString() {
        if (errorFlag) {
            return "** illegal **";
        }
        return rank + " of " + suit;
    }

    private static boolean isValid(char rank, Suit suit) {
        if (suit == null) {
            return false;
        }
        for (char allowedRank : CardInterface.RANKS) {
            if (rank == allowedRank) {
                return true;
            }
        }
        return false;
    }
}
