package lab5.test;

import lab5.game.*;
import lab5.players.RandyPlayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RandyTest {

    /**
     * This test that RandyPlayer picks a valid move on an empty board.
     */
    @Test
    void testPickMoveOnEmptyBoard() {
        Board board = new Board();
        RandyPlayer randy = new RandyPlayer("Randy");

        Position move = randy.pickNextMove(board);

        assertNotNull(move, "Randy should pick a move on empty board.");
        assertTrue(board.isEmptyAt(move), "Randy should pick an empty spot.");
    }


    /**
     * This test is for when RandyPlayer returns null if board is full.
     */
    @Test
    void testPickMoveOnFullBoard() {
        Board board = new Board();

        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                board.place(new Position(row, col), PlayerToken.O);
            }
        }

        RandyPlayer randy = new RandyPlayer("Randy");
        Position move = randy.pickNextMove(board);

        assertNull(move, "Randy should return null when no moves are available on the board.");
    }
}
