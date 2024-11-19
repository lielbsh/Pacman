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

    // Helper function for random direction
    // private char randomDirection(List<Character> availableDirections, int[][]
    // boardArray) {
    // int randomInt = random.nextInt(availableDirections.size());
    // char direction = availableDirections.get(randomInt);

    // if (isMovePossible(direction, boardArray)) {
    // return direction;

    // } else {
    // directionsList.remove(Character.valueOf(direction));
    // return randomDirection(availableDirections, boardArray);
    // }
    // }

    // Changes the direction to chase/run from the pacman
    @Override
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
        if (coordinates[0] < pacmanCoordinates[0] && isMovePossible('R', boardArray)) {
            if (!isPredetor) {
                direction = 'R';
            } else {
                direction = 'L';
            }
            return true;
        } else if (coordinates[0] > pacmanCoordinates[0] && isMovePossible('L', boardArray)) {
            if (!isPredetor) {
                direction = 'L';
            } else {
                direction = 'R';
            }
            return true;
        }
        return false;
    }

    private boolean moveVertical(int[][] boardArray, int[] pacmanCoordinates, boolean isPredetor) {
        if (coordinates[1] < pacmanCoordinates[1] && isMovePossible('D', boardArray)) {
            if (!isPredetor) {
                direction = 'D';
            } else {
                direction = 'U';
            }
            return true;

        } else if (coordinates[1] > pacmanCoordinates[1] && isMovePossible('U', boardArray)) {
            if (!isPredetor) {
                direction = 'U';
            } else {
                direction = 'D';
            }
            return true;
        }
        return false;
    }
}
