import IO.FileService;
import Source.CaesarCipher;
public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        Runner runner = new Runner(fileService);
        runner.run(args);
    }
}
