package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanAuthApi_ResponseTimeTest extends SpartanAuthTestBase {

    @DisplayName("ResponseTime verification, response.getTime() method")
    @Test
    public void test1(){

        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then()
                //asserting response time with hamcrest matchers
                .time(both(greaterThan(500l)).and(lessThanOrEqualTo(1800l)))
                .extract().response();

        //Response time
        long time = response.getTime();
        System.out.println("time = " + time);

    }

}
