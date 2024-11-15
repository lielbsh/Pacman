package com.example.PacMan;

public class Game {
    int lifeNum = 3;
    int score = 0;
    GameState currentState = GameState.OFF;

    public enum GameState {
        ON, OFF, WIN, LOSS
    };

    public Game() {
        currentState = GameState.ON;
        score = 0;
        lifeNum = 3;
        new Board();
    }

    public void GameOver() {
        currentState = GameState.LOSS;
    }
}
