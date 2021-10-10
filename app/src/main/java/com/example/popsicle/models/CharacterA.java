package com.example.popsicle.models;

import android.graphics.Picture;

public class CharacterA {
    private static final String TAG = "CharacterA";
    private Picture pict;
    private CharacterPosition pos;

    public CharacterA(float x, float y){
        this.pos = new CharacterPosition(x, y);
    }

    // TODO: Add a function for when a character moves!

    public Picture getPict() {
        return pict;
    }

    public CharacterPosition getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "CharacterA{" +
                "pict=" + pict +
                ", pos=" + pos +
                '}';
    }
}
