package com.example.popsicle;

import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Console;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;

import org.junit.Test;

/**
 * The TestConsole class to test all the methods in the Console class
 */
public class TestConsole {

    Console up = new Console("up");
    Console down = new Console("down");
    Console left = new Console("left");
    Console right = new Console("right");

    Constants constants = new Constants();
    int consoleWidth = constants.consoleWidth;
    int consoleHeight = constants.consoleHeight;

    Position upPos = new Position((constants.screenX*28/32)-consoleWidth,(constants.screenY *3)/4- consoleHeight);
    Position downPos = new Position((constants.screenX*28/32)-consoleWidth,(constants.screenY *3)/4 + consoleHeight);
    Position leftPos = new Position((constants.screenX*28/32)-(2*consoleWidth),(constants.screenY *3)/4);
    Position rightPos = new Position(constants.screenX*28/32,constants.screenY *3/4);

    /**
     * The method to test that the consoles are placed
     * in the correct position when the consoles are created
     */
    @Test
    public void CreateConsoleInCorrectPosition(){
        assert (up.getPos().getX() == upPos.getX());
        assert (up.getPos().getY() == upPos.getY());

        assert (down.getPos().getX() == downPos.getX());
        assert (down.getPos().getY() == downPos.getY());

        assert (left.getPos().getX() == leftPos.getX());
        assert (left.getPos().getY() == leftPos.getY());

        assert (right.getPos().getX() == rightPos.getX());
        assert (right.getPos().getY() == rightPos.getY());
    }

    /**
     * The method to test that the consoles have the
     * correct width and height when the consoles are created
     */
    @Test
    public void ConsoleWidthAndHeight(){
        assert (up.getHeight() == consoleHeight);
        assert (up.getWidth() == consoleWidth);

        assert (down.getHeight() == consoleHeight);
        assert (down.getWidth() == consoleWidth);

        assert (left.getHeight() == consoleHeight);
        assert (left.getWidth() == consoleWidth);

        assert (right.getHeight() == consoleHeight);
        assert (right.getWidth() == consoleWidth);
    }


}
