package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class BookItTestBase {

    @BeforeAll
    public static void init(){
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
    }
}
