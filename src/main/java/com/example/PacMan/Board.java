package com.example.PacMan;

import java.util.Timer;

public class Board {
    int lifeNum = 3;
    int score = 0;
    char nextMove = 'R';
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
    Ghost ghost1 = new Ghost();
    Ghost ghost2 = new Ghost();
    Ghost ghost3 = new Ghost();
    Pacman pacman = new Pacman();

    // public Board() {
    // this.score = 0;
    // this.lifeNum = 3;
    // }

}
