package lab4.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testBoardStartsEmpty() {
        Board board = new Board();
        Position pos = new Position(Row.TOP, Col.A);
        assertFalse(board.isOccupiedAt(pos), "Board should start empty");
    }

    @Test
    void testPlaceXThenOccupied() {
        Board board = new Board();
        Position pos = new Position(Row.MIDDLE, Col.B);
        board.placeX(pos);
        assertTrue(board.isOccupiedAt(pos), "After placing X, position should be occupied");
    }

    @Test
    void testPlaceOThenOccupied() {
        Board board = new Board();
        Position pos = new Position(Row.BOTTOM, Col.C);
        board.placeO(pos);
        assertTrue(board.isOccupiedAt(pos), "After placing O, position should be occupied");
    }

    @Test
    void testPlaceOnOccupiedFails() {
        Board board = new Board();
        Position pos = new Position(Row.TOP, Col.B);
        board.placeX(pos);
        assertThrows(IllegalStateException.class, () -> board.placeO(pos), "Cannot place O on occupied spot");
    }

    @Test
    void testToStringNotNull() {
        Board board = new Board();
        assertNotNull(board.toString(), "toString should return a valid string");
    }

    @Test
    void testBoardIsFull() {
        Board board = new Board();
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                board.placeX(new Position(row, col));
            }
        }
        assertTrue(board.isFull(), "Board should be full after filling all cells");
    }
}
