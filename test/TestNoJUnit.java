import java.io.IOException;

public class TestNoJUnit {
    public static void main(String args[]) throws IOException {
        CardGame game = new CardGame();
        game.initializeGame();
        game.runGame();
    }
}