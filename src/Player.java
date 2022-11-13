import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player implements Runnable {

    private int playerNum;
    private ArrayList<Card> playerHand;
    private String fileName;
    private boolean win;
    private Pack.CardDeck draw;
    private Pack.CardDeck disc;

    /**
     * Function that logs a message to a file
     * 
     * @param msg the message to be logged
     * @throws IOException if file is not found
     */
    public void log(String msg) throws IOException {
        FileWriter newFile = new FileWriter(fileName);
        newFile.write(msg);
        newFile.close();
    }

    /**
     * Function to get the number of the player
     * 
     * @return the player number
     */
    public int getPLayerNum() {
        return this.playerNum;
    }

    /**
     * Function that returns the player hand
     * 
     * @return ArrayList of the players hand
     */
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    /**
     * Function that checks if player has a winning hand
     * 
     * @throws IOException if cannot log
     */
    public void checkWin() throws IOException {
        Card n = playerHand.get(0);
        int value = n.getValue();
        // For loop that looks through everycard in the players hand
        for (int i = 1; i < 4; i++) {
            // Check if every card in the hand is of same value
            if (value != playerHand.get(i).getValue()) {
                win = false;
                return;
            }
        }
        String msg = "Player " + playerNum + " wins";
        log(msg);
        win = true;
    }

    /**
     * To string method that returns the player info
     * 
     * @return a string containing player number and the players hand
     */
    public String toString() {
        return "Player number is " + getPLayerNum() + " players current hand is " + getPlayerHand();
    }

    /**
     * Function that draws and discrads a card from a players hand
     * 
     * @throws IOException cannot log to a file
     */
    public synchronized void cardDrawDisc() throws IOException {
        ArrayList<Card> unwanted = new ArrayList<>();
        // For loop that looks through the players hand
        for (int i = 0; i < 4; i++) {
            Card n = playerHand.get(i);
            // Check if any card is of same value as player number to keep in hand
            if (n.getValue() != playerNum) {
                unwanted.add(n);
            }
        }
        // Shuffle the cards to discard one randomly
        Collections.shuffle(unwanted);
        Card cardToGo = unwanted.get(0);
        // Add the discraded card to the discard deck
        disc.addCardDeck(cardToGo);
        // Remove the discraded card from the player hand
        playerHand.remove(cardToGo);
        // Log player discard
        String discmsg = "Player " + playerNum + " discards a " + cardToGo.getValue() + " to deck " + disc.getDeckNum();
        log(discmsg);

        // Crad from the top of the draw pack
        Card card = draw.topCard();
        playerHand.add(card);
        // Log the card that was drawn
        String drawmsg = "Player " + playerNum + " draws a " + card.getValue() + " from deck " + draw.getDeckNum();
        log(drawmsg);
    }

    /**
     * Function to fill the players hand
     * 
     * @param card the crad to add to the hand
     */
    public void fillHand(Card card) {
        playerHand.add(card);
    }

    /**
     * Player class cunstructor
     * 
     * @param playerNum the player number
     * @param draw      the card deck the player is drawing from
     * @param disc      the card deck the players is discarding to
     */
    public Player(int playerNum, Pack.CardDeck draw, Pack.CardDeck disc) {
        this.playerNum = playerNum;
        this.playerHand = new ArrayList<Card>();
        this.fileName = "player" + playerNum + "_output.txt";
        this.win = false;
        this.draw = draw;
        this.disc = disc;
    }

    @Override
    public void run() {
        while (win == false) {
            try {
                cardDrawDisc();
                checkWin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.wait();
            this.
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
