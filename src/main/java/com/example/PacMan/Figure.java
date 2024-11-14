package com.example.PacMan;

import java.util.Map;

public abstract class Figure {
    protected int index;
    protected boolean IsPredetor;
    protected String PhotoHref;
    protected  int[] coordinates;
    protected Map<String, int[]> NextStep = Map.of(
            "UP", new int[] {0,1},
            "DOWN", new int[] {0,-1},
            "LEFT", new int[] {-1,0},
            "RIGHT", new int[] {1,0},
            "STAY", new int[] {0,0}
        );
    
    protected void move(int[][] boardArray, String nextStep){
        boardArray[coordinates[0]][coordinates[1]]=0;
        boardArray[coordinates[0]+NextStep.get(nextStep)[0]][coordinates[1]+NextStep.get(nextStep)[0]]=3;
    };
    public abstract  boolean die();

}
