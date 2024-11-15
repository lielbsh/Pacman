package com.example.PacMan;

import java.util.Map;

public abstract class Figure {
    Map<String, int[]> NEXT_STEP_OPTIONS = Map.of(
            "UP", new int[] {0,1},
            "DOWN", new int[] {0,-1},
            "LEFT", new int[] {-1,0},
            "RIGHT", new int[] {1,0},
            "STAY", new int[] {0,0}
        );

    protected int index;
    protected boolean IsPredetor;
    protected String PhotoHref;
    protected  int[] coordinates;
    protected String nextStep;
    protected void setCoordinates(int[] coordinates){
       this.coordinates=coordinates;
    };
    protected void setNextStep(String nextStep){
    this.nextStep=nextStep;
    };
    
    public abstract  boolean die();

}
