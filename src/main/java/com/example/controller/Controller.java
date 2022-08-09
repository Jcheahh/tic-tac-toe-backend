package com.example.controller;

import com.example.http.TurnRequest;
import com.example.http.TurnResponse;
import com.example.http.StateResponse;
import com.example.models.Player;
import com.example.services.GameStateService;
import com.example.services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@RestController
public class Controller {
    private final GameStateService gameStateService;
    private final PlayersService playersService;

    @Autowired
    public Controller(GameStateService gameStateService, PlayersService playersService) {
        this.gameStateService = gameStateService;
        this.playersService = playersService;
    }

    @GetMapping("/state")
    public StateResponse getState() {
        return new StateResponse(gameStateService.isGameOver(), gameStateService.board);
    }

    @PostMapping("/reset")
    public Map<String, String> resetState() {
        return gameStateService.resetBoard(gameStateService.board);
    }

    @PostMapping("/turn")
    public TurnResponse turn(@RequestBody @Valid TurnRequest request) {
        if (gameStateService.isGameOver()) {
            return new TurnResponse(findWinner(), gameStateService.board);
        } else {
            gameStateService.updateState(String.valueOf(request.getPosition()),
                    playersService.getPlayer(request.getPlayerId()));
            return new TurnResponse(findWinner(), gameStateService.board);
        }
    }

    private Player findWinner() {
        String winner = gameStateService.checkWinner();
        Player playerWinner;

        if (winner != null) {
            switch (winner) {
                case "X":
                case "O":
                    playerWinner = playersService.getPlayer(winner);
                    gameStateService.endGame(true);
                    break;
                case "Draw":
                    playerWinner = new Player("Draw", "No one wins");
                    gameStateService.endGame(true);
                    break;
                default:
                    playerWinner = null;
            }
            return playerWinner;
        }

        return null;

    }
}