package com.example.PacMan;

import java.util.Map;

public abstract class Figure {
    static Map<Character, int[]> NEXT_STEP_OPTIONS = Map.of(
            'U', new int[] { 0, -1 },
            'D', new int[] { 0, 1 },
            'L', new int[] { -1, 0 },
            'R', new int[] { 1, 0 },
            'S', new int[] { 0, 0 });

    protected int boardIndex; // e.g 8 or 16 or 32 or 64
    protected boolean IsPredetor;
    public boolean die = false;
    protected int[] coordinates;
    protected char direction = 'S';

    protected void setCoordinates() {
        // Special case: wrapping logic
        if (coordinates[0] == 0 && coordinates[1] == 7 && direction == 'L') {
            System.out.println("|  Wrapping around: Left edge  |");
            this.coordinates[0] = 14; // Move to the other side of the row
            return;
        } else if (coordinates[0] == 14 && coordinates[1] == 7 && direction == 'R') {
            System.out.println("|  Wrapping around: Right edge  |");
            this.coordinates[0] = 0; // Move to the start of the row
            return;
        }

        // Calculate the next coordinates based on the direction
        int[] nextCoordinates = { this.coordinates[0] + NEXT_STEP_OPTIONS.get(direction)[0],
                this.coordinates[1] + NEXT_STEP_OPTIONS.get(direction)[1] };

        this.coordinates = nextCoordinates;
    };

    protected void setCoordinates(int[] coordinates) {
        int[] nextCoordinates = { coordinates[0], coordinates[1] };
        this.coordinates = nextCoordinates;
        this.direction = 'S';
    };

    protected void setDirection(char newDirection, int[][] boardArray) {
    };

    protected boolean isMovePossible(char direction, int[][] boardArray) {
        if (die)
            return false;

        // Special case: wrapping logic
        if (((coordinates[0] == 0 && direction == 'L') || (coordinates[0] == 14 && direction == 'R'))
                && coordinates[1] == 7) {
            System.out.println("move is possible - wrap around");
            return true;
        }

        // Calculate the next coordinates
        int[] nextCoordinates = {
                this.coordinates[0] + NEXT_STEP_OPTIONS.get(direction)[0],
                this.coordinates[1] + NEXT_STEP_OPTIONS.get(direction)[1]
        };

        // Check if the position is invalid (e.g., a block)
        int cellValue = boardArray[nextCoordinates[1]][nextCoordinates[0]];
        if ((cellValue & 1) != 0) { // If the cell contains a block
            return false;
        }

        return true;
    }

    protected void setPredetor() {
    };

    protected void die() {
    };

}
