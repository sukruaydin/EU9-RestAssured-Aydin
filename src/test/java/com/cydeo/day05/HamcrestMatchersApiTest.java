package com.cydeo.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//java.class_2
public class HamcrestMatchersApiTest {

    @DisplayName("hamcrest example with Spartan API")
    @Test
    public void test1(){
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

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://54.82.123.95:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().body("id", equalTo(15),"name",
                        is("Meta"),"gender",is("Female"),"phone",equalTo(1938695106));

    }

    @DisplayName("hamcrest example with Cydeo Training API")
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

    @DisplayName("hamcrest example - 2 with Cydeo Training API, hasItems() method")
    @Test
    public void test3(){

        //verify Ron,Erik,Yet inside the all teachers

        given().contentType(ContentType.JSON)

                .when().get("https://api.training.cydeo.com/teacher/all")

                .then().statusCode(200)

                //"teachers.firstName" --> already returning a list
                .body("teachers.firstName",hasItems("Ron","Erik","Yet"));

    }

}
