import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Player> players;
    private ArrayList<Pack.CardDeck> decks;
    private Pack pack;
    private volatile boolean gameRunning;

    /**
     * Function to create a log file for a player
     * 
     * @param player the player for which to create the log file
     * @throws IOException if there is a problem while creating the file
     */
    public void createFile(Player player) throws IOException {
        String name = "player" + player.getPLayerNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    /**
     * Function to create a log file for a Card Deck
     * 
     * @param deck the card deck for which to create a log file
     * @throws IOException if there is a problem while creating the file
     */
    public void createFile(Pack.CardDeck deck) throws IOException {
        String name = "deck" + deck.getDeckNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    /**
     * Function to create a player for the card game
     * 
     * @param num  the player number
     * @param draw the crad deck from which the player will draw draw cards
     * @param disc the card deck to which the player will discard his cards
     */
    public void createPlayer(int num, Pack.CardDeck draw, Pack.CardDeck disc) {
        players.add(new Player(num, draw, disc));
    }

    /**
     * Function to create a card deck for the game
     * 
     * @param num the card deck number
     */
    public void createDeck(int num) {
        decks.add(new Pack.CardDeck(num));
    }

    /**
     * Function to create a pack of cards for the card game
     * 
     * @param fileName  file which contains the value of the cards
     * @param playerNum the total number of players participating in the game
     * @return true if pack created, false if the pack was not created
     * @throws IOException if the pathname for the pack is incorrect
     */
    public boolean createPack(String fileName, int playerNum) throws IOException {
        File file = new File(fileName);
        // Check that the pathname for the pack is correct
        if (file.exists()) {
            this.pack = new Pack(fileName);
            // Make sure the pack has 8n cards in it
            if (this.pack.getPackSize() == 8 * playerNum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to initialize the game with players and card decks
     * 
     * @throws IOException
     */
    public void initializeGame() throws IOException {
        int playerNum = 0;
        String file = "";
        // Asking for the number of players playing
        System.out.println("Please enter the number of players");
        Scanner scanner = new Scanner(System.in);
        playerNum = scanner.nextInt();
        // Asking for the location of the card pack
        System.out.println("Please enter the location of pack to load");
        if (scanner.hasNextLine()) {
            file = "src/" + scanner.next();
        }
        boolean valid = createPack(file, playerNum);
        // While loop asking for the correct pack, if pack wasn't created
        while (!valid) {
            System.out.println("File location or size of pack is invalid");
            if (scanner.hasNextLine()) {
                file = "src/" + scanner.next();
                valid = createPack(file, playerNum);
            }
        }
        scanner.close();
        pack = new Pack(file);
        // Create card decks for the correct player number
        for (int i = 0; i < playerNum; i++) {
            Pack.CardDeck deck = new Pack.CardDeck(i + 1);
            decks.add(deck);
        }
        // Create the players for the game
        for (int i = 0; i < playerNum; i++) {
            // Checking if player is the last one, since draws from deck 1
            if (i != playerNum - 1) {
                Player player = new Player(i + 1, decks.get(i), decks.get(i + 1));
                players.add(player);
            } else {
                Player player = new Player(i + 1, decks.get(i), decks.get(0));
                players.add(player);
            }
        }
        // For loop to fill the hands of each player with 4 cards
        for (int i = 0; i < 4; i++) {
            // For loop that goes through every player and adds 1 card to their hand
            for (int w = 0; w < players.size(); w++) {
                players.get(w).fillHand(pack.getPack().get(pack.getPackSize() - 1));
                // Remove the cards from the pack once handed out
                pack.getPack().remove(pack.getPackSize() - 1);
            }
        }
        // For loop to fill the card decks with 4 cards
        for (int i = 0; i < 4; i++) {
            // For loop that goes through every deck and adds 1 card to them
            for (int w = 0; w < players.size(); w++) {
                decks.get(w).addCardDeck(pack.getPack().get(pack.getPackSize() - 1));
                // Remove the cards from the pack once handed out
                pack.getPack().remove(pack.getPackSize() - 1);
            }
        }
    }

    /**
     * Function that declares a game won
     */
    public void declareWin() {
        gameRunning = false;
    }

    /**
     * CardGame class constructor
     */
    public CardGame() {
        this.players = new ArrayList<Player>();
        this.decks = new ArrayList<Pack.CardDeck>();
    }
}
