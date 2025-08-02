package lab6;


import java.awt.*;
import java.util.Random;

/**
 * In the game, the Crystal class stand in as a collected object.
 * It appears at a random point inside the screen's boundaries and extends the GameCharacter class.
 * A crystal becomes collected when a player touches it.
 */

public class Crystal extends GameCharacter {
    private final Color color = Color.CYAN;
    private final Random rand = new Random();

    public Crystal() {

        spawn();
    }

    public void spawn() {
        x = rand.nextInt(SCREEN_WIDTH / TILE_SIZE) * TILE_SIZE;
        y = rand.nextInt(SCREEN_HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public boolean collectedBy(Player p) {
        return this.getBounds().intersects(p.getBounds());
    }
}