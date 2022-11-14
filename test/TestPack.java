import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.*;

public class TestPack {

    private Pack pack;

    @Before
    public void setUp() throws IOException {
        pack = new Pack("test/testPack.txt");
    }

    @Test
    public void testGetPackSize() {
        assertTrue(pack.getPackSize()==8);
    }
}
