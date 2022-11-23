package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/*import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;*/


public class SpartanApi_Post extends SpartanTestBase {

    @DisplayName("post request, serialization with body() method - String")
    @Test
    public void test1(){
        /*
            Given accept type and Content type is JSON
            And request json body is:
            {
              "gender":"Male",
              "name":"Severus",
              "phone":8877445596
           }
            When user sends POST request to '/api/spartans'
            Then status code 201
            And content type should be application/json
            And json payload/response/body should contain:
            "A Spartan is Born!" message
            and same data what is posted
        */

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().response();

        //continue asserting
        String expectedMessage = "A Spartan is Born!";
        MatcherAssert.assertThat(response.path("success"), Matchers.is(expectedMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596l));

        response.prettyPrint();

    }

    @DisplayName("post request, serialization with body() method - Map")
    @Test
    public void test2(){
        /*
            Given accept type and Content type is JSON
            And request json body is:
            {
              "gender":"Male",
              "name":"Severus",
              "phone":8877445596
           }
            When user sends POST request to '/api/spartans'
            Then status code 201
            And content type should be application/json
            And json payload/response/body should contain:
            "A Spartan is Born!" message
            and same data what is posted
        */

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name","Severus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",8877445596l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                //auto-serialization by body() method
                .body(requestJsonMap)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().response();

        //continue asserting
        String expectedMessage = "A Spartan is Born!";
        MatcherAssert.assertThat(response.path("success"), Matchers.is(expectedMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596l));

        response.prettyPrint();

    }

    @DisplayName("post request, serialization with pojo ")
    @Test
    public void test3(){
        /*
            Given accept type and Content type is JSON
            And request json body is:
            {
              "gender":"Male",
              "name":"Severus",
              "phone":8877445596
           }
            When user sends POST request to '/api/spartans'
            Then status code 201
            And content type should be application/json
            And json payload/response/body should contain:
            "A Spartan is Born!" message
            and same data what is posted
        */

        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596l);
        //id is sent as 0 by default


        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                //auto-serialization by body() method
                .body(spartan)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .extract().response();

        //continue asserting
        String expectedMessage = "A Spartan is Born!";
        MatcherAssert.assertThat(response.path("success"), Matchers.is(expectedMessage));
        assertThat(response.path("data.name"), is("Severus"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(8877445596l));

        response.prettyPrint();
    }

}
