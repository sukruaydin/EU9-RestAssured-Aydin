package com.cydeo.day05;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

//java.class_3
public class HRApiHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void test1(){
         /*  send a get request to employees endpoint with query parameter job_id IT_PROG
        verify statusCode
        verify each job_id is IT_PROG
        verify first names are --> "Alexander","Bruce","David","Valli","Diana"
        verify emails without in order -->
        expected names
    */

        //will use later in the down
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")

                .when().get("/employees")

                .then().statusCode(200)
                .and().body("items.job_id", everyItem(equalTo("IT_PROG")))
                .and().body("items.first_name",containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .and().body("items.first_name",equalTo(names))
                .and().body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ"));

    }

    @DisplayName("extract() method")
    @Test
    public void employeesTest2(){

        //we want to chain and also get response object

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")

                .when().get("/employees")

                .then().statusCode(200)
                .and().body("items.job_id", everyItem(equalTo("IT_PROG")))

                .extract().response();

        response.prettyPrint();

        System.out.println("----------------------------------------------------------------------");

        //converting jsonPath

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")

                .when().get("/employees")

                .then().statusCode(200)
                .and().body("items.job_id", everyItem(equalTo("IT_PROG")))

                .extract().jsonPath();

        System.out.println("----------------------------------------------------------------------");

        //extract() --> method that allow us to get response object after we use then() method.

        //asserting with assertThat and jsonPath

        //asset that we have 5 first_names
        assertThat(jsonPath.get("items.first_name"),hasSize(5));

        //asset first_names order
        assertThat(jsonPath.get("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));

    }


}
