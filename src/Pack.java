import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {

    private static ArrayList<Card> cardPack;
    private int packSize;

    public ArrayList<Card> getPack() {
        return Pack.cardPack;
    }

    public int getPackSize() {
        return this.packSize;
    }

    public String toString() {
        return "This pack contains " + getPack() + " of size " + getPack().size();
    }

    public Pack(String textFile) throws IOException {
        Pack.cardPack = new ArrayList<Card>();
        try{
            Scanner scanner = new Scanner(new File(textFile));
            while (scanner.hasNextLine()){
                int val = Integer.parseInt(scanner.nextLine());
                Pack.cardPack.add(new Card(val));
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static class CardDeck {

        private ArrayList<Card> deck;
        private int deckNum;
        private String fileName;

        public int getDeckNum(){
            return deckNum;
        }
        public void addCardPack() {
            Card card = cardPack.get(0);
            deck.add(card);
        }

        public void addCardDeck(Card card) {
            deck.add(card);
        }

        public Card topCard() {
            int size = deck.size();
            Card card = deck.get(size - 1);
            deck.remove(card);
            return card;
        }

        public CardDeck(int num) {
            this.deck = new ArrayList<Card>();
            this.deckNum = num;
            this.fileName = "deck"+num + "_output.txt";
        }
    }
}