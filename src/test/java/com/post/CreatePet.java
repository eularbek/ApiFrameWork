package com.post;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class CreatePet {

    @Test
    public void createPetWithJSon(){
        File petFile = new File("src/test/resources/petstore/createpet.json");
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petFile)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

    }

    @Test
    public void createPetWithPayload(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(100,"Simba"))
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

    }










}
