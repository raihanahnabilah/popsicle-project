package com.example.popsicle;

import com.example.popsicle.models.Character;
import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;

import org.junit.Test;

/**
 * The TestClouds class to test all the methods in the Clouds class
 */
public class TestClouds {

    Clouds a1 = new Clouds("a1");
    Clouds a2 = new Clouds("a2");
    Clouds b1 = new Clouds("b1");
    Clouds b2 = new Clouds("b2");

    Constants constants = new Constants();
    Position cloudA1Pos = new Position(constants.screenX/4,constants.screenY/64);
    Position cloudA2Pos = new Position(constants.screenX/8,constants.screenY*7/10);
    Position cloudB1Pos = new Position(constants.screenX*3/4,constants.screenY/64);
    Position cloudB2Pos = new Position(constants.screenX/2,constants.screenY*3/4);

    int cloudWidth = constants.cloudWidth;
    int cloudHeight = constants.cloudHeight;

    /**
     * The method to test that the clouds are placed
     * in the correct position when the clouds are created
     */
    @Test
    public void CreateCloudsInCorrectPosition(){
        assert (a1.getPos().getX() == cloudA1Pos.getX());
        assert (a2.getPos().getY() == cloudA2Pos.getY());

        assert (b1.getPos().getX() == cloudB1Pos.getX());
        assert (b2.getPos().getY() == cloudB2Pos.getY());
    }

    /**
     * The method to test that the clouds have the
     * correct width and height when the clouds are created
     */
    @Test
    public void CloudsWidthAndHeight(){
        assert (b1.getHeight() == cloudHeight);
        assert (b1.getWidth() == cloudWidth);

        assert (b2.getHeight() == cloudHeight);
        assert (b2.getWidth() == cloudWidth);

        assert (a1.getHeight() == cloudHeight);
        assert (a1.getWidth() == cloudWidth);

        assert (a2.getHeight() == cloudHeight);
        assert (a2.getWidth() == cloudWidth);
    }

}
