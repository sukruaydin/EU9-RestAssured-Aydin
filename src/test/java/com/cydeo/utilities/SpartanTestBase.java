package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartan_ip_address");

        String dbUrl = "jdbc:oracle:thin:@54.82.123.95:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";
        //DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){
        //DBUtils.destroy();
    }

}
