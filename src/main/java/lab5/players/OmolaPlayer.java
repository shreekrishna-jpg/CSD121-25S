package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.game.Row;
import lab5.game.Col;
import lab5.game.PlayerToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * OmolaPlayer is a smart player which is advanced and that makes decisions based on simple strategy:
 *
 *First, it plays that move after checking if it can win immediately.
 *If not, it blocks the opponent after analyzing if they can win on the coming move.
 *It selects a randomly available position if not.
 */

public class OmolaPlayer extends Player {

    private final Random random = new Random();

    public OmolaPlayer(String name) {
        super(name);
    }

    /**
     * This picks the next move based on a basic strategic rule
     * and it tries to win, block opponent if needed, otherwise it moves randomly.
     *
     * @param board the current state of the game board
     * @return the selected position to place the token, or null if no move is available.
     */

    @Override
    public Position pickNextMove(Board board) {
        PlayerToken myToken = board.getNextTurnToken();
        PlayerToken opponentToken = (myToken == PlayerToken.X) ? PlayerToken.O : PlayerToken.X;

        List<Position> available = new ArrayList<>();

        // this chunk of code tries to collect all empty positions it can
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                if (board.isEmptyAt(pos)) {
                    available.add(pos);
                }
            }
        }

        // This chunk of code tries to win the game
        for (Position pos : available) {
            Board testBoard = new Board(board);
            testBoard.place(pos, myToken);
            if (testBoard.getWinner() == myToken) {
                return pos;
            }
        }


        // THis chunk of code is for blocking the opponent playing with it
        for (Position pos : available) {
            Board testBoard = new Board(board);
            testBoard.place(pos, opponentToken);
            if (testBoard.getWinner() == opponentToken) {
                return pos;
            }
        }

        //If it can't win immediately, or after blocking the opponent this chunk of code helps to choose a random available position for move
        if (available.isEmpty()) return null;
        return available.get(random.nextInt(available.size()));
    }
}
