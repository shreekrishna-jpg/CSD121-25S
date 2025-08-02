package lab6;

import java.awt.*;
import java.util.Random;

/**
 * The shadow class represents the enemy character in the game.
 * It moves randomly on the screen and the game ends if it touches the player.
 * Displays as a red square in the screen.
 * Four of them moves in random directions in the screen by moveRandom().
 * It detects the collision with the player using the method collideWith(player p).
 *
 */
public class Shadow extends GameCharacter {
    private final Color color = Color.RED;
    private final Random rand = new Random();

    public Shadow() {
        spawnRandom();
    }

    public void moveRandom() {
        int dir = rand.nextInt(4);
        switch (dir) {
            case 0 -> y -= TILE_SIZE;
            case 1 -> y += TILE_SIZE;
            case 2 -> x -= TILE_SIZE;
            case 3 -> x += TILE_SIZE;
        }
    }

    public void spawnRandom() {
        x = rand.nextInt(SCREEN_WIDTH / TILE_SIZE) * TILE_SIZE;
        y = rand.nextInt(SCREEN_HEIGHT / TILE_SIZE) * TILE_SIZE;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public boolean collidesWith(Player p) {
        return this.getBounds().intersects(p.getBounds());
    }
}