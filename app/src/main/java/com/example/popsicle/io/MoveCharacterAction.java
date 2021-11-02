package com.example.popsicle.io;

import android.util.Log;

import com.example.popsicle.HomePage;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Universe;
import com.example.popsicle.models.whichPlayer;

/**
 * The MoveCharacterAction class implements the ClickAction class.
 * The execute method here activates a specific behavior that Action is built on,
 * and that is to move the characters by clicking the position of the Console
 * on screen.
 */
public class MoveCharacterAction implements ClickAction {
    private static final String TAG = "MoveCharacterAction";

    /**
     * The Universe that contains all our models/elements.
     */
    private Universe universe;
    private HomePage homepage;

    /**
     * MoveCharacterAction constructor takes the Universe
     * to execute the action done by the user touch
     * on the screen
     * @param universe The universe of the game that has all models and elements
     */
    public MoveCharacterAction(Universe universe){
        this.universe = universe;
    }

    /**
     * The execute method is to execute the user input on the
     * screen. In this case, when the user touches the screen
     * on the location of the Console button (up, down, left, or right),
     * it will execute the CharacterMove method in the Universe,
     * and will make the characters move in the corresponding
     * direction.
     * @param pos The position of the user's touch on the screen
     */
    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveCharacterAction executed");
        //TODO: if userid is player A condition:
        if (whichPlayer.amIPlayerA){
            universe.CharacterMove(pos, universe.getCharacterA());
        }
        //TODO: if userid is player B condition:
        if (whichPlayer.amIPlayerB){
            universe.CharacterMove(pos, universe.getCharacterB());
        }
    }
}
