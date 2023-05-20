package IOTests.OutputTest;

import IO.OutputToFile;
import Source.TypeOfCommandEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OutputToFileTest {
    private Path testFilePath;
    private OutputToFile outputToFile;

    @BeforeEach
    public void setUp() throws IOException {
       testFilePath = Path.of("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\testFile[ ENCRYPT ].txt");
        outputToFile = new OutputToFile(testFilePath);
    }

    @Test
    public void testWriteToFile() throws IOException {
        TypeOfCommandEnum tag = TypeOfCommandEnum.DECRYPT;

        outputToFile.nameOfFile(tag);

        String expectedFileName = testFilePath.getFileName().toString();
        int dotIndex = expectedFileName.lastIndexOf(".");

        String nameWithoutExtension = expectedFileName.substring(0, dotIndex);
        int openingBracketIndex = nameWithoutExtension.lastIndexOf("[");
        int closingBracketIndex = nameWithoutExtension.lastIndexOf("]");

        if (openingBracketIndex != -1 && closingBracketIndex != -1 && closingBracketIndex == nameWithoutExtension.length() - 1) {
            nameWithoutExtension = nameWithoutExtension.substring(0, openingBracketIndex);
        }

        String extension = expectedFileName.substring(dotIndex);
        String expectedNewFileName = nameWithoutExtension + "[ DECRYPT ]" + extension;
        Path expectedTargetFile = testFilePath.resolveSibling(expectedNewFileName);

        Assertions.assertTrue(Files.exists(expectedTargetFile));
        Assertions.assertEquals(Files.size(testFilePath), Files.size(expectedTargetFile));

    }
}