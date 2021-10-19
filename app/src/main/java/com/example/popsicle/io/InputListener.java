package com.example.popsicle.io;

import android.view.MotionEvent;
import android.view.View;

import com.example.popsicle.models.CharacterPosition;
import com.example.popsicle.models.Universe;


/**
 * The InputListener object can be add as a Listener for any View object.
 * It will wait for touch events and interpret them as
 *  - Click
 * This simplifies the user actions.
 *
 * In order to get back these event, one has to add a callback function using the addCallback
 *
 */
public class InputListener implements View.OnTouchListener {
    private final static String TAG = "InputController";
    private Callback callback;
    private CharacterPosition up_pos;
    private Universe universe;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // TODO: When the user touch position is at a certain position
                //       i.e. the buttons, clouds, or the popsicle
                //       you want it to move/do something at a certain direction
                up_pos = new CharacterPosition(event.getX(), event.getY());
                callback.onClick(up_pos);
                break;
        }

        return true;
    }

    public void setCallback(InputHandler cb) {
        this.callback = cb;
    }


    /**
     * This is the interface a CallBack object has to follow
     * in order to be a valid CallBack for the InputListener.
     */
    public interface Callback {

        /**
         * triggered when a simple touch click occurs (no movements).
         * @param pos Position when touch click released.
         */
        void onClick ( CharacterPosition pos ) ;
    }
}
