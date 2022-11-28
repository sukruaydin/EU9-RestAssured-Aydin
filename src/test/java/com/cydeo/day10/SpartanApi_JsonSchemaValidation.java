package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_JsonSchemaValidation extends SpartanAuthTestBase {

    @DisplayName("JsonSchemaValidator class, matchesJsonSchemaInClasspath method")
    @Test
    public void test1(){

        given().pathParam("id",10)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanJsonSchema.json"))
                .log().all();

    }

    @DisplayName("JsonSchemaValidator class, matchesJsonSchema method")
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/AllSpartansJsonSchema.json")));

    }

    @DisplayName("Homework")
    @Test
    public void test3(){

        /*
            homework
            put your post json schema under day10
            post one spartan using dynamic input(name,gender,phone)
            verify your post response matching with json schema
         */


        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/AllSpartansJsonSchema.json")));

    }


}
