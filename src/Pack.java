import java.io.File;
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
        return "This pack contains " + getPack() + " of size " + getPackSize();
    }
    
    public Pack(String textFile) throws IOException{
        File pack = new File(textFile);
        Scanner myReader = new Scanner(pack);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int val = Integer.parseInt(data);
            Card card = new Card(val);
            cardPack.add(card);
        }
        myReader.close();
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
