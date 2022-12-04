package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//java_class_5
public class MockApi {

    @Test
    public void test1(){
        given().baseUri("https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io")
                .and().accept(ContentType.JSON)
                .when().get("/customer")
                .then().statusCode(200)
                .and().body("firstName", is("John"));

    }

}
