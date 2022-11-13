import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pack {

    private static ArrayList<Card> cardPack;

    public ArrayList<Card> getPack() {
        return Pack.cardPack;
    }

    public int getPackSize() {
        return cardPack.size();
    }

    public String toString() {
        return "This pack contains " + getPack() + " of size " + getPack().size();
    }

    public Pack(String textFile) throws IOException {
        Pack.cardPack = new ArrayList<Card>();
        try {
            Scanner scanner = new Scanner(new File(textFile));
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

        public int getDeckNum() {
            return deckNum;
        }

        public ArrayList<Card> getDeck() {
            return deck;
        }

        public void addCardPack() {
            Card card = cardPack.get(0);
            deck.add(card);
        }

        public void addCardDeck(Card card) {
            deck.add(card);
        }

        public Card topCard() {
            Card card = deck.get(0);
            deck.remove(card);
            return card;
        }

        public void log() throws IOException{
            FileWriter newFile = new FileWriter(fileName);
            String elements ="";
            for(int i=0;i<getDeck().size();i++){
                elements+= getDeck().get(i).getValue()+" ";
            }
            String log = "deck"+deckNum+" contents: "+elements;
            newFile.write(log);
            newFile.close();
        }

        public CardDeck(int num) {
            this.deck = new ArrayList<Card>();
            this.deckNum = num;
            this.fileName = "deck" + num + "_output.txt";
        }
    }
}