package IO;

import CommLineArgsValidation.PathValidator;
import Source.CaesarCipher;

public class FileService {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private InputFromFIle inputFromFIle;
    private OutputToFile outputToFile;
    private


    public void readToBuffer() {
        inputFromFIle = new InputFromFIle(caesarCipher.getPath());
    }
}
