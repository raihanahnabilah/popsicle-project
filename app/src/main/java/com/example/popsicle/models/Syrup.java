package com.example.popsicle.models;

public class Syrup {
    private SyrupPosition pos;

    public Syrup(float x, float y) {
        this.pos = new SyrupPosition(x,y);
    }
    public void move(SyrupMotion m) {
        this.pos.add(m);
    }
    public SyrupPosition getPosition() {
        return this.pos;
    }
}