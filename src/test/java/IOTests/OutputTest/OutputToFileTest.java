package IOTests.OutputTest;

import IO.OutputToFile;
import Source.TypeOfCommandEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class OutputToFileTest {
    private OutputToFile outputToFile;
    private Path testFilePath;

    @BeforeEach
    void setUp() throws Exception {
        // Создаем временный файл для тестов
        testFilePath = Path.of("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\IOTests\\OutputTest\\testFile[ ENCRYPT ].txt");
        outputToFile = new OutputToFile(testFilePath);
    }

    @Test
    void testWriteToFile() throws Exception {
        ArrayList<StringBuilder> lines = new ArrayList<>();
        lines.add(new StringBuilder("Line 1"));
        lines.add(new StringBuilder("Line 2"));
        lines.add(new StringBuilder("Line 3"));

        String expectedFileName = "[ TEST ]" + testFilePath.getFileName();
        Path expectedFilePath = testFilePath.resolveSibling(expectedFileName);

        // Записываем данные в файл
        outputToFile.writeToFile(expectedFilePath, lines);

        // Проверяем, что файл был успешно записан
        Assertions.assertTrue(Files.exists(expectedFilePath));

        // Проверяем содержимое файла
        ArrayList<String> actualLines = new ArrayList<>(Files.readAllLines(expectedFilePath));
        Assertions.assertEquals(3, actualLines.size());
        Assertions.assertEquals("Line 1", actualLines.get(0));
        Assertions.assertEquals("Line 2", actualLines.get(1));
        Assertions.assertEquals("Line 3", actualLines.get(2));
    }

    @Test
    void testNameOfFile() throws Exception {
        TypeOfCommandEnum tag = TypeOfCommandEnum.ENCRYPT;

        String expectedPrefix = "[ ENCRYPT ]";
        Path expectedFilePath = testFilePath.resolveSibling(expectedPrefix + testFilePath.getFileName());

        // Переименовываем файл
        Path actualFilePath = outputToFile.nameOfFile(tag);

        // Проверяем, что файл был успешно переименован
        Assertions.assertTrue(Files.exists(expectedFilePath));
        Assertions.assertEquals(expectedFilePath, actualFilePath);
    }
}