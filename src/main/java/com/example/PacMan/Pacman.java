package com.example.PacMan;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Pacman extends Figure {
    int lifeNum = 3;

    public Pacman() {
        this.coordinates = new int[] { 2, 7 };
        this.IsPredetor = false;
        this.lifeNum = 3;
        this.boardIndex = 8;
    }

    @Override
    protected void setDirection(char newDirection, int[][] boardArray) {
        if (die)
            return;
        if (isMovePossible(newDirection, boardArray)) {
            this.direction = newDirection;
        } else {
            this.direction = 'S';
        }
        System.out.println("| pacman direction:" + direction);
    }

    @Override
    public void die() {
        die = true;
        lifeNum -= 1;
        System.out.println("new coordinates" + Arrays.toString(this.coordinates));
        direction = 'S';
    }

    @Override
    protected void setPredetor() {
        IsPredetor = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                IsPredetor = false;
                System.out.println("Pacman is no longer a predator");
            }
        }, 10000); // delay of 10 sec
    }

}
