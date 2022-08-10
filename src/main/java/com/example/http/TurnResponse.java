package com.example.http;

import com.example.services.GameStateService.Choice;
import com.example.services.GameStateService.GameResult;

import java.util.Map;

public class TurnResponse {
    public Boolean gameOver = false;
    public GameResult winner;
    public Map<String, Choice> state;

    public TurnResponse(GameResult winner, Map<String, Choice> state) {
        if (winner != null) {
            this.winner = winner;
            this.gameOver = true;
        }
        this.state = state;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setState(Map<String, Choice> state) {
        this.state = state;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public Map<String, Choice> getState() {
        return state;
    }

}