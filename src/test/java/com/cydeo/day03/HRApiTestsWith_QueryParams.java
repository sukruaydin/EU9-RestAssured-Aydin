package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class HRApiTestsWith_QueryParams extends HRTestBase {

    @DisplayName("queryParam(key,value) method - 1")
    @Test
    public void test1(){
        /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @DisplayName("queryParam(key,value) method - 2")
    @Test
    public void test2(){
        /*
        Send a GET request to employees and get only employees who work as an IT_PROG
    */
        Response response = given().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        response.prettyPrint();
    }
}
