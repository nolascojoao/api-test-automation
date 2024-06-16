package com.example.tests;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;

public class HelloWorldRestAssured {

    @Test
    public void verifyGoogleHomePageIsAccessible() {
        given()
            .when()
            	.get("http://www.google.com")
            .then()
            	.statusCode(200);
    }

}
