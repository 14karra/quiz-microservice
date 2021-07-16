package quiz.exception;

public class NonexistentFKDataException extends RuntimeException {

    public NonexistentFKDataException(Class mainClazz, Class foreignKeyClazz, Long foreignKeyValue) {
        super("FK data is nonexistent. Main class=" + mainClazz.getSimpleName() +
                ", FK class=" + foreignKeyClazz.getSimpleName() +
                ", FK value=" + foreignKeyValue);
    }
}
