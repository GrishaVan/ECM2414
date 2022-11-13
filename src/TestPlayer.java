import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.*;

public class TestPlayer {

    private Player player0;

    @Before
    public void setup() {
        CardGame game = new CardGame();
        Pack.CardDeck deck = new Pack.CardDeck(0);
        this.player0 = new Player(0,deck,deck,game);
        Card n1 = new Card(0);
        Card n2 = new Card(2);
        Card n3 = new Card(3);
        Card n4 = new Card(5);
        player0.getPlayerHand().add(n1);
        player0.getPlayerHand().add(n2);
        player0.getPlayerHand().add(n3);
        player0.getPlayerHand().add(n4);
    }

    @Test
    public void testGetPlayerNum() {
        assertTrue(player0.getPlayerNum() == 0);
    }

    @Test
    public void testGetPlayerHand() {
        assertTrue(player0.getPlayerHand().size() == 4);
    }

    //@Test
    //public void testCheckWin() throws IOException {
    //    assertTrue(player0.checkWin() == false);
    //}

}
