package com.example.services;

import com.example.exceptions.InvalidChoiceException;
import com.example.models.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameStateService {

    public Boolean gameOver;
    public Map<String, String> board;

    public GameStateService() {
        this.board = new HashMap<>();
        this.board.put("0", null);
        this.board.put("1", null);
        this.board.put("2", null);
        this.board.put("3", null);
        this.board.put("4", null);
        this.board.put("5", null);
        this.board.put("6", null);
        this.board.put("7", null);
        this.board.put("8", null);
        this.gameOver = false;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void endGame(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void updateState(String position, Player player) {
        if (board.get(position) != null) {
            throw new InvalidChoiceException("The position has already been taken");
        }
        board.put(position, player.getId());
    }

    public Map<String, String> resetBoard(Map<String, String> board) {
        board.clear();
        board.put("0", null);
        board.put("1", null);
        board.put("2", null);
        board.put("3", null);
        board.put("4", null);
        board.put("5", null);
        board.put("6", null);
        board.put("7", null);
        board.put("8", null);
        this.gameOver = false;
        return board;
    };

    public String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board.get("0") + board.get("1") + board.get("2");
                    break;
                case 1:
                    line = board.get("3") + board.get("4") + board.get("5");
                    break;
                case 2:
                    line = board.get("6") + board.get("7") + board.get("8");
                    break;
                case 3:
                    line = board.get("0") + board.get("3") + board.get("6");
                    break;
                case 4:
                    line = board.get("1") + board.get("4") + board.get("7");
                    break;
                case 5:
                    line = board.get("2") + board.get("5") + board.get("8");
                    break;
                case 6:
                    line = board.get("0") + board.get("4") + board.get("8");
                    break;
                case 7:
                    line = board.get("2") + board.get("4") + board.get("6");
                    break;
            }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        if (board.values().stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return "Draw";
        }

        return null;
    }
}