package com.cydeo.day06;

import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApi_PojoGetRequestTest extends HRTestBase {

    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        Regions region1 = jsonPath.getObject("items[0]", Regions.class);
        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        System.out.println("region1.getLinks().get(0) = " + region1.getLinks().get(0));

    }

}
