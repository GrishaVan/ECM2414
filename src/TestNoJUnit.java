import java.io.IOException;

public class TestNoJUnit {
    public static void main(String args[]) throws IOException{
        Pack pack = new Pack("pack1.txt");
        int val = pack.getPack().get(0).getValue()+ pack.getPack().get(1).getValue();
        System.out.println(val);
    }
}