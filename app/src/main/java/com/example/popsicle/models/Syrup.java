package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Syrup {
    private SyrupPosition pos;
    String clouds;

    // Add syrup
    // Every single clouds need to have a handle of the global state that is the syrup
    // Global object to update the universe -- to keep all of the elements updated
    // Direction inside the syrup object

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