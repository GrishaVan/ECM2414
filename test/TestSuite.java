
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestCard.class,
   TestPlayer.class,
   TestDeck.class,
   TestPack.class,
   TestCardGame.class
})

public class TestSuite {   
}
