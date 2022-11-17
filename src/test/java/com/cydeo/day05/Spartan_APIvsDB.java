package com.cydeo.day05;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Spartan_APIvsDB extends SpartanTestBase {



    @Test
    public void test1(){
        //creating database connection
        DBUtils.createConnection();




        /*
            task -->
                for spartan id = 15-->
                    get id,name,gender phone  from database
                    get same information from api
                    compare
         */

        //getting data from DB
        String query = "SELECT SPARTAN_ID,NAME,GENDER,PHONE FROM SPARTANS WHERE SPARTAN_ID=15";
        Map<String,Object> spartanDB_Map = DBUtils.getRowMap(query);
        System.out.println("spartanDB_Map = " + spartanDB_Map);

        //getting data from API

        //i am already returning it as response object, since i will convert it to a map
        Response response = given().pathParam("id", 15)
                            .when().get("/api/spartans/{id}")
                            .then().statusCode(200)

                            //to convert it map afterwards
                            .extract().response();

        //deserialization
        Map<String,Object> spartanAPI_Map = response.body().as(Map.class);
        System.out.println("spartanAPI_Map = " + spartanAPI_Map);

        //hamcrest assertion
        assertThat(spartanAPI_Map.get("id").toString(),is(spartanDB_Map.get("SPARTAN_ID").toString()));
        assertThat(spartanAPI_Map.get("name").toString(),is(spartanDB_Map.get("NAME").toString()));
        assertThat(spartanAPI_Map.get("gender").toString(),is(spartanDB_Map.get("GENDER").toString()));
        assertThat(spartanAPI_Map.get("phone").toString(),is(spartanDB_Map.get("PHONE").toString()));


        //closing connection
        DBUtils.destroy();
    }

}
