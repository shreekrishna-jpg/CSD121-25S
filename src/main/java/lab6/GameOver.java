package lab6;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    public GameOver(GamePanel gamePanel, int finalScore) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 192)); // Semi-transparent black
        setOpaque(true);

        JLabel title = new JLabel("Game Over");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.RED);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreDisplay = new JLabel("Final Score: " + finalScore);
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 28));
        scoreDisplay.setForeground(Color.WHITE);
        scoreDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(e -> gamePanel.resetGame());

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(e -> gamePanel.quitGame());

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20))); // Spacing
        add(scoreDisplay);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(playAgainButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quitButton);
        add(Box.createVerticalGlue());
    }
}