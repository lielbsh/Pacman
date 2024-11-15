package com.example.PacMan;

public class Game {
    ;
    GameState currentState = GameState.OFF;

    public enum GameState {
        ON, OFF, WIN, LOSS
    };

    public Game() {
        currentState = GameState.ON;
        new Board();
    }

    public void GameOver() {
        currentState = GameState.LOSS;
    }
}
