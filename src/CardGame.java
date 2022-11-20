import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Player> players;
    private ArrayList<Pack.CardDeck> decks;
    private Pack pack;

    /**
     * Function that returns every player in the game
     * 
     * @return an ArrayList with every player in the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Function that returns every deck in the game
     * 
     * @return an ArrayList with every deck in the game
     */
    public ArrayList<Pack.CardDeck> getDecks() {
        return decks;
    }

    /**
     * Function that creates a log output file for the player
     *
     * @param player the player for which to create the file
     * @throws IOException if file cannot be created
     */
    public void createFile(Player player) throws IOException {
        String name = "player" + player.getPlayerNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    /**
     * Function that creates a log output file for the card deck
     * 
     * @param deck the card deck for which to create the file
     * @throws IOException if the file cannot be created
     */
    public void createFile(Pack.CardDeck deck) throws IOException {
        String name = "deck" + deck.getDeckNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    /**
     * Function to create a player in the game
     * 
     * @param num  the player number
     * @param draw the card deck from which the player will draw
     * @param disc the crad deck to which the player will discard
     */
    public void createPlayer(int num, Pack.CardDeck draw, Pack.CardDeck disc) {
        players.add(new Player(num, draw, disc, this));
    }

    /**
     * Function to create a card deck in the game
     * 
     * @param num the card deck number
     */
    public void createDeck(int num) {
        decks.add(new Pack.CardDeck(num));
    }

    /**
     * Function to create a pack of cards in the game
     * 
     * @param fileName  file which contains the value for the crads
     * @param playerNum the number of players
     * @return true if created successfully, false otherwise
     * @throws IOException if file pathname is incorrect
     */
    public boolean createPack(String fileName, int playerNum) throws IOException {
        File file = new File(fileName);
        // Check if a file exists
        if (file.exists()) {
            boolean onlyInts=true;
            Scanner scanner = new Scanner(new File(fileName));
            // Check if there are still values to be read from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //checking theyre are only digits entered in the pack file
                if(line.matches("[0-9]+")==false){
                    return false;
                }
            }
            scanner.close();
            if(onlyInts){
                this.pack = new Pack(fileName);
                // Check that pack confirms with size 8 * playerNum
                if (this.pack.getPackSize() == 8 * playerNum) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Function that sets up the game
     * 
     * @throws IOException error with loading files
     */
    public void initializeGame() throws IOException {
        int playerNum = 0;
        String file = "";
        boolean validNum = false;
        // System asks how many players participating
        Scanner scanner = new Scanner(System.in);

        //Will repeat request until a valid number is entered
        while(!validNum){
            try{
                System.out.println("Please enter a valid number of players");
                playerNum = scanner.nextInt();
                if(playerNum>1){
                        validNum=true;
                }
            }
            //catches if a non int is entered
            catch(InputMismatchException e){
                System.out.println("Invalid input");
                scanner.next();
            }
        }
        // System asks for the pack file location
        System.out.println("Please enter the location of pack to load");
        if (scanner.hasNextLine()) {
            file = scanner.next();
        }
        // Check if a pack could be created
        boolean valid = createPack(file, playerNum);
        // While pack not created ask to enter correct file location
        while (!valid) {
            System.out.println("File location or size of pack is invalid\nPlease enter another");
            if (scanner.hasNextLine()) {
                file = scanner.next();
                valid = createPack(file, playerNum);
            }
        }
        scanner.close();
        pack = new Pack(file);
        // For loop that creates an equal amount of card decks as players
        for (int i = 0; i < playerNum; i++) {
            Pack.CardDeck deck = new Pack.CardDeck(i + 1);
            decks.add(deck);
        }
        // For loop that goes thorugh every player
        for (int i = 0; i < playerNum; i++) {
            // Check if last player
            if (i != playerNum - 1) {
                // Lets player discards to deck 1
                Player player = new Player(i + 1, decks.get(i), decks.get(i + 1), this);
                players.add(player);
            } else {
                Player player = new Player(i + 1, decks.get(i), decks.get(0), this);
                players.add(player);
            }
        }
        // For loop to fill player hand with 4 cards
        for (int i = 0; i < 4; i++) {
            // For loop that goes through every player to fill hand 1 by 1
            for (int w = 0; w < players.size(); w++) {
                players.get(w).fillHand(pack.getPack().get(0));
                // Remove distributed cards from the pack
                pack.getPack().remove(0);
            }
        }
        // For loop to fill the card decks with 4 cards
        for (int i = 0; i < 4; i++) {
            // For loop that goes through every card deck to fill 1 by 1
            for (int w = 0; w < players.size(); w++) {
                decks.get(w).addCardDeck(pack.getPack().get(0));
                // Remove distributed card from the pack
                pack.getPack().remove(0);
            }
        }
    }

    /**
     * Function to declare the winner of the game
     * 
     * @param winner player number of the winning player
     * @throws IOException cannot log to a file
     */
    public void declareWin(int winner) throws IOException {
        // For loop tells evryplayer who the winner is
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setWin(winner);
        }
        // For loop that logs the cards in the deck at the end of game
        for (int i = 0; i < decks.size(); i++) {
            decks.get(i).log();
        }
    }

    /**
     * Function that runs the game
     * 
     * @throws IOException cannot log to a file
     */
    public void runGame() throws IOException {
        // For loop to check if any player has a winning hand at the start
        for (int i = 0; i < players.size(); i++) {
            players.get(i).checkWin();
        }
        // For loop that starts the player threads to play
        for (int i = 0; i < players.size(); i++) {
            players.get(i).start();
        }
    }

    /**
     * CardGame class object constructor
     */
    public CardGame() {
        this.players = new ArrayList<Player>();
        this.decks = new ArrayList<Pack.CardDeck>();
    }

    public static void main(String args[]) throws IOException {
        CardGame game = new CardGame();
        game.initializeGame();
        game.runGame();
    }
}
