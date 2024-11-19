package com.example.PacMan;

import com.example.PacMan.Game.GameState;

public class Game {
    private final ThingWebSocketHandler webSocketHandler;
    public enum GameState {
        ON, OFF, WIN, LOSS
    };
    GameState currentState = GameState.OFF;
    Board board; // Reference to the board

      // The WebSocket handler

    public Game() {
        
        this.currentState = GameState.ON;
        // Initialize the WebSocket handler
        this.webSocketHandler = new ThingWebSocketHandler(this);

        // Initialize the Board and pass the WebSocket handler to it
        this.board = new Board(webSocketHandler);

    }

    
    public Board getBoard(){
        return(board);
    }
    public void updateDirection(char direction){
        board.updateDirection(direction);
    }
    public void gameOver() {
        currentState = GameState.LOSS;
    }
}
