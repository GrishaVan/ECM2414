import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {

    private static ArrayList<Card> cardPack;

    /**
     * Function to get the ArrayList of cards in a pack
     * 
     * @return ArrayList with the cards in the pack
     */
    public ArrayList<Card> getPack() {
        return Pack.cardPack;
    }

    /**
     * Function to get the size of the pack
     * 
     * @return the size of the pack
     */
    public int getPackSize() {
        return cardPack.size();
    }

    /**
     * To string method that returns the info about the pack of cards
     * 
     * @return The string containing the cards in the pack and the size of the pack
     */
    public String toString() {
        return "This pack contains " + getPack() + " of size " + getPack().size();
    }

    /**
     * Pack class constructor
     * 
     * @param textFile the pathname to the pack file
     * @throws IOException if the pathname could not be found
     */
    public Pack(String textFile) throws IOException {
        Pack.cardPack = new ArrayList<Card>();
        try {
            Scanner scanner = new Scanner(new File(textFile));
            // While loop that goes through everyvalue in the file and adds it to the pack
            while (scanner.hasNextLine()) {
                int val = Integer.parseInt(scanner.nextLine());
                Pack.cardPack.add(new Card(val));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class CardDeck {

        private ArrayList<Card> deck;
        private int deckNum;
        private String fileName;

        /**
         * Function that returns the number of the deck
         * 
         * @return the number of the deck
         */
        public int getDeckNum() {
            return deckNum;
        }

        /**
         * Function that gets the card deck
         * 
         * @return ArrayList of the cards inside the card deck
         */
        public ArrayList<Card> getDeck() {
            return deck;
        }

        /**
         * Function to add a card from bottom of pack to a card deck
         */
        public void addCardPack() {
            Card card = cardPack.get(0);
            deck.add(card);
        }

        /**
         * Function that adds a card to the cards deck
         * 
         * @param card the card to be added to the card deck
         */
        public void addCardDeck(Card card) {
            deck.add(card);
        }

        /**
         * Function that returns the top card of the deck
         * 
         * @return the top card in the card deck
         */
        public Card topCard() {
            int size = deck.size();
            // Top card of the deck will be the last one in the ArrayList
            Card card = deck.get(size - 1);
            deck.remove(card);
            return card;
        }

        /**
         * Card deck class constructor class
         * 
         * @param num the number of the card deck
         */
        public CardDeck(int num) {
            this.deck = new ArrayList<Card>();
            this.deckNum = num;
            this.fileName = "deck" + num + "_output.txt";
        }
    }
}