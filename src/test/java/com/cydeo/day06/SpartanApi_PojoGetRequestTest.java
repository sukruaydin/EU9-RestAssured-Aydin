package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.Spartans;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanApi_PojoGetRequestTest extends SpartanTestBase {

    @DisplayName("2 ways for deserialization, as(), jsonPath.getObject()")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)

                .extract().response();

        //deserialize --> json to pojo (plain old java object)
        //we have 2 ways of deserialization json to java.class

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

    @DisplayName("2nd way --> powerful jsonPath due to accepting path")
    @Test
    public void test2(){
        // spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Female")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //jsonPath is much powerful, we can give path

        //get the first spartan from content list and put inside spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("s1.getName() = " + s1.getName());
        System.out.println("s1.getPhone() = " + s1.getPhone());

    }

    @DisplayName("1st way --> as()")
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

    @DisplayName("hollllly shit --> jsonPath().getList(\"content\", Spartan.class)")
    @Test
    public void test4(){

        /*List<Spartan> spartans = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println("spartans = " + spartans);*/

        List<Spartan> spartans = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "a")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content");

        System.out.println("spartans = " + spartans);

        /*String name = spartans.get(0).getName();
        System.out.println("name = " + name);*/

    }

    @DisplayName("study-1")
    @Test
    public void  test5(){

        List<String> listOfNames = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getList("content.name");

        System.out.println("listOfNames = " + listOfNames);

    }

    @DisplayName("study-2")
    @Test
    public void  test6(){

        Spartan spartan = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath().getObject("content[0]",Spartan.class);

        System.out.println("spartan = " + spartan);

    }

    @DisplayName("study-3")
    @Test
    public void  test7(){

        Spartans spartans = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "j")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response().as(Spartans.class);

        String name = spartans.getListSpartan().get(0).getName();
        System.out.println("name = " + name);


    }

}
