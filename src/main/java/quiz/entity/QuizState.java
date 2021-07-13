package quiz.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum QuizState {
    CREATED("C") {
        @Override
        public QuizState nextState() {
            return ACTIVE;
        }
    },
    ACTIVE("A") {
        @Override
        public QuizState nextState() {
            return ENDED;
        }
    },
    ENDED("E") {
        @Override
        public QuizState nextState() {
            throw new UnsupportedOperationException(String.format("Can't move quiz state further %s", this));
        }
    };

    private final String state;
    private static final Map<String, QuizState> charMap;

    static {
        charMap = new HashMap<>();
        Arrays.stream(QuizState.values())
                .forEach(s -> charMap.put(s.getStateChar(), s));
    }

    public static QuizState fromChar(String character) {
        QuizState state = charMap.get(character);
        if (state == null) {
            throw new UnsupportedOperationException(String.format("Quiz state '%s' is not defined", character));
        }
        return state;
    }

    QuizState(String state) {
        this.state = state;
    }

    public String getStateChar() {
        return state;
    }

    public abstract QuizState nextState();

    public static QuizState getInitialState() {
        return CREATED;
    }

    public boolean isFinalState() {
        return this == ENDED;
    }

    @Converter
    public static class QuizStateConverter implements AttributeConverter<QuizState, String> {

        @Override
        public String convertToDatabaseColumn(QuizState state) {
            return state.getStateChar();
        }

        @Override
        public QuizState convertToEntityAttribute(String s) {
            return QuizState.fromChar(s);
        }
    }
}

