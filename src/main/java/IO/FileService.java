package IO;

import Source.CaesarCipher;

import java.nio.file.Path;
import java.util.List;

public class FileService {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private final InputFromFIle inputFromFIle = new InputFromFIle(caesarCipher.getPath());
    private final OutputToFile outputToFile = new OutputToFile(caesarCipher.getPath());

    private List<String> lines;

    public List<String> readFromFile() {
        lines = inputFromFIle.readFile();
        return lines;
    }

    public void writeToFileWithTag() {
        outputToFile.nameOfFile(caesarCipher.getCommand());
        outputToFile.writeToFile();
    }
}
