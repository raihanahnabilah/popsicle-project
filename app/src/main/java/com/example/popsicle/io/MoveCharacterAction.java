package com.example.popsicle.io;

import android.util.Log;

import com.example.popsicle.models.Position;
import com.example.popsicle.models.Universe;

public class MoveCharacterAction implements ClickAction {
    private static final String TAG = "MoveCharacterAction";

    private Universe universe;

    public MoveCharacterAction(Universe universe){
        this.universe = universe;
    }

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveCharacterAction executed");
        universe.CharacterMove(pos, universe.getCharacterA());
        // universe.CharacterMove(pos, universe.getCharacterB());

    }
}
