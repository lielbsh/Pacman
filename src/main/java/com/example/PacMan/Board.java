package com.example.PacMan;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Board {
    Game game; // Reference to the Game class
    int score = 0;
    int step = 0; // for moving the pacman
    int[][] boardArray = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 128, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 128, 1 },
            { 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1 },
            { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
            { 1, 2, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2, 1, 2, 1 },
            { 1, 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 1 },
            { 1, 1, 1, 2, 1, 1, 0, 0, 0, 1, 1, 2, 1, 1, 1 },
            { 1, 0, 8, 2, 0, 0, 0, 112, 0, 0, 0, 2, 0, 0, 1 },
            { 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1 },
            { 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1 },
            { 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1 },
            { 1, 1, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 1 },
            { 1, 1, 1, 2, 2, 2, 2, 128, 2, 2, 2, 2, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };
    Timer timer;
    long timeInterval = 2000;
    Ghost[] ghosts = { new Ghost(1), new Ghost(2), new Ghost(3) };
    Pacman pacman = new Pacman();
    char[] path = {
            'R', 'U', 'U', 'U', 'U', 'R', 'S', 'R', 'R', 'R', 'U', 'U', 'S', 'S', 'L', 'L', 'L', 'L', 'L', 'L', 'D',
            'D', 'D', 'D', 'R', 'R', 'S', 'S'
    };

    public Board() {
        this.score = 0;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(updateBoard, 0, timeInterval);
    }

    TimerTask updateBoard = new TimerTask() {
        @Override
        public void run() {
            if (pacman.die)
                return;

            System.out.println("Updating the board...");
            System.out.println(boardToString(boardArray));

            // Update game logic each tick

            if (pacman.lifeNum <= 0) {
                game.gameOver();
            }

            // First ghost
            ghosts[0].setDirection(boardArray, pacman.coordinates); // Render diraction & Changes the nextStep
            updateBoardValue(ghosts[0]); // Delete value from the old location on the board

            if (step > 2) {
                ghosts[1].setDirection(boardArray, pacman.coordinates);
                updateBoardValue(ghosts[1]);
            }

            if (step > 5) {
                ghosts[2].setDirection(boardArray, pacman.coordinates);
                updateBoardValue(ghosts[2]);
            }

            // Pac-Man Logic
            char newDirection = path[step];
            pacman.setDirection(newDirection, boardArray);
            updateBoardValue(pacman);
            eat(); // Handle interactions

            System.out.print(" | score:" + score + " | lifeNum:" + pacman.lifeNum);

            step += 1;
            System.out.println(" | step:" + path[step] + "|step num:" + step);
        }
    };

    // Method for update the figure location on the board (base on the figure
    // direction)
    private void updateBoardValue(Figure figure) {
        int boardIndex = figure.boardIndex;
        System.out.print(" | figureInx:" + Integer.toString(boardIndex));

        // Delete value from the old location on the board
        int[] oldCor = figure.coordinates;
        boardArray[oldCor[1]][oldCor[0]] -= boardIndex;

        // Set the new coordinates & update the board
        figure.setCoordinates();
        int[] newCor = figure.coordinates;
        System.out.println(" | " + Arrays.toString(newCor));
        boardArray[newCor[1]][newCor[0]] += boardIndex;
    }

    // Method for update figure location on the board (with a specific location
    // for initialize)
    private void updateBoardValue(Figure figure, int[] newCoordinates) {
        int boardIndex = figure.boardIndex;
        System.out.print(" | ghostInx:" + Integer.toString(boardIndex));

        // Delete value from the old location on the board
        int[] oldCor = figure.coordinates;
        boardArray[oldCor[1]][oldCor[0]] -= boardIndex;

        // Set the new coordinates & update the board
        figure.setCoordinates(newCoordinates);
        int[] newCor = figure.coordinates;
        System.out.println(" | " + Arrays.toString(newCor));
        boardArray[newCor[1]][newCor[0]] += boardIndex;
    }

    private void eat() {
        int[] coordinates = pacman.coordinates;
        int x = coordinates[0];
        int y = coordinates[1];
        int boardValue = boardArray[y][x];

        if ((boardValue & 2) != 0) {
            // Collect the coin
            boardArray[y][x] -= 2;
            score += 10;
            System.out.print("| Coin collected");
        }

        if ((boardValue & 4) != 0) {
            // Collect the food
            boardArray[y][x] -= 4;
            score += 300;
            System.out.print(" | food eaten");
        }

        if (pacman.IsPredetor & (boardValue & 24) != 0) {
            // Eat the ghost
            boardArray[y][x] -= 16;
            score += 200;
            System.out.println("Eat ghost");
        }

        // Check if there's a power-up for Pacman to become a predato
        if ((boardValue & 128) != 0) {
            pacman.setPredetor(); // Make Pacman a predator
            System.out.println("pacman is preditor");
        }

        // check if pacman died
        if ((boardValue & 8) != 0 && ((boardValue & 16) != 0 || (boardValue & 32) != 0 || (boardValue & 64) != 0)) {

            // Move Pacman and Ghosts to starting point
            System.out.println("Pacman died at coordinates:" + Arrays.toString(coordinates));
            updateBoardValue(pacman, new int[] { 2, 7 });
            for (int i = 0; i < ghosts.length; i++) {
                updateBoardValue(ghosts[i], new int[] { 7, 7 });
            }

            // Update the score (-10 points)
            score = Math.max(score - 10, 0);
            // Kill pacmen
            pacman.die();
            step = -1;
            System.out.println("********* Pacman died *********");
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