package com.example.PacMan;

import org.springframework.stereotype.Component;

@Component
public class Game {
    public enum GameState {
        ON, OFF, WIN, LOSS
    };

    GameState currentState = GameState.OFF;
    public Board board; // Reference to the board

    // The WebSocket handler

    public Game() {
        this.currentState = GameState.ON;
        // Initialize the Board and pass the WebSocket handler to it
        this.board = new Board();
    }

    public Board getBoard() {
        return (board);
    }

    public void updateDirection(char direction) {
        board.updateDirection(direction);
    }

    public void gameOver() {
        currentState = GameState.LOSS;
    }
}
