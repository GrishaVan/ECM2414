import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestCard {

    private Card card0;

    @Before
    public void setUp() {
        this.card0 = new Card(1);
    }

    @Test
    public void testGetValue() {
        assertEquals(card0.getValue(),1);
    }

    @After
    public void cleanUp(){
        card0=null;
    }
}
