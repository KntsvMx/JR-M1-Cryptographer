import CommLineArgsValidation.CommandLineArgumentHolder;
import Encryption.BruteForce;
import Encryption.CipherHandler;
import IO.FileService;
import LanguageDetection.DetectionOfLanguage;
import Source.CaesarCipher;
import Source.TypeOfCommandEnum;
import java.util.ArrayList;

public class Runner {
    private final FileService fileService;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private final CommandLineArgumentHolder commandLineArgumentHolder = CommandLineArgumentHolder.getInstance();

    private final CipherHandler cipherHandler = new CipherHandler();
    private final BruteForce bruteForce = new BruteForce();


    private final DetectionOfLanguage detectionOfLanguage = DetectionOfLanguage.getInstance();

    public Runner(FileService fileService) {
        this.fileService = fileService;
    }

    public void run(String[] args) {
        boolean isValidate = commandLineArgumentHolder.validateArgument(args);
        if (!isValidate)
            throw new RuntimeException("Validation have benn wrong.");
        try {
            if (!caesarCipher.getCommand().equals(TypeOfCommandEnum.BRUTE_FORCE)) {
                ArrayList<StringBuilder> readFromFile = fileService.readFromFile();
                detectionOfLanguage.detectLanguage(readFromFile);
                readFromFile = cipherHandler.decryptLines(readFromFile, caesarCipher.getKey());
                fileService.writeToFileWithTag(readFromFile);
            } else {
                ArrayList<StringBuilder> readFromFile = fileService.readFromFile();
                detectionOfLanguage.detectLanguage(readFromFile);
                readFromFile = bruteForce.attackBruteForce(readFromFile);
                fileService.writeToFileWithTag(readFromFile);
            }


        } catch (Exception e) {
            throw new RuntimeException("Invalid command");
        }
    }
}
