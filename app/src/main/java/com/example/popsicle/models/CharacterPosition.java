package com.example.popsicle.models;

public class CharacterPosition {

    private float x,y;

    public CharacterPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // TODO: Check with Prof. The charaacter's position will be updated when the "Add" function is called.
    public CharacterPosition add(CharacterPosition p){
        return new CharacterPosition(this.x + p.x, this.y + p.y);
    }

    @Override
    public String toString() {
        return "CharacterPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
