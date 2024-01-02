package Encryption;


import Source.CaesarCipher;
import Source.TypeOfLanguageEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BruteForce {
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();
    private final Decrypt decrypt = new Decrypt();
    private static final String UKRAINIAN_ALPHABET = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private static final Map<Character, Integer> UKRAINIAN_CHAR_TO_INDEX = new HashMap<>();
    private static final Map<Character, Double> UKRAINIAN_CHAR_FREQUENCY = new HashMap<>();
    private static final Map<Character, Double> ENGLISH_CHAR_FREQUENCY = new HashMap<>();

    static {
        UKRAINIAN_CHAR_FREQUENCY.put('а', 0.0808);
        UKRAINIAN_CHAR_FREQUENCY.put('б', 0.0181);
        UKRAINIAN_CHAR_FREQUENCY.put('и', 0.0722);
        UKRAINIAN_CHAR_FREQUENCY.put('о', 0.0601);
        UKRAINIAN_CHAR_FREQUENCY.put('е', 0.0545);
        UKRAINIAN_CHAR_FREQUENCY.put('н', 0.0518);
        UKRAINIAN_CHAR_FREQUENCY.put('в', 0.0462);
        UKRAINIAN_CHAR_FREQUENCY.put('р', 0.0439);
        UKRAINIAN_CHAR_FREQUENCY.put('л', 0.0425);
        UKRAINIAN_CHAR_FREQUENCY.put('к', 0.0349);
        UKRAINIAN_CHAR_FREQUENCY.put('т', 0.0330);
        UKRAINIAN_CHAR_FREQUENCY.put('м', 0.0305);
        UKRAINIAN_CHAR_FREQUENCY.put('д', 0.0286);
        UKRAINIAN_CHAR_FREQUENCY.put('у', 0.0284);
        UKRAINIAN_CHAR_FREQUENCY.put('с', 0.0271);
        UKRAINIAN_CHAR_FREQUENCY.put('п', 0.0219);
        UKRAINIAN_CHAR_FREQUENCY.put('я', 0.0202);
        UKRAINIAN_CHAR_FREQUENCY.put('з', 0.0195);
        UKRAINIAN_CHAR_FREQUENCY.put('ї', 0.0187);
        UKRAINIAN_CHAR_FREQUENCY.put('г', 0.0163);
        UKRAINIAN_CHAR_FREQUENCY.put('ч', 0.0139);
        UKRAINIAN_CHAR_FREQUENCY.put('й', 0.0124);
        UKRAINIAN_CHAR_FREQUENCY.put('х', 0.0120);
        UKRAINIAN_CHAR_FREQUENCY.put('ж', 0.0107);
        UKRAINIAN_CHAR_FREQUENCY.put('є', 0.0106);
        UKRAINIAN_CHAR_FREQUENCY.put('ш', 0.0097);
        UKRAINIAN_CHAR_FREQUENCY.put('ц', 0.0095);
        UKRAINIAN_CHAR_FREQUENCY.put('ю', 0.0085);
        UKRAINIAN_CHAR_FREQUENCY.put('ф', 0.0062);
        UKRAINIAN_CHAR_FREQUENCY.put('щ', 0.0049);
        UKRAINIAN_CHAR_FREQUENCY.put('і', 0.0039);
        UKRAINIAN_CHAR_FREQUENCY.put('ґ', 0.0012);
        UKRAINIAN_CHAR_FREQUENCY.put('ь', 0.0004);

    }

    static {
        ENGLISH_CHAR_FREQUENCY.put('e', 0.1270);
        ENGLISH_CHAR_FREQUENCY.put('t', 0.0906);
        ENGLISH_CHAR_FREQUENCY.put('a', 0.0817);
        ENGLISH_CHAR_FREQUENCY.put('o', 0.0751);
        ENGLISH_CHAR_FREQUENCY.put('i', 0.0697);
        ENGLISH_CHAR_FREQUENCY.put('n', 0.0675);
        ENGLISH_CHAR_FREQUENCY.put('s', 0.0633);
        ENGLISH_CHAR_FREQUENCY.put('h', 0.0609);
        ENGLISH_CHAR_FREQUENCY.put('r', 0.0599);
        ENGLISH_CHAR_FREQUENCY.put('d', 0.0425);
        ENGLISH_CHAR_FREQUENCY.put('l', 0.0403);
        ENGLISH_CHAR_FREQUENCY.put('c', 0.0278);
        ENGLISH_CHAR_FREQUENCY.put('u', 0.0276);
        ENGLISH_CHAR_FREQUENCY.put('m', 0.0241);
        ENGLISH_CHAR_FREQUENCY.put('w', 0.0236);
        ENGLISH_CHAR_FREQUENCY.put('f', 0.0223);
        ENGLISH_CHAR_FREQUENCY.put('g', 0.0202);
        ENGLISH_CHAR_FREQUENCY.put('y', 0.0197);
        ENGLISH_CHAR_FREQUENCY.put('p', 0.0193);
        ENGLISH_CHAR_FREQUENCY.put('b', 0.0149);
        ENGLISH_CHAR_FREQUENCY.put('v', 0.0098);
        ENGLISH_CHAR_FREQUENCY.put('k', 0.0077);
        ENGLISH_CHAR_FREQUENCY.put('j', 0.0015);
        ENGLISH_CHAR_FREQUENCY.put('x', 0.0015);
        ENGLISH_CHAR_FREQUENCY.put('q', 0.0010);
        ENGLISH_CHAR_FREQUENCY.put('z', 0.0007);
    }

    static {
        for (int i = 0; i < UKRAINIAN_ALPHABET.length(); i++) {
            char c = UKRAINIAN_ALPHABET.charAt(i);
            UKRAINIAN_CHAR_TO_INDEX.put(c, i);
            UKRAINIAN_CHAR_TO_INDEX.put(Character.toUpperCase(c), i);
        }
    }

    public ArrayList<StringBuilder> attackBruteForce(ArrayList<StringBuilder> lines) {
        ArrayList<StringBuilder> decrypted = null;
        double bestScore = Double.MAX_VALUE;
        int bestKey = 0;

        for (int i = 0; i < caesarCipher.getCountOfAlphabetLetters(); i++) {
            decrypted = decrypt.decryptLines(lines, i);
            double score = calculateDecryptionScore(decrypted);

            if (score < bestScore) {
                bestScore = score;
                bestKey = i;
            }
        }

        System.out.println("Successful decryption with key: " + bestKey);
        return decrypt.decryptLines(lines, bestKey);
    }

    private int calculateDecryptionScore(ArrayList<StringBuilder> decryptedLines) {
        int score = 0;

        for (StringBuilder line : decryptedLines) {
            Map<Character, Integer> charCount = countCharacters(line);
            Map<Character, Double> charFrequency = calculateCharacterFrequency(charCount, line.length());

            double scoreForLine = calculateScoreForLine(charFrequency);
            score += scoreForLine;
        }
        return score;
    }



    private Map<Character, Integer> countCharacters(StringBuilder line) {
        Map<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (Character.isLetter(c)) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }

        return charCount;
    }

    private Map<Character, Double> calculateCharacterFrequency(Map<Character, Integer> charCount, int totalChars) {
        Map<Character, Double> charFrequency = new HashMap<>();

        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            double frequency = (double) count / totalChars * 100;
            charFrequency.put(c, frequency);
        }

        return charFrequency;
    }

    private double calculateScoreForLine(Map<Character, Double> charFrequency) {
        double score = 0;

        for (Map.Entry<Character, Double> entry : charFrequency.entrySet()) {
            char c = entry.getKey();
            double expectedFrequency;
            double observedFrequency = entry.getValue();

            if (caesarCipher.getLanguage().equals(TypeOfLanguageEnum.UKRAINIAN)) {
                expectedFrequency = UKRAINIAN_CHAR_FREQUENCY.getOrDefault(c, 0.0);
            } else {
                expectedFrequency = ENGLISH_CHAR_FREQUENCY.getOrDefault(c, 0.0);
            }

            double diff = Math.abs(observedFrequency - expectedFrequency);
            score += diff * diff; // Using square of the difference to give more weight to larger deviations.
        }

        return score;
    }
}

