package com.example.PacMan;

import java.util.Timer;
import java.util.TimerTask;

public class Board {
    int lifeNum = 3;
    int score = 0;
    char pacmanMove = 'R';
    int[][] boardArray = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2, 1, 2 },
            { 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2 },
            { 1, 1, 2, 1, 1, 0, 0, 0, 1, 1, 2, 1, 1 },
            { 0, 0, 2, 0, 0, 0, 16, 0, 0, 0, 2, 0, 0 },
            { 1, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 1 },
            { 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2 },
            { 1, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 1 },
            { 1, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1 },
            { 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 }
    };
    Timer timer;
    long timeInterval = 100;
    Ghost ghost1 = new Ghost();
    Ghost ghost2 = new Ghost();
    Ghost ghost3 = new Ghost();
    Pacman pacman = new Pacman();

    public Board() {
        this.score = 0;
        this.lifeNum = 3;
        timer.scheduleAtFixedRate(updateBoard, 0, timeInterval);
    }

    TimerTask updateBoard = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Updating the board...");
            // Add logic to update game board
            // pacman.nextStep
        }
    };

    private static char randomDirection(int[] location) {
        // random direction logic
        return 'L';
    }

}
