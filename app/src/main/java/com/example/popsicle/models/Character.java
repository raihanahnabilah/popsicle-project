package com.example.popsicle.models;

/**
 * Character Class is controls the positional information regarding the character
 */
public class Character {
    private static final String TAG = "Character";
    private CharacterPosition pos;

    /**
     * @param x x-coordinate of the character
     * @param y y-coordinate of the charater
     */
    public Character(float x, float y){
        this.pos = new CharacterPosition(x, y);
    }

    /**
     * Moving left: using the Character Position Class
     */
    public void moveLeft() {
        this.pos.addLeft();
    }
    /**
     * Moving Right: using the Character Position Class
     */
    public void moveRight() {
        this.pos.addRight();
    }
    /**
     * Moving Up: using the Character Position Class
     */
    public void moveUp() {
        this.pos.addUp();
    }
    /**
     * Moving Down: using the Character Position Class
     */
    public void moveDown() {
        this.pos.addDown();
    }
    /**
     * Returns pos - The position of the Characters
     */
    public CharacterPosition getPos() {
        return pos;
    }

    /**
     * @return String to edit/override the xml file
     */

    @Override
    public String toString() {
        return "Character{" +
                "pos=" + pos +
                '}';
    }
}
