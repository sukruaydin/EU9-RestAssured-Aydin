package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiTestWithPath extends HRTestBase {

    @DisplayName("path method with queryParam")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\": 2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        //print limit's result
        int limit = response.path("limit");
        System.out.println("limit = " + limit);

        //print hasMore's result
        boolean hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);

        //print first country's id
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country's name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print Canada's href
        String canadaHref = response.path("items[2].links[0].href");
        System.out.println("canadaHref = " + canadaHref);

        //get me all country names
        List<String> country_name = response.path("items.country_name");
        System.out.println("country_name = " + country_name);

        //assert that all regions ids are equal to 2
        List<Integer> regionIdList = response.path("items.region_id");
        for (Integer each : regionIdList) {
            System.out.println(each);
            assertEquals(each,2);
        }


    }

}
