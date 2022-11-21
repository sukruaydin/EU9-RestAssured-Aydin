package com.cydeo.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

//example_3
public class SpartanNegativeGetRequest {

    @BeforeAll
    public static void init(){
        baseURI = "http://54.82.123.95:8000";
    }

    @Test
    public void test1(){
    /*
        Given Accept type application/xml
        When user send get request to /api/spartans end point
        Then status code must be 406
        And response content-type must be application/xml;charset=UTF-8
     */

        /*Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans");*/

        //assertEquals(response.statusCode(),406);

        //System.out.println("response.statusCode() = " + response.statusCode());

        Response response = given().accept("application/xml")
                .when().get("api/spartans/10");
        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());

    }
}
