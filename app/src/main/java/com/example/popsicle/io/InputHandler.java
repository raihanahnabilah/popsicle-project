package com.example.popsicle.io;

import com.example.popsicle.models.Position;

/**
 * InputHandler object is the object that gets the
 * input from the inputListener and execute the action.
 */
public class InputHandler implements InputListener.Callback{

    /**
     * The onClickAction to be executed
     */
    private ClickAction onClickAction;

    /**
     * The setOnClickAction method will execute the onClickAction
     * after the user touches the screen
     * @param onClickAction The clickAction, in this case, our MoveCharacterAction
     */
    public void setOnClickAction(ClickAction onClickAction) {
        this.onClickAction = onClickAction;
    }

    /**
     * The onClick method to execute the onClickAction when the user
     * touch the screen.
     * @param pos Position when touch click released.
     */
    @Override
    public void onClick(Position pos) {
        if (onClickAction != null) onClickAction.execute(pos);
    }

}
