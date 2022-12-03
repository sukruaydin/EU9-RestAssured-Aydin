package com.cydeo.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestInJunit5 {

    //much closer to data driven testing
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5,21,32})
    public void testInt(int number){
        System.out.println("number = " + number);
        Assertions.assertTrue(number>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"şükrü","ahmet","jon"})
    public void testString(String name){
        System.out.println("name = " + name);
    }

}
