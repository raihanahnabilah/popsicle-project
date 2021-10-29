package com.example.popsicle.io;

import com.example.popsicle.models.Position;

/**
 * A ClickAction corresponds to a single tap
 * In our case: It's a single tap on:
 * (a) The console button (up, down, left, right)
 * (b) The clouds, to generate the syrup
 * (c) The popsicle, to show that a character has won!
 */
public interface ClickAction extends Action{
    /**
     * Actions are built with a specific behavior
     * The execute activate this behavior click actions
     * need the position of the tap.
     * If the position of the tap is:
     * (1) at the console button, it will make the characters move
     * (2) at the clouds, it will generate the syrup
     * (3) at the popsicle, it will make a character wins!
     * @param a Position of the tap in the screen.
     */
    void execute(Position a);
}
