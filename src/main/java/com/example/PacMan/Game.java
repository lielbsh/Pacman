package com.example.PacMan;

public class Game {
    GameState currentState = GameState.OFF;
    Board board; // Reference to the board

    public enum GameState {
        ON, OFF, WIN, LOSS
    };

    public Game() {
        this.currentState = GameState.ON;
        this.board = new Board();
    }

    public void gameOver() {
        currentState = GameState.LOSS;
    }
}
