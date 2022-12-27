package com.gorest.crudtest;

import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;


public class UserGetTest extends TestBase {
        @Test
        public void verifyNewUserInfo() {

            Response response = given()
                    .queryParams("id", 11654)
                    .when()
                    .get("/users");
            response.then().statusCode(200);
            response.prettyPrint();
        }
}
