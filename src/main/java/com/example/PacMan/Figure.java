package com.example.PacMan;

import java.util.Map;

public abstract class Figure {
    static Map<Character, int[]> NEXT_STEP_OPTIONS = Map.of(
            'U', new int[] { 0, -1 },
            'D', new int[] { 0, 1 },
            'L', new int[] { -1, 0 },
            'R', new int[] { 1, 0 },
            'S', new int[] { 0, 0 });

    protected int index;
    protected boolean IsPredetor;
    protected String PhotoHref;
    protected int[] coordinates;
    protected char direction = 'S';
    // protected int[] nextStep = { 0, 0 };

    protected void setCoordinates() {
        int[] nextCoordinates = { this.coordinates[0] + NEXT_STEP_OPTIONS.get(direction)[0],
                this.coordinates[1] + NEXT_STEP_OPTIONS.get(direction)[1] };

        this.coordinates = nextCoordinates;
    };

    protected void setDirection(char newDirection, int[][] boardArray) {
    };

    protected void setDirection(int[][] boardArray, int[] pacmanCoordinates) {
    };

    protected boolean isMovePossible(char direction, int[][] boardArray) {
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

    public abstract boolean die();

}
