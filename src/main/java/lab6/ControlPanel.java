package lab6;


import javax.swing.*;
import java.awt.*;

/**
 * A graphical control panel for player movement in the game is provided
 * by the ControlPanel class.  The directional buttons (up, down, left, and right)
 * are organized in a 3x3 grid pattern within a JPanel.
 *
 *  When buttons are pressed, this panel, which is connected to a GamePanel instance, calls
 *  its movement methods.
 */
public class ControlPanel extends JPanel implements GameConstants {

    private final GamePanel gamePanel;

    public ControlPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setPreferredSize(new Dimension(SCREEN_WIDTH, TILE_SIZE * 3));
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(3, 3, 5, 5));


        JButton upButton = new JButton("▲");
        upButton.addActionListener(e -> gamePanel.movePlayerUp());

        JButton downButton = new JButton("▼");
        downButton.addActionListener(e -> gamePanel.movePlayerDown());

        JButton leftButton = new JButton("◄");
        leftButton.addActionListener(e -> gamePanel.movePlayerLeft());

        JButton rightButton = new JButton("►");
        rightButton.addActionListener(e -> gamePanel.movePlayerRight());

        add(new JLabel(""));
        add(upButton);
        add(new JLabel(""));

        add(leftButton);
        add(new JLabel(""));
        add(rightButton);

        add(new JLabel(""));
        add(downButton);
        add(new JLabel(""));
    }
}