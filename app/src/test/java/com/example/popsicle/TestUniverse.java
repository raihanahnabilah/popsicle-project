package com.example.popsicle;

import com.example.popsicle.models.CharacterPosition;
import com.example.popsicle.models.Universe;
//import com.google.common.annotations.VisibleForTesting;

import org.junit.Test;

public class TestUniverse {
    @Test
    public void create_and_move_characterA() {
        Universe u = new Universe();

        u.AStepLeft();
        CharacterPosition Aleft = new CharacterPosition(5,10);
        assert(Aleft.getX() == u.getCharacterA().getPos().getX());
        assert(Aleft.getY() == u.getCharacterA().getPos().getY());
        org.junit.Assert.assertEquals(Aleft.toString(), u.getCharacterA().getPos().toString());

        u.AStepRight();
        CharacterPosition ARight = new CharacterPosition(10,10);
        org.junit.Assert.assertEquals(ARight.toString(), u.getCharacterA().getPos().toString());

        u.AStepDown();
        CharacterPosition ADown = new CharacterPosition(10,5);
        org.junit.Assert.assertEquals(ADown.toString(), u.getCharacterA().getPos().toString());

        u.AStepUp();
        CharacterPosition AUp = new CharacterPosition(10,10);
        org.junit.Assert.assertEquals(AUp.toString(), u.getCharacterA().getPos().toString());
    }

    @Test
    public void create_and_move_characterB() {
        Universe u = new Universe();

        u.BStepLeft();
        CharacterPosition Bleft = new CharacterPosition(45,10);
        assert(Bleft.getX() == u.getCharacterB().getPos().getX());
        assert(Bleft.getY() == u.getCharacterB().getPos().getY());
        org.junit.Assert.assertEquals(Bleft.toString(), u.getCharacterB().getPos().toString());

        u.BStepRight();
        CharacterPosition BRight = new CharacterPosition(50,10);
        org.junit.Assert.assertEquals(BRight.toString(), u.getCharacterB().getPos().toString());

        u.BStepDown();
        CharacterPosition BDown = new CharacterPosition(50,5);
        org.junit.Assert.assertEquals(BDown.toString(), u.getCharacterB().getPos().toString());

        u.BStepUp();
        CharacterPosition BUp = new CharacterPosition(50,10);
        org.junit.Assert.assertEquals(BUp.toString(), u.getCharacterB().getPos().toString());
    }

}
