package IO;

import Source.TypeOfCommandEnum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class OutputToFile {
    private final Path path;

    public OutputToFile(Path path) {
        this.path = path;
    }

    public Path nameOfFile(TypeOfCommandEnum tag) {
        String prefix = switch (tag) {
            case DECRYPT -> "[ DECRYPT ]";
            case ENCRYPT -> "[ ENCRYPT ]";
            case BRUTE_FORCE -> "[ BRUTE_FORCE ]";
        };

        return renameFile(prefix);
    }

    private Path renameFile(String tag) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");

        if (dotIndex != -1) {
            String newFileName = getNewFileName(tag, fileName, dotIndex);

            Path targetFile = path.resolveSibling(newFileName);

            try {
                Files.copy(path, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File successfully written: " + targetFile);
                return targetFile.toAbsolutePath();
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }
        } else {
            System.err.println("Invalid file name format: " + fileName);
        }
        return null;
    }

    public void writeToFile(Path path, ArrayList<StringBuilder> lines) throws IOException {
        List<String> stringLines = new ArrayList<>();
        for (StringBuilder line : lines) {
            stringLines.add(line.toString());
        }
        try {
            Files.write(path, stringLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("Error writing to file" + e.getMessage());
        }

    }

    private String getNewFileName(String tag, String fileName, int dotIndex) {
        String nameWithoutExtension = fileName.substring(0, dotIndex);
        String extension = fileName.substring(dotIndex);

        int openingBracketIndex = nameWithoutExtension.lastIndexOf("[");
        int closingBracketIndex = nameWithoutExtension.lastIndexOf("]");

        if (openingBracketIndex != -1 && closingBracketIndex != -1 && closingBracketIndex == nameWithoutExtension.length() - 1) {
            nameWithoutExtension = nameWithoutExtension.substring(0, openingBracketIndex);
        }

        return nameWithoutExtension + tag + extension;
    }
}
