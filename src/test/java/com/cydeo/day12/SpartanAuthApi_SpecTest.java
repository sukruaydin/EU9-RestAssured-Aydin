package com.cydeo.day12;

import com.cydeo.utilities.SpartanAuthNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

//java_class_3
public class SpartanAuthApi_SpecTest extends SpartanAuthNewTestBase {

    /*
        GET request

        all tests in this class will use admin credentials
        all test in this class will expect json result from response

        all test in this class response status code is 200
        all test in this class response content type header is json
     */

    @Test
    public void test1(){

        RequestSpecification requestSpec = given().accept(ContentType.JSON)
                                            .and().auth().basic("admin", "admin");

        ResponseSpecification responseSpec = expect().statusCode(200)
                                             .contentType(ContentType.JSON);


        given().spec(requestSpec)
                .when().get("/spartans")
                .then().spec(responseSpec);

    }

    @Test
    public void test2(){

        given().spec(requestSpec)
                .and().pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec);

    }

    @Test
    public void test3(){
        given().spec(requestSpec)
                .and().queryParams("nameContains","j","gender","Female")
                .when().get("/spartans/search")
                .then().spec(responseSpec)
                .and().body("numberOfElements", Matchers.is(6));

    }


}
