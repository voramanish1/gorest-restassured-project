package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }
    //1.Extract the title
    @Test
    public void test001() {
        List<String> title =response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title is : " + title);
        System.out.println("------------------End of Test---------------------------");

    }
    @Test
    //2.Extract the total number of record
    public void test002() {
        int total = response.extract().path("total.size");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of record is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }
    //3.Extract the body of 15th record
    @Test
    public void test003() {
        String record = response.extract().path("[15].body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + record);
        System.out.println("------------------End of Test---------------------------");
    }
    //4.Extract the user_id of all the records
    @Test
    public void test004() {
        List<String> allRecords = response.extract().path("user_id");
        allRecords.size();
        System.out.println(" record size : " + allRecords.size());

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all UserID : " + allRecords);
        System.out.println("------------------End of Test---------------------------");
    }
    //5.Extract the title of all the records
    @Test
    public void test005() {
        List<String> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records is : " + title);
        System.out.println("------------------End of Test---------------------------");

    }
    //6.Extract the title of all records whose user_id = 5431
    @Test
    public void test006(){
        List<String> titleAllRecord = response.extract().path("findAll{it.user_id==5431}.title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of title"+titleAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }
    //6.Extract the body of all records whose id = 2671
    @Test
    public void test007(){
        List<String> bodyAllRecord = response.extract().path("findAll{it.id==2652}.body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of body"+bodyAllRecord);
        System.out.println("------------------End of Test---------------------------");
    }





}
