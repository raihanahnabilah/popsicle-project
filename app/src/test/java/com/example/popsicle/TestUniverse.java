package com.example.popsicle;

import static org.junit.Assert.assertEquals;

import com.example.popsicle.models.Character;
import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;
import android.graphics.Rect;
//import com.google.common.annotations.VisibleForTesting;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TestUniverse class
 * The following class does the Unit Testing of all the methods in the Universe class,
 * with the exception of checkPopsicleCollision method and checkSyrupCollisions method.
 * The reason for this is because these two methods require android's graphics Rect,
 * and thus more suitable to test this in Instrumentation testing instead of
 * Unit Testing.
 */
public class TestUniverse {

    // TODO: Finish the remaining testing.

    Universe u = new Universe();

    /**
     * The function addSyrups() asserts that when we add the syrups
     * to the universe by calling the addSyrup method, the syrups are
     * indeed created and stored in the Collections
     */
    @Test
    public void addSyrups() {
        assertEquals(0, u.getSyrups().size());
        u.addSyrup(500, 500, "a1");
        u.addSyrup(500, 500, "a2");
        u.addSyrup(500, 500, "b1");
        u.addSyrup(500, 500, "b2");
        assertEquals(4, u.getSyrups().size());
    }

    /**
     * The function moveSyrups() asserts that when we make the syrups move
     * by calling the syrupSteps method, it actually moves to the expected locations
     */
    @Test
    public void moveSyrups() {
        u.addSyrup(500, 500, "a1");
        u.addSyrup(500, 500, "a2");
        u.addSyrup(500, 500, "b1");
        u.addSyrup(500, 500, "b2");
        u.syrupSteps();

        Collection <Position> positionLists = new ArrayList<>();
        positionLists.add(new Position(500 + u.getConstants().syrupMovementPixelsX,500 + u.getConstants().syrupMovementPixelsX*(u.getConstants().screenX/u.getConstants().screenY)));
        positionLists.add(new Position(500 + u.getConstants().syrupMovementPixelsX,500 - u.getConstants().syrupMovementPixelsX*(u.getConstants().screenX/u.getConstants().screenY)));
        positionLists.add(new Position(500 - u.getConstants().syrupMovementPixelsX,500 + u.getConstants().syrupMovementPixelsX*(u.getConstants().screenX/u.getConstants().screenY)));
        positionLists.add(new Position(500 - u.getConstants().syrupMovementPixelsX,500 - u.getConstants().syrupMovementPixelsX*(u.getConstants().screenX/u.getConstants().screenY)));

        for (int i=0 ; i < u.getSyrups().size() ; i++) {
            assert(((Position)positionLists.toArray()[i]).getX() == ((Syrup) u.getSyrups().toArray()[i]).getPos().getX());
            assert(((Position)positionLists.toArray()[i]).getY() == ((Syrup) u.getSyrups().toArray()[i]).getPos().getY());
        }
    }

    /**
     * The function booleanMoveCharacters() tests the moveCharacter method in Universe.
     * It asserts that when we click at the certain direction of the console,
     * the boolean of either the up/left/down/right direction will be set to true
     */
    @Test
    public void booleanMoveCharacters(){
        assertEquals(false, u.getCharacterA().getMovingRight());
        u.CharacterMove(new Position(u.getRight().getPos().getX() + (u.getRight().getWidth()/4) + 20, u.getRight().getPos().getY() + (u.getRight().getHeight()/4) + 20), u.getCharacterA());
        assertEquals(true, u.getCharacterA().getMovingRight());

        assertEquals(false, u.getCharacterA().getMovingLeft());
        u.CharacterMove(new Position(u.getLeft().getPos().getX() + (u.getLeft().getWidth()/4) + 20, u.getLeft().getPos().getY() + (u.getLeft().getHeight()/4) + 20), u.getCharacterA());
        assertEquals(true, u.getCharacterA().getMovingLeft());

        assertEquals(false, u.getCharacterA().getMovingUp());
        u.CharacterMove(new Position(u.getUp().getPos().getX() + (u.getUp().getWidth()/4) + 20, u.getUp().getPos().getY() + (u.getUp().getHeight()/4) + 20), u.getCharacterA());
        assertEquals(true, u.getCharacterA().getMovingUp());

        assertEquals(false, u.getCharacterA().getMovingDown());
        u.CharacterMove(new Position(u.getDown().getPos().getX() + (u.getDown().getWidth()/4) + 20, u.getDown().getPos().getY() + (u.getDown().getHeight()/4) + 20), u.getCharacterA());
        assertEquals(true, u.getCharacterA().getMovingDown());

    }

    /**
     * The function moveUpdateCharacters() tests the updateCharacter method in Universe.
     * It asserts that once the up/left/down/right boolean is set to true,
     * the characters are indeed moving by 20 pixels in respective directions.
     */
    @Test
    public void moveUpdateCharacters(){
        Position beforeMoveRight = new Position(u.getCharacterA().getPos().getX(), u.getCharacterA().getPos().getY());
        u.getCharacterA().setMovingRight(true);
        u.updateCharacter();
        Position afterMoveRight = new Position(beforeMoveRight.getX() + 20, beforeMoveRight.getY());
        assert(u.getCharacterA().getPos().getX() == afterMoveRight.getX());
        assert(u.getCharacterA().getPos().getY() == afterMoveRight.getY());

        Position beforeMoveLeft = new Position(u.getCharacterA().getPos().getX(), u.getCharacterA().getPos().getY());
        u.getCharacterA().setMovingLeft(true);
        u.updateCharacter();
        Position afterMoveLeft = new Position(beforeMoveLeft.getX() - 20, beforeMoveLeft.getY());
        assert(u.getCharacterA().getPos().getX() == afterMoveLeft.getX());
        assert(u.getCharacterA().getPos().getY() == afterMoveLeft.getY());

        Position beforeMoveUp = new Position(u.getCharacterA().getPos().getX(), u.getCharacterA().getPos().getY());
        u.getCharacterA().setMovingUp(true);
        u.updateCharacter();
        Position afterMoveUp = new Position(beforeMoveUp.getX(), beforeMoveUp.getY() - 20);
        assert(u.getCharacterA().getPos().getX() == afterMoveUp.getX());
        assert(u.getCharacterA().getPos().getY() == afterMoveUp.getY());

        Position beforeMoveDown = new Position(u.getCharacterA().getPos().getX(), u.getCharacterA().getPos().getY());
        u.getCharacterA().setMovingUp(true);
        u.updateCharacter();
        Position afterMoveDown = new Position(beforeMoveDown.getX(), beforeMoveDown.getY() - 20);
        assert(u.getCharacterA().getPos().getX() == afterMoveDown.getX());
        assert(u.getCharacterA().getPos().getY() == afterMoveDown.getY());
    }

    @Test
    public void getCharacters(){
        // TODO: Testing the getters characters
    }

    @Test
    public void getClouds(){
        // TODO: Testing the getters clouds
    }

    @Test
    public void getPopsicles(){
        // TODO: Testing the getters popsicles/candies
    }

    @Test
    public void getConsoles(){
        // TODO: Testing the getters popsicles/candies
    }

    @Test
    public void isPlayingIsGameOver(){
        // TODO: Testing the setter/getter of isPlayingisGameOver methods.
    }

}