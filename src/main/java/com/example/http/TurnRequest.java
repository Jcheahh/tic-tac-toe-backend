package com.example.http;

import javax.validation.constraints.*;

public class TurnRequest {

    @Max(8)
    @Min(0)
    @NotNull
    private Integer position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}