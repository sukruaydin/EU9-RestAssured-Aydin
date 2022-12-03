package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    //have to be static
    @BeforeAll
    public static void init(){
        System.out.println("Before is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("\tBefore Each is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("\tAfter Each is running");
    }

    @Test
    public void test1(){
        System.out.println("test1 is running");
    }

    //@Disabled //ignores this test
    @Test
    public void test2(){
        System.out.println("test2 is running");
    }

    //have to be static
    @AfterAll
    public static void close(){
        System.out.println("After is running");
    }



}
