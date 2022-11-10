import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Player> players;
    private ArrayList<Pack.CardDeck> decks;
    private Pack pack;

    public void createFile(Player player) throws IOException{
        String name = "player"+player.getPLayerNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    public void createFile(Pack.CardDeck deck) throws IOException{
        String name = "deck"+deck.getDeckNum() + "_output.txt";
        File newFile = new File(name);
        newFile.delete();
        newFile.createNewFile();
    }

    public void createPlayer(int num){
        players.add(new Player(num));
    }

    public void createDeck(int num){
        decks.add(new Pack.CardDeck(num));
    }

    public boolean createPack(String fileName, int playerNum) throws IOException{
        File file = new File(fileName);
        if (file.exists()){
            this.pack= new Pack(fileName);
            if(this.pack.getPackSize() == 8 * playerNum){
            return true;
            }
        }
        return false;
    }

    public void startGame() throws IOException{
        System.out.println("Please enter the number of players");
        Scanner scanner = new Scanner(System.in);
        int playerNum = scanner.nextInt();
        scanner.close();

        System.out.println("Please enter the location of pack to load");
        scanner = new Scanner(System.in);
        String file = scanner.nextLine();
        boolean valid = createPack(file, playerNum);
        scanner.close();
        while (!valid){
            System.out.println("File location or size is invalid. Please enter another");
            scanner = new Scanner(System.in);
            file = scanner.nextLine();
            scanner.close();
            valid = createPack(file, playerNum);
        }
    }

    public CardGame (){}
}
