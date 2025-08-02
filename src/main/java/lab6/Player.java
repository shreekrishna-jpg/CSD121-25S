package lab6;

import java.awt.*;
        import java.awt.event.KeyEvent;


public class Player extends GameCharacter {
    private final Color color = Color.YELLOW;

    public Player() {

        x = SCREEN_WIDTH / 2;
        y = SCREEN_HEIGHT / 2;
    }


    public void move(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> y -= TILE_SIZE;
            case KeyEvent.VK_DOWN -> y += TILE_SIZE;
            case KeyEvent.VK_LEFT -> x -= TILE_SIZE;
            case KeyEvent.VK_RIGHT -> x += TILE_SIZE;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}