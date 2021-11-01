package com.example.popsicle.io;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.popsicle.models.Position;


/**
 * The InputListener object can be add as a Listener for any View object.
 * It will wait for touch events (onTouch) and interpret them as click.
 * This simplifies the user actions. In order to get back these event,
 * one has to add a callback function using the setCallback method
 * in the Callback interface.
 *
 */
public class InputListener implements View.OnTouchListener {
    private final static String TAG = "InputController";

    /**
     * Callback to communicate between InputListener and inputHandler
     */
    private Callback callback;

    /**
     * The position of the user touch on the screen
     */
    private Position up_pos;

    /**
     * The onTouch method that will listen to the user touch
     * on the screen. There are two cases in which the user touches the screen.
     * ACTION_DOWN means we perform an action when the user still holds/touches
     * the screen. ACTION_UP means we perform an action when the user
     * lifted their finger from the screen after touching it. In our case,
     * we execute our action in case ACTION_UP since we only want the Characters
     * to move when the user touches and lifted their finger from screen.
     * @param v the view of the screen
     * @param event the user input on screen
     * @return Boolean true if a user touches the screen
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "Action Down");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "Action Up");
                up_pos = new Position(event.getX(), event.getY());
                callback.onClick(up_pos);

                break;
        }

        return true;
    }

    /**
     * The set callback function is for the inputListener to
     * pass on the information/the user input click to the inputHandler.
     * InputHandler will then later execute the action.
     * @param cb the inputHandler
     */
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
        void onClick ( Position pos ) ;
    }
}
