import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDeck {

    private Pack.CardDeck deck1;
    private int[] correct1;
    private Pack.CardDeck deck2;

    @Before
    public void setUp(){
        Card n1 = new Card(1);
        Card n2 = new Card(2);
        Card n3 = new Card(3);
        Card n4 = new Card(4);

        deck1 = new Pack.CardDeck(1);
        deck1.addCardDeck(n1);
        deck1.addCardDeck(n2);
        deck1.addCardDeck(n3);
        deck1.addCardDeck(n4);
        //Array shows what should be the correct values of deck1
        correct1 = new int[] {1,2,3,4};

        deck2 = new Pack.CardDeck(2);
        deck2.addCardDeck(n1);
    }

    @Test
    public void testGetDeckNum(){
        assertEquals(deck1.getDeckNum(),1);
    }

    @Test
    public void testGetDeck(){
        assertEquals(deck1.getDeck().size(),4);
        //Checking values of deck against correct values
        for(int i=0;i<4;i++){
            assertEquals(deck1.getDeck().get(i).getValue(),correct1[i]);
        }
    }

    @Test
    public void testAddCardDeck(){
        deck1.addCardDeck(new Card(0));
        assertEquals(deck1.getDeck().get(4).getValue(),0);
        deck1.getDeck().remove(4);
    }

    @Test
    public void testTopCard(){
        assertEquals(deck2.getDeck().size(),1);
        assertEquals(deck2.topCard().getValue(),1);
        assertEquals(deck2.getDeck().size(),0);
    }

    @After
    public void cleanUp(){
        deck1=null;
        deck2=null;
        correct1=null;
    }
}
