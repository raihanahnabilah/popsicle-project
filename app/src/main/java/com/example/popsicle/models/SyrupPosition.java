package com.example.popsicle.models;

public class SyrupPosition {
    private float x,y;
    public SyrupPosition(float v) {
        this.x = v;
        this.y = v;
    }
    public SyrupPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void add(SyrupPosition p) {
        this.x += p.x;
        this.y += p.y;
    }
    @Override
    public boolean equals(Object obj) {
        SyrupPosition p = (SyrupPosition) obj;
        if (p != null) return x == p.x && y == p.y;
        return false;
    }
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

}
