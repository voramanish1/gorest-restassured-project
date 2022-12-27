package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
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
   //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size", equalTo(20));
    }
    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trived
    @Test
    public void test002() {
        response.body("findAll{it.id==5322}.name", hasItem("Rudra Ganaka VM"));
    }
    //3.Check the single ‘Name’ in the Array list (Dhana Kaul)
    @Test
    public void test003() {
        List<String>names=response.extract().path("name");
        names.contains("Dhana Kaul");
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Rudra Ganaka VM","Dhana Kaul","Deeptimoyee Sharma"," Vimala Kakkar)
    @Test
    public void test004() {
        response.body("name",hasItems("Rudra Ganaka VM","Dhana Kaul","Deeptimoyee Sharma"," Vimala Kakkar"));
    }
    //5. Verify the emai of userid =5322 is equal “vm_rudra_ganaka@heathcote-miller.io”
    @Test
    public void test005() {
        response.body("findAll{it.id==5322}.email", hasItem("vm_rudra_ganaka@heathcote-miller.io"));
    }

    //6. Verify the status is “Active” of user name is “Dhana kaul”
    @Test
    public void test006() {
        response.body("findAll{it.status=='active'}.name", hasItem("Aaryan Nayar"));
    }

    //7. Verify the Gender = male of user name is “Dhana Kaul”
    @Test
    public void test007() {
        response.body("findAll{it.gender=='female'}.name", hasItem("Udit Menon"));
    }
}
