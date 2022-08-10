package com.example.http;

import java.util.Map;
import com.example.services.GameStateService.Choice;
import com.example.services.GameStateService.GameResult;;

public class StateResponse {

    public Boolean gameOver = false;
    public Map<String, Choice> board;
    public Choice player;
    public GameResult winner;

    public StateResponse(Boolean gameOver, Map<String, Choice> board, Choice player, GameResult winner) {
        this.board = board;
        this.gameOver = gameOver;
        this.player = player;
        this.winner = winner;

    }

}