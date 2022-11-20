package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWith_PathMethod extends SpartanTestBase {

    @DisplayName("getting into json body by .path method")
    @Test
    public void test1(){
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

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);

    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        int id0 = response.path("id[0]");
        System.out.println("id0 = " + id0);

        String name0 = response.path("name[0]");
        System.out.println("name0 = " + name0);

        String nameLast = response.path("name[-1]");
        System.out.println("nameLast = " + nameLast);

        System.out.println("--------------------------------------------");

        //since it is returning multiple objects, i can be able to store them into a List
        List<String> nameList= response.path("name");
        for (String name : nameList) {
            System.out.println(name);
        }

    }

}
