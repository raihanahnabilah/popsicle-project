package com.example.popsicle.models;

public class Clouds {
    private static final String TAG = "Clouds";
    private CloudsPosition pos;

    public Clouds(float x, float y){
        this.pos = new CloudsPosition(x, y);
    }

    public CloudsPosition getPos() {
        return pos;
    }
}
