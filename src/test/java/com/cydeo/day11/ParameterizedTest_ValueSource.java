package com.cydeo.day11;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

//java_class_2
public class ParameterizedTest_ValueSource {

    //much closer to data driven testing
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5,21,32})
    public void test1(int number){
        System.out.println("number = " + number);
        Assertions.assertTrue(number>5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"şükrü","ahmet","jon"})
    public void test2(String name){
        System.out.println("name = " + name);
    }


    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void test3(int zipCode){
         /*
            SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
            with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
            check status code 200
           */

        given().pathParam("zipcode",zipCode)
                .and().baseUri("https://api.zippopotam.us")
                .when().get("/us/{zipcode}")
                .then().statusCode(200);
    }

}
