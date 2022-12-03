package com.cydeo.day11;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//java_class_3
public class ParameterizedTest_CsvSource {

    //comma seperated values
    //accept values as a String
    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
                "2, 3 , 5",
                "8, 7 , 15",
                "10, 9 , 19"})
    public void test1(int first, int second, int third){
             /*
                Test first number + second number = third number
                    1, 3 , 4
                    2, 3 , 5
                    8, 7 , 15
                    10, 9 , 19
            */

        MatcherAssert.assertThat(first+second, Matchers.is(third));
    }


    @ParameterizedTest
    @CsvSource({"NY, New York",
                "CO, Denver",
                "VA, Fairfax",
                "VA, Arlington",
                "MA, Boston",
                "NY, New York",
                "MD, Annapolis"})
    public void test2(String state, String city){
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
