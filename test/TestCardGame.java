import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCardGame {
    private CardGame game;
    //player1 and deck1 are the correct representations of what the tests should produce
    private Player player1;
    private Pack.CardDeck deck1;
    
    @Before
    public void setUp(){
        game=new CardGame();
        player1 = new Player(1, null, null, game);
        game.getPlayers().add(player1);

        deck1 = new Pack.CardDeck(1);
        game.getDecks().add(deck1);
    }

    @Test
    public void testGetPlayers(){
        assertEquals(game.getPlayers().get(0),player1);
        assertEquals(game.getPlayers().size(),1);
    }

    @Test
    public void testCreatePlayer(){
        game.createPlayer(2, null, null);
        assertEquals(game.getPlayers().get(1).getPlayerNum(),2);
        assertEquals(game.getPlayers().size(), 2);
    }

    @Test
    public void testGetDecks(){
        assertEquals(game.getDecks().get(0),deck1);
        assertEquals(game.getDecks().size(),1);
    }

    @Test
    public void testCreateDeck(){
        game.createDeck(2);
        assertEquals(game.getDecks().get(1).getDeckNum(),2);
        assertEquals(game.getDecks().size(), 2);
    }

    @After
    public void cleanUp(){
        game=null;
        player1=null;
        deck1=null;
    }
}
