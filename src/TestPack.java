import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.*;

public class TestPack {

    private Pack pack;

    @Before
    public void setUp() throws IOException {
        this.pack = new Pack("pack1.txt");
    }

    @Test
    public void testGetPack() {
        assertTrue(pack.getPack().size()==0);
    }
}
