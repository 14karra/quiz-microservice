package quiz.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum QuestionType {
    OPEN_ANSWER_QUESTION("OAQ"),
    SINGLE_ANSWER_QUESTION("SAQ"),
    MULTIPLE_ANSWER_QUESTION("MAQ");

    private final String type;
    private static final Map<String, QuestionType> charsMap;

    static {
        charsMap = new HashMap<>();
        Arrays.stream(QuestionType.values())
                .forEach(s -> charsMap.put(s.getType(), s));
    }

    public static QuestionType fromChars(String characters) {
        QuestionType type = charsMap.get(characters);
        if (type == null) {
            throw new UnsupportedOperationException(String.format("Question type '%s' is not defined", characters));
        }
        return type;
    }

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static QuestionType getDefaultType() {
        return OPEN_ANSWER_QUESTION;
    }

    @Converter
    public static class QuestionTypeConverter implements AttributeConverter<QuestionType, String> {

        @Override
        public String convertToDatabaseColumn(QuestionType state) {
            return state.getType();
        }

        @Override
        public QuestionType convertToEntityAttribute(String s) {
            return QuestionType.fromChars(s);
        }
    }
}

