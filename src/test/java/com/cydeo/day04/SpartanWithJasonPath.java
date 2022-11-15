package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJasonPath extends SpartanTestBase {

     /*
        Given accept type is json
        And path param id is 10
        When user sends a get request to "api/spartans/{id}"
        Then status code is 200
        And content-type is "application/json"
        And response payload values match the following:
             id is 10,
             name is "Lorenza",
             gender is "Female",
             phone is 3312820936
      */

    @DisplayName("jason-path")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        //printing name with path method
        String name = response.path("name");
        System.out.println("name = " + name);

        //using jsonPath method
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender = jsonPath.get("gender");
        long phone = jsonPath.get("phone");

        //printing all of them
        System.out.println("id = " + id);
        System.out.println("name1 = " + name1);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //asserting
        assertEquals(10,id);
        assertEquals(name1,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);

    }

}
