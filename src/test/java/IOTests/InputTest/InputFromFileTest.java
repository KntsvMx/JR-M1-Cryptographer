package IOTests.InputTest;

import IO.InputFromFIle;
import IO.OutputToFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputFromFileTest {
    private Path testFilePath;
    private InputFromFIle inputFromFIle;
    private List<String> buffer;

    @BeforeEach
    public void setUp() throws IOException {
        testFilePath = Path.of("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\IOTests\\OutputTest\\testFile[ ENCRYPT ].txt");
        inputFromFIle = new InputFromFIle(testFilePath);
    }

    @Test
    public void readFileFromFileTest() {
        buffer = inputFromFIle.readFile();
        Assertions.assertTrue(Files.exists(testFilePath));
        Assertions.assertEquals("[Line 1, Line 2, Line 3]", buffer);
    }
}
