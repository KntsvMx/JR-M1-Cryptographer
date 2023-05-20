package Source;

import CommLineArgsValidation.CommandLineArgumentHolder;
import CommLineArgsValidation.PathValidator;

import java.nio.file.Path;

public class CaesarCipher {
    private static final CaesarCipher CAESARCIPHER = new CaesarCipher();
    private TypeOfCommandEnum command; // присвоить команду после валидации
    private int key; // присвоить ключ после валидации
    private Path path; // присвоить путь после валидации
    private TypeOfLanguageEnum language; // присвоить язык после проверки.

    private CaesarCipher() {}

    public static CaesarCipher getInstance() {
        return CAESARCIPHER;
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

    public void setKey(String key) {
        this.key = Integer.parseInt(key);
    }

    public Path getPath() {
        return  this.path;
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
