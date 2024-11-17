package com.example.PacMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Figure {
    List<Character> directionsList = new ArrayList<>(NEXT_STEP_OPTIONS.keySet()); // for dummy ghost
    Random random = new Random();
    char direction = 'S';

    public Ghost() {
        this.coordinates = new int[] { 7, 7 };
    }

    @Override
    protected void setNextStep(int[][] boardArray) {
        this.nextStep = NEXT_STEP_OPTIONS.get(direction); // set the next step
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
    public void setSmartDirection(int[][] boardArray, int[] pacmanCoordinates) {
        direction = 'S'; // initilaize direction
        int randomNum = random.nextInt(2);
        if (randomNum == 0) {
            // Choose horizontal direction first
            chaseHorizontal(boardArray, pacmanCoordinates);
        } else {
            // If horizontal direction is blocked, choose vertical
            chaseVertical(boardArray, pacmanCoordinates);
        }

        this.nextStep = NEXT_STEP_OPTIONS.get(direction);
        System.out.println("direction:" + direction);
    }

    private void chaseHorizontal(int[][] boardArray, int[] pacmanCoordinates) {
        if (coordinates[0] < pacmanCoordinates[0] && isMovePossible('R', boardArray)) {
            direction = 'R';
        } else if (coordinates[0] > pacmanCoordinates[0] && isMovePossible('L', boardArray)) {
            direction = 'L';
        }
    }

    private void chaseVertical(int[][] boardArray, int[] pacmanCoordinates) {
        if (coordinates[1] < pacmanCoordinates[1] && isMovePossible('D', boardArray)) {
            direction = 'D';
        } else if (coordinates[1] > pacmanCoordinates[1] && isMovePossible('U', boardArray)) {
            direction = 'U';
        }
    }
}
