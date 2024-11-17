package com.example.PacMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Figure {
    List<Character> directionsList = new ArrayList<>(NEXT_STEP_OPTIONS.keySet()); // for dummy ghost
    Random random = new Random();

    public Ghost() {
        this.coordinates = new int[] { 7, 7 };
    }

    @Override
    public boolean die() {
        return false;
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

    // Changes the direction to chase the pacman
    @Override
    public void setDirection(int[][] boardArray, int[] pacmanCoordinates) {
        direction = 'S'; // initilaize direction
        int randomNum = random.nextInt(2);

        // First attempt: prioritize horizontal or vertical based on random number
        if (randomNum == 0) {
            if (!chaseHorizontal(boardArray, pacmanCoordinates)) {
                chaseVertical(boardArray, pacmanCoordinates); // Fallback to vertical
            }
        } else {
            if (!chaseVertical(boardArray, pacmanCoordinates)) {
                chaseHorizontal(boardArray, pacmanCoordinates); // Fallback to horizontal
            }
        }

        System.out.println("direction:" + direction);
    }

    private boolean chaseHorizontal(int[][] boardArray, int[] pacmanCoordinates) {
        if (coordinates[0] < pacmanCoordinates[0] && isMovePossible('R', boardArray)) {
            direction = 'R';
            return true;
        } else if (coordinates[0] > pacmanCoordinates[0] && isMovePossible('L', boardArray)) {
            direction = 'L';
            return true;
        }
        return false;
    }

    private boolean chaseVertical(int[][] boardArray, int[] pacmanCoordinates) {
        if (coordinates[1] < pacmanCoordinates[1] && isMovePossible('D', boardArray)) {
            direction = 'D';
            return true;
        } else if (coordinates[1] > pacmanCoordinates[1] && isMovePossible('U', boardArray)) {
            direction = 'U';
            return true;
        }
        return false;
    }
}
