package IO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFromFIle {
    private final Path path;
    private List<String> buffer;

    public InputFromFIle(Path path) {
        this.path = path;
        buffer = new ArrayList<>();
    }

    public List<String> readFile() {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Reading error" + e);
        }
    }
}
