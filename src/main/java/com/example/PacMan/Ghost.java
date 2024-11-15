package com.example.PacMan;

public class Ghost extends Figure {

    @Override
    protected void setCoordinates(int[] coordinates) {
        super.setCoordinates(coordinates);
    }

    @Override
    protected void setNextStep(char nextStepDir) {
    }

    @Override
    public boolean die() {
        return false;
    };
}
