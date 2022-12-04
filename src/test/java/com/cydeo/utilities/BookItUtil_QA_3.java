package com.cydeo.utilities;

import static io.restassured.RestAssured.given;

public class BookItUtil_QA_3 extends BookItTestBase_QA_3{

    public static String generateToken(String email, String password){
        String token = given().queryParam("email", email)
                .queryParam("password", password)
                .get("/sign")
                .then().statusCode(200)
                .extract().response().path("accessToken");

        return "Bearer " + token;
    }

}
