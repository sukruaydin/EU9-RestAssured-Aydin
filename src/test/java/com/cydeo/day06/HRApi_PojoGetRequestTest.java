package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HRApi_PojoGetRequestTest extends HRTestBase {

    @DisplayName("returning 1 region, with jsonPath.getObject()")
    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getLinks().get(0) = " + region1.getLinks().get(0));

    }

    @DisplayName("@JsonIgnoreProperties annotation used, returning certain values")
    @Test
    public void test2(){

        Employee e1 = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        String firstName = e1.getFirstName();
        System.out.println("firstName = " + firstName);

    }

    @DisplayName("@JsonIgnoreProperties annotation used, returning certain values")
    @Test
    public void test3(){
        /*  send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */

        //as method is enough

        Regions object = get("/regions")
                .then().statusCode(200)
                .extract().response().as(Regions.class);

        //asserting count
        assertThat(object.getCount(), is(4));

        List<Integer> regionIds = new ArrayList<>();
        List<String> regionNames = new ArrayList<>();

        //each item is a list of Regions
        for (Region item : object.getItems()) {
            regionIds.add(item.getRegionId());
            regionNames.add(item.getRegion_name());
        }

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(expectedRegionNames));

        int offset = object.getOffset();
        System.out.println("offset = " + offset);

    }

    @DisplayName("shortest way of test3")
    @Test
    public void test4(){
        /*  send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        List<Integer> listOfRegionId = jsonPath.getList("items.region_id");
        List<String> listOfRegionName = jsonPath.getList("items.region_name");

        System.out.println("listOfRegionId = " + listOfRegionId);
        System.out.println("listOfRegionName = " + listOfRegionName);

        assertThat(listOfRegionId, containsInRelativeOrder(1,2,3,4));
        assertThat(listOfRegionName, containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"));

    }

}
