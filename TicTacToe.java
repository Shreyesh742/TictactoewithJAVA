import java.util.Scanner;

public class TicTacToe {
    private static char[] board = new char[9];
    private static char currentPlayer = 'X';
    private static boolean gameActive = true;
    private static int moveCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the board with empty spaces
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        printBoard();

        // Main game loop
        while (gameActive) {
            System.out.println("Player " + currentPlayer + "'s turn");
            System.out.print("Enter a position (1-9): ");
            int position = scanner.nextInt() - 1;

            if (position < 0 || position > 8 || board[position] != ' ') {
                System.out.println("Invalid move. Try again.");
            } else {
                board[position] = currentPlayer;
                moveCount++;
                printBoard();
                checkGameStatus();
                if (gameActive) {
                    // Switch to the next player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }

        scanner.close();
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i * 3] + " | " + board[i * 3 + 1] + " | " + board[i * 3 + 2] + " |");
            System.out.println("-------------");
        }
    }

    private static void checkGameStatus() {
        if (checkWinner()) {
            System.out.println("Player " + currentPlayer + " wins!");
            gameActive = false;
        } else if (moveCount == 9) {
            System.out.println("It's a tie!");
            gameActive = false;
        }
    }

    private static boolean checkWinner() {
        // Winning combinations
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}              // Diagonals
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == currentPlayer && 
                board[pattern[1]] == currentPlayer && 
                board[pattern[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }
}
