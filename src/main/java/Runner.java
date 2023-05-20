import CommLineArgsValidation.CommandLineArgumentHolder;
import IO.FileService;
import Source.CaesarCipher;
import Source.TypeOfCommandEnum;

public class Runner {
    private final FileService fileService;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private final CommandLineArgumentHolder commandLineArgumentHolder = CommandLineArgumentHolder.getInstance();

    public Runner(FileService fileService) {
        this.fileService = fileService;
    }

    public void run(String[] args) {
        boolean isValidate = commandLineArgumentHolder.validateArgument(args);
        if (!isValidate)
            throw new RuntimeException("Validation have benn wrong.");

        if (caesarCipher.getCommand().equals(TypeOfCommandEnum.ENCRYPT)) {
//            fileService
        } else if (caesarCipher.getCommand().equals(TypeOfCommandEnum.DECRYPT)) {

        } else if (caesarCipher.getCommand().equals(TypeOfCommandEnum.BRUTE_FROCE)) {

        } else {
            throw new RuntimeException("Invalid command");
        }


    }
}
