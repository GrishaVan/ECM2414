import java.util.ArrayList;

public class MockPack {
    private static ArrayList<Card> cardPack;

    /**
     * Function that returns the cards in a pack
     * 
     * @return an ArrayList of the cards in the pack
     */
    public ArrayList<Card> getPack() {
        return cardPack;
    }

    /**
     * Function that returns the size of the pack
     * 
     * @return the size of a pack
     */
    public int getPackSize() {
        return cardPack.size();
    }

    /**
     * To string method that returns info about the pack
     * 
     * @return string with the cards in the pack and its size
     */
    public String toString() {
        return "This pack contains " + getPack() + " of size " + getPack().size();
    }

    /**
     * Pack class object constructor
     * 
     * @param textFile pathname for the location of the pack file
     * @throws IOException if pathname cannot be located
     */
    public MockPack() {
        MockPack.cardPack = new ArrayList<Card>();
    }

    public static class CardDeck {

        private ArrayList<Card> deck;
        private int deckNum;
        private String fileName;

        /**
         * Function that returns the number of the deck
         * 
         * @return the int value of the deck number
         */
        public int getDeckNum() {
            return deckNum;
        }

        /**
         * Function that returns the cards in the deck
         * 
         * @return an ArrayList of the cards in the deck
         */
        public ArrayList<Card> getDeck() {
            return deck;
        }

        /**
         * Function that adds a card to the card deck
         * 
         * @param card the card to be added to the deck
         */
        public void addCardDeck(Card card) {
            deck.add(card);
        }

        /**
         * Function that takes the top card from the the deck and returns it
         * 
         * @return the top card of the card deck
         */
        public Card topCard() {
            Card card = deck.get(0);
            deck.remove(card);
            return card;
        }

        /**
         * Card deck class object constructor
         * 
         * @param num the int number of the Crad Deck
         */
        public CardDeck(int num) {
            this.deck = new ArrayList<Card>();
            this.deckNum = num;
            this.fileName = "deck" + num + "_output.txt";
        }
    }
}
