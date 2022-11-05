import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Pack.CardDeck;

public class Player {
    
    private int playerNum;
    private ArrayList<Card> playerHand;

    public int getPLayerNum() {
        return this.playerNum;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public boolean checkWin() {
        Card n = playerHand.get(0);
        int value = n.getValue();
        for(int i = 1; i < 4; i++) {
            if (value != playerHand.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "Player number is " + getPLayerNum() + " players current hand is " + getPlayerHand();
    }

    public void cardDrawDisc(Pack.CardDeck draw, Pack.CardDeck disc) {
        ArrayList<Card> unwanted = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Card n = playerHand.get(i);
            if (n.getValue() != playerNum) {
                unwanted.add(n);
            }
        }
        Collections.shuffle(unwanted);
        disc.addCardDeck(unwanted.get(0));
        playerHand.remove(unwanted.get(0));
        playerHand.add(draw.topCard());
    }

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.playerHand = new ArrayList<Card>();
    }
}
