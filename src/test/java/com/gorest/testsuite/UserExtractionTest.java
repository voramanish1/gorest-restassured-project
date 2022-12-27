package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users")
                .then().statusCode(200);
    }
    //1.Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allIDs = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All IDs is : " + allIDs);
        System.out.println("------------------End of Test---------------------------");

    }
    //2.Extract the all Names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All Names is : " + allNames);
        System.out.println("------------------End of Test---------------------------");

    }
    //3.Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[5].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Names 5 is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }
    //4.Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> inactiveNames = response.extract().path("findAll{it.status=='inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Inactives Names are : " + inactiveNames);
        System.out.println("------------------End of Test---------------------------");

    }
    //5.Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> activeNames = response.extract().path("findAll{it.status=='active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The active Names are : " + activeNames);
        System.out.println("------------------End of Test---------------------------");

    }
    //6.Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> femaleNames = response.extract().path("findAll{it.gender=='female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The female Names are : " + femaleNames);
        System.out.println("------------------End of Test---------------------------");

    }
    //7.Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status=='inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails are : " + emails);
        System.out.println("------------------End of Test---------------------------");

    }
    //8.Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> maleIDs = response.extract().path("findAll{it.gender=='male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The male IDs are : " + maleIDs);
        System.out.println("------------------End of Test---------------------------");

    }
    //9.Get all the status
    @Test
    public void test009() {
        List<String> allStatus = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The male IDs are : " + allStatus );
        System.out.println("------------------End of Test---------------------------");

    }
    //10.Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test0010() {
        List<?> email = response.extract().path("findAll{it.name == 'Ganak Jain'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Email is : " + email );
        System.out.println("------------------End of Test---------------------------");

    }
    //11.Get gender of id = 5238
    @Test
    public void test0011() {
        List<?> gender = response.extract().path("findAll{it.id==5238}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender is : " + gender );
        System.out.println("------------------End of Test---------------------------");

    }


}
