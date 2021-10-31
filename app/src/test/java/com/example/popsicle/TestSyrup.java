package com.example.popsicle;

import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;

import org.junit.Test;

public class TestSyrup {
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



}
