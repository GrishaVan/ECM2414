import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player extends Thread {

    private int playerNum;
    private ArrayList<Card> playerHand;
    private String fileName;
    private Pack.CardDeck draw;
    private Pack.CardDeck disc;
    private CardGame game;
    private volatile int winner;
    private String logs;

    /**
     * Function that logs the player actions to a file
     * 
     * @throws IOException cannot log to a file
     */
    public void log() throws IOException {
        FileWriter newFile = new FileWriter(fileName);
        newFile.write(logs);
        newFile.close();
    }

    /**
     * Function that returns the plaeyr number
     * 
     * @return int value of the player number
     */
    public int getPlayerNum() {
        return this.playerNum;
    }

    /**
     * Function that returns the cards in the player hand
     * 
     * @return the cards in the player hand
     */
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    /**
     * Function that sets the winner of the game
     * 
     * @param winner int value of the player number that won
     */
    public void setWin(int winner){
        this.winner=winner;
    }

    /**
     * Function that returns the player number of the winner
     * 
     * @return int of the player number that won
     */
    public int getWinner(){
        return winner;
    }

    /**
     * Function that creates a string of the contents of the plaeyr hand
     * 
     * @return a string of the player hand contents
     */
    public String elements(){
        String elements ="";
        // For loop that goes through every card in the plaeyr hand
        for(int i=0;i<getPlayerHand().size();i++){
            // Adds the value of each card to the final string
            elements+= getPlayerHand().get(i).getValue()+" ";
        }
        return elements;
    }

    /**
     * Function that checks if player has a winning hand
     * 
     * @throws IOException cannot log to a file
     */
    public void checkWin() throws IOException {
        Card n = playerHand.get(0);
        int value = n.getValue();
        // For loop that looks through every card in the player hand
        for (int i = 1; i < 4; i++) {
            // Check if the cards in the player hand all have the same value
            if (value != playerHand.get(i).getValue()) {
                // Return if any card has a different value
                return;
            }
        }
        System.out.println("Player " + playerNum + " wins");
        // Player declares win and tells the other players
        game.declareWin(playerNum);
        this.winner=playerNum;
    }

    /**
     * To string method that include the player info
     * 
     * @return a string with the player number and his current hand
     */
    public String toString() {
        return "Player number is " + getPlayerNum() + " players current hand is " + getPlayerHand();
    }

    /**
     * Synchronized method to draw and discrad cards
     * 
     * @throws IOException cannot log
     */
    public synchronized void cardDrawDisc() throws IOException {
        // Check that deck has 4 cards before anything for thread safety
        if (draw.getDeck().size()<4){
            return;
        }
        ArrayList<Card> unwanted = new ArrayList<>();
        // For loop that looks through the cards in the plaeyr hand
        for (int i = 0; i < 4; i++) {
            Card n = playerHand.get(i);
            // Check if any card value matches the player number
            if (n.getValue() != playerNum) {
                // If not add them to an ArrayList
                unwanted.add(n);
            }
        }
        // Shuffle the unwanted cards to randomly discard
        Collections.shuffle(unwanted);
        Card cardToGo = unwanted.get(0);
        // Add the discarded card to the discrad deck, then remove it from the hand
        disc.addCardDeck(cardToGo);
        playerHand.remove(cardToGo);
        // Adding player discrad action for the log later
        String discmsg = "Player " + playerNum + " discards a " + cardToGo.getValue() + " to deck " + disc.getDeckNum();
        logs+=discmsg+"\n";
        // Player draws top crad from the draw deck
        Card card = draw.topCard();
        playerHand.add(card);
        // Adding player draw action for the log later
        String drawmsg = "Player " + playerNum + " draws a " + card.getValue() + " from deck " + draw.getDeckNum();
        logs+=drawmsg+"\n";
        // Adding player current hand for the log later
        String msg = "Player " + playerNum + " current hand is " + elements();
        logs+=msg+"\n\n";
    }

    /**
     * Function that adds a card to a player hand
     * 
     * @param card the crad to be added to the player hand
     */
    public void fillHand(Card card) {
        playerHand.add(card);
    }

    /**
     * Player class object constructor
     * 
     * @param playerNum the player number
     * @param draw the card deck to draw from
     * @param disc the card deck to discrad to
     * @param game the game the player is participating in
     */
    public Player(int playerNum, Pack.CardDeck draw, Pack.CardDeck disc, CardGame game) {
        this.playerNum = playerNum;
        this.playerHand = new ArrayList<Card>();
        this.fileName = "player" + playerNum + "_output.txt";
        this.draw = draw;
        this.disc = disc;
        this.game = game;
        this.winner = 0;
        this.logs="";
    }

    /**
     * Function that runs a player thread to play the game
     */
    @Override
    public void run() {
        // Logs the player initial hand
        logs+="Player "+playerNum+" initial hand "+elements()+"\n"+"\n";
        // While there is no wiiner in the game
        while(winner==0){
            // Try drawing/discrading cards then checking if hand is winning
            try {
                cardDrawDisc();
                checkWin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Try logging every player action after game finishes
        try {
            // Cehking which player has won the game
            if (winner==playerNum){
                String msg = "Player " + playerNum + " wins"+"\nPlayer "+playerNum+ " exits"+"\nPlayer "+playerNum+" final hand: "+elements();
                logs+=msg+"\n";
            }
            // Winner notifies the losing players
            else{
                String msg = "Player " + winner + " has informed "+playerNum + " that player "+winner+" has won"+"\nPlayer "+playerNum+ " exits"+"\nPlayer "+playerNum+" final hand: "+elements();
                logs+=msg+"\n";
            }
            // Log every player action
            log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

