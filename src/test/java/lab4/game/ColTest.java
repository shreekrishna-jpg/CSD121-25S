package lab4.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColTest {

    @Test
    void testValidLowercaseInputs() {
        assertEquals(Col.Left, Col.from("l"));
        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Right, Col.from("r"));
    }

    @Test
    void testValidUppercaseInputs() {
        assertEquals(Col.Left, Col.from("L"));
        assertEquals(Col.Middle, Col.from("M"));
        assertEquals(Col.Right, Col.from("R"));
    }

    @Test
    void testCaseInsensitivity() {
        assertEquals(Col.Left, Col.from("L"));
        assertEquals(Col.Middle, Col.from("m"));
        assertEquals(Col.Right, Col.from("R"));
    }

    @Test
    void testInvalidInputThrows() {
        assertThrows(IllegalArgumentException.class, () -> Col.from("Z"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("1"));
        assertThrows(IllegalArgumentException.class, () -> Col.from(""));
    }
}