package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", value -> {
            if (value == null) {
                return true;
            }

            for (var entry : schemas.entrySet()) {
                var key = entry.getKey();
                var schema = entry.getValue();
                var fieldValue = value.get(key);

                if (!schema.isValidValue(fieldValue)) {
                    return false;
                }
            }

            return true;
        });
        return this;
    }
}