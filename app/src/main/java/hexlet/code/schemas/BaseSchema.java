package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public final boolean isValid(T value) {
        for (var check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public final boolean isValidValue(Object value) {
        return isValid((T) value);
    }

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }
}
