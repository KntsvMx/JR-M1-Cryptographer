import CommLineArgsValidation.CommandLineArgumentHolder;
import Encryption.BruteForce;
import Encryption.Decrypt;
import Encryption.Encrypt;
import IO.FileService;
import LanguageDetection.DetectionOfLanguage;
import Source.CaesarCipher;
import Source.TypeOfCommandEnum;

import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    private final FileService fileService;
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private final CommandLineArgumentHolder commandLineArgumentHolder = CommandLineArgumentHolder.getInstance();

    private final Encrypt encrypt = new Encrypt();
    private final Decrypt decrypt = new Decrypt();
    private final BruteForce bruteForce = new BruteForce();


    private final DetectionOfLanguage detectionOfLanguage = DetectionOfLanguage.getInstance();

    public Runner(FileService fileService) {
        this.fileService = fileService;
    }

    public void run(String[] args) throws IOException {
        boolean isValidate = commandLineArgumentHolder.validateArgument(args);
        if (!isValidate)
            throw new RuntimeException("Validation have benn wrong.");

        if (caesarCipher.getCommand().equals(TypeOfCommandEnum.ENCRYPT)) {
            ArrayList<StringBuilder> readFromFile = fileService.readFromFile();
            detectionOfLanguage.detectLanguage(readFromFile);
            readFromFile = encrypt.encryptLines(readFromFile, caesarCipher.getKey());
            fileService.writeToFileWithTag(readFromFile);
        } else if (caesarCipher.getCommand().equals(TypeOfCommandEnum.DECRYPT)) {
            ArrayList<StringBuilder> readFromFile = fileService.readFromFile();
            detectionOfLanguage.detectLanguage(readFromFile);
            readFromFile = decrypt.decryptLines(readFromFile, caesarCipher.getKey());
            fileService.writeToFileWithTag(readFromFile);
        } else if (caesarCipher.getCommand().equals(TypeOfCommandEnum.BRUTE_FORCE)) {
//            ArrayList<StringBuilder> readFromFile = fileService.readFromFile();
//            detectionOfLanguage.detectLanguage(readFromFile);
//            readFromFile = encrypt.encryptLines(readFromFile, caesarCipher.getKey());
//            fileService.writeToFileWithTag(readFromFile);
        } else {
            throw new RuntimeException("Invalid command");
        }


    }
}
