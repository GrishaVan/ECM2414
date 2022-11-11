import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestNoJUnit {
    public static void main(String args[]) throws IOException {
        File file = new File("pack1.txt");
        if (file.exists()) {
            System.out.println("YEs");
        }
        System.out.println("No");
    }
}