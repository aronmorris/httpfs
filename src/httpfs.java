import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class httpfs {

    public static void main(String[] args) {

        PrimitiveServer serv = new PrimitiveServer(8080, Paths.get("F:/COMP 354"));

        try {
            serv.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
