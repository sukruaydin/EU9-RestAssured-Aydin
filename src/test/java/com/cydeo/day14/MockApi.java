package com.cydeo.day14;


import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MockApi {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://0ba318f1-2183-4868-a98d-9095b4522afd.mock.pstmn.io";
    }

    @Test
    public void test1(){

        given().get("/customer")
                .then().statusCode(200)
                .body("firstName", Matchers.is("John"));

    }

    @Test
    public void test2(){

        //it fails since it is negative test scenario
        given().get("/employees")
                .then().statusCode(401);

    }

}
