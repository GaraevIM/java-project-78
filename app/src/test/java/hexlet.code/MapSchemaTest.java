package hexlet.code;

import hexlet.code.schemas.MapSchema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    @Test
    void testMapSchemaWithoutRequired() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testRequired() {
        var validator = new Validator();
        MapSchema schema = validator.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeof() {
        var validator = new Validator();
        MapSchema schema = validator.map().required();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeofWithoutRequired() {
        var validator = new Validator();
        MapSchema schema = validator.map().sizeof(2);

        assertTrue(schema.isValid(null));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void testSizeofCalledMultipleTimes() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        assertFalse(schema.sizeof(1).sizeof(3).isValid(data));

        data.put("key3", "value3");

        assertTrue(schema.isValid(data));
    }
}