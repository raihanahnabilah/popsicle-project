package com.example.popsicle.models;

public class Console {
    private static final String TAG = "Console";
    private ConsolePosition pos;

    public Console(float x, float y){
        this.pos = new ConsolePosition(x, y);
    }

    public ConsolePosition getPos() {
        return pos;
    }
}
