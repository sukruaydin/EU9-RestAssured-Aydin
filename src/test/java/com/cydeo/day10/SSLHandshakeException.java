package com.cydeo.day10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SSLHandshakeException {

    @DisplayName("as")
    @Test
    public void test1(){
        given()
                .relaxedHTTPSValidation() //even if it doesn't have valid certificate I want to send request
                .when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();

    }

    @DisplayName("providing certificate")
    public void test2(){
        given()
                .keyStore("pathOfTheFile","password")
                .when().get("apiUrl");

    }

}
