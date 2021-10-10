package com.example.popsicle.models;

import android.util.Log;


import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Universe {
    List<CharacterA> dataA; // TODO: Is this the correct type?
    List<CharacterB> dataB;
    // TODO: Implement how the character moves.
    // In the Ball lab, the motion is essentially the gravity. But I want it to move following the
    // users movement.
    CharacterMotion userInput;

    public static final String TAG = "Universe";

    public Universe(){
        this (new CharacterMotion(0, 5f));
    }

    public Universe(CharacterMotion x){
        dataA = new Vector<>();
        dataB = new Vector<>();
        userInput = x;
    }

    public void addCharacterA(float x, float y){
        dataA.add(new CharacterA (x,y));
    }

    public void addCharacterA(CharacterPosition pos){
        this.addCharacterA(pos.getX(), pos.getY());
    }

    public Collection<CharacterA> getCharacterA() {
        return dataA;
    }

    public Collection<CharacterB> getCharacterB() {
        return dataB;
    }

    // TODO: Implement a function to move the characters


    /**
     * Callback when the universe changes.
     * These functions here are sort-of a given.
     * So don't touch this section below, please.
     */

    public interface Callback {
        void universeChanged ( Universe u ) ;
    }

    public void setCallBack(Callback c) {
        callback = c;
    }

    public void addCallBack (Callback c ) {
        this.callback = c;
    }

    protected void castChanges() {
        if (callback != null) {
            callback.universeChanged(this);
        } else {
            Log.w(TAG, "Callback is not available.");
        }
    }

    private Callback callback = null;

}
