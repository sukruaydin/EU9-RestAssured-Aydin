package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public abstract class CydeoTrainingTestBase {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("cydeo_training_api");
    }

}
