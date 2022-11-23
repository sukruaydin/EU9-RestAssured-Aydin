package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanApi_Put_Patch_Delete extends SpartanTestBase {

    @DisplayName("put request")
    @Test
    public void test1(){

        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","HaticePolatKonya");
        putRequestMap.put("gender","Female");
        putRequestMap.put("phone",7854165478l);

        given().contentType(ContentType.JSON)
                .and().pathParam("id",120)
                .body(putRequestMap)
                .put("/api/spartans/{id}");



    }

}
