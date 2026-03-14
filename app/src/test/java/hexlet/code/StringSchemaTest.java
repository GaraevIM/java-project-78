package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    @Test
    void testStringSchemaWithoutRequired() {
        var validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testRequired() {
        var validator = new Validator();
        StringSchema schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testContains() {
        var validator = new Validator();
        StringSchema schema = validator.string().required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void testMinLength() {
        var validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.minLength(5).isValid("hexlet"));
        assertFalse(schema.minLength(10).isValid("hexlet"));
    }

    @Test
    void testMinLengthWithoutRequired() {
        var validator = new Validator();
        StringSchema schema = validator.string().minLength(10);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("hexlet"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    void testContainsWithoutRequired() {
        var validator = new Validator();
        StringSchema schema = validator.string().contains("hex");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("java"));
    }

    @Test
    void testCombinedChecks() {
        var validator = new Validator();
        StringSchema schema = validator.string().required().minLength(5).contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testMinLengthCalledMultipleTimes() {
        var validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }

    @Test
    void testContainsCalledMultipleTimes() {
        var validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.contains("bc").contains("cd").isValid("abcde"));
        assertFalse(schema.isValid("abxyz"));
    }

    @Test
    void testRequiredWithOtherChecks() {
        var validator = new Validator();
        StringSchema schema = validator.string().minLength(3).contains("ab").required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("abc"));
    }
}
