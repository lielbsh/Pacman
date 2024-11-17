package com.example.PacMan;

public class Pacman extends Figure {

    public Pacman() {
        this.coordinates = new int[] { 3, 7 };
        this.IsPredetor = false;
    }

    @Override
    protected void setDirection(char newDirection, int[][] boardArray) {
        if (isMovePossible(newDirection, boardArray)) {
            this.direction = newDirection;
        } else {
            this.direction = 'S';
        }
        System.out.println("pacman direction:" + direction);
    }

    @Override
    public boolean die() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'die'");
    }

    @Override
    protected void setPredetor() {
        IsPredetor = true;
    }

}
