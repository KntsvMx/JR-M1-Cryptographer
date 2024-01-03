package IO;

import Source.CaesarCipher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileService {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    public ArrayList<StringBuilder> readFromFile() {
        InputFromFIle inputFromFIle = new InputFromFIle();
        return inputFromFIle.readFile();
    }

    public void writeToFileWithTag(ArrayList<StringBuilder> lines) throws IOException {
        OutputToFile outputToFile = new OutputToFile(caesarCipher.getPath());
        Path path = outputToFile.nameOfFile(caesarCipher.getCommand());
        outputToFile.writeToFile(path, lines);
    }
}
