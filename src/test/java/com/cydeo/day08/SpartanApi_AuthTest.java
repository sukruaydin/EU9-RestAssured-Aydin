package com.cydeo.day08;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_AuthTest extends SpartanAuthTestBase {

    //BASIC AUTH

    @DisplayName("as guest(without authorization), get request, expect 401")
    @Test
    public void test1(){

        //same problem
        get("/api/spartans")
                .then().statusCode(401)
                .log().all();

    }

    @DisplayName("as admin, get request, expect 200")
    @Test
    public void test2(){

        given().auth().basic("admin","admin")
                .get("/api/spartans")
                .then().statusCode(200)
                .log().all();

    }

    @DisplayName("as editor, delete request, expect 403")
    @Test
    public void test3(){

        //same problem
        //editor ca not delete
        given().auth().basic("editor","editor")
                .pathParam("id",90)
                .delete("/api/spartans/{id}")
                .then().statusCode(403)
                .log().all();


    }

    @DisplayName("homework")
    @Test
    public void test4(){
        /*
            when there is a new API this Role Base Access Control(RBAC) is required

            As a homework,write a detailed test for RBAC in Spartan Auth app (7000)

                Admin should be able to take all CRUD ops (admin, admin)
                Editor should be able to take all CRUD ops, --> other than DELETE (editor, editor)
                User should be able to only READ data, --> not update,delete,create (POST,PUT,PATCH,DELETE) (user,user)
           --------------------------------------------------------
            Can guest even read data ? 401 for all
     */

        //admin GET request
        String s = given().contentType(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .pathParam("id",10)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().asString();
        System.out.println("s = " + s);

        //admin POST request
        Spartan spartan = new Spartan();
        spartan.setName("sukruAydin");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);
        int id = given().contentType(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .body(spartan)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .extract().jsonPath().getInt("data.id");

        String s1 = given().pathParam("id", id)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response().asString();

        System.out.println("s1 = " + s1);


    }
}
