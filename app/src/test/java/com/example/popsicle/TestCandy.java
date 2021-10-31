package com.example.popsicle;

import com.example.popsicle.models.Candy;
import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;

import org.junit.Test;

public class TestCandy {

    //TODO: Finish the remaining testings.

    Candy a = new Candy("a");
    Candy b = new Candy("b");

    Constants constants = new Constants();
    Position candyAPos = new Position(constants.screenX/16,constants.screenY*45/100);
    Position candyBPos = new Position(constants.screenX*14/16,constants.screenY*45/100);

    int candyWidth = constants.popsicleWidth;
    int candyHeight = constants.popsicleHeight;

    @Test
    public void CreatePopsiclesInCorrectPosition(){
        assert (a.getPos().getX() == candyAPos.getX());
        assert (a.getPos().getY() == candyAPos.getY());

        assert (b.getPos().getX() == candyBPos.getX());
        assert (b.getPos().getY() == candyBPos.getY());
    }

    @Test
    public void PopsiclesCollisionShape(){
        //TODO: Testing the Popsicles' collision shape
    }

    @Test
    public void PopsiclesWidthAndHeight(){
        //TODO: Testing the Popsicles' width and height
    }

}
