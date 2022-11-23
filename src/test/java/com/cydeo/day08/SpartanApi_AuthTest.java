package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_AuthTest extends SpartanAuthTestBase {

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

    @DisplayName("as editor, get request, expect 403")
    @Test
    public void test3(){

        //same problem
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



    }
}
