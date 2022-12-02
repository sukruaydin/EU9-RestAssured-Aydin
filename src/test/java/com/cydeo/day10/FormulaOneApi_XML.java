package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

//java_class_2
public class FormulaOneApi_XML {
    @BeforeAll
    public static void init(){
        baseURI = "http://ergast.com/api/f1";
    }

    @DisplayName("getting attribute in XML body, xmlPath() method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .and().pathParam("driver", "alonso")
                .when().get("drivers/{driver}")
                .then().statusCode(200)
                //.log().all()
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        //get GivenName
        String name = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("name = " + name);

        //get FamilyName
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);

        //get driverId on the 3rd line
        String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);

        //get code on the 3rd line
        String code = xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println("code = " + code);

        //get url on the 3rd line
        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println("url = " + url);

    }

}
