package com.example.PacMan;

import java.util.Map;

public abstract class Figure {
    Map<Character, int[]> NEXT_STEP_OPTIONS = Map.of(
            'U', new int[] { 0, 1 },
            'D', new int[] { 0, -1 },
            'L', new int[] { -1, 0 },
            'R', new int[] { 1, 0 },
            'S', new int[] { 0, 0 });

    protected int index;
    protected boolean IsPredetor;
    protected String PhotoHref;
    protected int[] coordinates;
    protected int[] nextStep;

    protected void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    };

    protected void setNextStep(char nextStepDir) {
        this.nextStep = NEXT_STEP_OPTIONS.get(nextStepDir);
    };

    public abstract boolean die();

}
