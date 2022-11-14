import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {

    private static ArrayList<Card> cardPack;

    /**
     * Function that returns the cards in a pack
     * 
     * @return an ArrayList of the cards in the pack
     */
    public ArrayList<Card> getPack() {
        return Pack.cardPack;
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
    public Pack(String textFile) throws IOException {
        Pack.cardPack = new ArrayList<Card>();
        // Try to open the file 
        try {
            Scanner scanner = new Scanner(new File(textFile));
            // Check if there are still values to be read from the file
            while (scanner.hasNextLine()) {
                int val = Integer.parseInt(scanner.nextLine());
                // Add the card with the value to the pack
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
         * Function that adds the bottom card from the pack to the deck
         */
        public void addCardPack() {
            // Last card will be the first in the ArrayList
            Card card = cardPack.get(0);
            deck.add(card);
        }

        /**
         * Function that adds a card to the card deck
         * 
         * @param card the crad to be added to the deck
         */
        public void addCardDeck(Card card) {
            deck.add(card);
        }

        /**
         * Function that returns rhe top card of the deck
         * 
         * @return the top card of the crad deck
         */
        public Card topCard() {
            Card card = deck.get(0);
            deck.remove(card);
            return card;
        }

        /**
         * Function that logs the actions of the card deck
         * 
         * @throws IOException cant write to a file
         */
        public void log() throws IOException{
            FileWriter newFile = new FileWriter(fileName);
            String elements ="";
            // For loops that check what cards are in the deck
            for(int i=0;i<getDeck().size();i++){
                // Adds the value of each card to a string
                elements+= getDeck().get(i).getValue()+" ";
            }
            String log = "deck"+deckNum+" contents: "+elements;
            // Log the contents of the card deck
            newFile.write(log);
            newFile.close();
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