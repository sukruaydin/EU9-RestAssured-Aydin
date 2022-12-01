package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_Put_Patch_Delete extends SpartanTestBase {

    @DisplayName("put request, get request")
    @Test
    public void test1(){
        /*
            for PUT request i need to provide everything needed for PUT request
            you can't only provided the data to be changed
         */
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","MustafaKavak");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",1111111111l);

        given().contentType(ContentType.JSON)
                .and().body(putRequestMap)
                .and().pathParam("id",122)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);

        //sending GET request for assertion
        Spartan spartanUpdated = given().accept(ContentType.JSON)
                .and().pathParam("id", 122)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().as(Spartan.class);

        assertThat(putRequestMap.get("name"),is(spartanUpdated.getName()));
        assertThat(putRequestMap.get("gender"),is(spartanUpdated.getGender()));
        assertThat(putRequestMap.get("phone"),is(spartanUpdated.getPhone()));

    }

    @DisplayName("patch request, get request")
    @Test
    public void test2(){

        /*
            for PATCH request it is allowed to provide only the data to be changed.
            it can be multiple data btw
         */
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",7777777777l);

        given().contentType(ContentType.JSON)
                .and().body(putRequestMap)
                .and().pathParam("id",122)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

        //sending GET request for assertion
        //de-serialization with pojo
        Spartan spartanUpdated = given().accept(ContentType.JSON)
                .and().pathParam("id", 122)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().as(Spartan.class);

        assertThat(putRequestMap.get("phone"),is(spartanUpdated.getPhone()));

    }

    @DisplayName("delete request, get request --> (didn't work)")
    @Test
    public void test3(){

        given().pathParam("id",122)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        /*given().pathParam("id",119)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);*/

    }

}
