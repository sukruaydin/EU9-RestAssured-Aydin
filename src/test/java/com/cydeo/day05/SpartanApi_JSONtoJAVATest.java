package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

//java.class_5
public class SpartanApi_JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("added jackson dependency, converting map")
    @Test
    public void test1(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();

        //get the json and convert it to map
        //deserialization..?? bla bla
        //using jackson dependency now
        Map<String,Object> jsonMap = response.as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = jsonMap.get("name").toString();
        assertThat(actualName,is("Meta"));

    }

    @DisplayName("added jackson dependency, converting listOfMap")
    @Test
    public void test2(){

        Response response = when().get("/api/spartans")
                .then().statusCode(200)
                .extract().response();

        //converting json to java collection
        List<Map<String,Object>> jsonList = response.as(List.class);

        //printing second object's name
        String nameOfSecondObject = jsonList.get(1).get("name").toString();
        System.out.println("nameOfSecondObject = " + nameOfSecondObject);

        //printing 3rd spartan
        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println("spartan3 = " + spartan3);

    }

}
