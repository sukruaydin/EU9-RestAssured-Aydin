package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

//example_1
public class SpartanGetRequest {

    //normally it is not like this, just for today
    String baseUrl = "http://54.82.123.95:8000";

    @DisplayName("Adding Header by accept method")
    @Test
    public void test1(){
         /*
        Given Accept type application/json
        When user send GET request to /api/spartans end point
        Then status code must be 200
        Then response Content Type must be application/json
        And response body should be json format
    */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/spartans");

        //printing status code
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //printing response content type
        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);

        //print whole response body
        response.prettyPrint();

        //ASSERTING
        Assertions.assertEquals(statusCode,200);
        Assertions.assertEquals(contentType,"application/json");

    }

    @DisplayName("Getting one spartan by path")
    @Test
    public void test2(){
        /*
        Given accept is application/json
        When users send a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl+"/api/spartans/3");

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"application/json");
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

    @DisplayName("header(), headers() methods")
    @Test
    public void test3(){
        /*
        Given no headers provided
        When users send a get request to /api/hello
        Then response status code should be 200
        And content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And content-length should be 17
        And body should be "Hello from Sparta"
     */
        Response response = RestAssured.when().get(baseUrl+"/api/hello");

        Assertions.assertEquals(response.statusCode(),200);
        Assertions.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //new method --> response.headers().hasHeaderWithName(String name)
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);

        //new method --> response.header(String headerName)
        Assertions.assertEquals(response.header("Content-length"),"17");

        Assertions.assertEquals(response.body().asString(),"Hello from Sparta");

    }

}
