package Encryption;

import Source.CaesarCipher;
import Source.TypeOfLanguageEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Decrypt {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private static final String UKRAINIAN_ALPHABET = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private static final Map<Character, Integer> UKRAINIAN_CHAR_TO_INDEX = new HashMap<>();

    static {
        for (int i = 0; i < UKRAINIAN_ALPHABET.length(); i++) {
            char c = UKRAINIAN_ALPHABET.charAt(i);
            UKRAINIAN_CHAR_TO_INDEX.put(c, i);
            UKRAINIAN_CHAR_TO_INDEX.put(Character.toUpperCase(c), i);
        }
    }

    public ArrayList<StringBuilder> decryptLines(ArrayList<StringBuilder> lines, int key) {
        ArrayList<StringBuilder> decryptedLines = new ArrayList<>();

        for (StringBuilder line : lines) {
            StringBuilder decryptedLine = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);

                if (Character.isLetter(currentChar)) {
                    char decryptedChar = decryptChar(currentChar, key);
                    decryptedLine.append(decryptedChar);
                } else {
                    decryptedLine.append(currentChar);
                }
            }

            decryptedLines.add(decryptedLine);
        }

        return decryptedLines;
    }

    private char decryptChar(char letter, int key) {
        TypeOfLanguageEnum typeLanguage = caesarCipher.getLanguage();

        if (Character.isLowerCase(letter) || Character.isUpperCase(letter)) {
            int alphabetSize;
            int offset;

            switch (typeLanguage) {
                case UKRAINIAN -> {
                    alphabetSize = UKRAINIAN_ALPHABET.length();
                    offset = Character.isLowerCase(letter) ? 'а' : 'А';
                }
                case ENGLISH -> {
                    alphabetSize = caesarCipher.getCountOfAlphabetLetters();
                    offset = Character.isLowerCase(letter) ? 'a' : 'A';
                }
                default -> {
                    return letter;
                }
            }

            int charIndex;
            if (typeLanguage == TypeOfLanguageEnum.UKRAINIAN) {
                char lowercaseLetter = Character.toLowerCase(letter);
                Integer index = UKRAINIAN_CHAR_TO_INDEX.get(lowercaseLetter);
                if (index != null) {
                    charIndex = index;
                } else {
                    return letter;
                }
            } else {
                charIndex = letter - offset;
            }

            int encryptedIndex = (charIndex - key) % alphabetSize;
            if (encryptedIndex < 0) {
                encryptedIndex += alphabetSize;
            }

            if (typeLanguage == TypeOfLanguageEnum.UKRAINIAN) {
                char encryptedChar = UKRAINIAN_ALPHABET.charAt(encryptedIndex);
                return Character.isLowerCase(letter) ? encryptedChar : Character.toUpperCase(encryptedChar);
            } else {
                return (char) (encryptedIndex + offset);
            }
        }

        return letter;
    }

}






