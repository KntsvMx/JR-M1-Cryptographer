package CommLineArgsValidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

class PathValidatorTest {

    @Test
    void testGetAndValidation_ValidAbsolutePath() {
        String[] stringPath = {"ENCRYPT","C:/Users/Max/Desktop/Саморазвитие/Java/JavaRush/JR-Exams/M1-Exam-1/ExamProject/src/test/java/fileTest/testFile", "[ ", "ENCRYPT", " ]", ".txt,", "1"};
        PathValidator validator = PathValidator.getInstance();

        Path result = validator.getAndValidation(stringPath);

        Assertions.assertEquals("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\fileTest\\testFile[ ENCRYPT ].txt", result.toString());
    }
    @Test
    void testGetAndValidation_EmptyStringPath() {
        String[] stringPath = {""};
        PathValidator validator = PathValidator.getInstance();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validator.getAndValidation(stringPath);
        });
    }

    @Test
    void testGetAndValidation_ValidPath() {
        String[] stringPath = {"ENCRYPT","C:/Users/Max/Desktop/Саморазвитие/Java/JavaRush/JR-Exams/M1-Exam-1/ExamProject/src/test/java/fileTest/testFile[ ENCRYPT ].txt,", "1"};
        PathValidator validator = PathValidator.getInstance();

        Path result = validator.getAndValidation(stringPath);

        Assertions.assertEquals("C:\\Users\\Max\\Desktop\\Саморазвитие\\Java\\JavaRush\\JR-Exams\\M1-Exam-1\\ExamProject\\src\\test\\java\\fileTest\\testFile[ ENCRYPT ].txt", result.toString());
    }

}