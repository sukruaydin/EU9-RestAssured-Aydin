package com.cydeo.day12;

import com.cydeo.utilities.*;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

//java_class_4
public class BookIt_QA3_Api_SpecTest extends BookItTestBase_QA_3 {

    @Test
    public void test1(){
        /*
                send a get request to /api/users/me endpoint as a teacher
                verify status code and content type
         */

        given().spec(teacherRequestSpec)
                .when().get("/api/users/me")
                .then().spec(responseSpec);

    }

    @Test
    public void test2(){
        /*
                send a get request to /api/users/me endpoint as a team_leader
                verify status code and content type
         */

        given().spec(teamLeaderRequestSpec)
                .when().get("/api/users/me")
                .then().spec(getDynamicResSpec_StatusCode(200));

    }

}
