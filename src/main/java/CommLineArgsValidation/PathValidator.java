package CommLineArgsValidation;

import java.nio.file.Path;
import java.util.Arrays;

public class PathValidator {
    private static final PathValidator VALIDATOR = new PathValidator();
    private Path path;

    private PathValidator() {

    }

    public static PathValidator getInstance() {
        return VALIDATOR;
    }

    public Path getAndValidation(String[] stringPath) {
        validateInput(stringPath);
        Path validatedPath = convertToPath(stringPath);
        processInvalidPath(validatedPath);
        return this.path;
    }

    public Path getAndValidation(String stringPath) {
        validateInput(stringPath);
        Path validatedPath = convertToPath(stringPath);
        processInvalidPath(validatedPath);
        return this.path;
    }

    private Path convertToPath(String[] stringPath) {
        String sanitizedPath = String.join(" ", Arrays.copyOfRange(stringPath, 1, stringPath.length)).replaceAll("\\s+$", "");
        if (sanitizedPath.isEmpty()) {
            throw new IllegalArgumentException("Empty string path");
        }
        return Path.of(sanitizedPath);
    }

    private Path convertToPath(String stringPath) {
        if (stringPath.isEmpty()) {
            throw new IllegalArgumentException("Empty string path");
        }
        String sanitizedPath = stringPath.replaceAll("\\s+$", "");
        return Path.of(sanitizedPath);
    }

    private void validateInput(String[] stringPath) {
        if (stringPath == null || stringPath.length < 2)
            throw new IllegalArgumentException("Invalid input: " + Arrays.toString(stringPath));
    }

    private void validateInput(String stringPath) {
        if (stringPath == null || stringPath.trim().isEmpty())
            throw new IllegalArgumentException("Invalid input: " + stringPath);

    }

    private void processInvalidPath(Path validatedPath) {
        if (!validatePath(validatedPath)) {
            try {
                this.path = convertToAbsolute(validatedPath);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to convert path to absolute: " + validatedPath, e);
            }
        } else {
            this.path = validatedPath;
        }
    }

    private boolean validatePath(Path path) {
        return path.isAbsolute();
    }

    private Path convertToAbsolute(Path path) {
        return path.toAbsolutePath();
    }
}
