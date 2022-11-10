import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {

    private int playerNum;
    private ArrayList<Card> playerHand;
    private String fileName;

    public void log(String msg) throws IOException{
        FileWriter newFile = new FileWriter(fileName);
        newFile.write(msg);
        newFile.close();
    }
    
    public int getPLayerNum() {
        return this.playerNum;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public boolean checkWin() throws IOException {
        Card n = playerHand.get(0);
        int value = n.getValue();
        for (int i = 1; i < 4; i++) {
            if (value != playerHand.get(i).getValue()) {
                return false;
            }
        }
        String msg = "Player " + playerNum + " wins";
        log(msg);
        return true;
    }

    public String toString() {
        return "Player number is " + getPLayerNum() + " players current hand is " + getPlayerHand();
    }

    public void cardDrawDisc(Pack.CardDeck draw, Pack.CardDeck disc) throws IOException {
        ArrayList<Card> unwanted = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Card n = playerHand.get(i);
            if (n.getValue() != playerNum) {
                unwanted.add(n);
            }
        }
        Collections.shuffle(unwanted);
        Card cardToGo = unwanted.get(0);
        disc.addCardDeck(cardToGo);
        playerHand.remove(cardToGo);
        String discmsg = "Player " + playerNum + " discards a "+cardToGo.getValue()+" to deck "+disc.getDeckNum();
        log(discmsg);

        Card card = draw.topCard();
        playerHand.add(card);
        String drawmsg = "Player " + playerNum + " draws a "+card.getValue()+" from deck "+draw.getDeckNum();
        log(drawmsg);
    }

    public void fillHand(Card card){
        playerHand.add(card);
    }

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.playerHand = new ArrayList<Card>();
        this.fileName = "player"+playerNum + "_output.txt";
    }
}
