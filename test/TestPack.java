import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.*;

public class TestPack {

    private Pack pack1;
    private int[] correct1;

    @Before
    public void setUp() throws IOException {
        Card n1 = new Card(1);
        Card n2 = new Card(2);
        Card n3 = new Card(3);
        Card n4 = new Card(4);
        Card n5 = new Card(5);

        pack1 = new Pack("test/testPack.txt");

        pack1.getPack().add(n1);
        pack1.getPack().add(n2);
        pack1.getPack().add(n3);
        pack1.getPack().add(n4);
        pack1.getPack().add(n5);
        correct1 = new int[] {1,2,3,4,5};
    }

    @Test
    public void testGetPackSize() {
        assertEquals(pack1.getPackSize(),5);
    }

    @Test
    public void testGetPack(){
        for(int i=0;i<5;i++){
            assertEquals(pack1.getPack().get(i).getValue(),correct1[i]);
        }
    }

    @After
    public void cleanUp(){
        pack1=null;
        correct1=null;
    }
}
