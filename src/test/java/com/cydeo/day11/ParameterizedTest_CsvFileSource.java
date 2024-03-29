package com.cydeo.day11;

import io.restassured.RestAssured;
import org.apache.commons.collections4.Get;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//java_class_4
public class ParameterizedTest_CsvFileSource {

    //numLinesToSkip : lines not to be executed, for headers
    @ParameterizedTest
    @CsvFileSource(resources = "/postalCode.csv",numLinesToSkip = 1)
    public void test1(String state, String city, int count){
        /*
            Read postalCode.csv from resources
            Write a parameterized test for this request
            Get the data from csv source
            GET https://api.zippopotam.us/us/{state}/{city}
            Verify place number matches with zipCount
        */

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("count = " + count);

        given().pathParam("state",state)
                        .and().pathParam("city",city)
                        .and().baseUri("https://api.zippopotam.us")
                        .when().get("/us/{state}/{city}")
                        .then().statusCode(200)
                        .and().body("places",hasSize(count));

        System.out.println("----------------------");

    }

}
