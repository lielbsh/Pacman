package com.example.PacMan;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Board {
    Game game; // Reference to the Game class
    int lifeNum = 3;
    int score = 0;
    int[][] boardArray = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2, 1, 2, 1 },
            { 1, 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 1 },
            { 1, 1, 1, 2, 1, 1, 0, 0, 0, 1, 1, 2, 1, 1, 1 },
            { 1, 0, 0, 10, 0, 0, 0, 48, 0, 0, 0, 2, 0, 0, 1 },
            { 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1 },
            { 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1 },
            { 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1 },
            { 1, 1, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 1 },
            { 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };
    Timer timer;
    long timeInterval = 2000;
    Ghost[] ghosts = { new Ghost(), new Ghost(), new Ghost() };
    Pacman pacman = new Pacman();
    char[] path = {
            'U', 'U', 'U', 'U', 'R', 'R', 'R', 'R', 'U', 'U', 'L', 'L', 'L', 'L', 'L', 'L', 'D', 'D', 'D', 'D',
            'R', 'R'
    };

    public Board() {
        this.score = 0;
        this.lifeNum = 3;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(updateBoard, 0, timeInterval);
    }

    TimerTask updateBoard = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Updating the board...");
            System.out.println(boardToString(boardArray));

            // Update game logic each tick

            if (lifeNum <= 0) {
                game.gameOver();
            }

            // First ghost
            ghosts[0].setDirection(boardArray, pacman.coordinates); // Render diraction & Changes the nextStep
            updateBoardValue(ghosts[0]); // Delete value from the old location on the board

            if (score > 2) {
                ghosts[1].setDirection(boardArray, pacman.coordinates);
                updateBoardValue(ghosts[1]);
            }

            if (score > 4) {
                ghosts[2].setDirection(boardArray, pacman.coordinates);
                updateBoardValue(ghosts[2]);
            }

            score += 1;
            System.out.println("score:" + score);

            // Pac-Man Logic
            char newDirection = path[score - 1];
            pacman.setDirection(newDirection, boardArray);
            updateBoardValue(pacman);
            eat(); // Handle interactions
        }
    };

    private void updateBoardValue(Ghost ghost) {
        // Delete value from the old location on the board
        int[] oldCor = ghost.coordinates;
        System.out.println(Arrays.toString(oldCor));
        boardArray[oldCor[1]][oldCor[0]] -= 16;

        // Set the new coordinates & update the board
        ghost.setCoordinates();
        int[] newCor = ghost.coordinates;
        System.out.println(Arrays.toString(newCor));
        boardArray[newCor[1]][newCor[0]] += 16;
    }

    private void updateBoardValue(Pacman pacman) {
        int[] oldCor = pacman.coordinates;
        System.out.println("pacman" + Arrays.toString(oldCor));
        boardArray[oldCor[1]][oldCor[0]] -= 8;

        // Set the new coordinates & update the board
        pacman.setCoordinates();
        int[] newCor = pacman.coordinates;
        System.out.println("pacman" + Arrays.toString(newCor));
        boardArray[newCor[1]][newCor[0]] += 8;
    }

    public char getPacmanDirection(Pacman pacman) {
        char newDirection = 'S';
        return newDirection;
    }

    private void eat() {
        int[] coordinates = pacman.coordinates;
        int x = coordinates[0];
        int y = coordinates[1];
        int boardValue = boardArray[y][x];

        if ((boardValue & 2) != 0) {
            // Collect the coin
            boardArray[y][x] -= 2;
            score += 1;
        }

        if ((boardValue & 3) != 0) {
            // Collect the food
            boardArray[y][x] -= 4;
            score += 10;
        }

        if (pacman.IsPredetor & (boardValue & 4) != 0) {
            // Eat the ghost
            boardArray[y][x] -= 16;
            score += 100;
        }

        // Check if there's a power-up for Pacman to become a predato
        if ((boardValue & 5) != 0) {
            pacman.setPredetor(); // Make Pacman a predator
            System.out.println("pacman is preditor");
        }
    }

    public static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]).append(" "); // Append each element in the board with space separation
            }
            sb.append("\n"); // Add a new line after each row
        }
        return sb.toString(); // Return the generated string representation
    }

}