package LanguageDetection;

import Source.CaesarCipher;
import Source.TypeOfLanguageEnum;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DetectionOfLanguage {

    private static final DetectionOfLanguage DETECTION_OF_LANGUAGE = new DetectionOfLanguage();
    private final CaesarCipher caesarCipher = CaesarCipher.getInstance();

    private DetectionOfLanguage() {

    }

    public static DetectionOfLanguage getInstance() {
        return DETECTION_OF_LANGUAGE;
    }

    public void detectLanguage(ArrayList<StringBuilder> lines) {
        Pattern ukrainianPattern = Pattern.compile("[\\p{IsCyrillic}&&[^Ёё]]+");
        Pattern englishPattern = Pattern.compile("[\\p{IsLatin}]+");

        boolean isUkrainian = ukrainianPattern.matcher(lines.get(0)).find();
        boolean isEnglish = englishPattern.matcher(lines.get(0)).find();

        if (isUkrainian && !isEnglish) {
            caesarCipher.setLanguage(TypeOfLanguageEnum.UKRAINIAN);
            caesarCipher.setCountOfAlphabetLetters(33);
        } else if (!isUkrainian && isEnglish) {
            caesarCipher.setLanguage(TypeOfLanguageEnum.ENGLISH);
            caesarCipher.setCountOfAlphabetLetters(26);
        } else {
            caesarCipher.setLanguage(TypeOfLanguageEnum.UNKNOWN);
            caesarCipher.setCountOfAlphabetLetters(0);
        }
    }
}
