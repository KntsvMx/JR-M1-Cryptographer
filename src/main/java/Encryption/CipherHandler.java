package Encryption;

import Source.CaesarCipher;
import Source.TypeOfCommandEnum;
import Source.TypeOfLanguageEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CipherHandler {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private static final String UKRAINIAN_ALPHABET = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private static final Map<Character, Integer> UKRAINIAN_CHAR_TO_INDEX = new HashMap<>();

    static {
        initializeUkrainianCharToIndexMap();
    }

    private static void initializeUkrainianCharToIndexMap() {
        for (int i = 0; i < UKRAINIAN_ALPHABET.length(); i++) {
            char c = UKRAINIAN_ALPHABET.charAt(i);
            UKRAINIAN_CHAR_TO_INDEX.put(c, i);
            UKRAINIAN_CHAR_TO_INDEX.put(Character.toUpperCase(c), i);
        }
    }

    public ArrayList<StringBuilder> decryptLines(ArrayList<StringBuilder> lines, int key) {
        ArrayList<StringBuilder> decryptedLines = new ArrayList<>();

        for (StringBuilder line : lines) {
            decryptedLines.add(decryptLine(key, line));
        }

        return decryptedLines;
    }

    private StringBuilder decryptLine(int key, StringBuilder line) {
        StringBuilder decryptedLine = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            decryptedLine.append(decryptChar(currentChar, key));
        }

        return decryptedLine;
    }

    private char decryptChar(char letter, int key) {
        TypeOfLanguageEnum typeLanguage = caesarCipher.getLanguage();
        int alphabetSize;
        int offset;

        if (!Character.isLetter(letter))
            return letter;

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

        int charIndex = calculateCharIndex(letter, offset);
        int encryptedIndex = encryptedIndex(charIndex, key, alphabetSize);
        
        if (encryptedIndex < 0) {
            encryptedIndex += alphabetSize;
        }

        return getDecryptedChar(letter, typeLanguage, offset, encryptedIndex);

    }

    private static char getDecryptedChar(char letter, TypeOfLanguageEnum typeLanguage, int offset, int encryptedIndex) {
        if (typeLanguage == TypeOfLanguageEnum.UKRAINIAN) {
            char encryptedChar = UKRAINIAN_ALPHABET.charAt(encryptedIndex);
            return Character.isLowerCase(letter) ? encryptedChar : Character.toUpperCase(encryptedChar);
        } else {
            return (char) (encryptedIndex + offset);
        }
    }

    private int calculateCharIndex(char letter, int offset) {
        return (caesarCipher.getLanguage() == TypeOfLanguageEnum.UKRAINIAN) ? 
                getUkrainianCharIndex(letter) : letter - offset;
    }
    
    private int getUkrainianCharIndex(char letter) {
        char lowercaseLetter = Character.toLowerCase(letter);
        Integer index = UKRAINIAN_CHAR_TO_INDEX.get(lowercaseLetter);
        return (index != null) ? index :letter;
    }

    private int encryptedIndex(int charIndex, int key, int alphabetSize) {
        return (caesarCipher.getCommand() == TypeOfCommandEnum.ENCRYPT) ?
                (charIndex + key) % alphabetSize : (charIndex - key) % alphabetSize;
    }

}






