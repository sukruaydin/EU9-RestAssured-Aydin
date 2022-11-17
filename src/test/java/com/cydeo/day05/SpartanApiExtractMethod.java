package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

//java.class_4
public class SpartanApiExtractMethod extends SpartanTestBase {

    @DisplayName("extracting List<String>")
    @Test
    public void test1(){

        //at the end, i used extract() method to convert every bull-shit to list without doing anything extra
        //it does not have any effect on the assertions

        List<String> names = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Male")

                .when().get("/api/spartans/search")

                .then().statusCode(200)
                .body("totalElement", is(3))

                .extract().jsonPath().get("content.name");

        System.out.println("names = " + names);
    }

    @DisplayName("extracting statusCode")
    @Test
    public void test2(){

        //at the end, i used extract() method to convert every bull-shit to int statusCode without doing anything extra
        //it does not have any effect on the assertions

        int statusCode = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Male")

                .when().get("/api/spartans/search")

                .then().statusCode(200)
                .body("totalElement", is(3))

                .extract().response().statusCode();

        System.out.println("statusCode = " + statusCode);
    }

}
