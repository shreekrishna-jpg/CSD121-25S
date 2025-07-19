package lab5.test;

import lab5.game.*;
import lab5.players.OmolaPlayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic tests for the class OmolaPlayer.
 * By verifying that OmolaPlayer takes an actual move when move is available and returns null when no move is there.
 * These tests confirm the basic concept of the pickNextMove method.
 */
public class OmolaTest {

    /**
     * This code tests that when the board is empty, OmolaPlayer chooses an actual move.
     *
     * Confirms that the selected place is empty and that the move is not null.
     */
    @Test
    void testPickMoveOnEmptyBoard() {
        Board board = new Board();
        OmolaPlayer omola = new OmolaPlayer("Omola");

        Position move = omola.pickNextMove(board);

        assertNotNull(move, "Omola should pick a move when the board is empty.");
        assertTrue(board.isEmptyAt(move), "Omola should choose a vacant space for the move.");
    }

    /**
     * This chunk of code tests that when the board is fully filled, OmolaPlayer returns null.
     */
    @Test
    void testPickMoveOnFullBoard() {
        Board board = new Board();
        PlayerToken token = PlayerToken.X;

        // Fill the board alternating tokens to simulate full board
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                board.place(new Position(row, col), token);
                token = (token == PlayerToken.X) ? PlayerToken.O : PlayerToken.X;
            }
        }

        OmolaPlayer omola = new OmolaPlayer("Omola");

        Position move = omola.pickNextMove(board);
        assertNull(move, "Omola should return null when no moves are available in the board.");
    }
}
