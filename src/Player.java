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

    public void log() throws IOException {
        FileWriter newFile = new FileWriter(fileName);
        newFile.write(logs);
        newFile.close();
    }

    public int getPlayerNum() {
        return this.playerNum;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    public void setWin(int winner){
        this.winner=winner;
    }

    public String elements(){
        String elements ="";
        for(int i=0;i<getPlayerHand().size();i++){
            elements+= getPlayerHand().get(i).getValue()+" ";
        }
        return elements;
    }
    public void checkWin() throws IOException {
        Card n = playerHand.get(0);
        int value = n.getValue();
        for (int i = 1; i < 4; i++) {
            if (value != playerHand.get(i).getValue()) {
                return;
            }
        }
        System.out.println("Player " + playerNum + " wins");
        game.declareWin(playerNum);
        this.winner=playerNum;
    }

    public String toString() {
        return "Player number is " + getPlayerNum() + " players current hand is " + getPlayerHand();
    }

    public synchronized void cardDrawDisc() throws IOException {
        if (draw.getDeck().size()<4){
            return;
        }
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
        String discmsg = "Player " + playerNum + " discards a " + cardToGo.getValue() + " to deck " + disc.getDeckNum();
        logs+=discmsg+"\n";

        Card card = draw.topCard();
        playerHand.add(card);
        String drawmsg = "Player " + playerNum + " draws a " + card.getValue() + " from deck " + draw.getDeckNum();
        logs+=drawmsg+"\n";

        String msg = "Player " + playerNum + " current hand is " + elements();
        logs+=msg+"\n\n";
    }

    public void fillHand(Card card) {
        playerHand.add(card);
    }

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

    @Override
    public void run() {
        logs+="Player "+playerNum+" initial hand "+elements()+"\n"+"\n";
        while(winner==0){
            try {
                cardDrawDisc();
                checkWin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (winner==playerNum){
                String msg = "Player " + playerNum + " wins"+"\nPlayer "+playerNum+ " exits"+"\nPlayer "+playerNum+" final hand: "+elements();
                logs+=msg+"\n";
            }
            else{
                String msg = "Player " + winner + " has informed "+playerNum + " that player "+winner+" has won"+"\nPlayer "+playerNum+ " exits"+"\nPlayer "+playerNum+" final hand: "+elements();
                logs+=msg+"\n";
            }
            log();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

