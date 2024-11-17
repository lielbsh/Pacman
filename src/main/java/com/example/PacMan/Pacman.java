package com.example.PacMan;

public class Pacman extends Figure {

    public Pacman() {
        this.coordinates = new int[] { 5, 7 };
    }

    @Override
    protected void setNextStep(int[][] boardArray) {

    }

    @Override
    public boolean die() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'die'");
    }

}
