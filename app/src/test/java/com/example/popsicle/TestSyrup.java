package com.example.popsicle;

import com.example.popsicle.models.CharacterPosition;
import com.example.popsicle.models.Syrup;
import com.example.popsicle.models.SyrupMotion;
import com.example.popsicle.models.SyrupPosition;
import com.example.popsicle.models.Universe;

import org.junit.Test;

public class TestSyrup {
    @Test
    public void CreateSyrup(){
//        Universe u = new Universe();

        Syrup d1 = new Syrup(3, 3);
        d1.move(new SyrupMotion(1,-1));
        SyrupPosition p1 = new SyrupPosition(4,2);
        System.out.println(d1.getPosition());
        System.out.println(p1);
        assert (d1.getPosition() == p1);
    }



}
