package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002() {
        response.body("findAll{it.id==2730}.title", hasItem("Ad ipsa coruscus ipsam eos demitto centum."));
    }

    //3.Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        List<Integer> user_id = response.extract().path("user_id");
        user_id.contains("5522");
    }
    //4. Check the multiple ids in the ArrayList (2615, 2613, 2612)
    @Test
    public void test004() {
       response.body("id",hasItems(2615, 2613, 2612));
    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("findAll{it.id=='2670'}.body", hasItem("Praesentium patrocinor sophismata. Deprecator aeneus acervus. Supellex qui aperiam. Succedo suffoco canis. Approbo consequatur debeo. Victus vir nobis. Varietas super amo. Terreo baiulus desino. Adipisci caterva concedo. Torqueo abutor dens. Claudeo dicta tantillus. Cohors campana delectatio. Iure sortitus abutor. Accedo deprimo cenaculum. Summisse supra curtus. Necessitatibus delinquo cunabula. Patrocinor tepesco amplitudo. Vulticulus solutio solium. Succedo vorax baiulus."));
    }
}
