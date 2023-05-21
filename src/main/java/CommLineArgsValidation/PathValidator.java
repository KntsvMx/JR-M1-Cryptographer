package CommLineArgsValidation;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidator {
    private static final PathValidator VALIDATOR = new PathValidator();
    private Path path;

    private PathValidator() {

    }

    public static PathValidator getInstance() {
        return VALIDATOR;
    }

    public Path getAndValidation(String[] stringPath) {
        Path validatedPath = convertToPath(stringPath);
        if (!validatePath(validatedPath)) {
            try {
                this.path = convertToAbsolute(validatedPath);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert path to absolute: " + stringPath, e);
            }
        }
        return this.path = validatedPath;
    }

    public Path getAndValidation(String stringPath) {
        Path validatedPath = convertToPath(stringPath);
        if (!validatePath(validatedPath)) {
            try {
                this.path = convertToAbsolute(validatedPath);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert path to absolute: " + stringPath, e);
            }
        }
        return this.path = validatedPath;
    }

    private Path convertToPath(String[] stringPath) {
        StringBuilder path = new StringBuilder();
        for (String temp : stringPath) {
            if (temp.isEmpty()) {
                throw new IllegalArgumentException("Empty string path");
            }
        }
        for (int i = 1; i < stringPath.length - 1; i++) {
            path.append(stringPath[i]).append(" ");
        }
        String sanitizedPath = path.toString().replaceAll("\\s+$", "");
        return Path.of(sanitizedPath);
    }

    private Path convertToPath(String stringPath) {
        if (stringPath.isEmpty()) {
            throw new IllegalArgumentException("Empty string path");
        }
        String sanitizedPath = stringPath.replaceAll("\\s+$", "");
        return Paths.get(sanitizedPath);
    }

    private boolean validatePath(Path path) {
        return path.isAbsolute();
    }

    private Path convertToAbsolute(Path path) {
        return path.toAbsolutePath();
    }
}
