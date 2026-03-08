package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

    @Test
    void testShapeValid() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(schema.isValid(human));
    }

    @Test
    void testShapeInvalidWithNullLastName() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);

        assertFalse(schema.isValid(human));
    }

    @Test
    void testShapeInvalidWithShortLastName() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Anna");
        human.put("lastName", "B");

        assertFalse(schema.isValid(human));
    }

    @Test
    void testShapeWithAdditionalConstraints() {
        var validator = new Validator();
        MapSchema schema = validator.map().required().sizeof(2);

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required().contains("ex"));
        schemas.put("age", validator.number().required().positive().range(18, 60));

        schema.shape(schemas);

        Map<String, Object> user = new HashMap<>();
        user.put("name", "Alex");
        user.put("age", 25);

        assertTrue(schema.isValid(user));

        user.put("age", 17);
        assertFalse(schema.isValid(user));

        user.put("age", 25);
        user.put("name", "Bob");
        assertFalse(schema.isValid(user));
    }

    @Test
    void testShapeWithMissingField() {
        var validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().required().positive());

        schema.shape(schemas);

        Map<String, Object> user = new HashMap<>();
        user.put("name", "Alex");

        assertFalse(schema.isValid(user));
    }
}
