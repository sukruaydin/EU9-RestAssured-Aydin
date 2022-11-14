package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(response.statusCode(),200);
    }

    /*
        Given accept type is application/json
        When user sends get request for regions/2
        Then response status code must be 200
        And response content-type is in application/json
        And response body contains Americas
     */
    @DisplayName("get request for regions/2")
    @Test
    public void test2(){
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .when().get("/regions/2");

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json");
        Assertions.assertTrue(response.body().asString().contains("Americas"));

        response.prettyPrint();
    }

}
