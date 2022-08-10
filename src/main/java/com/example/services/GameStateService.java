package com.example.services;

import com.example.exceptions.InvalidChoiceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameStateService {

    public enum Choice {
        X,
        O
    }

    public enum GameResult {
        X,
        O,
        Draw
    }

    public Boolean gameOver;
    public Map<String, Choice> board;
    public Choice nextPlayer;

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
        this.nextPlayer = Choice.X;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void endGame(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void updateState(String position) {
        if (board.get(position) != null) {
            throw new InvalidChoiceException("The position has already been taken");
        }
        board.put(position, this.nextPlayer);
        changeNextPlayer();
    }

    public void changeNextPlayer() {

        if (this.nextPlayer.equals(Choice.X)) {
            this.nextPlayer = Choice.O;
        } else if (this.nextPlayer.equals(Choice.O)) {
            this.nextPlayer = Choice.X;
        }
    }

    public Map<String, Choice> resetBoard(Map<String, Choice> board) {
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
        this.nextPlayer = Choice.X;
        return board;
    };

    private GameResult choiceToGameResult(Choice choice) {
        switch (choice) {
            case X:
                return GameResult.X;

            case O:
                return GameResult.O;

            default:
                throw new Error(":(");
        }
    }

    public GameResult checkWinner() {
        if (((board.get("0") == board.get("1")) && (board.get("1") == board.get("2"))
                || (board.get("0") == board.get("3")) && (board.get("3") == board.get("6"))
                || (board.get("0") == board.get("4")) && (board.get("4") == board.get("8")))
                && board.get("0") != null) {
            return choiceToGameResult(board.get("0"));
        } else if (((board.get("3") == board.get("4")) && (board.get("4") == board.get("5"))
                || (board.get("1") == board.get("4")) && (board.get("4") == board.get("7"))
                || (board.get("2") == board.get("4")) && (board.get("4") == board.get("6")))
                && board.get("4") != null) {
            return choiceToGameResult(board.get("4"));
        } else if (((board.get("2") == board.get("5")) && (board.get("5") == board.get("8"))
                || (board.get("6") == board.get("7")) && (board.get("7") == board.get("8")))
                && board.get("8") != null) {
            return choiceToGameResult(board.get("8"));
        }

        if (board.values().stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return GameResult.Draw;
        }

        return null;
    }
}