import static org.junit.Assert.assertTrue;

import org.junit.*;

public class TestPlayer {

    private Player player0;

    @Before
    public void setup() {
        this.player0 = new Player(0);
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
        assertTrue(player0.getPLayerNum() == 0);
    }

    @Test
    public void testGetPlayerHand() {
        assertTrue(player0.getPlayerHand().size() == 4);
    }

    @Test
    public void testCheckWin() {
        assertTrue(player0.checkWin() == false);
    }

}