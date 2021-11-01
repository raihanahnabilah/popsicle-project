package com.example.popsicle;

import android.graphics.Rect;

import com.example.popsicle.models.Candy;
import com.example.popsicle.models.Clouds;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;

import org.junit.Test;

/**
 * The TestCandy class to test all the methods in the Candy class
 */
public class TestCandy {

    Candy a = new Candy("a");
    Candy b = new Candy("b");

    Constants constants = new Constants();
    Position candyAPos = new Position(constants.screenX/16,constants.screenY*45/100);
    Position candyBPos = new Position(constants.screenX*14/16,constants.screenY*45/100);

    int candyWidth = constants.popsicleWidth;
    int candyHeight = constants.popsicleHeight;

    /**
     * The method to test that the popsicles are placed
     * in the correct position when the popsicles are created
     */
    @Test
    public void CreatePopsiclesInCorrectPosition(){
        assert (a.getPos().getX() == candyAPos.getX());
        assert (a.getPos().getY() == candyAPos.getY());

        assert (b.getPos().getX() == candyBPos.getX());
        assert (b.getPos().getY() == candyBPos.getY());
    }

    /**
     * The method to test that the popsicles have the
     * correct Rectangle bounds when popsicles are created
     */
    @Test
    public void PopsiclesCollisionShape(){
          int a_left = (int) candyAPos.getX()*33/32;
          int a_top = (int) candyAPos.getY()*33/32;
          int a_right = (int) (candyAPos.getX() + candyWidth)*31/32;
          int a_bottom = (int) (candyAPos.getY() + candyHeight)*61/64;
          Rect rec_a = new Rect(a_left, a_top, a_right, a_bottom);
          assert (rec_a.left == a.getCollisionShape().left);
          assert (rec_a.right == a.getCollisionShape().right);
          assert (rec_a.bottom == a.getCollisionShape().bottom);
          assert (rec_a.top == a.getCollisionShape().top);

          int b_left = (int) candyBPos.getX()*33/32;
          int b_top = (int) candyBPos.getY()*33/32;
          int b_right = (int) (candyBPos.getX() + candyWidth)*31/32;
          int b_bottom = (int) (candyBPos.getY() + candyHeight)*61/64;
          Rect rec_b = new Rect(b_left, b_top, b_right, b_bottom);
          assert (rec_b.left == b.getCollisionShape().left);
          assert (rec_b.right == b.getCollisionShape().right);
          assert (rec_b.bottom == b.getCollisionShape().bottom);
          assert (rec_b.top == b.getCollisionShape().top);
    }

    /**
     * The method to test that the popsicles have the
     * correct width and height when the popsicles are created
     */
    @Test
    public void PopsiclesWidthAndHeight(){
        assert (a.getHeight() == candyHeight);
        assert (a.getWidth() == candyWidth);

        assert (b.getHeight() == candyHeight);
        assert (b.getWidth() == candyWidth);
    }

}
