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
        this.y = this.y;
    }

    public CharacterPosition addRight(){
        return new CharacterPosition(this.x + 5, this.y);
    }

    public CharacterPosition addUp(){
        return new CharacterPosition(this.x, this.y + 5);
    }

    public CharacterPosition addDown(){
        return new CharacterPosition(this.x, this.y - 5);
    }

    @Override
    public String toString() {
        return "CharacterPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
