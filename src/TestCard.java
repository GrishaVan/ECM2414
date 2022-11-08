import static org.junit.Assert.assertTrue;

import org.junit.*;

public class TestCard {

    private Card card0;

    @Before
    public void setUp() {
        this.card0 = new Card(1);
    }

    @Test
    public void testGetValue() {
        assertTrue(card0.getValue() == 1);
    }

}
