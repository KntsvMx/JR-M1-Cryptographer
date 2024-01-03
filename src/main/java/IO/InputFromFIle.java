package IO;

import Source.CaesarCipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class InputFromFIle {
    private final ArrayList<StringBuilder> buffer;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    public InputFromFIle() {
        buffer = new ArrayList<>();
    }

    public ArrayList<StringBuilder> readFile() {
        try (BufferedReader reader = Files.newBufferedReader(caesarCipher.getPath(), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(new StringBuilder(line));
            }
            return buffer;
        } catch (NoSuchFileException e) {
            throw new RuntimeException("File doesn't exist: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }
}
