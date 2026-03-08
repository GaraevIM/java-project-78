package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int start, int end) {
        addCheck("range", value -> value == null || value >= start && value <= end);
        return this;
    }
}