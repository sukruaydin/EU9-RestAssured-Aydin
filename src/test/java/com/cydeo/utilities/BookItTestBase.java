package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class BookItTestBase {



    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("book_it_api_qa1");

    }

    @AfterAll
    public void close(){
        reset();
    }
}
