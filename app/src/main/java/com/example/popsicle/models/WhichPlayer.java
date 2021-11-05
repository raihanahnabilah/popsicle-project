package com.example.popsicle.models;

/**
 *  WhichPlayer handles the booleans that will be sent to the database to determine which Character is the user, A or B.
 *  @author Hana, Valeria
 */
public class WhichPlayer {

    /**
     * Booleans amIPlayerA and amIPlayerB are by default false. A change to true is done depending on which button the user clicks on HomePage to choose its character.
     */
    public static Boolean amIPlayerA = false;
    public static Boolean amIPlayerB = false;
    /**
    *  Depending on which button the user clicks on the HomePage, the respective boolean will be true, while the other remains false.
     * @params amIPlayerA boolean for determining if the user chose Character A
     * @params amIPlayerB boolean for determining if the user chose Character B
     */
    public WhichPlayer(Boolean amIPlayerA, Boolean amIPlayerB){
        this.amIPlayerA = amIPlayerA;
        this.amIPlayerB = amIPlayerB;
    }

}
