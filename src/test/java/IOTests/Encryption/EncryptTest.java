package IOTests.Encryption;

import Encryption.Decrypt;
import Encryption.Encrypt;
import Source.CaesarCipher;
import Source.TypeOfLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptTest {
    private static Encrypt encryptor;
    private static final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    @BeforeAll
    public static void setup() {
        // Создание экземпляра Encrypt перед каждым тестом
        encryptor = new Encrypt();
        // Установка языка и ключа для шифра Цезаря
        caesarCipher.setLanguage(TypeOfLanguageEnum.ENGLISH);
//        caesarCipher.setKey(3);
        caesarCipher.setCountOfAlphabetLetters(26);
    }

    @Test
    public void testEncryptLines() {
        // Создание списка зашифрованных строк
        ArrayList<StringBuilder> lines = new ArrayList<>();
        lines.add(new StringBuilder("Hello world!"));


        ArrayList<StringBuilder> encryptedLines = encryptor.encryptLines(lines, caesarCipher.getCountOfAlphabetLetters());

        // Ожидаемый результат после шифрования
        ArrayList<StringBuilder> expectedLines = new ArrayList<>();
        expectedLines.add(new StringBuilder("Khoor zruog!"));

        // Проверка соответствия ожидаемого и фактического результата
        assertEquals(expectedLines, encryptedLines);
    }
}
