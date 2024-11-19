package com.example.PacMan;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ThingWebSocketHandler extends TextWebSocketHandler {

    private final Game game;  // Game instance
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>(); // Thread-safe session collection

    public ThingWebSocketHandler(Game game) {
        this.game = game;  // Inject the Game instance
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // Add the new session to the list
        System.out.println("Connection established: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String directionmess = message.getPayload(); // Get the direction message (String)
        
        if (directionmess != null && !directionmess.isEmpty()) {
            char direction = directionmess.charAt(0);  // Extract the direction (first character)

            // Get the single board instance from the game
            Board board = game.getBoard();

            // Update the board's state with the new direction
            board.updateDirection(direction);
        } else {
            System.out.println("Received empty or invalid direction message.");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session); // Remove the session when closed
        System.out.println("Connection closed: " + session.getId());
    }


        /**
     * Function to send a Map as JSON to all connected clients.
     * @param dataMap The Map containing the data to send to the frontend.
     */
    public void sendDataToClients(Map<String, String> data) {
        try {
            // Create a JSONObject from the Map
            JSONObject jsonObject = new JSONObject(data);

            // Convert the JSONObject to a string
            String jsonData = jsonObject.toString();

            // Send the JSON string to each open session
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(jsonData));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    




}