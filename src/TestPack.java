import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.*;

public class TestPack {

    private Pack pack0;

    @Before
    public void setUp() throws IOException {
        this.pack0 = new Pack("/Packs/pack1.txt");
    }

    @Test
    public void testGetPack() {
        assertTrue(pack0.getPack().size() == 0);
    }
}
