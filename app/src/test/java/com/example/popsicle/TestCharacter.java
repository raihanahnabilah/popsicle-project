package com.example.popsicle;

import android.graphics.Rect;

import com.example.popsicle.models.Character;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TestCharacter {

    Character a = new Character("a");
    Character b = new Character("b");
    Constants constants = new Constants();
    Position aPos = new Position(constants.screenX/8,constants.screenY*45/100);
    Position bPos = new Position(constants.screenX*13/16,constants.screenY*45/100);
    int charWidth = constants.charWidth;
    int charHeight = constants.charHeight;
    int characterALives = constants.charALives;
    int characterBLives = constants.charBLives;
    Queue<Integer> queueX = new LinkedList<>();
    Queue<Integer> queueY = new LinkedList<>();

    //TODO: SAMPLE ADDING QUEUE AND REMOVING FROM QUEUE
    @Test
    public void CharacterPositionQueue(){
        System.out.println(b.getPos());
        queueX.add(4);
        queueX.add(8);
        queueY.add(5);
        queueY.add(10);
        b.setPos(new Position(queueX.remove(), queueY.remove()));
        System.out.println(b.getPos());
        b.setPos(new Position(queueX.remove(), queueY.remove()));
        System.out.println(b.getPos());
    }

    @Test
    public void CreateCharactersInCorrectPosition(){
        assert (a.getPos().getX() == aPos.getX());
        assert (a.getPos().getY() == aPos.getY());

        assert (b.getPos().getX() == bPos.getX());
        assert (b.getPos().getY() == bPos.getY());
    }

    @Test
    public void CharactersMoveLeft(){
        a.moveLeft();
        Position aMoveLeft = new Position(aPos.getX()-20, aPos.getY());
        assert (a.getPos().getX() == aMoveLeft.getX());
        assert (a.getPos().getY() == aMoveLeft.getY());

        b.moveLeft();
        Position bMoveLeft = new Position(bPos.getX()-20, bPos.getY());
        assert (b.getPos().getX() == bMoveLeft.getX());
        assert (b.getPos().getY() == bMoveLeft.getY());
    }

    @Test
    public void CharactersMoveRight(){
        a.moveRight();
        Position aMoveRight = new Position(aPos.getX()+20, aPos.getY());
        assert (a.getPos().getX() == aMoveRight.getX());
        assert (a.getPos().getY() == aMoveRight.getY());

        b.moveRight();
        Position bMoveRight = new Position(bPos.getX()+20, bPos.getY());
        assert (b.getPos().getX() == bMoveRight.getX());
        assert (b.getPos().getY() == bMoveRight.getY());
    }

    @Test
    public void CharactersMoveUp(){
        a.moveUp();
        Position aMoveUp = new Position(aPos.getX(), aPos.getY()-20);
        assert (a.getPos().getX() == aMoveUp.getX());
        assert (a.getPos().getY() == aMoveUp.getY());

        b.moveUp();
        Position bMoveUp = new Position(bPos.getX(), bPos.getY() - 20);
        assert (b.getPos().getX() == bMoveUp.getX());
        assert (b.getPos().getY() == bMoveUp.getY());
    }

    @Test
    public void CharactersMoveDown(){
        a.moveDown();
        Position aMoveDown = new Position(aPos.getX(), aPos.getY() + 20);
        assert (a.getPos().getX() == aMoveDown.getX());
        assert (a.getPos().getY() == aMoveDown.getY());

        b.moveDown();
        Position bMoveDown = new Position(bPos.getX(), bPos.getY() + 20);
        assert (b.getPos().getX() == bMoveDown.getX());
        assert (b.getPos().getY() == bMoveDown.getY());
    }

    @Test
    public void CharactersBooleanMovedUp(){
        a.setMovingUp(true);
        assert(a.getMovingUp());

        b.setMovingUp(true);
        assert(b.getMovingUp());
    }

    @Test
    public void CharactersBooleanMovedDown(){
        a.setMovingDown(true);
        assert(a.getMovingDown());

        b.setMovingDown(true);
        assert(b.getMovingDown());
    }

    @Test
    public void CharactersBooleanMovedLeft(){
        a.setMovingLeft(true);
        assert(a.getMovingLeft());

        b.setMovingLeft(true);
        assert(b.getMovingLeft());
    }

    @Test
    public void CharactersBooleanMovedRight(){
        a.setMovingRight(true);
        assert(a.getMovingRight());

        b.setMovingRight(true);
        assert(b.getMovingRight());
    }

    @Test
    public void CharactersCollisionShape(){
        int a_left = (int) (aPos.getX()*65)/64;
        int a_top = (int) (aPos.getY()*65)/64;
        int a_right = (int) (aPos.getX() + charWidth)*61/64;
        int a_bottom = (int) (aPos.getY() + charHeight)*61/64;
        Rect rec_a = new Rect(a_left, a_top, a_right, a_bottom);
        assert (rec_a.left == a.getCollisionShape().left);
        assert (rec_a.right == a.getCollisionShape().right);
        assert (rec_a.bottom == a.getCollisionShape().bottom);
        assert (rec_a.top == a.getCollisionShape().top);

        int b_left = (int) (bPos.getX()*65)/64;
        int b_top = (int) (bPos.getY()*65)/64;
        int b_right = (int) (bPos.getX() + charWidth)*61/64;
        int b_bottom = (int) (bPos.getY() + charHeight)*61/64;
        Rect rec_b = new Rect(b_left, b_top, b_right, b_bottom);
        assert (rec_b.left == b.getCollisionShape().left);
        assert (rec_b.right == b.getCollisionShape().right);
        assert (rec_b.bottom == b.getCollisionShape().bottom);
        assert (rec_b.top == b.getCollisionShape().top);
    }

    @Test
    public void CharactersWidthAndHeight(){
        assert (a.getHeight() == charHeight);
        assert (a.getWidth() == charWidth);

        assert (b.getHeight() == charHeight);
        assert (b.getWidth() == charWidth);
    }

    @Test
    public void CharactersLives() {
        for (int i = characterALives; i < characterALives; i--) {
            characterALives--;
            if (i == 0) {
                assert (characterALives == i);
                break;
            }
        }

        for (int i = characterBLives; i < characterBLives; i--) {
            characterBLives--;
            if (i == 0) {
                assert (characterBLives == i);
                break;
            }
        }
    }

}
