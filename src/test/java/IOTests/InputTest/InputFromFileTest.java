package IOTests.InputTest;

import IO.InputFromFIle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InputFromFileTest {

    private InputFromFIle inputFromFile;

    @BeforeEach
    public void setUp() {
        Path path = Paths.get("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\IOTests\\InputTest\\testfile.txt"); // Путь к тестовому файлу
        inputFromFile = new InputFromFIle(path);
    }

    @Test
    public void testReadFile() {
        List<StringBuilder> result = inputFromFile.readFile();

        // Проверяем, что результат не является пустым
        Assertions.assertNotNull(result);

        // Проверяем, что список не пустой
        Assertions.assertFalse(result.isEmpty());

        // Проверяем содержимое каждой строки в списке
        for (StringBuilder line : result) {
            Assertions.assertNotNull(line);
            Assertions.assertNotEquals(0, line.length());
        }
    }
}