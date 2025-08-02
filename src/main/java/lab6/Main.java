package lab6;

import javax.swing.*;
import java.awt.*;

/**
 * This main class acts as the entry point of the game as it creates the main application
 * window and it makes the window visible on the screen.
 */
public class Main {

    public Main() {
        JFrame gameFrame = new JFrame("Shadow Dodger");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        gameFrame.setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        ControlPanel controlPanel = new ControlPanel(gamePanel);

        gameFrame.add(gamePanel, BorderLayout.CENTER);
        gameFrame.add(controlPanel, BorderLayout.SOUTH);

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);

        gameFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}