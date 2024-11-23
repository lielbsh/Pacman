package com.example.PacMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Figure {
    List<Character> directionsList = new ArrayList<>(NEXT_STEP_OPTIONS.keySet()); // for dummy ghost
    Random random = new Random();

    public Ghost(int index) { // e.g 1 or 2 or 3
        this.coordinates = new int[] { 7, 7 };
        this.boardIndex = (int) Math.pow(2, index + 3);
        this.IsPredetor = true;
    }

    @Override
    public void die() {
        System.out.println("ghost died");
    };

    @Override
    protected boolean isMovePossible(char direction, int[][] boardArray) {
        // Call the parent method for general move validation
        if (!super.isMovePossible(direction, boardArray)) {
            return false;
        }

        // Special case: wrapping logic
        if (((coordinates[0] == 0 && direction == 'L') || (coordinates[0] == 14 && direction == 'R'))
                && coordinates[1] == 7) {
            System.out.println("move is possible - wrap around");
            return false;
        }

        // Check for the specific condition for Ghost (e.g., value 128)
        int[] nextCoordinates = {
                this.coordinates[0] + NEXT_STEP_OPTIONS.get(direction)[0],
                this.coordinates[1] + NEXT_STEP_OPTIONS.get(direction)[1]
        };

        int cellValue = boardArray[nextCoordinates[1]][nextCoordinates[0]];
        if ((cellValue & 128) != 0) {
            return false;
        }

        return true;
    };

    // Changes the direction to chase/run from the pacman
    public void setDirection(int[][] boardArray, Pacman pacman) {
        int[] pacmanCoordinates = pacman.coordinates;
        boolean isPredetor = pacman.IsPredetor;
        direction = 'S'; // initilaize direction
        int randomNum = random.nextInt(2);

        // First attempt: prioritize horizontal or vertical based on random number
        if (randomNum == 0) {
            if (!moveHorizontal(boardArray, pacmanCoordinates, isPredetor)) {
                moveVertical(boardArray, pacmanCoordinates, isPredetor); // Fallback to vertical
            }
        } else {
            if (!moveVertical(boardArray, pacmanCoordinates, isPredetor)) {
                moveHorizontal(boardArray, pacmanCoordinates, isPredetor); // Fallback to horizontal
            }
        }

        System.out.print("| direction:" + direction);
    }

    private boolean moveHorizontal(int[][] boardArray, int[] pacmanCoordinates, boolean isPredetor) {
        char firstDirection = 'R';
        char secondDirection = 'L';
        if (isPredetor) {
            firstDirection = 'L';
            secondDirection = 'R';
        }
        if (coordinates[0] < pacmanCoordinates[0] && isMovePossible(firstDirection, boardArray)) {
            direction = firstDirection;
            return true;
        } else if (coordinates[0] > pacmanCoordinates[0] && isMovePossible(secondDirection, boardArray)) {
            direction = secondDirection;
            return true;
        }
        return false;
    }

    private boolean moveVertical(int[][] boardArray, int[] pacmanCoordinates, boolean isPredetor) {
        char firstDirection = 'D';
        char secondDirection = 'U';

        if (isPredetor) {
            firstDirection = 'U';
            secondDirection = 'D';
        }

        if (coordinates[1] < pacmanCoordinates[1] && isMovePossible(firstDirection, boardArray)) {
            direction = firstDirection;
            return true;
        } else if (coordinates[1] > pacmanCoordinates[1] && isMovePossible(secondDirection, boardArray)) {
            direction = secondDirection;
            return true;
        }

        return false;
    }
}
