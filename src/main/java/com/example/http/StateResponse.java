package com.example.http;

import java.util.Map;

public class StateResponse {

    public Boolean gameOver = false;
    public Map<String, String> board;

    public StateResponse(Boolean gameOver, Map<String, String> board) {
        this.board = board;
        this.gameOver = gameOver;
    }

}