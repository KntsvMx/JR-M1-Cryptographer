package LanguageDetection;

import Source.TypeOfCommandEnum;

public class TypeOfLanguage {
    private static final TypeOfLanguage INSTANCE = new TypeOfLanguage();
    private TypeOfCommandEnum language;

    public static TypeOfLanguage getInstance() {
        return INSTANCE;
    }

    public TypeOfCommandEnum getLanguage() {
        if (language == null)
            throw new IllegalArgumentException("Language not set");
        return language;
    }

    public void setLanguage(String language) {

    }
}
