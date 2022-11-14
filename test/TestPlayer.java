import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

public class TestPlayer {

    private Player player1;
    private Player player2;
    private Player player3;
    private int[] correct1;
    private Pack.CardDeck deck;
    private Pack.CardDeck invalidDeck;

    @Before
    public void setup() {
        Card n1 = new Card(1);
        Card n2 = new Card(2);
        Card n3 = new Card(3);
        Card n4 = new Card(4);
        Card n5 = new Card(5);
        CardGame game = new CardGame();

        deck = new Pack.CardDeck(2);
        deck.getDeck().add(n1);
        deck.getDeck().add(n2);
        deck.getDeck().add(n3);
        deck.getDeck().add(n4);

        invalidDeck = new Pack.CardDeck(1);
        invalidDeck.getDeck().add(n5);

        this.player1 = new Player(1,invalidDeck,deck,game);
        player1.getPlayerHand().add(n1);
        player1.getPlayerHand().add(n2);
        player1.getPlayerHand().add(n3);
        player1.getPlayerHand().add(n4);
        correct1 = new int[] {1,2,3,4};
        
        player2 = new Player(2, deck, deck, game);
        player2.getPlayerHand().add(n5);
        player2.getPlayerHand().add(n5);
        player2.getPlayerHand().add(n5);
        player2.getPlayerHand().add(n5);

        player3 = new Player(3, deck, invalidDeck, game);
        player3.getPlayerHand().add(n3);
        player3.getPlayerHand().add(n3);
        player3.getPlayerHand().add(n3);
        player3.getPlayerHand().add(n5);
    }

    @Test
    public void testGetPlayerNum() {
        assertTrue(player1.getPlayerNum() == 1);
    }

    @Test
    public void testGetPlayerHand() {
        assertTrue(player1.getPlayerHand().size() == 4);
        for(int i=0;i<4;i++){
            assertTrue(player1.getPlayerHand().get(i).getValue()==correct1[i]);
        }
    }

    @Test
    public void testGetWinner() {
    assertTrue(player1.getWinner()==0);
    }

    @Test
    public void testSetWin() {
        player1.setWin(1);
        assertTrue(player1.getWinner()==1);
        player1.setWin(0);
    }

    @Test
    public void testElements(){
        assertEquals(player1.elements(),"1 2 3 4 ");
    }

    @Test
    public void testCheckWin() throws IOException{
        assertTrue(player1.getWinner()==0);
        assertTrue(player2.getWinner()==0);
        player1.checkWin();
        player2.checkWin();
        assertTrue(player1.getWinner()==0);
        assertTrue(player2.getWinner()==2);
    }

    @Test
    public void testCardDrawDisc() throws IOException{
        player3.cardDrawDisc();
        assertTrue(deck.getDeck().size()==3);
        assertTrue(invalidDeck.getDeck().size()==2);
        assertEquals(player3.elements(),"3 3 3 1 ");
        player1.cardDrawDisc();
        assertTrue(deck.getDeck().size()==3);
        assertTrue(invalidDeck.getDeck().size()==2);
        assertEquals(player1.elements(),"1 2 3 4 ");
    }

    @Test
    public void testFillHand(){
        player1.fillHand(new Card(3));
        assertEquals(player1.elements(),"1 2 3 4 3 ");
    }
}
