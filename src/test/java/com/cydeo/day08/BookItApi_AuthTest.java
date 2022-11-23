package com.cydeo.day08;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BookItApi_AuthTest extends BookItTestBase {

        /*
            created --> BookItUtil then create a method,
                        that accepts email and password return token Bearer +yourToken as a String

            2 ways for authorization
                1 - auth().basic("editor","editor")
                2 - header("Authorization",token)
         */

    @DisplayName("GET request, with auth")
    @Test
    public void test1(){

        String token = BookItUtil.token("blyst6@si.edu", "barbabaslyst");
        System.out.println("token = " + token);

        //we can provide header by header method
        given().accept(ContentType.JSON)
                .header("Authorization",token)
                .when().get("/api/campuses")
                .then().statusCode(200)
                .log().all();

    }
}
