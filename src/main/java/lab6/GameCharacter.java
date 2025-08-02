package lab6;

import java.awt.Rectangle;

/**
 * Every character or object in the game with a position and size on the
 * screen is abstracted from the GameCharacter class.
 */
public abstract class GameCharacter implements GameConstants {
    public int x, y;
    public final int size = TILE_SIZE;


    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean collidesWith(Rectangle other) {
        return this.getBounds().intersects(other);
    }
}
