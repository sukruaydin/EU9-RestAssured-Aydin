package com.cydeo.day12;

import com.cydeo.utilities.SpartanAuthNewTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

//java_class_1
public class OldRestAssuredTest extends SpartanAuthNewTestBase {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .and().log().all()
                .when().get("/spartans")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .body("id[0]", is(10))
                .body("id[5]", is(199))
                .and().log().all();

    }

    @Test
    public void test2(){

        /*
            first version --> given, when, then
            second version --> given, expect, when

            Second one keeps asserting the following ones if the previous one fails
         */

        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .and().log().all()

                .expect().statusCode(200)
                .expect().contentType(ContentType.JSON)
                .expect().body("id[0]", is(10))
                .expect().body("id[5]", is(199))
                .expect().logDetail(LogDetail.ALL)

                .when().get("/spartans");

    }


}
