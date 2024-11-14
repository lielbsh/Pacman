package com;

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
    }

    public void GameOver() {
        currentState = GameState.LOSS;
    }
}
