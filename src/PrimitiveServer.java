import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimitiveServer extends Observer {

    private int DEFAULT_PORT = 8080;
    private Path DEFAULT_DIR = FileSystems.getDefault().getPath("");
    private int port;
    private Path dir;

    //various overloads follow

    public PrimitiveServer() {

        this.port = DEFAULT_PORT;
        this.dir = DEFAULT_DIR;

    }

    public PrimitiveServer(int port) {

        this.port = port;
        this.dir = DEFAULT_DIR;

    }

    public PrimitiveServer(Path dir) {

        this.port = DEFAULT_PORT;
        this.dir = dir;

    }

    public PrimitiveServer(int port, Path dir) {

        this.port = port;
        this.dir = dir;

    }


    //operates the server
    public void initialize() throws IOException {

        HTTPServerResource.initializeServer(port, this);

    }


    @Override
    public void update(String data) {

        try {

            if (data.equalsIgnoreCase("/")) {

                this.subject.responseData = returnFilesInDir();

                System.out.println(returnFilesInDir());

                System.out.println(this.subject.toString());

            } else {

                this.subject.responseData = returnSpecificFileContent(data);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //-- GET --

    //need to return all the files & directories in the parent directory that the server is operating in
    private String returnFilesInDir() throws IOException {

        List<String> files = new LinkedList<>();


        try (Stream<Path> walk = Files.walk(dir)) {

            files = walk.map(x -> x.toString()).collect(Collectors.toList());

        }

        StringBuilder reply = new StringBuilder();

        for (String s : files) {
            reply.append(s + "\n");
        }

        System.out.println(System.getProperty("user.dir"));

        return reply.toString();

    }

    private String returnSpecificFileContent(String filename) throws IOException {

        filename = filename.substring(1); //strip the / from it or it fucks up

        File tmp = new File(filename);

        if (tmp.exists()) {

            System.out.println("Desired file: " +  filename);

        }

        return new String(Files.readAllBytes(Paths.get(filename)), "UTF-8");

    }

    //-- GET END --


    //-- POST --


    //TODO should be given a stream or some array of data and write it to a new file with the desired name in the working dir
    private void createNewFile() {



    }

}
