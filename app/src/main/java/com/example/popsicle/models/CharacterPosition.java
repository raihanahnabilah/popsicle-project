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

    public void addLeft(){
        this.x = this.x - 5;
    }

    public void addRight(){
        this.x = this.x + 5;
    }

    public void addUp(){
        this.y = this.y + 5;
    }

    public void addDown(){
        this.y = this.y - 5;
    }

    @Override
    public String toString() {
        return "CharacterPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
