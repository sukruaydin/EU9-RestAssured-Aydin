package com.cydeo.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class MethodSourceParameterizedTest {

    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs
        List<String> nameList = Arrays.asList("şükrü","oğuz","pişgin","kavak");
        return nameList;
    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name){
        System.out.println("name = " + name);
    }

}
