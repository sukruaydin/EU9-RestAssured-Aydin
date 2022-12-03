package com.cydeo.day11;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.ExcelUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

//java_class_7
public class BookItParameterized extends BookItTestBase {

    public static List<Map<String,String>> getExcelData(){
        ExcelUtil bookItFile = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
        return bookItFile.getDataList();
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void test1(Map<String,String> user){
        String email = user.get("email");
        String password = user.get("password");
        System.out.println(email + " - " + password);

        //247 passed, 33 failed
        given().queryParams(user)
                .and().baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .when().get("/sign")
                .then().statusCode(200);
    }

}
