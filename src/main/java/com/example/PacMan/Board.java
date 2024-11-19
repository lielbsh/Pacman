package com.example.PacMan;

import java.lang.classfile.constantpool.PackageEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class Board {
    ThingWebSocketHandler  webSocketHandler;
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

    public Board(ThingWebSocketHandler  webSocketHandler) {
        this.score = 0;
        this.timer = new Timer();
        timer.scheduleAtFixedRate(updateBoard, 0, timeInterval);
        this.webSocketHandler=webSocketHandler;
    }
    
    public void updateDirection(char direction){
        pacman.setDirection(direction,boardArray);
    }
    TimerTask updateBoard = new TimerTask() {
        @Override
        public void run() {
            handlerun();
             Map<String, String> data = Map.of(
            "boardArray", arrayToString(boardArray),
            "score", String.valueOf(score),
            "lives",String.valueOf(pacman.lifeNum),
            "isLive", String.valueOf(pacman.die),
            "isPredator",String.valueOf(pacman.IsPredetor),
            "status","started"
             );
            webSocketHandler.sendDataToClients(data);
        }
    };

    public static String arrayToString(int[][] boardArray) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : boardArray) {
            sb.append("[");
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove trailing space
            sb.append("]\n");
        }
        return sb.toString();
    }
    

    public  void handlerun(){
        if (pacman.die)
                return;

            System.out.println("Updating the board...");
            System.out.println(boardToString(boardArray));

            // Update game logic each tick

            if (pacman.lifeNum <= 0) {
                game.gameOver();
                cancelTimer();
            }

            // First ghost
            ghosts[0].setDirection(boardArray, pacman); // Render diraction & Changes the nextStep
            updateBoardValue(ghosts[0]); // Delete value from the old location on the board

            if (step > 2) {
                ghosts[1].setDirection(boardArray, pacman);
                updateBoardValue(ghosts[1]);
            }

            if (step > 5) {
                ghosts[2].setDirection(boardArray, pacman);
                updateBoardValue(ghosts[2]);
            }

            // Pac-Man Logic
            char newDirection = path[step];
            // pacman.setDirection(newDirection, boardArray);
            updateBoardValue(pacman);
            eat(); // Handle interactions

            System.out.print(" | score:" + score + " | lifeNum:" + pacman.lifeNum);

            step += 1;
            System.out.println(" | step:" + path[step] + "|step num:" + step);
        
    }
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
        System.out.print(" | figure:" + Integer.toString(boardIndex));

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

        // Check if there's a power-up for Pacman to become a predato
        if ((boardValue & 128) != 0) {
            pacman.setPredetor(); // Make Pacman a predator
            boardArray[y][x] -= 128;
            System.out.println("| Pac-Man is preditor !! |");
        }

        // check the case of Pacman and Ghosts collision
        if ((boardValue & 8) != 0 && (boardValue & (16 | 32 | 64)) != 0) {

            if (pacman.IsPredetor) {
                // Iterate over the ghosts array to check which ghost is in the same cell
                for (Ghost ghost : ghosts) {
                    // Check if the ghost's boardIndex matches the boardValue of the ghost
                    if ((boardValue & ghost.boardIndex) != 0) {
                        // Eat the ghost
                        boardArray[y][x] -= ghost.boardIndex; // Remove ghost from board
                        ghost.die();
                        updateBoardValue(ghost, new int[] { 7, 7 });
                        score += 200; // Increase score
                        System.out.println("Pacman ate the ghost!");
                    }
                }

            } else { // Pacman die
                // Move Pacman and Ghosts to starting point
                System.out.println("Pacman died at coordinates:" + Arrays.toString(coordinates));
                updateBoardValue(pacman, new int[] { 2, 7 });
                for (Ghost ghost : ghosts) {
                    updateBoardValue(ghost, new int[] { 7, 7 });
                }
                // Update the score (-10 points)
                score = Math.max(score - 10, 0);
                // Kill pacmen
                pacman.die();
                step = -1;
                System.out.println("********* Pacman died *********");
            }
        }
    }

    public void cancelTimer() {
        // This method cancels the timer and stops further updates
        if (timer != null) {
            timer.cancel();
            timer = null; // Nullify to ensure it's not used again
        }
    }

    public static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();

        // Loop through each row of the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Get the board value for the current cell
                int cellValue = board[i][j];

                // Generate the visual representation for the cell value
                String cellDisplay = getCellDisplay(cellValue);

                // Ensure each cell has the same width (e.g., 3 characters for each)
                sb.append(String.format("%-3s", cellDisplay)); // Left-justified with width of 3
            }
            sb.append("\n"); // Add a new line after each row
        }

        return sb.toString(); // Return the string representation of the board
    }

    public static String getCellDisplay(int cellValue) {
        if (cellValue == 0) {
            return " "; // Empty space
        }
        if ((cellValue & 1) != 0) {
            return "#"; // Block
        }
        if ((cellValue & 2) != 0) {
            return "."; // Coin
        }
        if ((cellValue & 4) != 0) {
            return "F"; // Food
        }
        if ((cellValue & 8) != 0) {
            return "P"; // PacMan
        }
        if ((cellValue & 16) != 0) {
            return "G1"; // Ghost1
        }
        if ((cellValue & 32) != 0) {
            return "G2"; // Ghost2
        }
        if ((cellValue & 64) != 0) {
            return "G3"; // Ghost3
        }
        if ((cellValue & 128) != 0) {
            return "@"; // Power-Up Coin
        }
        return "^"; // Default case (should not happen)
    }

}