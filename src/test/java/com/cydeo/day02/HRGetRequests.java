package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

//example_2
public class HRGetRequests {

    @BeforeAll
    public static void init(){
        //new thing --> it allows us not to write baseUrl again and again in get method
        RestAssured.baseURI = "http://54.82.123.95:1000/ords/hr";
    }

    @DisplayName("get request for regions")
    @Test
    public void test1(){
        Response response = RestAssured.get("/regions");
        assertEquals(response.statusCode(),200);
    }

    @DisplayName("get request for regions/2")
    @Test
    public void test2(){

    /*
        Given accept type is application/json
        When user sends get request for regions/2
        Then response status code must be 200
        And response content-type is in application/json
        And response body contains Americas
     */

        //static import implemented

        Response response = given().contentType(ContentType.JSON)
                .when().get("/regions/2");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        System.out.println(response.contentType());
        assertTrue(response.body().asString().contains("Americas"));

        response.prettyPrint();

    }

}
