package com.example.PacMan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.PacMan.Game.GameState;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GameController {

    private final Game game;

    @Autowired
    public GameController(Game game) {
        this.game = game; // Injected Game instance
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Map<String, String> getGameData(@RequestBody String direction) {
        if (game.currentState != GameState.ON) {
            game.currentState = GameState.ON;
        }

        game.board.updateDirection(direction.charAt(1));
        game.board.handlerun();

        return game.board.getData();
    }
}
