package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class HRApiTestWith_JsonPath extends HRTestBase {

    @DisplayName("findAll method - 1 with jsonPath")
    @Test
    public void test1(){

        Response response = get("/countries");

        //get the second country name with JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country_id's
        List<Integer> countryIds = jsonPath.get("items.country_id");
        System.out.println("countryIds = " + countryIds);

        //get all country names where their region_id is 2
        //in this situation, i still have everything as response, and i am asked to do filtering
        //MY_WAY
        //all names
        List<String> names = jsonPath.get("items.country_name");
        //all ids
        List<Integer> regionIds = jsonPath.get("items.region_id");

        //i will store those 2 list in a map, key-value format
        Map<String,Integer> map = new LinkedHashMap<>();

        //using entrySet i can check which one of them having id number 2
        for (int i = 0; i < names.size(); i++) {
            map.put(names.get(i),regionIds.get(i));
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()==2){
                System.out.println(entry.getKey());
            }
        }

        //SECOND_WAY (METHODIC_WAY)
        List<String> countriesWith2 = jsonPath.get("items.findAll {it.region_id==2}.country_name");
        //it --> like iterator
        System.out.println("countriesWith2 = " + countriesWith2);

    }

    @DisplayName("findAll, max methods - 2 with jsonPath")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit",107)
                .when().get("/employees");

        //in this situation, i still have everything as response, and i am asked to do filtering

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List<String> emailList = jsonPath.get("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("emailList = " + emailList);

        //get me first name of employees who is making more than 10000
        List<String> firstNames = jsonPath.get("items.findAll {it.salary>10000}.first_name");
        System.out.println("firstNames = " + firstNames);

        //get the max salary's first name
        String maxSalaryFirstName = jsonPath.get("items.max {it.salary}.first_name");
        System.out.println("maxSalaryFirstName = " + maxSalaryFirstName);

        String maxSalaryFirstNameWithPath = response.path("items.max {it.salary}.first_name");
        System.out.println("maxSalaryFirstNameWithPath = " + maxSalaryFirstNameWithPath);
        //syntax is same for path method

    }

}




