import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Player> players;
    private ArrayList<Pack.CardDeck> decks;
    private Pack pack;
    private volatile boolean gameRunning;

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void createFile(Player player) throws IOException {
        String name = "player" + player.getPlayerNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    public void createFile(Pack.CardDeck deck) throws IOException {
        String name = "deck" + deck.getDeckNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    public void createPlayer(int num, Pack.CardDeck draw, Pack.CardDeck disc) {
        players.add(new Player(num, draw, disc,this));
    }

    public void createDeck(int num) {
        decks.add(new Pack.CardDeck(num));
    }

    public boolean createPack(String fileName, int playerNum) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            this.pack = new Pack(fileName);
            if (this.pack.getPackSize() == 8 * playerNum) {
                return true;
            }
        }
        return false;
    }

    public void initializeGame() throws IOException {
        int playerNum = 0;
        String file = "";
        System.out.println("Please enter the number of players");
        Scanner scanner = new Scanner(System.in);
        playerNum = scanner.nextInt();
        System.out.println("Please enter the location of pack to load");
        if (scanner.hasNextLine()) {
            file = scanner.next();
        }
        boolean valid = createPack(file, playerNum);
        while (!valid) {
            System.out.println("File location or size of pack is invalid");
            if (scanner.hasNextLine()) {
                file = scanner.next();
                valid = createPack(file, playerNum);
            }
        }
        scanner.close();
        pack = new Pack(file);
        for (int i = 0; i < playerNum; i++) {
            Pack.CardDeck deck = new Pack.CardDeck(i + 1);
            decks.add(deck);
        }
        for (int i = 0; i < playerNum; i++) {
            if (i != playerNum - 1) {
                Player player = new Player(i + 1, decks.get(i), decks.get(i + 1),this);
                players.add(player);
            } else {
                Player player = new Player(i + 1, decks.get(i), decks.get(0),this);
                players.add(player);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int w = 0; w < players.size(); w++) {
                players.get(w).fillHand(pack.getPack().get(0));
                pack.getPack().remove(0);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int w = 0; w < players.size(); w++) {
                decks.get(w).addCardDeck(pack.getPack().get(0));
                pack.getPack().remove(0);
            }
        }
        for(int i=0;i<players.size();i++){
            System.out.println(""+ players.get(i).getPlayerNum() + players.get(i).getPlayerHand());
        }
    }

    public void declareWin(int winner) throws IOException {
        for (int i=0;i<players.size();i++){
            players.get(i).setWin(winner);
        }
        for (int i=0;i<decks.size();i++){
            decks.get(i).log();
        }
    }

    public void runGame() throws IOException {
        for (int i=0;i<players.size();i++){
            players.get(i).checkWin();
        }
        for (int i=0;i<players.size();i++){
            players.get(i).start();
        }
    }

    public CardGame() {
        this.players = new ArrayList<Player>();
        this.decks = new ArrayList<Pack.CardDeck>();
        this.gameRunning = true;
    }
}
