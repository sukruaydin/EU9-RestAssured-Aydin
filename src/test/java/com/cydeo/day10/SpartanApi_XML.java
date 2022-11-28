package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_XML extends SpartanAuthTestBase {

    @DisplayName("aa")
    @Test
    public void test1(){

        /*
            we will ask for xml response
            assert status code 200
            assert content type is xml (we got xml response)
            verify first spartan name is Meade
            verify first spartan gender is Male
         */

        given().accept(ContentType.XML)
                .and().auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"))
                .body("List.item[0].gender", is("Male"));

    }

    @DisplayName("xmlPath")
    @Test
    public void test2(){

        /*
            we will ask for xml response
            assert status code 200
            assert content type is xml (we got xml response)
            verify first spartan name is Meade
            verify first spartan gender is Male
         */

        Response response = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans");

        //just like jsonPath, this is for XML body
        XmlPath xmlPath = response.xmlPath();

        String name1 = xmlPath.getString("List.item[0].name");
        int id3 = xmlPath.getInt("List.item[2].id");
        List<String> nameList = xmlPath.getList("List.item.name");


        System.out.println("name1 = " + name1);
        System.out.println("id3 = " + id3);
        System.out.println("nameList = " + nameList);


    }

}
