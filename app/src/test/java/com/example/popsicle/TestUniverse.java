package com.example.popsicle;

import com.example.popsicle.models.CharacterPosition;
import com.example.popsicle.models.Universe;

import org.junit.Assert;
import org.junit.Test;

public class TestUniverse {
    @Test
    public void create_and_move_characterA() {
        Universe u = new Universe();
        u.AStepLeft();
        System.out.println(u.getCharacterA().getPos());
        CharacterPosition testing = new CharacterPosition(5,10);
        System.out.println(testing);
        System.out.println(testing.getX());
        System.out.println(testing.getY());

//       this test the position
        assert(testing.getX() == u.getCharacterA().getPos().getX());
        assert(testing.getY() == u.getCharacterA().getPos().getY());
//        u.AStepRight();
//        Assert.assertEquals(new CharacterPosition(10,10), u.characterA.getPos());
//        u.AStepDown();
//        Assert.assertEquals(new CharacterPosition(10,5), u.characterA.getPos());
//        u.AStepUp();
//        Assert.assertEquals(new CharacterPosition(10,10), u.characterA.getPos());
    }

//    @Test
//    public void create_and_move_characterB() {
//        Universe u = new Universe();
//        u.BStepLeft();
//        Assert.assertEquals(new CharacterPosition(45,10), u.characterA.getPos());
//        u.BStepRight();
//        Assert.assertEquals(new CharacterPosition(50,10), u.characterA.getPos());
//        u.BStepDown();
//        Assert.assertEquals(new CharacterPosition(50,5), u.characterA.getPos());
//        u.BStepUp();
//        Assert.assertEquals(new CharacterPosition(10,10), u.characterA.getPos());
//    }


}
