package com.example.PacMan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    public ThingWebSocketHandler getThingWebSocketHandler() {
        return webSocketHandler;
    }
    public void startWebSocketServer() {
        // Manually start Spring and pass your handler to the WebSocketConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(WebSocketConfig.class, 
            () -> new WebSocketConfig(webSocketHandler));
        context.refresh();
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
