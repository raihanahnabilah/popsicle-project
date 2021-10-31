package com.example.popsicle;

import android.graphics.Rect;

import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;

import org.junit.Test;

public class TestSyrup {

    Syrup s1 = new Syrup("a1");
    Syrup s2 = new Syrup("a2");
    Syrup s3 = new Syrup("b1");
    Syrup s4 = new Syrup("b2");

    Constants constants = new Constants();
    int syrupWidth = constants.syrupWidth;
    int syrupHeight = constants.syrupHeight;

    @Test
    public void CreateSyrup(){
        s1.setPos(new Position(0,1));
        Position pos1 = new Position(0,1);
        assert (s1.getPos().getX() == pos1.getX());
        assert (s1.getPos().getY() == pos1.getY());

        s2.setPos(new Position(200,100));
        Position pos2 = new Position(200,100);
        assert (s2.getPos().getX() == pos2.getX());
        assert (s2.getPos().getY() == pos2.getY());

        s3.setPos(new Position(400,500));
        Position pos3 = new Position(400,500);
        assert (s3.getPos().getX() == pos3.getX());
        assert (s3.getPos().getY() == pos3.getY());

        s4.setPos(new Position(600,40));
        Position pos4 = new Position(600,40);
        assert (s4.getPos().getX() == pos4.getX());
        assert (s4.getPos().getY() == pos4.getY());
    }

    @Test
    public void GetMoveXMoveYSyrups(){
        assert (s1.getMovex() == Constants.syrupMovementPixelsX);
        assert ((s1.getMovey() == Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)));
        assert (s2.getMovex() == Constants.syrupMovementPixelsX);
        assert ((s2.getMovey() == -Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)));
        assert (s3.getMovex() == -Constants.syrupMovementPixelsX);
        assert ((s3.getMovey() == Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)));
        assert (s4.getMovex() == -Constants.syrupMovementPixelsX);
        assert ((s4.getMovey() == -Constants.syrupMovementPixelsX*(Constants.screenX/Constants.screenY)));
    }

    @Test
    public void MoveSyrups(){
        s1.setPos(new Position(0,1));
        s1.syrupMove(new Position(10,20));
        Position pos1 = new Position(0,1);
        pos1.add(new Position(10,20));
        assert (s1.getPos().getX() == pos1.getX());
        assert (s1.getPos().getY() == pos1.getY());

        s2.setPos(new Position(100,200));
        s2.syrupMove(new Position(-200,20));
        Position pos2 = new Position(100,200);
        pos2.add(new Position(-200,20));
        assert (s2.getPos().getX() == pos2.getX());
        assert (s2.getPos().getY() == pos2.getY());

        s3.setPos(new Position(400,500));
        s3.syrupMove(new Position(-30,-49));
        Position pos3 = new Position(400,500);
        pos3.add(new Position(-30,-49));
        assert (s3.getPos().getX() == pos3.getX());
        assert (s3.getPos().getY() == pos3.getY());

        s4.setPos(new Position(600,40));
        s4.syrupMove(new Position(-500, 1000));
        Position pos4 = new Position(600,40);
        pos4.add(new Position(-500, 1000));
        assert (s4.getPos().getX() == pos4.getX());
        assert (s4.getPos().getY() == pos4.getY());
    }

    @Test
    public void SyrupsCollisionShape(){
        s1.setPos(new Position(10,1));
        int s1_left = (int) s1.getPos().getX()*33/32;
        int s1_top = (int) s1.getPos().getY()*33/32;
        int s1_right = (int) (s1.getPos().getX() + syrupWidth)*31/32;
        int s1_bottom = (int) (s1.getPos().getY() + syrupHeight)*61/64;
        Rect rec1 = new Rect(s1_left, s1_top, s1_right, s1_bottom);
        assert (rec1.left == s1.getCollisionShape().left);
        assert (rec1.right == s1.getCollisionShape().right);
        assert (rec1.bottom == s1.getCollisionShape().bottom);
        assert (rec1.top == s1.getCollisionShape().top);

        s2.setPos(new Position(100,200));
        int s2_left = (int) s2.getPos().getX()*33/32;
        int s2_top = (int) s2.getPos().getY()*33/32;
        int s2_right = (int) (s2.getPos().getX() + syrupWidth)*31/32;
        int s2_bottom = (int) (s2.getPos().getY() + syrupHeight)*61/64;
        Rect rec2 = new Rect(s2_left, s2_top, s2_right, s2_bottom);
        assert (rec2.left == s2.getCollisionShape().left);
        assert (rec2.right == s2.getCollisionShape().right);
        assert (rec2.bottom == s2.getCollisionShape().bottom);
        assert (rec2.top == s2.getCollisionShape().top);

        s3.setPos(new Position(400,500));
        int s3_left = (int) s3.getPos().getX()*33/32;
        int s3_top = (int) s3.getPos().getY()*33/32;
        int s3_right = (int) (s3.getPos().getX() + syrupWidth)*31/32;
        int s3_bottom = (int) (s3.getPos().getY() + syrupHeight)*61/64;
        Rect rec3 = new Rect(s2_left, s2_top, s2_right, s2_bottom);
        assert (rec3.left == s3.getCollisionShape().left);
        assert (rec3.right == s3.getCollisionShape().right);
        assert (rec3.bottom == s3.getCollisionShape().bottom);
        assert (rec3.top == s3.getCollisionShape().top);

        s4.setPos(new Position(600,40));
        int s4_left = (int) s4.getPos().getX()*33/32;
        int s4_top = (int) s4.getPos().getY()*33/32;
        int s4_right = (int) (s4.getPos().getX() + syrupWidth)*31/32;
        int s4_bottom = (int) (s4.getPos().getY() + syrupHeight)*61/64;
        Rect rec4 = new Rect(s2_left, s2_top, s2_right, s2_bottom);
        assert (rec4.left == s4.getCollisionShape().left);
        assert (rec4.right == s4.getCollisionShape().right);
        assert (rec4.bottom == s4.getCollisionShape().bottom);
        assert (rec4.top == s4.getCollisionShape().top);
    }
}
