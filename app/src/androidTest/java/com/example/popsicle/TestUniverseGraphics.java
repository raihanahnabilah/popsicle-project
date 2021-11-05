package com.example.popsicle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.popsicle.models.Universe;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestUniverseGraphics extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void UniverseCreated(){
        Universe u = new Universe();
        u.addSyrup(500, 500, "a1");
        u.addSyrup(500, 500, "a2");
        u.addSyrup(500, 500, "b1");
        u.addSyrup(500, 500, "b2");
        u.syrupSteps();
        u.getCharacterA().setMovingRight(true);
        u.getCharacterA().setMovingLeft(true);
        u.getCharacterA().setMovingUp(true);
        u.getCharacterA().setMovingDown(true);
        u.updateCharacter();
        assert(false == u.getGameOver());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


}
