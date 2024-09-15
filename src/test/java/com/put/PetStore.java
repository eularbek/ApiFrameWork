package com.put;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class PetStore {

    @Test
    public void updatePet() throws InterruptedException {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/";

     //1. Create a pet
        // We are updating an existing pet using their id
        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(1980, "Mufasa")).when().put().then()
                        .statusCode(200).extract().response();


        //2. Get the pet
        RestAssured.given().accept(ContentType.JSON).when()
                .get("1980").then().
                statusCode(200)
                .body("id", Matchers.is( 1980) )
                .body("name", Matchers.equalTo("Mufasa"))
                .extract().response();

        String updatedPetName = "Boika";

        //3. Update the pet
       RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
               .body(Payloads.getPetPayload(1980, updatedPetName))
               .when()
               .put()
               .then()
               .statusCode(200)
               .body("id", Matchers.is(1980))
               .body("name", Matchers.equalTo(updatedPetName))
               .extract().response();

       //4. Get update pet
        RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("1980")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedPetName))
                .extract().response();

        //5. Delete updated pet
        RestAssured.given().accept(ContentType.JSON).when().delete("/1980").then()
                .statusCode(200).extract().response();

    }



}
