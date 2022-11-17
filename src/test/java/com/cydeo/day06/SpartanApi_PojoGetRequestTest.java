package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanApi_PojoGetRequestTest extends SpartanTestBase {

    @DisplayName("aa")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)

                .extract().response();

        //deserialize --> json to pojo (plain old java object)
        //we have 2 ways of serialization json to java.class

        //1st-way
        //we convert json response to Spartan object with the help of jackson dependency
        //as() method uses jackson to deserialize
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println("spartan15 = " + spartan15);

        //getter id
        int id = spartan15.getId();
        System.out.println("id = " + id);

        //getter gender
        String gender = spartan15.getGender();
        System.out.println("gender = " + gender);

        //2nd-way
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println("s15 = " + s15);

        //getter name
        String name = s15.getName();
        System.out.println("name = " + name);

    }

    @Test
    public void test2(){
        // spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //json path is much powerful, we can give path

        //get the first spartan from content list and put inside spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getPhone() = " + s1.getPhone());

    }

    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchObj = response.as(Search.class);

        //getter content
        List<Spartan> content = searchObj.getContent();
        String nameOfFirstSpartan = content.get(0).getName();
        System.out.println("nameOfFirstSpartan = " + nameOfFirstSpartan);

        //getter totalElement
        int totalElement = searchObj.getTotalElement();
        System.out.println("totalElement = " + totalElement);


    }

    @DisplayName("different approach?")
    @Test
    public void test4(){

        List<Spartan> spartans = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);

        String name = spartans.get(0).getName();
        System.out.println("name = " + name);

    }

}
