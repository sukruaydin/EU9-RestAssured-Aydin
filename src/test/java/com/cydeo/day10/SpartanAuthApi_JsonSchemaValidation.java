package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanAuthApi_JsonSchemaValidation extends SpartanAuthTestBase {

    @DisplayName("SingleSpartanJsonSchema, JsonSchemaValidatorClass, matchesJsonSchemaInClasspath() method")
    @Test
    public void test1(){

        //if ".json file" is in resources, we provide the path starting with file name one

        given().pathParam("id",10)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanJsonSchema.json"))
                .log().all();

    }

    @DisplayName("AllSpartansJsonSchema, JsonSchemaValidatorClass, matchesJsonSchema() method")
    @Test
    public void test2(){

        //if ".json file" is NOT in resources, we provide full path with different method

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/AllSpartansJsonSchema.json")));

    }

    @DisplayName("SpartanPostJsonSchema, JsonSchemaValidatorClass, matchesJsonSchema() method")
    @Test
    public void test3(){

        /*
            homework
            put your post json schema under day10
            post one spartan using dynamic input(name,gender,phone)
            verify your post response matching with json schema
         */


        int id = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .body(SpartanUtils.generateSpartan())
                .when().post("/api/spartans")
                .then().statusCode(201)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/SpartanPostJsonSchema.json")))
                .extract().response().path("data.id");

        //GET request for asserting
        given().pathParam("id",id)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200);

    }

    //SearchSpartan hasn't been practiced yet.

}
