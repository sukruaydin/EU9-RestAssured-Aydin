package com.cydeo.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("Spartan API hamcrest example")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://54.82.123.95:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("id", equalTo(15),"name",is("Meta"),"gender",is("Female"),"phone",equalTo(1938695106));

    }

    @DisplayName("CydeoTraining API hamcrest example-1")
    @Test
    public void test2(){

        given().contentType(ContentType.JSON)
                .and().pathParam("id",5)

                .when().get("https://api.training.cydeo.com/teacher/{id}")

                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("transfer-encoding",equalTo("chunked"))
                .and().header("date",notNullValue())
                .and().body("teachers[0].firstName",is("Mario"));

    }

    @DisplayName("CydeoTraining API hamcrest example-2, hasItems()")
    @Test
    public void test3(){

        //verify Ron,Erik,Yet inside the all teachers

        given().contentType(ContentType.JSON)

                .when().get("https://api.training.cydeo.com/teacher/all")

                .then().statusCode(200)
                .body("teachers.firstName",hasItems("Ron","Erik","Yet"));

    }

}
