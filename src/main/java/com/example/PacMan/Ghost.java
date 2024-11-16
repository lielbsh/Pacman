package com.example.PacMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends Figure {
    List<Character> directions = new ArrayList<>(NEXT_STEP_OPTIONS.keySet());
    Random random = new Random();

    public Ghost() {
        this.coordinates = new int[] { 7, 7 };
    }

    // changes the nextStep (in Ghost)
    @Override
    protected void setNextStep(int[][] boardArray) {
        directions = new ArrayList<>(NEXT_STEP_OPTIONS.keySet());
        char direction = randomDirection(directions, boardArray); // char diraction
        System.out.println(direction);
        this.nextStep = NEXT_STEP_OPTIONS.get(direction); // set the next step
    }

    @Override
    public boolean die() {
        return false;
    };

    // Helper function for random direction
    private char randomDirection(List<Character> availableDirections, int[][] boardArray) {
        int randomInt = random.nextInt(availableDirections.size());
        char direction = availableDirections.get(randomInt);

        if (isMovePossible(direction, boardArray)) {
            return direction;

        } else {
            directions.remove(Character.valueOf(direction));
            return randomDirection(availableDirections, boardArray);
        }
    }
}
