import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {

    private ArrayList<Card> cardPack;
    private int packSize;

    public ArrayList<Card> getPack() {
        return this.cardPack;
    }

    public int getPackSize() {
        return this.packSize;
    }

    public String toString() {
        return "This pack contains " + getPack() + " of size " + getPack().size();
    }

    public Pack(String textFile) throws IOException {
        this.cardPack = new ArrayList<Card>();
        try{
            Scanner scanner = new Scanner(new File(textFile));
            while (scanner.hasNextLine()){
                int val = Integer.parseInt(scanner.nextLine());
                this.cardPack.add(new Card(val));
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public class CardDeck {

        private ArrayList<Card> deck;

        public void addCardPack() {
            Card card = cardPack.get(0);
            deck.add(card);
        }

        public void addCardDeck(Card card) {
            deck.add(card);
        }

        public Card topCard() {
            int size = deck.size();
            return deck.get(size - 1);
        }

        public CardDeck() {
            this.deck = new ArrayList<Card>();
        }
    }
}