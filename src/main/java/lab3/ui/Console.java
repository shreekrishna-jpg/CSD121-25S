package lab3.ui;

import java.util.Scanner;
import java.util.InputMismatchException;
import lab3.game.*;

public class Console {
    private Board gameBoard;
    private char currentPlayer;
    private int moves;
    private Scanner scanner;

    public Console() {
        gameBoard = new Board();
        currentPlayer = 'X';
        moves = 0;
        scanner = new Scanner(System.in);
    }

    /**
     * This starts the main TicTacToe game loop.
     * This helps in handling the player input, processing the move, checking if the player has won or draw and switching the player.
     * The game will end if the player has won or draw.
     */
    public void startGame() {
        boolean gameEnded = false;

        gameBoard.printBoard();

        while (!gameEnded) {
//            This will ask for a players to enter their move for the game
           System.out.println("Player " + currentPlayer + ", enter your move (row then enter and column then enter, e.g., 1 2):");

            int row = -1;
            int col = -1;
            boolean moveMade = false;

            while (!moveMade) {
                try {
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (gameBoard.makeMove(row, col, currentPlayer)) {
                        moveMade = true;
                        moves++;
                        gameBoard.printBoard();
                    } else {
                        System.out.println("Invalid move. Please choose an empty cell within the board (1-3 for row/column).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter two numbers for row and column.");
                    scanner.next();
                    scanner.next();
                }
            }

            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (checkDraw()) {
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                switchPlayer();
            }
        }
        scanner.close();
        System.out.println("Game Over!");
    }

    /**
     * This checks whether the current player has won the game or not
     * A win happens when the player has 3 of their marks in a row, column, or diagonal.
     * It returns true if the current player has won otherwise it returns false
     */
    private boolean checkWin() {
//          This will check all the rows for the win of the game
        for (int i = 0; i < 3; i++) {
            if (gameBoard.getCell(i, 0) == currentPlayer &&
                    gameBoard.getCell(i, 1) == currentPlayer &&
                    gameBoard.getCell(i, 2) == currentPlayer) {
                return true;
            }
        }
//          this will check the column for the win of the game
        for (int j = 0; j < 3; j++) {
            if (gameBoard.getCell(0, j) == currentPlayer &&
                    gameBoard.getCell(1, j) == currentPlayer &&
                    gameBoard.getCell(2, j) == currentPlayer) {
                return true;
            }
        }
        if ((gameBoard.getCell(0, 0) == currentPlayer &&
                gameBoard.getCell(1, 1) == currentPlayer &&
                gameBoard.getCell(2, 2) == currentPlayer) ||
                (gameBoard.getCell(0, 2) == currentPlayer &&
                        gameBoard.getCell(1, 1) == currentPlayer &&
                        gameBoard.getCell(2, 0) == currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        return moves == 9 && !checkWin();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        Console game = new Console();
        game.startGame();
    }
}
