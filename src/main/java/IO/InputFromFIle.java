package IO;

import Source.CaesarCipher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFromFIle {
    private final ArrayList<StringBuilder> buffer;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    public InputFromFIle(Path path) {
        buffer = new ArrayList<>();
    }

    public ArrayList<StringBuilder> readFile() {
        ArrayList<String> builderArrayList;
        try {
             builderArrayList = (ArrayList<String>) Files.readAllLines(caesarCipher.getPath(), StandardCharsets.UTF_8);
             for (String line: builderArrayList) {
                 buffer.add(new StringBuilder(line));
             }
             return buffer;
        } catch (IOException e) {
            throw new RuntimeException("Reading error" + e);
        }
    }
}
