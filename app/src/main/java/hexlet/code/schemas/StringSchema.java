package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    private Predicate<String> requiredCheck;

    private Predicate<String> minLengthCheck = value -> true;

    private Predicate<String> containsCheck = value -> true;

    public StringSchema required() {
        requiredCheck = value -> value != null && !value.isEmpty();
        return this;
    }

    public StringSchema minLength(int length) {
        minLengthCheck = value -> value == null || value.length() >= length;
        return this;
    }

    public StringSchema contains(String substring) {
        containsCheck = value -> value == null || value.contains(substring);
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (requiredCheck == null) {
            return minLengthCheck.test(value) && containsCheck.test(value);
        }

        return requiredCheck.test(value) && minLengthCheck.test(value) && containsCheck.test(value);
    }
}