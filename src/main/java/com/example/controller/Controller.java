package com.example.controller;

import com.example.http.TurnRequest;
import com.example.http.TurnResponse;
import com.example.http.StateResponse;
import com.example.services.GameStateService;
import com.example.services.GameStateService.Choice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@RestController
public class Controller {
    private final GameStateService gameStateService;

    @Autowired
    public Controller(GameStateService gameStateService) {
        this.gameStateService = gameStateService;
    }

    @GetMapping("/state")
    public StateResponse getState() {
        return new StateResponse(gameStateService.isGameOver(),
                gameStateService.board,
                gameStateService.nextPlayer, findWinner());
    }

    @PostMapping("/reset")
    public Map<String, Choice> resetState() {
        return gameStateService.resetBoard(gameStateService.board);
    }

    @PostMapping("/turn")
    public TurnResponse turn(@RequestBody @Valid TurnRequest request) {
        if (gameStateService.isGameOver()) {
            return new TurnResponse(findWinner(), gameStateService.board);
        } else {
            gameStateService.updateState(String.valueOf(request.getPosition()));
            return new TurnResponse(findWinner(), gameStateService.board);
        }
    }

    private GameStateService.GameResult findWinner() {
        GameStateService.GameResult winner = gameStateService.checkWinner();

        if (winner != null) {
            gameStateService.endGame(true);
        }

        return winner;

    }
}