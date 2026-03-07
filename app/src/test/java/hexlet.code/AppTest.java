package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void testGetGreeting() {
        assertEquals("Hello, World!", App.getGreeting());
    }
}