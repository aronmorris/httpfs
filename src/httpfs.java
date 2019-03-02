import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class httpfs {

    public static void main(String[] args) {

        PrimitiveServer serv = new PrimitiveServer(8080);

        try {
            serv.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
