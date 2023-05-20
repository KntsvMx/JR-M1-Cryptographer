package CommLineArgsValidation;

import Source.CaesarCipher;
import Source.TypeOfCommandEnum;

public class CommandLineArgumentHolder {
    private static final CommandLineArgumentHolder INSTANCE = new CommandLineArgumentHolder();
    private PathValidator pathValidator = PathValidator.getInstance();
    private CaesarCipher caesarCipher = CaesarCipher.getInstance();


    private CommandLineArgumentHolder() {
    }

    public static CommandLineArgumentHolder getInstance() {
        return INSTANCE;
    }

    public boolean validateArgument(String[] args) {
        boolean checker = checkArgumentAreEmpty(args);
        if (!checker) {
            setCommand(args[0]);
            caesarCipher.setPath(pathValidator.getAndValidation(args[1]));
            caesarCipher.setKey(args[2]);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkArgumentAreEmpty(String[] args) {
        for (String argument: args) {
            if (argument.isEmpty()) {
                return true;
            }
        }
        return false;
    }

// посмотреть за что отвечает contentEquals
    public void setCommand(String command) {
        if (command.equalsIgnoreCase("ENCRYPT")) {
            caesarCipher.setCommand(TypeOfCommandEnum.ENCRYPT);
        } else if (command.equalsIgnoreCase("DECRYPT")) {
            caesarCipher.setCommand(TypeOfCommandEnum.DECRYPT);
        } else if (command.equalsIgnoreCase("BRUTE_FROCE")) {
            caesarCipher.setCommand(TypeOfCommandEnum.BRUTE_FORCE);
        } else {
            throw new IllegalArgumentException("Any argument didn't match");
        }
    }
}

