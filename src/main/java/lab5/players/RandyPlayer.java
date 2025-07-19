package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.game.Row;
import lab5.game.Col;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandyPlayer extends Player {

    private final Random random = new Random();

    public RandyPlayer(String name) {
        super(name);
    }

    /**
     * This chunk of code selects the next move by randomly choosing an empty position on the board.
     *
     * @param board the current state of the game board
     * @return the randomly selected position which are valid, or null if there is no moves available
     */

    @Override
    public Position pickNextMove(Board board) {
        List<Position> available = new ArrayList<>();

        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                if (board.isEmptyAt(pos)) {
                    available.add(pos);
                }
            }
        }

        if (available.isEmpty()) return null;

        return available.get(random.nextInt(available.size()));
    }
}
