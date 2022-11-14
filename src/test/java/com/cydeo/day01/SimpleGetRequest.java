package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    //normally it is not like this, just for today
    String url = "http://54.82.123.95:8000/api/spartans";

    @Test
    public void test1(){

        Response response = RestAssured.get(url);
        int statusCode = response.statusCode(); //200

        //printing statusCode
        System.out.println("statusCode = " + statusCode);

        //printing response body
        response.prettyPrint();

    }

}
