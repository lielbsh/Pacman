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
    protected int[] nextStep = { -1, 0 };

    protected void setCoordinates() {
        int[] nextCoordinates = { this.coordinates[0] + nextStep[0],
                this.coordinates[1] + nextStep[1] };

        this.coordinates = nextCoordinates;
    };

    protected void setNextStep(int[][] boardArray) {
    };

    protected boolean isMovePossible(char diraction, int[][] boardArray) {
        int[] nextCoordinates = { this.coordinates[0] + NEXT_STEP_OPTIONS.get(diraction)[0],
                this.coordinates[1] + NEXT_STEP_OPTIONS.get(diraction)[1] };
        System.out.println(coordinates[0]);
        System.out.println(coordinates[1]);
        System.out.println(diraction);
        if (boardArray[nextCoordinates[1]][nextCoordinates[0]] == 1) {
            return false;
        }

        return true;
    }

    public abstract boolean die();

}
