package com.example.popsicle.models;


public class Character {
    private static final String TAG = "Character";
    private CharacterPosition pos;

    public Character(float x, float y){
        this.pos = new CharacterPosition(x, y);
    }

    public void moveLeft() {
        this.pos.addLeft();
    }

    public void moveRight() {
        this.pos.addRight();
    }

    public void moveUp() {
        this.pos.addUp();
    }

    public void moveDown() {
        this.pos.addDown();
    }

    public CharacterPosition getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "Character{" +
                "pos=" + pos +
                '}';
    }
}
