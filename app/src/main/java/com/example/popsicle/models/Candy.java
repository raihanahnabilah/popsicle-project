package com.example.popsicle.models;

public class Candy {
    private static final String TAG = "Candies";
    private CandyPosition pos;

    public Candy(float x, float y){
        this.pos = new CandyPosition(x, y);
    }

    public CandyPosition getPos() {
        return pos;
    }
}
