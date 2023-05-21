import IO.FileService;
import Source.CaesarCipher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        Runner runner = new Runner(fileService);
        runner.run(args);
    }
}
