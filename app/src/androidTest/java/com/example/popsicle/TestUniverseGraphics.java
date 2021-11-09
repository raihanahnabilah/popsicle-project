package com.example.popsicle;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.popsicle.models.Constants;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.Universe;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TestUniverseGraphics class
 * The following class does the Unit Testing of almost all the methods in the Universe class,
 * with the exception of the Collision and the Firebase methods.
 */
@RunWith(AndroidJUnit4.class)
public class TestUniverseGraphics extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

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

        Collection<Position> positionLists = new ArrayList<>();
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
        assert(false == u.getCharacterA().getMovingRight());
        u.CharacterMove(new Position(u.getRight().getPos().getX() + (u.getRight().getWidth()/4) + 20, u.getRight().getPos().getY() + (u.getRight().getHeight()/4) + 20), u.getCharacterA());
        assert(true == u.getCharacterA().getMovingRight());

        assert(false == u.getCharacterA().getMovingLeft());
        u.CharacterMove(new Position(u.getLeft().getPos().getX() + (u.getLeft().getWidth()/4) + 20, u.getLeft().getPos().getY() + (u.getLeft().getHeight()/4) + 20), u.getCharacterA());
        assert(true == u.getCharacterA().getMovingLeft());

        assert(false == u.getCharacterA().getMovingUp());
        u.CharacterMove(new Position(u.getUp().getPos().getX() + (u.getUp().getWidth()/4) + 20, u.getUp().getPos().getY() + (u.getUp().getHeight()/4) + 20), u.getCharacterA());
        assert(true == u.getCharacterA().getMovingUp());

        assert(false == u.getCharacterA().getMovingDown());
        u.CharacterMove(new Position(u.getDown().getPos().getX() + (u.getDown().getWidth()/4) + 20, u.getDown().getPos().getY() + (u.getDown().getHeight()/4) + 20), u.getCharacterA());
        assert(true == u.getCharacterA().getMovingDown());
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

    /**
     * Testing for the randomlyAddedSyrups method!
     * Given a cloud, direction, and n integer, it will indeed generate a syrup
     * and trigger the addSyrup method.
     */
    @Test
    public void testingRandomlyAddedSyrups(){
        assertEquals(0, u.getSyrups().size());
        u.randomlyAddSyrups(0, u.getCloudA1(), "a1");
        assertEquals(1, u.getSyrups().size());
        u.randomlyAddSyrups(1, u.getCloudA2(), "a2");
        u.randomlyAddSyrups(2, u.getCloudB1(), "b1");
        u.randomlyAddSyrups(3, u.getCloudB2(), "b2");
        assertEquals(4, u.getSyrups().size());
    }

    /**
     * Testing the readCharacter method for the universe
     * to continuously update the opponent's character's position that is read
     * from the database
     */
    @Test
    public void testingReadCharacter(){
        u.readCharacter(500, 500, u.getCharacterA());
        Position A = new Position(500, 500);
        assert(A.getX() == u.getCharacterA().getPos().getX());
        assert(A.getY() == u.getCharacterA().getPos().getY());
        u.readCharacter(500, 500, u.getCharacterB());
        Position B = new Position(500, 500);
        assert(B.getX() == u.getCharacterB().getPos().getX());
        assert(B.getY() == u.getCharacterB().getPos().getY());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


}
