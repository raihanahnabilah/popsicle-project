package com.example.popsicle.io;

import android.util.Log;

import com.example.popsicle.models.CharacterPosition;
import com.example.popsicle.models.Universe;

public class MoveCharacterAction implements ClickAction {
    private static final String TAG = "MoveCharacterAction";

    private Universe universe;

    public MoveCharacterAction(Universe universe){
        this.universe = universe;
    }

    @Override
    public void execute(CharacterPosition pos) {
        Log.i(TAG, "MoveCharacterAction executed");
        universe.CharacterMove(pos, universe.getCharacterA());

//        universe.SyrupMove(pos);
//        System.out.println(universe.getCloudsA1().getPos().getX() - 100);
//        System.out.println(pos);
    }
}
