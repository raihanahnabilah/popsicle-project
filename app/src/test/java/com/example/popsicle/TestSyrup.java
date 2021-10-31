package com.example.popsicle;

import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;

import org.junit.Test;

public class TestSyrup {

    Syrup a1 = new Syrup("a1");
    Syrup a2 = new Syrup("a2");
    Syrup b1 = new Syrup("b1");
    Syrup b2 = new Syrup("b2");

    Constants constants = new Constants();
    int syrupWidth = constants.syrupWidth;
    int syrupHeight = constants.syrupHeight;

    @Test
    public void CreateSyrup(){
//        Universe u = new Universe();
        System.out.println("PRINTING SCREENX FROM CLASS");
//        System.out.println(Constants.screenX);
//
        Syrup s1 = new Syrup("a1");
//        System.out.println("SYRUP CREATED!!!!");
        s1.setPos(new Position(0,1));
        System.out.println(s1.getPos().toString());
        assert (s1.getPos().getX() == 0);
        assert (s1.getPos().getY() == 1);
//        assert (s1.getMovex() == Constants.syrupMovementPixelsX);
//        assert ((s1.getMovex() == Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)) || (s1.getMovex() == -Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)) );

//        S/ystem.out.println();
        //        moveX =  Constants.syrupMovementPixelsX;
//        moveY = -Constants.syrupMovementPixelsX*(screenX/screenY);
//        d1.move(new SyrupMotion(1,-1));
//        SyrupPosition p1 = new SyrupPosition(4,2);
//        System.out.println(d1.getPosition());
//        System.out.println(p1);
//        assert (d1.getPosition().getX() == p1.getX());
//        assert (d1.getPosition().getY() == p1.getY());
//        org.junit.Assert.assertEquals(p1.toString(), d1.getPosition().toString());
    }

    @Test
    public void GetMoveXMoveYSyrups(){
        //TODO: Testing the getMoveX and getMoveY functionality

    }


    @Test
    public void MoveSyrups(){
        //TODO: Testing the SyrupMove functionality
    }

    @Test
    public void SyrupsCollisionShape(){
        //TODO: Testing the Syrups collision functionality
    }





}
