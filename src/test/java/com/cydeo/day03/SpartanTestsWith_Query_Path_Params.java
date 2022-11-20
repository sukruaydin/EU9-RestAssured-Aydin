package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWith_Query_Path_Params extends SpartanTestBase {

    @DisplayName("pathParam(key,value) method - 1")
    @Test
    public void test1() {
        /*
          Given accept type is json
          When id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload(body)
     */

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Blythe"));

    }

    @DisplayName("pathParam(key,value) method - 2")
    @Test
    public void test2(){
        /*
        TASK
        Given accept type is json
        And id parameter value is 50
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

        Response response = given().log().all().accept(ContentType.JSON)
                            .and().pathParam("id",50)
                            .when().get("/api/spartans/{id}");


        System.out.println(response.statusCode());
        assertEquals(response.statusCode(),200);
    }

    @DisplayName("queryParam(), log().all()")
    @Test
    public void test3(){
        /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertFalse(response.body().asString().contains("Male"));
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Janette"));

    }


    @DisplayName("queryParams(map) method")
    @Test
    public void test4(){
        /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

        //create a map and add query parameters
        Map<String,Object> queryMap = new LinkedHashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertFalse(response.body().asString().contains("Male"));
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Janette"));

    }

}
