import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Player> players;
    private ArrayList<Pack.CardDeck> decks;
    private Pack pack;
    private volatile boolean gameRunning;

    public void createFile(Player player) throws IOException {
        String name = "player" + player.getPLayerNum() + "_output.txt";
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
        players.add(new Player(num, draw, disc));
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
            file = "src/" + scanner.next();
        }
        boolean valid = createPack(file, playerNum);
        while (!valid) {
            System.out.println("File location or size of pack is invalid");
            if (scanner.hasNextLine()) {
                file = "src/" + scanner.next();
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
                Player player = new Player(i + 1, decks.get(i), decks.get(i + 1));
                players.add(player);
            } else {
                Player player = new Player(i + 1, decks.get(i), decks.get(0));
                players.add(player);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int w = 0; w < players.size(); w++) {
                players.get(w).fillHand(pack.getPack().get(pack.getPackSize() - 1));
                pack.getPack().remove(pack.getPackSize() - 1);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int w = 0; w < players.size(); w++) {
                decks.get(w).addCardDeck(pack.getPack().get(pack.getPackSize() - 1));
                pack.getPack().remove(pack.getPackSize() - 1);
            }
        }
    }

    public void declareWin() {
        gameRunning = false;
    }

    public CardGame() {
        this.players = new ArrayList<Player>();
        this.decks = new ArrayList<Pack.CardDeck>();
    }
}
