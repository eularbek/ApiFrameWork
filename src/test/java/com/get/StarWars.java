package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class StarWars {

    @Test
    public void findPeopleOnSW(){

        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people/1";

        //Making a GET call
        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        //Deserializing the response using TypeRef
        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        String actualName = (String) parsedResponse.get("name");
        String expectedName = "Luke Skywalker";

        Assert.assertEquals(actualName, expectedName, "Failed to validate name field");


    }

}
