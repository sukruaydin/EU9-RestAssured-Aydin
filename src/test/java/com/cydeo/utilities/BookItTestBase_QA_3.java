package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookItTestBase_QA_3 {

    public static RequestSpecification teacherRequestSpec;
    public static RequestSpecification teamLeaderRequestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("book_it_api_qa3");

        teacherRequestSpec = given().accept(ContentType.JSON)
                            .header("Authorization", BookItUtil_QA_3.generateToken(ConfigurationReader.getProperty("teacher_email"),
                            ConfigurationReader.getProperty("teacher_password")));

        teamLeaderRequestSpec = given().contentType(ContentType.JSON)
                                .header("Authorization", BookItUtil_QA_3.generateToken(ConfigurationReader.getProperty("team_leader_email"),
                                ConfigurationReader.getProperty("team_leader_password")));

        responseSpec = expect().statusCode(200)
                        .contentType(ContentType.JSON);
    }

    @AfterAll
    public static void close(){
        reset();
    }


    public static ResponseSpecification getDynamicResSpec_StatusCode(int statusCode){
        return expect().statusCode(statusCode)
                .and().contentType(ContentType.JSON);
    }

    public static ResponseSpecification userCheck(String firstName,String lastName){

        return expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is(firstName))
                .body("lastName",is(lastName))
                .logDetail(LogDetail.ALL);
    }


    //teacher,student-member,student-leader
    public static RequestSpecification userReqSpec(String email, String password){
        //advanced lazy way :)
        return given()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtil_QA_3.generateToken(email,password))
                .log().all();
    }





}
