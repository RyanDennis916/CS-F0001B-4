/**
 * One object of this class represents a deck which can hold one or more packs of playing cards
 * A deck is built or reset to a full pack order with the init methods then shuffle changes that order while deal transfers the top card to a caller and inspect allows a card to be examined without removing it
 *
 * @author Atharv Dua
 * @author Ryan Dennis
 */
class Deck {
    private static final int MAX_PACKS = 6;
    private static final int NUM_CARDS_PER_PACK = Card.NUM_RANKS * Card.NUM_SUITS;
    private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;

    private static Card[] packTemplate;

    private Card[] cards;
    private int topCard;


    /**
     * constructs a deck containing a single pack of cards
     */
    public Deck() {
        this(1);
    }

    /**
     * constructs a deck containing the given number of packs falling back to a single pack if the number given is not supported
     *
     * @param numPacks the number of packs to include in the deck
     */
    public Deck(int numPacks) {
        allocatePackTemplate();
        cards = new Card[MAX_CARDS_PER_DECK];
        if (!init(numPacks)) {
            init();
        }
    }

    private static void allocatePackTemplate() {
        if (packTemplate != null) {
            return;
        }
        packTemplate = new Card[NUM_CARDS_PER_PACK];
        int index = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (char rank : Card.RANKS) {
                packTemplate[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    /**
     * resets the deck to a single full pack of cards in pack order
     */
    public void init() {
        init(1);
    }

    /**
     * resets the deck to the given number of packs in pack order
     *
     * @param numPacks the number of packs to include in the deck
     * @return true if the number of packs was supported, false otherwise
     */
    public boolean init(int numPacks) {
        if (numPacks < 1 || numPacks > MAX_PACKS) {
            return false;
        }
        topCard = 0;
        for (int pack = 0; pack < numPacks; pack++) {
            for (int i = 0; i < NUM_CARDS_PER_PACK; i++) {
                cards[topCard] = new Card(packTemplate[i]);
                topCard++;
            }
        }
        for (int i = topCard; i < MAX_CARDS_PER_DECK; i++) {
            cards[i] = null;
        }
        return true;
    }

    /**
     * randomizes the order of the cards that are still in the deck
     */
    public void shuffle() {
        for (int i = topCard - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /**
     * removes and returns the top card of the deck
     *
     * @return the removed card or an invalid card if the deck was empty
     */
    public Card dealCard() {
        if (topCard == 0) {
            return new Card('?', Card.Suit.spades);
        }
        topCard--;
        Card dealt = cards[topCard];
        cards[topCard] = null;
        return dealt;
    }

    /**
     * @return the number of cards remaining in the deck
     */
    public int getNumCards() {
        return topCard;
    }

    /**
     * @param k the position of the card to inspect
     * @return a copy of the selected card or an invalid card if the position is out of bounds
     */
    public Card inspectCard(int k) {
        if (k < 0 || k >= topCard) {
            return new Card('?', Card.Suit.spades);
        }
        return new Card(cards[k]);
    }
}
