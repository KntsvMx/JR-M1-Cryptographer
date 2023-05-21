package Source;

import java.nio.file.Path;

public class CaesarCipher {
    private static final CaesarCipher CAESARCIPHER = new CaesarCipher();
    private TypeOfCommandEnum command;
    private int key;
    private Path path;
    private TypeOfLanguageEnum language;

    private int CountOfAlphabetLetters;

    private CaesarCipher() {
    }

    public static CaesarCipher getInstance() {
        return CAESARCIPHER;
    }

    public void setKey(String key) {
        this.key = Integer.parseInt(key);
    }

    public int getCountOfAlphabetLetters() {
        return CountOfAlphabetLetters;
    }

    public void setCountOfAlphabetLetters(int CountOfAlphabetLetters) {
        this.CountOfAlphabetLetters = CountOfAlphabetLetters;
    }


    public TypeOfCommandEnum getCommand() {
        if (command == null)
            throw new IllegalArgumentException("Command not set");
        return command;
    }

    public void setCommand(TypeOfCommandEnum command) {
        this.command = command;
    }

    public int getKey() {
        return this.key;
    }
    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public TypeOfLanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(TypeOfLanguageEnum language) {
        this.language = language;
    }
}
