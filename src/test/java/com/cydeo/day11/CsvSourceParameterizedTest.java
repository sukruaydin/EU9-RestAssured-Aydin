package com.cydeo.day11;

import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CsvSourceParameterizedTest {

    /*
     Test first number + second number = third number
            1, 3 , 4
            2, 3 , 5
            8, 7 , 15
            10, 9 , 19
     */

    //comma seperated values
    //accept values as a String
    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
                "2, 3 , 5",
                "8, 7 , 15",
                "10, 9 , 19"})
    public void test1(int first, int second, int third){
        //Assertions.assertEquals((first+second),third);
        //hamcrest comes default with RestAssured library
        MatcherAssert.assertThat(first+second, Matchers.is(third));
    }


    /*
        Write a parameterized test for this request
        GET https://api.zippopotam.us/us/{state}/{city}

        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"

        verify place name contains your city name
        print number of places for each request
     */

    @ParameterizedTest
    @CsvSource({"NY, New York",
                "CO, Denver",
                "VA, Fairfax",
                "VA, Arlington",
                "MA, Boston",
                "NY, New York",
                "MD, Annapolis"})
    public void test2(String state, String city){

        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int numberOfCity = given().pathParam("state",state)
                .and().pathParam("city",city)
                .and().baseUri("https://api.zippopotam.us")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .and().body("places.'place name'",everyItem(containsStringIgnoringCase(city)))
                .extract().jsonPath().getList("places").size();

        System.out.println("numberOfCity = " + numberOfCity);
        System.out.println("--------------------");

    }

}
