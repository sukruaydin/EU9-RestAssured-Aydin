package com.cydeo.utilities;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookItUtil extends BookItTestBase{

      public static String token(String email, String password){
            String token = given().queryParam("email", email)
                    .queryParam("password", password)
                    .get("/sign")
                    .then().statusCode(200)
                    .extract().response().path("accessToken");
            return "Bearer " + token;
      }

}
