package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserPostTest extends TestBase {
    @Test
    public void verifyUserCreatedSuccesFully(){

    UserPojo userpojo = new UserPojo();
        userpojo.setName("Manish");
        userpojo.setEmail("prime1245@gmail.com");
        userpojo.setGender("male");
        userpojo.setStatus("active");

        Response response=given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(userpojo)
                .post("/users");
        response.then().log().all().statusCode(201);
        response.prettyPrint();
}
}
