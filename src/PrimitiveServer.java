import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimitiveServer extends Observer {

    private int DEFAULT_PORT = 8080;
    private Path DEFAULT_DIR = Paths.get("");

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

        Signaller.attach(this); //register file server as listening to http server

        PrimHTTPServer httpServer = new PrimHTTPServer(port);


    }

    //get the appropriate signal and perform the desired operation
    @Override
    public void update() {

        if (Signaller.currentSignal == Signaller.SIGNAL.GET) {



        }

        if (Signaller.currentSignal == Signaller.SIGNAL.POST) {

        }

        if (Signaller.currentSignal == Signaller.SIGNAL.ROOT) {

            try {
                returnFilesInDir();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }



    //-- GET --

    //need to return all the files & directories in the parent directory that the server is operating in
    private void returnFilesInDir() throws IOException {

        List<String> files = new LinkedList<>();


        try (Stream<Path> walk = Files.walk(dir)) {

            files = walk.map(x -> x.toString()).collect(Collectors.toList());

        }

        StringBuilder reply = new StringBuilder();

        for (String s : files) {
            reply.append(s + "\n");
        }

        RootHandler.writeRootResponse("Hello world!" + reply.toString());

    }

    private void returnSpecificFileContent(String filename) {

        //only want file in the working directory, nowhere else
        File tmp = new File(dir.toString() + filename);

        if (!tmp.exists()) {

            //TODO 404 STUB
        }

        //TODO 200 OK STUB AND RETURN FILE CONTENT

    }

    //-- GET END --


    //-- POST --


    //TODO should be given a stream or some array of data and write it to a new file with the desired name in the working dir
    private void createNewFile() {



    }

}
