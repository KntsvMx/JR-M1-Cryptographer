package CommLineArgsValidation;

import Console.Console;
import Source.CaesarCipher;
import Source.TypeOfCommandEnum;

import java.nio.file.Path;

public class CommandLineArgumentHolder {
    private static final CommandLineArgumentHolder INSTANCE = new CommandLineArgumentHolder();
    private final PathValidator pathValidator = PathValidator.getInstance();
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    private final Console console = new Console();


    private CommandLineArgumentHolder() {
    }

    public static CommandLineArgumentHolder getInstance() {
        return INSTANCE;
    }

    public boolean validateArgument(String[] args) {
        if (args.length == 0) {
            workWithConsole();
        } else  {
            workWithArgs(args);
        }
        return true;
    }

    private void workWithArgs(String[] args) {
        setCommand(args[0]);
        Path path = pathValidator.getAndValidation(args);
        caesarCipher.setPath(path);
    }

    private void workWithConsole() {
        console.workWithConsole();
        setCommand(console.getCommand());
        caesarCipher.setPath(pathValidator.getAndValidation(console.getPath()));
        if (caesarCipher.getCommand() != TypeOfCommandEnum.BRUTE_FORCE)
            caesarCipher.setKey(console.getKey());
        else
            caesarCipher.setKey("0");
    }

    public void setCommand(String command) {
        if (command.equalsIgnoreCase("ENCRYPT")) {
            caesarCipher.setCommand(TypeOfCommandEnum.ENCRYPT);
        } else if (command.equalsIgnoreCase("DECRYPT")) {
            caesarCipher.setCommand(TypeOfCommandEnum.DECRYPT);
        } else if (command.equalsIgnoreCase("BRUTE_FORCE")) {
            caesarCipher.setCommand(TypeOfCommandEnum.BRUTE_FORCE);
        } else {
            throw new IllegalArgumentException("Any argument didn't match");
        }
    }
}

