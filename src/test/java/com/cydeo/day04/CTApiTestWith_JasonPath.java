package com.cydeo.day04;

import com.cydeo.utilities.CydeoTrainingTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CTApiTestWith_JasonPath extends CydeoTrainingTestBase {

    @DisplayName("response.jsonPath(), advanced example")
    @Test
    public void test1(){
         /*
            send a get request to student id 23 as a path parameter and accept header application/json
            verify status code=200
                   content type=application/json;charset=UTF-8
            verify Date header exists
            assert that
                    firstName Derick
                    batch 19
                    section N/A
                    emailAddress cpfeffel9@fema.gov
                    companyName Quinu
                    state Virginia
                    zipCode 69807

                    using JsonPath
             */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",23)
                .when().get("student/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //asserting date exists
        boolean isDateExist = response.headers().hasHeaderWithName("date");
        assertTrue(isDateExist);

        //firstName
        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.get("students[0].firstName");
        System.out.println("firstName = " + firstName);

        //batchNumber
        int batchNumber = jsonPath.get("students[0].batch");
        System.out.println("batchNumber = " + batchNumber);

        //section
        String section = jsonPath.get("students[0].section");
        System.out.println("section = " + section);

        //email
        String email = jsonPath.get("students[0].contact.emailAddress");
        System.out.println("email = " + email);

        //companyName
        String companyName = jsonPath.get("students[0].company.companyName");
        System.out.println("companyName = " + companyName);

        //state
        String state = jsonPath.get("students[0].company.address.state");
        System.out.println("state = " + state);

        //zipCode
        int zipCode = jsonPath.get("students[0].company.address.zipCode");
        System.out.println("zipCode = " + zipCode);

        assertEquals(firstName,"Derick");
        assertEquals(batchNumber,19);
        assertEquals(section,"N/A");
        assertEquals(email,"cpfeffel9@fema.gov");
        assertEquals(companyName,"Quinu");
        assertEquals(state,"Virginia");
        assertEquals(zipCode,69807);

    }

}
