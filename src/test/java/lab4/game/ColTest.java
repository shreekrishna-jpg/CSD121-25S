package lab4.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColTest {

    @Test
    void testValidLowercaseInputs() {
        assertEquals(Col.A, Col.from("a"));
        assertEquals(Col.B, Col.from("b"));
        assertEquals(Col.C, Col.from("c"));
    }

    @Test
    void testValidUppercaseInputs() {
        assertEquals(Col.A, Col.from("A"));
        assertEquals(Col.B, Col.from("B"));
        assertEquals(Col.C, Col.from("C"));
    }

    @Test
    void testCaseInsensitivity() {
        assertEquals(Col.D, Col.from("D"));
        assertEquals(Col.D, Col.from("d"));
        assertEquals(Col.M, Col.from("M"));
        assertEquals(Col.M, Col.from("m"));
    }

    @Test
    void testInvalidInputThrows() {
        assertThrows(IllegalArgumentException.class, () -> Col.from("Z"));
        assertThrows(IllegalArgumentException.class, () -> Col.from("1"));
        assertThrows(IllegalArgumentException
