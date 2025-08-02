package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is the fundamental component of the game is the GamePanel class.
 *  It controls all aspects of the game, including graphics, player interactions,
 *  enemy shadows, and collecting crystals as described below:

 *  It detects the collision between player and the shadow.
 *  It draws all the visual components used in the game.
 *  It detects the collection of the crystals by the player. etc.
 *  It shows if the player want to quit the game or want to play again.
 */


public class GamePanel extends JPanel implements ActionListener, GameConstants {

    private Timer gameTimer;
    private Player player;
    private Crystal crystal;
    private List<Shadow> shadowsList;
    private boolean isRunning = false;
    private int score = 0;
    private final int numberOfShadows = 4;

    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        player = new Player();
        crystal = new Crystal();
        shadowsList = new ArrayList<>();

        initializeGame();

        startGame();
    }

    private void initializeGame() {
        this.removeAll();

        player = new Player();
        crystal = new Crystal();
        shadowsList = new ArrayList<>();
        score = 0;

        for (int i = 0; i < numberOfShadows; i++) {
            Shadow newShadow = new Shadow();
            while (newShadow.collidesWith(player.getBounds())) {
                newShadow.spawnRandom();
            }
            shadowsList.add(newShadow);
        }

        spawnCrystalSafely();

        repaint();
    }

    private void spawnCrystalSafely() {
        crystal.spawn();
        while (crystal.collidesWith(player.getBounds())) {
            crystal.spawn();
        }
        for (Shadow shadow : shadowsList) {
            while (crystal.collidesWith(shadow.getBounds())) {
                crystal.spawn();
            }
        }
    }

    public void movePlayerUp() {
        if (isRunning) {
            player.move(KeyEvent.VK_UP);
            keepCharacterOnScreen(player);
            repaint();
        }
    }

    public void movePlayerDown() {
        if (isRunning) {
            player.move(KeyEvent.VK_DOWN);
            keepCharacterOnScreen(player);
            repaint();
        }
    }

    public void movePlayerLeft() {
        if (isRunning) {
            player.move(KeyEvent.VK_LEFT);
            keepCharacterOnScreen(player);
            repaint();
        }
    }

    public void movePlayerRight() {
        if (isRunning) {
            player.move(KeyEvent.VK_RIGHT);
            keepCharacterOnScreen(player);
            repaint();
        }
    }

    private void startGame() {
        if (gameTimer == null) {
            gameTimer = new Timer(100, this);
        }
        isRunning = true;
        gameTimer.start();
    }

    private void keepCharacterOnScreen(GameCharacter character) {
        if (character.x < 0) character.x = 0;
        if (character.x > SCREEN_WIDTH - TILE_SIZE) character.x = SCREEN_WIDTH - TILE_SIZE;
        if (character.y < 0) character.y = 0;
        if (character.y > SCREEN_HEIGHT - TILE_SIZE) character.y = SCREEN_HEIGHT - TILE_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRunning) {
            player.draw(g);
            crystal.draw(g);
            for (Shadow shadow : shadowsList) {
                shadow.draw(g);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score: " + score, 10, 25);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            for (Shadow shadow : shadowsList) {
                shadow.moveRandom();
                keepCharacterOnScreen(shadow);
            }

            if (crystal.collectedBy(player)) {
                score++;
                spawnCrystalSafely();
            }

            for (Shadow shadow : shadowsList) {
                if (shadow.collidesWith(player)) {
                    gameOver();
                    break;
                }
            }
            repaint();
        }
    }

    public void gameOver() {
        isRunning = false;
        gameTimer.stop();

        this.removeAll();

        GameOver gameOverPanel = new GameOver(this, score);
        this.add(gameOverPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void resetGame() {
        initializeGame();
        startGame();
    }

    public void quitGame() {
        System.exit(0);
    }
}