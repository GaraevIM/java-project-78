package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    @Test
    void testNumberSchemaWithoutRequired() {
        var validator = new Validator();
        NumberSchema schema = validator.number();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testPositiveWithoutRequired() {
        var validator = new Validator();
        NumberSchema schema = validator.number().positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRequired() {
        var validator = new Validator();
        NumberSchema schema = validator.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testRequiredAndPositive() {
        var validator = new Validator();
        NumberSchema schema = validator.number().positive().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRange() {
        var validator = new Validator();
        NumberSchema schema = validator.number().required().positive().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRangeWithoutRequired() {
        var validator = new Validator();
        NumberSchema schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRangeCalledMultipleTimes() {
        var validator = new Validator();
        NumberSchema schema = validator.number();

        assertTrue(schema.range(1, 5).range(10, 20).isValid(15));
        assertFalse(schema.isValid(4));
    }
}