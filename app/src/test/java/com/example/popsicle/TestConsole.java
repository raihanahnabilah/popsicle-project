package com.example.popsicle;

import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Console;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;

import org.junit.Test;

public class TestConsole {

    //TODO: Finish the testings.

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


    @Test
    public void CreateConsoleInCorrectPosition(){
        assert (up.getPos().getX() == upPos.getX());
        assert (up.getPos().getY() == upPos.getY());

        assert (down.getPos().getX() == downPos.getX());
        assert (down.getPos().getY() == downPos.getY());

        //TODO: Continue testing for left and right.
    }

    @Test
    public void ConsoleWidthAndHeight(){
        //TODO: Testing the Console' width and height
    }


}
