package com.example.popsicle;

import com.example.popsicle.models.Character;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;

import org.junit.Test;

public class TestCharacter {

    //TODO: Finish the remaining testings.

    Character a = new Character("a");
    Character b = new Character("b");
    Constants constants = new Constants();
    Position aPos = new Position(constants.screenX/8,constants.screenY*45/100);
    Position bPos = new Position(constants.screenX*13/16,constants.screenY*45/100);
    int charWidth = constants.charWidth;
    int charHeight = constants.charHeight;

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
        //TODO: Testing when the characters move up
    }

    @Test
    public void CharactersMoveDown(){
        //TODO: Testing when the characters move down
    }

    @Test
    public void CharactersBooleanMovedUp(){
        //TODO: Testing the Boolean when characters move up
    }

    @Test
    public void CharactersBooleanMovedDown(){
        //TODO: Testing the Boolean when the characters move down
    }

    @Test
    public void CharactersBooleanMovedLeft(){
        //TODO: Testing the Boolean when the characters move left
    }

    @Test
    public void CharactersBooleanMovedRight(){
        //TODO: Testing the Boolean when the characters move right
    }

    @Test
    public void CharactersCollisionShape(){
        //TODO: Testing the Characters collision shape
    }

    @Test
    public void CharactersWidthAndHeight(){
        //TODO: Testing the Characters' width and height
    }

}
