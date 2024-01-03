package IO;

import Source.CaesarCipher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class InputFromFIle {
    private final ArrayList<StringBuilder> buffer;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    public InputFromFIle() {
        buffer = new ArrayList<>();
    }

    public ArrayList<StringBuilder> readFile() {
        ArrayList<String> builderArrayList;
        try {
            if (Files.exists(caesarCipher.getPath())) {
                builderArrayList = (ArrayList<String>) Files.readAllLines(caesarCipher.getPath(), StandardCharsets.UTF_8);
                for (String line : builderArrayList) {
                    buffer.add(new StringBuilder(line));
                }
                return buffer;
            } else {
                throw new FileNotFoundException("File is not exist");
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading error" + e);
        }
    }
}
