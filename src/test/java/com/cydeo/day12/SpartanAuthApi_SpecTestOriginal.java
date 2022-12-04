package com.cydeo.day12;

import com.cydeo.utilities.SpartanAuthNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

//java_class_2
public class SpartanAuthApi_SpecTestOriginal extends SpartanAuthNewTestBase {
    /*
            GET request

            all tests in this class will use admin credentials
            all test in this class will expect json result from response

            all test in this class response status code is 200
            all test in this class response content type header is json
     */

    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("/spartans")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);

    }
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON);

    }

}
