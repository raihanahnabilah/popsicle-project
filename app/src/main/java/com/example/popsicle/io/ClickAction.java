package com.example.popsicle.io;

import com.example.popsicle.models.Position;

/**
 * A ClickAction corresponds to a single tap
 * In our case, it's a single tap on
 * the console button (up, down, left, right)
 * @author Hana
 */
public interface ClickAction extends Action{
    /**
     * Actions are built with a specific behavior.
     * The execute method here activates this behavior.
     * ClickAction needs the position of the tap.
     * @param a Position of the user tap in the screen.
     */
    void execute(Position a);
}
